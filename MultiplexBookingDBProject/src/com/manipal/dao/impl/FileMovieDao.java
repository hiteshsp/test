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

import com.manipal.dao.DaoException;
import com.manipal.dao.MovieDao;
import com.manipal.entity.Hall;
import com.manipal.entity.Movie;
import com.manipal.entity.Show;
import com.manipal.entity.User;
import com.manipal.util.FileUtil;
import com.manipal.util.FileUtilException;

public class FileMovieDao implements MovieDao {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet result = null;

	@Override
	public void addMovie(Movie movie) throws DaoException {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from movies where movieid = '"
							+ movie.getMovieId() + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from movies where moviename = '"
							+ movie.getMovieName() + "'");
			result.next();
			int count1 = result.getInt(1);
			stmt.close();

			if (count == 0 && count1 == 0) {

				String query = "insert into movies(movieid,moviename) values(?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, movie.getMovieId());
				pstmt.setString(2, movie.getMovieName());
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
	public Movie getMovie(int movieId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from movies where movieid = '"
							+ movieId + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();
			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from movies where movieid = "
								+ movieId);
				result.next();
				Movie movie = new Movie();
				movie.setMovieId(result.getInt(1));
				movie.setMovieName(result.getString(2));
				con.close();
				return movie;
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
	public void updateMovie(Movie movie) throws DaoException {
		/*
		 * try { Set<Movie> movies = FileUtil.getContent("movies.dat"); for
		 * (Movie movie1 : movies) { if (movie.getMovieId() ==
		 * movie1.getMovieId()) { movies.remove(movie1); movies.add(movie);
		 * FileUtil.putContent("movies.dat", movies); break; } } } catch
		 * (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "update movies set moviename = ? where movieid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, movie.getMovieName());
			pstmt.setInt(2, movie.getMovieId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void deleteMovie(int movieId) throws DaoException {
		/*
		 * try { Set<Movie> movies = FileUtil.getContent("movies.dat"); for
		 * (Movie movie : movies) { if (movie.getMovieId() == movieId) {
		 * movies.remove(movie); FileUtil.putContent("movies.dat", movies);
		 * break; } } } catch (FileUtilException e) { e.printStackTrace(); throw
		 * new DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "delete from movies where movieid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, movieId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void addShow(Show show) throws DaoException {
		/*
		 * try { Set<Show> shows = FileUtil.getContent("shows.dat"); if
		 * (shows.contains(show)) { throw new
		 * DaoException("Show already exists"); }
		 * 
		 * shows.add(show); FileUtil.putContent("shows.dat", shows); } catch
		 * (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "insert into shows(showid,hallid,movieid,slotno,fromdate,todate) values(?,?,?,?,to_date(?,'DD/MM/YY'),to_date(?,'DD/MM/YY'))";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, show.getShowId());
			pstmt.setInt(2, show.getHallId());
			pstmt.setInt(3, show.getMovieId());
			pstmt.setInt(4, show.getSlot());

			String dateStr = show.getFromDate().toString();
			DateFormat formatter = new SimpleDateFormat(
					"E MMM dd HH:mm:ss Z yyyy");
			Date date = (Date) formatter.parse(dateStr);
			System.out.println(date);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String formatedDate = cal.get(Calendar.DATE) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);
			System.out.println("formatedDate : " + formatedDate);

			pstmt.setString(5, formatedDate);
			dateStr = show.getToDate().toString();
			formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			date = (Date) formatter.parse(dateStr);
			System.out.println(date);

			cal = Calendar.getInstance();
			cal.setTime(date);
			formatedDate = cal.get(Calendar.DATE) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);
			System.out.println("formatedDate : " + formatedDate);

			pstmt.setString(6, formatedDate);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public Show getShow(int showId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			
			//Student code here
			
			result = stmt
					.executeQuery("select count(*) from shows where showid = '"
							+ showId + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from shows where showid = "
								+ showId);
				Show show = new Show();

				result.next();
				show.setShowId(result.getInt(1));
				show.setHallId(result.getInt(2));
				show.setMovieId(result.getInt(3));
				show.setSlot(result.getInt(4));
				show.setFromDate(result.getDate(5));
				show.setToDate(result.getDate(6));

				con.close();
				return show;
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
	public void updateShow(Show show) throws DaoException {
		/*
		 * try { Set<Show> shows = FileUtil.getContent("shows.dat"); for (Show
		 * show1 : shows) { if (show.getShowId() == show1.getShowId()) {
		 * shows.remove(show1); shows.add(show);
		 * FileUtil.putContent("shows.dat", shows); break; } } } catch
		 * (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "update shows set hallid = ?,movieid =?,slotno = ?,fromdate = to_date(?,'dd/mm/yyyy'),todate = to_date(?,'dd/mm/yyyy') where showid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, show.getHallId());
			pstmt.setInt(2, show.getMovieId());
			pstmt.setInt(3, show.getSlot());

			String dateStr = show.getFromDate().toString();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date) formatter.parse(dateStr);
			System.out.println(date);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String formatedDate = cal.get(Calendar.DATE) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);
			System.out.println("formatedDate : " + formatedDate);

			pstmt.setString(4, formatedDate);
			dateStr = show.getToDate().toString();
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date) formatter.parse(dateStr);
			System.out.println(date);

			cal = Calendar.getInstance();
			cal.setTime(date);
			formatedDate = cal.get(Calendar.DATE) + "/"
					+ (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);
			System.out.println("formatedDate : " + formatedDate);
			pstmt.setString(5, formatedDate);

			pstmt.setInt(6, show.getShowId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public int deleteShow(int showId) throws DaoException {
		/*
		 * try { Set<Show> shows = FileUtil.getContent("shows.dat"); for (Show
		 * show : shows) { if (show.getShowId() == showId) { shows.remove(show);
		 * FileUtil.putContent("shows.dat", shows); break; } } } catch
		 * (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */
		int status = 0;	

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			
			//Student code here
			
			result = stmt
					.executeQuery("select count(*) from shows where showid = '"
							+ showId + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				String query = "delete from shows where showid = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, showId);
				pstmt.executeUpdate();
				status = 1;
			}
			else
				status = 2;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}
		return status;
	}

