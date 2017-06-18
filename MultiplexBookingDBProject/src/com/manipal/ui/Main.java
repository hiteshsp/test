package com.manipal.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.manipal.entity.Booking;
import com.manipal.entity.BookingDetail;
import com.manipal.entity.Hall;
import com.manipal.entity.HallCapacity;
import com.manipal.entity.Movie;
import com.manipal.entity.SeatType;
import com.manipal.entity.Show;
import com.manipal.entity.User;
import com.manipal.service.BookingService;
import com.manipal.service.HallService;
import com.manipal.service.MovieService;
import com.manipal.service.ServiceException;
import com.manipal.service.ServiceFactory;
import com.manipal.service.UserService;
import com.manipal.util.KeyboardUtil;

public class Main {
	private String[] slots = { "10:30 AM", "2:30 PM", "5:30 PM", "8:30 PM" };

	public static void main(String[] args) {
		new Main().start(); //first call
	}

	public void start() {
		int choice;

		the_loop: while ((choice = mainMenu()) != 99) { 
			
			switch (choice) {
			case 1:
				handleUsersMenu();
				break;
			case 2:
				handleHallsMenu();
				break;
			case 3:
				handleMoviesMenu();
				break;
			case 4:
				handleBookingsMenu();
				break;
			case 0:
				// ignore
				break;
			case 99:
				break the_loop;
			default:
				System.out.println("***** INVALID CHOICE *****");
			}

		} 

		System.out.println("The application is terminated");
		System.out.println("Thanks for using the Multiplex Booking System");
	}

