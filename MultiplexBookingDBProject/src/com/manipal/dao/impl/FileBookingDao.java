package com.manipal.dao.impl;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.manipal.dao.BookingDao;
import com.manipal.dao.DaoException;
import com.manipal.entity.Booking;
import com.manipal.entity.BookingDetail;
import com.manipal.entity.Show;
import com.manipal.entity.User;
import com.manipal.util.FileUtil;
import com.manipal.util.FileUtilException;

public class FileBookingDao implements BookingDao {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet result = null;

	@Override
	public void addBooking(Booking booking) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			
			//Student code here
			
			result = stmt
					.executeQuery("select count(*) from booking where bookingid = '"
							+ booking.getBookingId()
							+ "' and userid = '"
							+ booking.getUserId() + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count == 0) {

				String query = "insert into booking values(?,?,?,to_date(?,'DD/MM/YY'),to_date(?,'DD/MM/YY'))";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, booking.getBookingId());
				pstmt.setInt(2, booking.getShowId());
				pstmt.setInt(3, booking.getUserId());

				String dateStr = booking.getBookingDate().toString();
				DateFormat formatter = new SimpleDateFormat(
						"E MMM dd HH:mm:ss Z yyyy");
				Date date = (Date) formatter.parse(dateStr);

				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String formatedDate = cal.get(Calendar.DATE) + "/"
						+ (cal.get(Calendar.MONTH) + 1) + "/"
						+ cal.get(Calendar.YEAR);
				System.out.println("formatedDate : " + formatedDate);

				pstmt.setString(4, formatedDate);

				dateStr = booking.getShowDate().toString();
				formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				date = (Date) formatter.parse(dateStr);

				cal = Calendar.getInstance();
				cal.setTime(date);
				formatedDate = cal.get(Calendar.DATE) + "/"
						+ (cal.get(Calendar.MONTH) + 1) + "/"
						+ cal.get(Calendar.YEAR);
				System.out.println("formatedDate : " + formatedDate);

				pstmt.setString(5, formatedDate);

