import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.manipal.dao.BookingDao;
import com.manipal.dao.DaoException;
import com.manipal.dao.DaoFactory;
import com.manipal.dao.MovieDao;
import com.manipal.dao.UserDao;
import com.manipal.entity.Booking;
import com.manipal.entity.Movie;
import com.manipal.entity.Show;
import com.manipal.entity.User;


import org.junit.Before;
import org.junit.Test;


public class MultiplexBookingDBProjectTest {
	private UserDao userDao;
	private MovieDao movieDao;
	private BookingDao bookingDao;

	@Before
	public void setup() {
		try {
			userDao = DaoFactory.getDaoFactory("file").getUserDao();
            
			User user1, user2, user3;
			user1 = new User(1, "vinod", "A", "9731424784",
					"vinod@mailinator.com");
			user2 = new User(2, "kumar", "A", "9844083934",
					"kumar@mailinator.com");
			user3 = new User(3, "scott", "C", "555332345",
					"scott@mailinator.com");

			userDao.addUser(user1);
			userDao.addUser(user2);
			userDao.addUser(user3);

			movieDao = DaoFactory.getDaoFactory("file").getMovieDao();
			
			Movie m1 = new Movie(1, "Something about mary");
			Movie m2 = new Movie(2, "Finding nemo");
			Movie m3 = new Movie(3, "Avatar");

			Show s1 = new Show();
			s1.setShowId(22);
			s1.setMovieId(2);
			s1.setHallId(10);
			s1.setSlot(2);
			s1.setFromDate(stringToDate("2015-08-07"));
			s1.setToDate(stringToDate("2017-08-20"));

			movieDao.addMovie(m1);
			movieDao.addMovie(m2);
			movieDao.addMovie(m3);
			movieDao.addShow(s1);

			bookingDao = DaoFactory.getDaoFactory("file").getBookingDao();

		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void UTC_02_01_testGetExistingUserByMobile() {
		try {
			User user1 = userDao.getUsersByMobileNumber("9731424784");
			assertEquals(user1.getEmailId(), "vinod@mailinator.com");
			assertEquals(user1.getUsername(), "vinod");
			assertEquals(user1.getUsertype(), "A");
		} catch (DaoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void UTC_02_02_testGetNonExistingUserByMobile() {
		try {
			User user1 = userDao.getUsersByMobileNumber("2478497314");
			assertNull(user1);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void UTC_03_01_testGetExistingShow() {
		try {
			Show show = movieDao.getShow(22);
			assertEquals(2, show.getMovieId());
			assertEquals(10, show.getHallId());
			assertEquals(stringToDate("2015-08-07"),
					show.getFromDate());
			assertEquals(stringToDate("2017-08-20"), show.getToDate());
			assertEquals(2, show.getSlot());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void UTC_03_02_testGetNonExistingShow() {
		try {
			Show show = movieDao.getShow(1);
			assertNull(show);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void UTC_05_03_testGetExistingBookingsByShow() {
		try {
			Booking b1 = new Booking();
			b1.setBookingId(101);
			b1.setShowId(22);
			b1.setUserId(1);
			b1.setShowDate(stringToDate("2015-09-12"));
			b1.setBookingDate(stringToDate("2015-09-11"));

			Booking b2 = new Booking();
			b2.setBookingId(102);
			b2.setShowId(22);
			b2.setUserId(2);
			b2.setShowDate(stringToDate("2015-09-12"));
			b2.setBookingDate(stringToDate("2015-09-12"));

			Booking b3 = new Booking();
			b3.setBookingId(103);
			b3.setShowId(22);
			b3.setUserId(3);
			b3.setShowDate(stringToDate("2015-09-12"));
			b3.setBookingDate(stringToDate("2015-09-12"));

			bookingDao.addBooking(b1);
			bookingDao.addBooking(b2);
			bookingDao.addBooking(b3);

			List<Booking> list = bookingDao.getBookingsByShow(22);
			assertEquals(3, list.size());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

    @Test
    public void deleteRecordFromTables() {
           try {
           	bookingDao.deleteBooking(101);
            bookingDao.deleteBooking(102);
            bookingDao.deleteBooking(103);

			movieDao.deleteShow(22);

            userDao.deleteUser(1);            
            userDao.deleteUser(2);
            userDao.deleteUser(3);
			movieDao.deleteMovie(1);
			movieDao.deleteMovie(2);
			movieDao.deleteMovie(3);
        }catch (Exception e) {
			fail(e.getMessage());
		}
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
