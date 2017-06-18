package com.manipal.dao.impl;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.manipal.dao.DaoException;
import com.manipal.dao.UserDao;
import com.manipal.entity.Hall;
import com.manipal.entity.User;
import com.manipal.util.FileUtil;
import com.manipal.util.FileUtilException;

public class FileUserDao implements UserDao {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet result = null;

	@Override
	public void addUser(User user) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
           	
           	String query = "insert into users(userId,username,usertype,mobileNumber,emailId) values(?,?,?,?,?)";
           
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getUsertype());
			pstmt.setString(4, user.getMobileNumber());
			pstmt.setString(5, user.getEmailId());
			pstmt.executeUpdate();
           
           
           
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public User getUser(int userId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from users where userid = '"
							+ userId + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from users where userid = "
								+ userId);
				User user = new User();

				while (result.next()) {

					user.setUserId(result.getInt(1));
					user.setUsername(result.getString(2));
					user.setUsertype(result.getString(3));
					user.setMobileNumber(result.getString(4));
					user.setEmailId(result.getString(5));
				}
				con.close();
				return user;
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
	public void updateUser(User user) throws DaoException {
		/*
		 * try { Set<User> users = FileUtil.getContent("users.dat"); for (User u
		 * : users) { if (user.getUserId() == u.getUserId()) { users.remove(u);
		 * users.add(user); FileUtil.putContent("users.dat", users); break; } }
		 * } catch (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
			con = DriverManager.getConnection(url, username, password);
			String query = "update users set username = ?,usertype = ?,mobileno = ?,emailid = ? where userid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getUsertype());
			pstmt.setString(3, user.getMobileNumber());
			pstmt.setString(4, user.getEmailId());
			pstmt.setInt(5, user.getUserId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void deleteUser(int userId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
			String query = "delete from users where userId = ?";
			con = DriverManager.getConnection(url, username, password);
		
		    pstmt = con.prepareStatement(query);
		    pstmt.setInt(1,userId);
		    pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
			con = DriverManager.getConnection(url, username, password);
			con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from users");
			List<User> userList = new ArrayList<User>();

			while (result.next()) {

				User user = new User();
				user.setUserId(result.getInt(1));
				user.setUsername(result.getString(2));
				user.setUsertype(result.getString(3));
				user.setMobileNumber(result.getString(4));
				user.setEmailId(result.getString(5));

				userList.add(user);
			}
			con.close();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public List<User> getUsersByUserType(String userType) throws DaoException {
		/*
		 * try { Set<User> users = FileUtil.getContent("users.dat"); List<User>
		 * list = new ArrayList<User>(); for (User user : users) { if
		 * (user.getUsertype().equals(userType)) { list.add(user); } } return
		 * list; } catch (FileUtilException e) { e.printStackTrace(); throw new
		 * DaoException(e); }
		 */
		return null;
	}
	
	@Override
	public User getUserByUsername(String username) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String userName = "test";
            String password = "test";
            con = DriverManager.getConnection(url, userName, password);
			stmt = (Statement) con.createStatement();
		
		    //Student code here
		    
		    result = stmt
					.executeQuery("select count(*) from users where username = '"
							+ username + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from users where username = '"
								+ username + "'");
				User user = new User();

				while (result.next()) {

					user.setUserId(result.getInt(1));
					user.setUsername(result.getString(2));
					user.setUsertype(result.getString(3));
					user.setMobileNumber(result.getString(4));
					user.setEmailId(result.getString(5));
				}
				con.close();
				return user;
			} else
				return null;
		} 
            		
			 catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public User getUsersByMobileNumber(String mobileNumber) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			
			//Student code here
			
			result = stmt
					.executeQuery("select count(*) from users where mobileno = '"
							+ mobileNumber + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from users where mobileno = '"
								+ mobileNumber + "'");
				User user = new User();

				while (result.next()) {

					user.setUserId(result.getInt(1));
					user.setUsername(result.getString(2));
					user.setUsertype(result.getString(3));
					user.setMobileNumber(result.getString(4));
					user.setEmailId(result.getString(5));
				}
				con.close();
				return user;
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
	public User getUsersByEmailId(String emailId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from users where emailid = '"
							+ emailId + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from users where emailid = '"
								+ emailId + "'");
				User user = new User();

				while (result.next()) {

					user.setUserId(result.getInt(1));
					user.setUsername(result.getString(2));
					user.setUsertype(result.getString(3));
					user.setMobileNumber(result.getString(4));
					user.setEmailId(result.getString(5));
				}
				con.close();
				return user;
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}
	}

}