	@Override
	public List<Movie> getAllMovies() throws DaoException {
		/*
		 * try { Set<Movie> movies = FileUtil.getContent("movies.dat"); return
		 * new ArrayList<Movie>(movies); } catch (FileUtilException e) {
		 * e.printStackTrace(); throw new DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
            stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from movies");
			List<Movie> movieList = new ArrayList<Movie>();

			while (result.next()) {

				Movie movie = new Movie();
				movie.setMovieId(result.getInt(1));
				movie.setMovieName(result.getString(2));
				movieList.add(movie);
			}
			con.close();
			return movieList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public List<Movie> getMovies(String token) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from movies where moviename like '%"
							+ token + "%'");
			result.next();
			int count = result.getInt(1);
			stmt.close();
			if (count > 0) {
			        stmt = (Statement) con.createStatement();
			        result = stmt.executeQuery("select * from movies where moviename like '%" + token + "%'");
			        List<Movie> movieList = new ArrayList<Movie>();

			        while (result.next()) {
    				    Movie movie = new Movie();
	    			    movie.setMovieId(result.getInt(1));
		    		    movie.setMovieName(result.getString(2));
			    	    movieList.add(movie);
			        }
			        con.close();
			    return movieList;
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
	public List<Show> getShowsByHall(int hallId) throws DaoException {
		try {
			Set<Show> shows = FileUtil.getContent("shows.dat");
			List<Show> list = new ArrayList<Show>();
			for (Show show : shows) {
				if (show.getHallId() == hallId) {
					list.add(show);
				}
			}
			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Show> getShowsByMovie(int movieId) throws DaoException {
		try {
			Set<Show> shows = FileUtil.getContent("shows.dat");
			List<Show> list = new ArrayList<Show>();
			for (Show show : shows) {
				if (show.getMovieId() == movieId) {
					list.add(show);
				}
			}
			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Show> getShowsBySlot(int slot) throws DaoException {
		try {
			Set<Show> shows = FileUtil.getContent("shows.dat");
			List<Show> list = new ArrayList<Show>();
			for (Show show : shows) {
				if (show.getSlot() == slot) {
					list.add(show);
				}
			}
			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Show> getShowsByDate(Date fromDate, Date toDate)
			throws DaoException {
		try {
			Set<Show> shows = FileUtil.getContent("shows.dat");
			List<Show> list = new ArrayList<Show>();
			for (Show show : shows) {
				if (show.getFromDate().after(fromDate)
						&& show.getToDate().before(toDate)) {
					list.add(show);
				}
			}
			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Show> getAllShows() throws DaoException {
		/*
		 * try { Set<Show> shows = FileUtil.getContent("shows.dat"); return new
		 * ArrayList<Show>(shows); } catch (FileUtilException e) {
		 * e.printStackTrace(); throw new DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from shows");
			List<Show> showList = new ArrayList<Show>();

			while (result.next()) {

				Show show = new Show();
				show.setShowId(result.getInt(1));
				show.setHallId(result.getInt(2));
				show.setMovieId(result.getInt(3));
				show.setSlot(result.getInt(4));
				show.setFromDate(result.getDate(5));
				show.setToDate(result.getDate(6));
				showList.add(show);
			}
			con.close();
			return showList;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

}