				pstmt.executeUpdate();

			} else {
				System.out.println("Information already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public Booking getBooking(int bookingId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from booking where bookingid = "
							+ bookingId);
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {

				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from booking where bookingid = "
								+ bookingId);
				Booking booking = new Booking();

				result.next();
				booking.setBookingId(result.getInt(1));
				booking.setShowId(result.getInt(2));
				booking.setUserId(result.getInt(3));
				booking.setBookingDate(result.getDate(4));
				booking.setShowDate(result.getDate(5));
				con.close();
				return booking;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public void updateBooking(Booking booking) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			String query = "update booking set showid = ?,bookeddate =  to_date(?,'dd/mm/yyyy'),showdate = to_date(?,'dd/mm/yyyy') where bookingid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, booking.getShowId());

			String dateStr = booking.getBookingDate().toString();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date) formatter.parse(dateStr);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String formatedDate = cal.get(Calendar.DATE) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);
			System.out.println("formatedDate : " + formatedDate);
			pstmt.setString(2, formatedDate);

			try {
				dateStr = booking.getShowDate().toString();
				formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				date = (Date) formatter.parse(dateStr);

				cal = Calendar.getInstance();
				cal.setTime(date);
				formatedDate = cal.get(Calendar.DATE) + "/"
						+ (cal.get(Calendar.MONTH) + 1) + "/"
						+ cal.get(Calendar.YEAR);
				System.out.println("formatedDate : " + formatedDate);
			} catch (Exception e) {

			}

			pstmt.setString(3, formatedDate);
			pstmt.setInt(4, booking.getBookingId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void deleteBooking(int bookingId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "delete from bookingdetail where bookingid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bookingId);
			pstmt.executeUpdate();

			query = "delete from booking where bookingid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bookingId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void addBookingDetail(BookingDetail bookingDetail)
			throws DaoException {
		/*
		 * try { Set<BookingDetail> bookingDetails = FileUtil
		 * .getContent("bookingdetails.dat"); if
		 * (bookingDetails.contains(bookingDetail)) { throw new
		 * DaoException("Booking details already exists"); }
		 * 
		 * bookingDetails.add(bookingDetail);
		 * FileUtil.putContent("bookingdetails.dat", bookingDetails); } catch
		 * (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			String query = "insert into bookingdetail values(?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bookingDetail.getBookingId());
			pstmt.setString(2, bookingDetail.getSeatTypeId());
			pstmt.setInt(3, bookingDetail.getNumberOfSeats());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void updateBookingDetail(BookingDetail bookingDetail)
			throws DaoException {
		/*
		 * try { Set<BookingDetail> bookingDetils = FileUtil
		 * .getContent("bookingdetails.dat"); for (BookingDetail bookingDetail1
		 * : bookingDetils) { if (bookingDetail.getBookingId() == bookingDetail1
		 * .getBookingId() && bookingDetail.getSeatTypeId() == bookingDetail1
		 * .getSeatTypeId()) { bookingDetils.remove(bookingDetail1);
		 * bookingDetils.add(bookingDetail);
		 * FileUtil.putContent("bookingdetails.dat", bookingDetils); break; } }
		 * } catch (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "update bookingdetail set noofseats = ? where bookingid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bookingDetail.getNumberOfSeats());
			pstmt.setInt(2, bookingDetail.getBookingId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

		/*
		 * try { con = ConnectionHelper.getMySqlConnection();
		 * 
		 * String query =
		 * "update bookingdetail set showid = ?,bookeddate =  to_date(?,'dd/mm/yyyy'),showdate = to_date(?,'dd/mm/yyyy') where bookingid = ?"
		 * ; pstmt = con.prepareStatement(query); pstmt.setInt(1,
		 * booking.getShowId());
		 * 
		 * String dateStr = booking.getBookingDate().toString(); DateFormat
		 * formatter = new SimpleDateFormat( "E MMM dd HH:mm:ss Z yyyy"); Date
		 * date = (Date) formatter.parse(dateStr);
		 * 
		 * Calendar cal = Calendar.getInstance(); cal.setTime(date); String
		 * formatedDate = cal.get(Calendar.DATE) + "/" +
		 * (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
		 * System.out.println("formatedDate : " + formatedDate);
		 * pstmt.setString(2, formatedDate);
		 * 
		 * dateStr = booking.getShowDate().toString(); formatter = new
		 * SimpleDateFormat( "E MMM dd HH:mm:ss Z yyyy"); date = (Date)
		 * formatter.parse(dateStr);
		 * 
		 * cal = Calendar.getInstance(); cal.setTime(date); formatedDate =
		 * cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +
		 * cal.get(Calendar.YEAR); System.out.println("formatedDate : " +
		 * formatedDate); pstmt.setString(3, formatedDate); pstmt.setInt(4,
		 * booking.getBookingId());
		 * 
		 * pstmt.executeUpdate(); } catch (Exception e) { e.printStackTrace();
		 * 
		 * } finally { ConnectionHelper.cleanup(con, stmt, null); }
		 */
	}

	@Override
	public List<Booking> getAllBookings() throws DaoException {
		/*
		 * try { Set<Booking> bookings = FileUtil.getContent("bookings.dat");
		 * return new ArrayList<Booking>(bookings); } catch (FileUtilException
		 * e) { e.printStackTrace(); throw new DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from booking");
			List<Booking> bookingList = new ArrayList<Booking>();

			while (result.next()) {

				Booking booking = new Booking();
				booking.setBookingId(result.getInt(1));
				booking.setShowId(result.getInt(2));
				booking.setUserId(result.getInt(3));
				booking.setBookingDate(result.getDate(4));
				booking.setShowDate(result.getDate(5));

				bookingList.add(booking);
			}
			con.close();
			return bookingList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}
	}

	@Override
	public List<Booking> getBookingsByShow(int showId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from booking where showid = "
							+ showId);
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from booking where showid = "
								+ showId);
			List<Booking> bookingList = new ArrayList<Booking>();

			while (result.next()) {

				Booking booking = new Booking();
				booking.setBookingId(result.getInt(1));
				booking.setShowId(result.getInt(2));
				booking.setUserId(result.getInt(3));
				booking.setBookingDate(result.getDate(4));
				booking.setShowDate(result.getDate(5));

				bookingList.add(booking);
			}
			con.close();
			return bookingList;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public List<Booking> getBookingsByMovie(int movieId) throws DaoException {
		try {
			Set<Booking> bookings = FileUtil.getContent("bookings.dat");
			Set<Show> shows = FileUtil.getContent("shows.dat");

			List<Booking> list = new ArrayList<Booking>();

			for (Show show : shows) {
				if (show.getMovieId() == movieId) {
					for (Booking booking : bookings) {
						if (booking.getShowId() == show.getShowId()) {
							list.add(booking);
						}
					}
				}
			}

			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Booking> getBookingsByUser(int userId) throws DaoException {
		try {
			Set<Booking> bookings = FileUtil.getContent("bookings.dat");
			List<Booking> list = new ArrayList<Booking>();
			for (Booking booking : bookings) {
				if (booking.getUserId() == userId) {
					list.add(booking);
				}
			}
			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Booking> getBookingsByUser(String username) throws DaoException {
		try {
			Set<User> users = FileUtil.getContent("users.dat");
			User theUser = null;
			for (User user : users) {
				if (user.getUsername().equals(username)) {
					theUser = user;
				}
			}
			List<Booking> list = new ArrayList<Booking>();
			if (theUser != null) {
				Set<Booking> bookings = FileUtil.getContent("bookings.dat");
				for (Booking booking : bookings) {
					if (booking.getUserId() == theUser.getUserId()) {
						list.add(booking);
					}
				}
			}
			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Booking> getBookingsByBookingDate(Date bookingDate)
			throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			
			result = stmt
					.executeQuery("select * from booking where bookeddate = '" + dateToString(bookingDate) + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt.executeQuery("select * from booking where bookeddate = '" + dateToString(bookingDate) + "'");
			List<Booking> bookingList = new ArrayList<Booking>();

			while (result.next()) {

				Booking booking = new Booking();
				booking.setBookingId(result.getInt(1));
				booking.setShowId(result.getInt(2));
				booking.setUserId(result.getInt(3));
				booking.setBookingDate(result.getDate(4));
				booking.setShowDate(result.getDate(5));

				bookingList.add(booking);
			}
			con.close();
			return bookingList;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}
	}


	@Override
	public BookingDetail getBookingDetails(int bookingId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from bookingdetail where bookingid = "
							+ bookingId);
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from bookingdetail where bookingid = "
								+ bookingId);
				result.next();

				BookingDetail bookingDetail = new BookingDetail();
				bookingDetail.setBookingId(result.getInt(1));
				bookingDetail.setSeatTypeId(result.getString(2));
				bookingDetail.setNumberOfSeats(result.getInt(3));

				con.close();
				return bookingDetail;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}


	private String dateToString(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dt);
	}
	
}