	private int mainMenu() { 
		line("-", 40);
		System.out.println("Multiplex Booking System");
		line("-", 40);
		System.out.println("1.  Users");
		System.out.println("2.  Halls");
		System.out.println("3.  Movies and Shows");
		System.out.println("4.  Booking");
		System.out.println("99. Exit");
		try {
			String input = KeyboardUtil.getString("Enter your choice: [99] ");
			if (input.equals("")) {
				input = "99";
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Invalid input, please retry.");
		}
		return 0;
	}

	private int usersMenu() {
		line("-", 40);
		System.out.println("Users Menu");
		line("-", 40);
		System.out.println("1.  Add new user data");
		System.out.println("2.  Search and modify the user data");
		System.out.println("3.  Delete user data");
		System.out.println("4.  List all users");
		System.out.println("99. Back");
		try {
			String input = KeyboardUtil.getString("Enter your choice: [99] ");
			if (input.equals("")) {
				input = "99";
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Invalid input, please retry.");
		}
		return 0;
	}

	private int hallsMenu() {
		line("-", 40);
		System.out.println("Halls Menu");
		line("-", 40);
		System.out.println(" 1. Add new hall data");
		System.out.println(" 2. Search and modify the hall data");
		System.out.println(" 3. Delete hall data");
		System.out.println(" 4. List all halls");
		System.out.println(" 5. Add new seat type");
		System.out.println(" 6. Search and modify seat type");
		System.out.println(" 7. Delete a seat type");
		System.out.println(" 8. List all seat types");
		System.out.println(" 9. Add new hall capacity");
		System.out.println("10. Search and modify hall capacity");
		System.out.println("11. Delete a hall capacity data");
		System.out.println("12. List all hall capacity data");

		System.out.println("99. Back");
		try {
			String input = KeyboardUtil.getString("Enter your choice: [99] ");
			if (input.equals("")) {
				input = "99";
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Invalid input, please retry.");
		}
		return 0;
	}

	private int moviesAndShowsMenu() {
		line("-", 40);
		System.out.println("Movies and shows Menu");
		line("-", 40);
		System.out.println(" 1. Add new movie data");
		System.out.println(" 2. Search and modify the movie data");
		System.out.println(" 3. Delete movie data");
		System.out.println(" 4. List all movies");
		System.out.println(" 5. Add a show");
		System.out.println(" 6. Search and modify show data");
		System.out.println(" 7. Delete a show");
		System.out.println(" 8. List all shows");
		System.out.println("99. Back");
		try {
			String input = KeyboardUtil.getString("Enter your choice: [99] ");
			if (input.equals("")) {
				input = "99";
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Invalid input, please retry.");
		}
		return 0;
	}

	private int bookingsMenu() {
		line("-", 40);
		System.out.println("Bookings Menu");
		line("-", 40);
		System.out.println(" 1. Book a show");
		System.out.println(" 2. Search and modify booking data");
		System.out.println(" 3. Delete booking");
		System.out.println(" 4. List all bookings");
		System.out.println("99. Back");
		try {
			String input = KeyboardUtil.getString("Enter your choice: [99] ");
			if (input.equals("")) {
				input = "99";
			}
			return Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("Invalid input, please retry.");
		}
		return 0;
	}

	private void handleUsersMenu() {
		int choice;
		the_loop: while ((choice = usersMenu()) != 99) {
			switch (choice) {
			case 1: // Add new user data
				addNewUser();
				break;
			case 2: // Search and modify the user data
				searchAndModifyUser();
				break;
			case 3: // Delete user data
				deleteUserData();
				break;
			case 4: // List all users
				listAllUsers();
				break;
			case 0:
				// ignore
				break;
			case 99:
				break the_loop;
			default:
				System.out.println("***** INVALID CHOICE *****");
			}

		}
	}

	private void addNewUser() {
		try {

			int userId = KeyboardUtil.getInt("Enter user id: ");
			String username = KeyboardUtil.getString("Enter username: ");
			String usertype = KeyboardUtil
					.getString("Enter user type (administrator/user): ");
			String mobileNumber = KeyboardUtil
					.getString("Enter mobile number: ");
			String emailId = KeyboardUtil.getString("Enter email address: ");

			User user = new User(userId, username, usertype, mobileNumber,
					emailId);

			UserService service = ServiceFactory.getUserService();
			service.addUser(user);

		} catch (Exception e) {
			System.out
					.println("There was a problem while adding the user data");
			System.out.println(e.getMessage());
		}

		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void searchAndModifyUser() {

		UserService service;
		try {
			service = ServiceFactory.getUserService();
			int id = KeyboardUtil.getInt("Enter user's id to edit: ");
			User user = service.getUserById(id);
			if (user == null) {
				System.out.println("No data found for the id " + id);
			} else {
				System.out.println("User data: ");
				System.out.println("-----------");
				System.out.println("Username       : " + user.getUsername());
				System.out.println("Usertype       : " + user.getUsertype());
				System.out
						.println("Mobile number  : " + user.getMobileNumber());
				System.out.println("Email id       : " + user.getEmailId());

				String ans = KeyboardUtil
						.getString("Do you wish to edit this user? yes/no: [yes] ");
				if (ans.equals("") || ans.equalsIgnoreCase("yes")) {
					String input;
					input = KeyboardUtil.getString("Username       : ["
							+ user.getUsername() + "] ");
					if (!input.equals("")) {
						user.setUsername(input);
					}
					input = KeyboardUtil.getString("Usertype       : ["
							+ user.getUsertype() + "] ");
					if (!input.equals("")) {
						user.setUsertype(input);
					}
					input = KeyboardUtil.getString("Mobile number  : ["
							+ user.getMobileNumber() + "] ");
					if (!input.equals("")) {
						user.setMobileNumber(input);
					}
					input = KeyboardUtil.getString("Email id       : ["
							+ user.getEmailId() + "] ");
					if (!input.equals("")) {
						user.setEmailId(input);
					}

					service.updateUser(user);
				}
			}

		} catch (Exception e) {
			System.out.println("There was a problem while updating user data");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void deleteUserData() {
		UserService service;
		try {
			service = ServiceFactory.getUserService();
			int id = KeyboardUtil.getInt("Enter user's id to delete: ");

			User user = service.getUserById(id);
			if (user == null) {
				System.out.println("No data found for the id " + id);
			} else {
				System.out.println("User data: ");
				System.out.println("-----------");
				System.out.println("Username       : " + user.getUsername());
				System.out.println("Usertype       : " + user.getUsertype());
				System.out
						.println("Mobile number  : " + user.getMobileNumber());
				System.out.println("Email id       : " + user.getEmailId());

				String ans = KeyboardUtil
						.getString("Are you sure to delete this user? yes/no: [no] ");
				if (ans.equalsIgnoreCase("yes")) {
					service.deleteUser(id);
					System.out.println("User data deleted successfully!");
				} else {
					System.out.println("User data was not deleted!");
				}
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("There was a problem while deleting user data");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");

	}

	private void listAllUsers() {
		UserService service;
		try {
			service = ServiceFactory.getUserService();
			List<User> list = service.getAllUsers();

			line("=", 100);
			System.out.printf("%4s | %-15s | %-15s | %-15s | %-40s\n", "ID",
					"Username", "Usertype", "Mobile#", "Email id");
			line("=", 100);
			for (User user : list) {
				System.out.printf("%4d | %-15s | %-15s | %-15s | %-40s\n",
						user.getUserId(), user.getUsername(),
						user.getUsertype(), user.getMobileNumber(),
						user.getEmailId());
			}
			line(".", 100);
		} catch (ServiceException e) {
			System.out.println("There was a problem while listing all users");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void handleHallsMenu() {
		int choice;
		the_loop: while ((choice = hallsMenu()) != 99) {
			switch (choice) {
			case 1: // Add new hall data
				addHall();
				break;
			case 2: // Search and modify the hall data
				searchAndModifyHall();
				break;
			case 3: // Delete hall data
				deleteHall();
				break;
			case 4: // List all halls
				listAllHalls();
				break;
			case 5: // Add new seat type
				addNewSeatType();
				break;
			case 6: // Search and modify seat type
				searchAndModifySeatType();
				break;
			case 7: // Delete a seat type
				deleteSeatType();
				break;
			case 8: // List all seat types
				listAllSeatTypes();
				break;
			case 9: // Add new hall capacity
				addNewHallCapacity();
				break;
			case 10: // Search and modify hall capacity
				searchAndModifyHallCapacity();
				break;
			case 11: // Delete a hall capacity data
				deleteHallCapacity();
				break;
			case 12: // List all hall capacity data
				listAllHallCapacity();
				break;
			case 0: // ignore
				break;
			case 99:
				break the_loop;
			default:
				System.out.println("***** INVALID CHOICE *****");

			}

		}
	}

	private void addHall() {
		try {

			int id = KeyboardUtil.getInt("Enter hall id: ");
			String description = KeyboardUtil.getString("Enter description: ");
			int hallCapacity = KeyboardUtil.getInt("Enter hall capacity: ");

			Hall hall = new Hall(id, description, hallCapacity);
			HallService service = ServiceFactory.getHallService();
			service.addHall(hall);

		} catch (Exception e) {
			System.out
					.println("There was a problem while adding the hall data");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void searchAndModifyHall() {
		try {
			HallService service = ServiceFactory.getHallService();

			int id = KeyboardUtil.getInt("Enter hall id: ");
			Hall hall = service.getHallById(id);
			if (hall == null) {
				System.out.println("No hall found with id " + id);
			} else {
				System.out.println("Description:" + hall.getDescription());
				System.out.println("Total Capacity:" + hall.getHallCapacity());
				String ans = KeyboardUtil
						.getString("Do you wish to edit this hall? yes/no: [yes] ");

				if (ans.equals("") || ans.equalsIgnoreCase("yes")) {
					String input = KeyboardUtil
							.getString("Enter new description: ");
					hall.setDescription(input);
					service.updateHall(hall);
				}
			}

		} catch (Exception e) {
			System.out.println("There was a problem while updating hall data");
			System.out.println(e.getMessage());
		}

		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void deleteHall() {
		HallService service;
		try {
			service = ServiceFactory.getHallService();
			int id = KeyboardUtil.getInt("Enter hall id to delete: ");

			Hall hall = service.getHallById(id);
			if (hall == null) {
				System.out.println("No data found for the id " + id);
			} else {
				System.out.println("Hall data: ");
				System.out.println("-----------");
				System.out.println("Description       : "
						+ hall.getDescription());
				System.out.println("Total Capacity    : "
						+ hall.getHallCapacity());
				String ans = KeyboardUtil
						.getString("Are you sure to delete this hall? yes/no: [no] ");
				if (ans.equalsIgnoreCase("yes")) {
					service.deleteHall(id);
					System.out.println("Hall data deleted successfully!");
				} else {
					System.out.println("Hall data was not deleted!");
				}
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("There was a problem while deleting hall");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void listAllHalls() {
		HallService service;
		try {
			service = ServiceFactory.getHallService();
			List<Hall> list = service.getAllHalls();

			line("=", 100);
			System.out.printf("%4s | %-50s | %-50s\n", "ID", "Description",
					"Total Capacity");
			line("=", 100);

			for (Hall hall : list) {
				System.out.printf("%4d | %-50s | %-50s\n", hall.getHallId(),
						hall.getDescription(), hall.getHallCapacity());
			}
			line(".", 100);

		} catch (ServiceException e) {
			System.out.println("There was a problem while listing all halls");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void addNewSeatType() {
		try {

			String id = KeyboardUtil.getString("Enter seat type id: ");
			String description = KeyboardUtil.getString("Enter description: ");
			double fare = KeyboardUtil.getDouble("Enter fare: ");

			SeatType st = new SeatType(id, description, fare);
			HallService service = ServiceFactory.getHallService();
			service.addSeatType(st);

		} catch (Exception e) {
			System.out.println("There was a problem adding the seat type data");
			System.out.println(e.getMessage());
		}

		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void searchAndModifySeatType() {
		try {
			HallService service = ServiceFactory.getHallService();

			String id = KeyboardUtil.getString("Enter seat type id: ");
			SeatType st = service.getSeatType(id);

			if (st == null) {
				System.out.println("No seat type found with id " + id);
			} else {
				System.out.println("Description : " + st.getDescription());
				System.out.println("Fare        : " + st.getFare());
				String ans = KeyboardUtil
						.getString("Do you wish to edit this seat type? yes/no: [yes] ");

				if (ans.equals("") || ans.equalsIgnoreCase("yes")) {
					String input = KeyboardUtil
							.getString("Enter new description: ");
					if (!input.equals("")) {
						st.setDescription(input);
					}

					input = KeyboardUtil.getString("Enter fare: ["
							+ st.getFare() + "] ");
					if (!input.equals("")) {
						st.setFare(Double.parseDouble(input));
					}
					service.updateSeatType(st);
				}
			}

		} catch (Exception e) {
			System.out.println("There was a problem while updating seat type");
			System.out.println(e.getMessage());
		}

		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void deleteSeatType() {
		HallService service;
		try {
			service = ServiceFactory.getHallService();
			String id = KeyboardUtil
					.getString("Enter seat type id to delete: ");

			SeatType seatType = service.getSeatType(id);
			if (seatType == null) {
				System.out.println("No data found for the id " + id);
			} else {
				System.out.println("Seat type: ");
				System.out.println("-----------");
				System.out.println("Description  : "
						+ seatType.getDescription());
				System.out.println("Fare         : " + seatType.getFare());
				String ans = KeyboardUtil
						.getString("Are you sure to delete this seat type? yes/no: [no] ");
				if (ans.equalsIgnoreCase("yes")) {
					service.deleteSeatType(id);
					System.out.println("Seat type deleted successfully!");
				} else {
					System.out.println("Seat type was not deleted!");
				}
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("There was a problem deleting seat type");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void listAllSeatTypes() {
		try {
			HallService service = ServiceFactory.getHallService();
			List<SeatType> list = service.getAllSeatTypes();

			line("=", 70);
			System.out.printf("%4s | %-50s | %-10s\n", "ID", "Description",
					"Fare");
			line("=", 70);

			for (SeatType seatType : list) {
				System.out.printf("%4s | %-50s | %-10.2f \n",
						seatType.getSeatTypeId(), seatType.getDescription(),
						seatType.getFare());
			}
			line(".", 70);

		} catch (ServiceException e) {
			System.out.println("There was a problem listing all seat types");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void addNewHallCapacity() {
		try {
			HallService service = ServiceFactory.getHallService();
			int id = KeyboardUtil.getInt("Enter hall id: ");
			Hall hall = service.getHallById(id);
			if (hall == null) {
				System.out.println("Invalid hall id!");
				return;
			}
			System.out.println("Hall description      : "
					+ hall.getDescription());

			String seatId = KeyboardUtil.getString("Enter seat type id: ");
			SeatType seatType = service.getSeatType(seatId);
			if (seatType == null) {
				System.out.println("Invalid seat type!");
				return;
			}
			System.out.println("Seat type description : "
					+ seatType.getDescription());
			System.out.println("Seat type fare        : " + seatType.getFare());

			int seatCount = KeyboardUtil.getInt("Enter seat count: ");
			HallCapacity hc = new HallCapacity(hall.getHallId(),
					seatType.getSeatTypeId(), seatCount);
			service.addHallCapacity(hc);

		} catch (Exception e) {
			System.out
					.println("There was a problem while adding new hall capacity");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}

	}

	private void searchAndModifyHallCapacity() {
		try {
			HallService service = ServiceFactory.getHallService();
			int hallId = KeyboardUtil.getInt("Enter hall id: ");
			String seatTypeId = KeyboardUtil.getString("Enter seat type id: ");

			HallCapacity hallCapacity = service.getHallCapacity(hallId,
					seatTypeId);

			if (hallCapacity == null) {
				System.out.printf(
						"No data found for hallId=%d and seatTypeId=%2s\n",
						hallId, seatTypeId);
				return;
			}

			String input = KeyboardUtil.getString("Enter seat count : ["
					+ hallCapacity.getSeatCount() + "] ");
			if (!input.equals("")) {
				hallCapacity.setSeatCount(Integer.parseInt(input));
				service.updateHallCapacity(hallCapacity);
				System.out.println("Hall capacity updated successfully!");
			} else {
				System.out.println("Nothing changed.");
			}

		} catch (Exception e) {
			System.out
					.println("There was a problem while updating hall capacity");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void deleteHallCapacity() {
		try {
			HallService service = ServiceFactory.getHallService();
			int hallId = KeyboardUtil.getInt("Enter hall id: ");
			String seatTypeId = KeyboardUtil.getString("Enter seat type id: ");

			/*
			 * HallCapacity hallCapacity = service.getHallCapacity(hallId,
			 * seatTypeId);
			 * 
			 * if (hallCapacity == null) { System.out.printf(
			 * "No data found for hallId=%d and seatTypeId=%d\n", hallId,
			 * seatTypeId); return; }
			 */
			Hall hall = service.getHallById(hallId);
			SeatType seatType = service.getSeatType(seatTypeId);

			System.out.println("Hall capacity info: ");
			System.out.println("Hall           : " + hall.getDescription());
			System.out.println("Seat type      : " + seatType.getDescription());
			System.out.println("Seat type fare : " + seatType.getFare());
			/*
			 * System.out.println("Seat count     : " +
			 * hallCapacity.getSeatCount());
			 */System.out.println();

			String input = KeyboardUtil
					.getString("Are you sure you want to delete this? (yes/no) : [no] ");
			if (input.equals("yes")) {
				service.deleteHallCapacity(hallId, seatTypeId);
				System.out.println("Hall capacity deleted successfully!");
			} else {
				System.out.println("Hall capacity was not deleted");
			}

		} catch (Exception e) {
			System.out
					.println("There was a problem while deleting hall capacity");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void listAllHallCapacity() {
		try {
			HallService service = ServiceFactory.getHallService();
			List<HallCapacity> list = service.getAllHallCapacities();

			line("=", 100);
			System.out.printf("%-40s | %-40s | %-10s\n", "Hall", "Seat type",
					"Seat count");
			line("=", 100);

			for (HallCapacity capacity : list) {
				int hallId = capacity.getHallId();
				String seatTypeId = capacity.getSeatTypeId();

				System.out.printf("%-40s | %-40s | %-10d\n", service
						.getHallById(hallId).getDescription(), service
						.getSeatType(seatTypeId).getDescription(), capacity
						.getSeatCount());
			}
			line(".", 100);
		} catch (Exception e) {
			System.out
					.println("There was a problem while listing all hall capacities");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void handleMoviesMenu() {
		int choice;
		the_loop: while ((choice = moviesAndShowsMenu()) != 99) {
			switch (choice) {
			case 1: // Add new movie data
				addNewMovie();
				break;
			case 2: // Search and modify the movie data
				searchAndModifyMovie();
				break;
			case 3: // Delete movie data
				deleteMovie();
				break;
			case 4: // List all movies
				listAllMovies();
				break;
			case 5: // Add a show
				addShow();
				break;
			case 6: // Search and modify show data
				searchAndModifyShow();
				break;
			case 7: // Delete a show
				deleteShow();
				break;
			case 8: // List all shows
				listAllShows();
				break;
			case 0: // ignore
				break;
			case 99:
				break the_loop;
			default:
				System.out.println("***** INVALID CHOICE *****");
			}

		}
	}

	private void addNewMovie() {
		try {
			int id = KeyboardUtil.getInt("Enter movie id: ");
			String movieName = KeyboardUtil.getString("Enter movie name: ");

			Movie movie = new Movie(id, movieName);
			MovieService service = ServiceFactory.getMovieService();
			service.addMovie(movie);

		} catch (Exception e) {
			System.out
					.println("There was a problem while adding the movie data");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void searchAndModifyMovie() {
		try {
			MovieService service = ServiceFactory.getMovieService();
			int id = KeyboardUtil.getInt("Enter movie id to edit: ");
			Movie movie = service.getMovie(id);
			if (movie == null) {
				System.out.println("No data found for the id " + id);
			} else {
				String input;
				input = KeyboardUtil.getString("Movie name       : ["
						+ movie.getMovieName() + "] ");
				if (!input.equals("")) {
					movie.setMovieName(input);
					service.updateMovie(movie);
					System.out.println("Movie data updated successfully!");
				} else {
					System.out.println("Nothing changed.");
				}
			}
		} catch (Exception e) {
			System.out.println("There was a problem while updating movie");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void deleteMovie() {
		try {
			MovieService service = ServiceFactory.getMovieService();
			int id = KeyboardUtil.getInt("Enter movie id to delete: ");
			Movie movie = service.getMovie(id);
			if (movie == null) {
				System.out.println("No data found for the id " + id);
			} else {

				System.out.println("Movie name: " + movie.getMovieName());
				String ans = KeyboardUtil
						.getString("Are you sure you want to delete this movie? (yes/no) : [no] ");
				if (ans.equalsIgnoreCase("yes")) {
					service.deleteMovie(id);
					System.out.println("Movie data updated successfully!");
				} else {
					System.out.println("Nothing changed.");
				}
			}
		} catch (Exception e) {
			System.out.println("There was a problem while deleting the movie");
			System.out.println(e.getMessage());
		}
		KeyboardUtil.getString("\nPress ENTER key to continue...");
	}

	private void listAllMovies() {
		try {
			MovieService service = ServiceFactory.getMovieService();
			List<Movie> list = service.getAllMovies();

			line("=", 60);
			System.out.printf("%4s | %-50s\n", "ID", "Movie name");
			line("=", 60);
			for (Movie movie : list) {
				System.out.printf("%4d | %-50s\n", movie.getMovieId(),
						movie.getMovieName());
			}
			line(".", 60);
		} catch (ServiceException e) {
			System.out.println("There was a problem while listing all movies");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void addShow() {
		try {
			MovieService service = ServiceFactory.getMovieService();
			HallService hallService = ServiceFactory.getHallService();

			int showId = KeyboardUtil.getInt("Enter the show id: ");
			Show show = service.getShow(showId);
			if (show != null) {
				System.out.println("Show already exists!");
				return;
			}

			int hallId = KeyboardUtil.getInt("Enter the hall id: ");
			Hall hall = hallService.getHallById(hallId);
			if (hall == null) {
				System.out.println("Hall does not exist with id " + hallId);
				return;
			} else {
				System.out.println("Hall : " + hall.getDescription());
			}

			int movieId = KeyboardUtil.getInt("Enter the movie id: ");
			Movie movie = service.getMovie(movieId);
			if (movie == null) {
				System.out.println("Movie does not exist with id " + movieId);
			} else {
				System.out.println("Movie : " + movie.getMovieName());
			}

			System.out.println("Available slots for the show are: ");
			System.out.println("1. 10:30 AM");
			System.out.println("2.  2:30 PM");
			System.out.println("3.  5:30 PM");
			System.out.println("4.  8:30 PM");

			int slot = KeyboardUtil.getInt("Enter slot number: ");
			if (slot < 1 || slot > 4) {
				System.out.println("Invalid slot number");
				return;
			}

			Date fromDate = KeyboardUtil
					.getDate("Enter from date (YYYY-MM-DD format): ");
			Date toDate = KeyboardUtil
					.getDate("Enter to date (YYYY-MM-DD format): ");

			if (toDate.before(fromDate)) {
				System.out.println("'To-Date' should be after 'From-Date'!");
				return;
			}

			show = new Show();
			show.setShowId(showId);
			show.setMovieId(movieId);
			show.setHallId(hallId);
			show.setSlot(slot);
			show.setFromDate(fromDate);
			show.setToDate(toDate);

			service.addShow(show);

		} catch (Exception e) {
			System.out
					.println("There was a problem while adding the show data");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void searchAndModifyShow() {
		try {
			MovieService service = ServiceFactory.getMovieService();
			HallService hallService = ServiceFactory.getHallService();

			int showId = KeyboardUtil.getInt("Enter the show id: ");
			Show show = service.getShow(showId);
			if (show == null) {
				System.out.println("Show does not exists!");
				return;
			}

			Hall hall = hallService.getHallById(show.getHallId());
			Movie movie = service.getMovie(show.getMovieId());

			System.out.println("Movie Show details: ");
			System.out.println("Hall      : (" + hall.getHallId() + ") "
					+ hall.getDescription());
			System.out.println("Movie     : (" + movie.getMovieId() + ") "
					+ movie.getMovieName());
			System.out.println("Slot      : (" + show.getSlot() + ") "
					+ slots[show.getSlot() - 1]);
			System.out.println("From      : " + show.getFromDate());
			System.out.println("To        : " + show.getToDate());

			System.out.println();
			String input = KeyboardUtil
					.getString("Do you want to edit this? (yes/no) : [yes] ");
			if (input.equals("") || input.equalsIgnoreCase("yes")) {

				input = KeyboardUtil.getString("Enter hall id : ["
						+ show.getHallId() + "] ");
				if (!input.equals("")) {
					int hallId = Integer.parseInt(input);
					hall = hallService.getHallById(hallId);
					if (hall == null) {
						System.out.println("Hall does not exist!");
						return;
					}
					show.setHallId(hallId);
				}

				input = KeyboardUtil.getString("Enter movie id : ["
						+ show.getMovieId() + "] ");
				if (!input.equals("")) {
					int movieId = Integer.parseInt(input);
					movie = service.getMovie(movieId);
					if (movie == null) {
						System.out.println("Movie does not exist!");
						return;
					}
					show.setMovieId(movieId);
				}

				input = KeyboardUtil.getString("Enter slot : ["
						+ show.getSlot() + "] ");
				if (!input.equals("")) {
					int slot = Integer.parseInt(input);
					if (slot < 1 || slot > 4) {
						System.out.println("Invalid slot");
						return;
					}
					show.setSlot(slot);
				}

				input = KeyboardUtil.getString("Enter from date : ["
						+ dateToString(show.getFromDate()) + "] ");
				if (!input.equals("")) {
					Date fromDate = stringToDate(input);
					if (fromDate == null) {
						System.out.println("Invalid date format");
						return;
					}
					show.setFromDate(fromDate);
				}

				input = KeyboardUtil.getString("Enter to date : ["
						+ dateToString(show.getToDate()) + "] ");
				if (!input.equals("")) {
					Date toDate = stringToDate(input);
					if (toDate == null) {
						System.out.println("Invalid date format");
						return;
					}
					show.setToDate(toDate);
				}

				if (show.getFromDate().after(show.getToDate())) {
					System.out.println("'To-Date' must be after 'From-Date'!");
					return;
				}

				service.updateShow(show);
				System.out.println("Show data updated succesfully");
			} else {
				System.out.println("Nothing changed.");
			}

		} catch (Exception e) {

			System.out
					.println("There was a problem while updating the show data");
			System.out.println(e.getMessage());

		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void deleteShow() {
		try {
			MovieService service = ServiceFactory.getMovieService();
			HallService hallService = ServiceFactory.getHallService();

			int showId = KeyboardUtil.getInt("Enter the show id: ");
			Show show = service.getShow(showId);
			if (show == null) {
				System.out.println("Show does not exists!");
				return;
			}

			Hall hall = hallService.getHallById(show.getHallId());
			Movie movie = service.getMovie(show.getMovieId());

			System.out.println("Movie Show details: ");
			System.out.println("Hall      : " + hall.getDescription());
			System.out.println("Movie     : " + movie.getMovieName());
			System.out.println("Slot      : " + slots[show.getSlot() - 1]);
			System.out.println("From      : " + show.getFromDate());
			System.out.println("To        : " + show.getToDate());

			System.out.println();
			String input = KeyboardUtil
					.getString("Are you sure you want to delete this? (yes/no) : [no] ");
			if (input.equalsIgnoreCase("yes")) {
				int cnt = service.deleteShow(showId);
				if (cnt == 1)
					System.out.println("Show deleted");
				else
					System.out
							.println("Show Can't be deleted, because booking is available");
			} else {
				System.out.println("Show data not deleted.");
			}

		} catch (Exception e) {
			System.out
					.println("There was a problem while deleting the show data");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void listAllShows() {
		try {
			MovieService service = ServiceFactory.getMovieService();
			HallService hallService = ServiceFactory.getHallService();
			List<Show> list = service.getAllShows();

			line("=", 120);
			System.out.printf("%4s | %-30s | %-40s | %-10s | %-10s | %-10s\n",
					"ID", "Movie name", "Hall", "Slot", "From", "To");
			line("=", 120);

			for (Show show : list) {
				System.out.printf(
						"%4d | %-30s | %-40s | %-10s | %-10s | %-10s\n", show
								.getShowId(),
						service.getMovie(show.getMovieId()).getMovieName(),
						hallService.getHallById(show.getHallId())
								.getDescription(), slots[show.getSlot() - 1],
						dateToString(show.getFromDate()), dateToString(show
								.getToDate()));
			}
			line(".", 120);

		} catch (Exception e) {
			System.out
					.println("There was a problem while listing the show details");
			System.out.println(e.getMessage());
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void handleBookingsMenu() {
		int choice;
		the_loop: while ((choice = bookingsMenu()) != 99) {
			switch (choice) {
			case 1: // Book a show
				bookShow();
				break;
			case 2: // Search and modify booking data
				searchAndModifyBookingData();
				break;
			case 3: // Delete booking
				deleteBooking();
				break;
			case 4: // List all bookings
				listAllBookings();
				break;
			case 0: // ignore
				break;
			case 99:
				break the_loop;
			default:
				System.out.println("***** INVALID CHOICE *****");
			}

		}
	}

	private void bookShow() {
		try {
			BookingService service = ServiceFactory.getBookingService();
			MovieService movieService = ServiceFactory.getMovieService();
			UserService userService = ServiceFactory.getUserService();
			HallService hallService = ServiceFactory.getHallService();

			int bookingId = KeyboardUtil.getInt("Enter booking id: ");
			Booking booking = service.getBooking(bookingId);
			if (booking != null) {
				System.out.println("Booking with this id already exists!");
				return;
			}

			int showId = KeyboardUtil.getInt("Enter show id: ");
			Show show = movieService.getShow(showId);
			if (show == null) {
				System.out.println("Show with this id does not exist!");
				return;
			} else {
				System.out.println("Hall     : "
						+ hallService.getHallById(show.getHallId())
								.getDescription());
				System.out.println("Movie    : "
						+ movieService.getMovie(show.getMovieId())
								.getMovieName());
				System.out.println("Slot     : " + slots[show.getSlot() - 1]);
			}

			int userId = KeyboardUtil.getInt("Enter user id: ");
			User user = userService.getUserById(userId);
			if (user == null) {
				System.out.println("User with this id does not exist!");
				return;
			} else {
				System.out.println("Username : " + user.getUsername());
				System.out.println("Usertype : " + user.getUsertype());
				System.out.println("Mobile # : " + user.getMobileNumber());
				System.out.println("Email id : " + user.getEmailId());
			}

			System.out.println("Show is between "
					+ dateToString(show.getFromDate()) + " and "
					+ dateToString(show.getToDate()));
			Date showDate = KeyboardUtil
					.getDate("Enter the show date (YYYY-MM-DD format): ");
			Date today = stringToDate(dateToString(new Date()));
			if (showDate.before(today)) {
				System.out.println("Show date cannot be before today!");
				return;
			} else if (showDate.before(show.getFromDate())
					|| showDate.after(show.getToDate())) {
				System.out.println("Show date should be between "
						+ dateToString(show.getFromDate()) + " and "
						+ dateToString(show.getToDate()));
				return;
			}

			List<String> seatTypeIds = hallService.getSeatTypeIds(show
					.getHallId());
			System.out
					.println("Seat type ids available for the selected show: "
							+ seatTypeIds);
			String seatTypeId = KeyboardUtil.getString("Enter seat type id: ");
			if (!seatTypeIds.contains(seatTypeId)) {
				System.out
						.println("Seat type is invalid for the selected show");
				return;
			}

			int numberOfSeats = KeyboardUtil
					.getInt("Enter the number of seats: ");
			if (numberOfSeats < 1) {
				System.out.println("Number of seats must be >= 1");
				return;
			}

			booking = new Booking();
			booking.setBookingId(bookingId);
			booking.setShowId(showId);
			booking.setUserId(userId);
			booking.setShowDate(showDate);
			booking.setBookingDate(today);

			BookingDetail bookingDetail = new BookingDetail(bookingId,
					seatTypeId, numberOfSeats);

			service.addNewBooking(booking);
			service.addNewBookingDetail(bookingDetail);

			System.out.println("Show booked successfully");
		} catch (Exception e) {
			System.out.println("There was a problem while booking the show");
			String msg = e.getMessage();
			if (msg != null && !msg.equals("null")) {
				System.out.println(msg);
			}
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void searchAndModifyBookingData() {
		try {
			BookingService service = ServiceFactory.getBookingService();
			MovieService movieService = ServiceFactory.getMovieService();
			UserService userService = ServiceFactory.getUserService();
			HallService hallService = ServiceFactory.getHallService();

			int bookingId = KeyboardUtil.getInt("Enter booking id: ");
			Booking booking = service.getBooking(bookingId);
			if (booking == null) {
				System.out.println("Booking with this id does not exist!");
				return;
			}

			Show show = movieService.getShow(booking.getShowId());
			Movie movie = movieService.getMovie(show.getMovieId());
			Hall hall = hallService.getHallById(show.getHallId());
			User user = userService.getUserById(booking.getUserId());

			System.out.println("Hall         : " + hall.getDescription());
			System.out.println("Movie        : " + movie.getMovieName());
			System.out.println("Username     : " + user.getUsername());
			System.out.println("Slot         : " + slots[show.getSlot() - 1]);
			System.out.println("Booking date : "
					+ dateToString(booking.getBookingDate()));
			System.out.println("Show date    : "
					+ dateToString(booking.getShowDate()));

			BookingDetail bookingDetail = service.getBookingDetail(bookingId);

			System.out.println("Seat type    : "
					+ hallService.getSeatType(bookingDetail.getSeatTypeId())
							.getDescription());
			System.out.println("No.of seats  : "
					+ bookingDetail.getNumberOfSeats());

			String ans = KeyboardUtil
					.getString("Do you want to edit this? (yes/no) : [yes] ");
			if (ans.equals("") || ans.equalsIgnoreCase("yes")) {

				System.out.println("Show date is between "
						+ dateToString(show.getFromDate()) + " and "
						+ dateToString(show.getToDate()));
				String input = KeyboardUtil.getString("Enter the show date: ["
						+ dateToString(booking.getShowDate()) + "] ");
				if (!input.equals("")) {
					Date showDate = stringToDate(input);
					booking.setShowDate(showDate);

					if (showDate.before(show.getFromDate())
							|| showDate.after(show.getToDate())) {
						System.out
								.println("Show date is invalid. Should be between "
										+ dateToString(show.getFromDate())
										+ " and "
										+ dateToString(show.getToDate()));
						return;
					}
				}

				List<String> seatTypeIds = hallService.getSeatTypeIds(show
						.getHallId());
				System.out
						.println("Seat type ids available for the selected show: "
								+ seatTypeIds);
				input = KeyboardUtil.getString("Enter seat type id: ["
						+ bookingDetail.getSeatTypeId() + "]");

				if (!input.equals("")) {
					String seatTypeId = input;
					if (!seatTypeIds.contains(seatTypeId)) {
						System.out
								.println("Seat type is invalid for the selected show");
						return;
					}
					bookingDetail.setSeatTypeId(seatTypeId);
				}

				input = KeyboardUtil.getString("Enter the number of seats: ["
						+ bookingDetail.getNumberOfSeats() + "]");
				if (!input.equals("")) {
					int numberOfSeats = Integer.parseInt(input);
					if (numberOfSeats < 1) {
						System.out.println("Number of seats must be >= 1");
						return;
					}
					bookingDetail.setNumberOfSeats(numberOfSeats);
				}

				service.updateBooking(booking);
				service.updateBookingDetail(bookingDetail);
				System.out.println("Booking details updated successfully!");
			} else {
				System.out.println("Nothing changed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("There was a problem while updating the booking details");
			String msg = e.getMessage();
			if (msg != null && !msg.equals("null")) {
				System.out.println(msg);
			}
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void deleteBooking() {
		try {
			BookingService service = ServiceFactory.getBookingService();
			MovieService movieService = ServiceFactory.getMovieService();
			UserService userService = ServiceFactory.getUserService();
			HallService hallService = ServiceFactory.getHallService();

			int bookingId = KeyboardUtil.getInt("Enter booking id: ");
			Booking booking = service.getBooking(bookingId);
			if (booking == null) {
				System.out.println("Booking with this id does not exist!");
				return;
			}

			Show show = movieService.getShow(booking.getShowId());
			Movie movie = movieService.getMovie(show.getMovieId());
			Hall hall = hallService.getHallById(show.getHallId());
			User user = userService.getUserById(booking.getUserId());

			System.out.println("Hall         : " + hall.getDescription());
			System.out.println("Movie        : " + movie.getMovieName());
			System.out.println("Username     : " + user.getUsername());
			System.out.println("Slot         : " + slots[show.getSlot() - 1]);
			System.out.println("Booking date : "
					+ dateToString(booking.getBookingDate()));
			System.out.println("Show date    : "
					+ dateToString(booking.getShowDate()));

			System.out.println();
			String ans = KeyboardUtil
					.getString("Are you sure you want to delete this? (yes/no) : [no] ");
			if (ans.equalsIgnoreCase("yes")) {
				service.deleteBooking(bookingId);
				System.out.println("Booking deleted successfully!");
			} else {
				System.out.println("Booking was not deleted!");
			}

		} catch (Exception e) {
			System.out.println("There was a problem while booking the show");
			String msg = e.getMessage();
			if (msg != null && !msg.equals("null")) {
				System.out.println(msg);
			}
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}
	}

	private void listAllBookings() {
		try {
			BookingService service = ServiceFactory.getBookingService();
			MovieService movieService = ServiceFactory.getMovieService();
			UserService userService = ServiceFactory.getUserService();
			HallService hallService = ServiceFactory.getHallService();

			List<Booking> list = service.getAllBookings();
			line("=", 120);
			System.out.printf("%-4s | %-60s | %-15s | %-10s | %-10s\n", "Id",
					"Movie", "Username", "Booking", "Show");
			line("=", 120);
			for (Booking booking : list) {
				Show show = movieService.getShow(booking.getShowId());
				Movie movie = movieService.getMovie(show.getMovieId());
				Hall hall = hallService.getHallById(show.getHallId());
				User user = userService.getUserById(booking.getUserId());

				System.out.printf("%-4d | %-60s | %-15s | %-10s | %-10s\n",
						booking.getBookingId(),
						movie.getMovieName() + " (" + slots[show.getSlot() - 1]
								+ ", " + hall.getDescription() + ")",
						user.getUsername(),
						dateToString(booking.getBookingDate()),
						dateToString(booking.getShowDate()));
			}
			line(".", 120);
		} catch (Exception e) {
			System.out
					.println("There was a problem while listing the bookings");
			String msg = e.getMessage();
			if (msg != null && !msg.equals("null")) {
				System.out.println(msg);
			}
		} finally {
			KeyboardUtil.getString("\nPress ENTER key to continue...");
		}

	}

	private void line(String str, int length) {
		for (int i = 0; i < length; i++) {
			System.out.print(str);
		}
		System.out.println();
	}

	private Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	private String dateToString(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dt);
	}
}
