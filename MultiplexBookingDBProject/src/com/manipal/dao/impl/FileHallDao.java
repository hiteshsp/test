package com.manipal.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.sql.*;

import com.manipal.dao.DaoException;
import com.manipal.dao.HallDao;
import com.manipal.entity.Hall;
import com.manipal.entity.HallCapacity;
import com.manipal.entity.SeatType;
import com.manipal.util.FileUtil;
import com.manipal.util.FileUtilException;

public class FileHallDao implements HallDao {

	Connection con = null;
	Statement stmt = null;
	ResultSet result = null;
    PreparedStatement pstmt = null;
	@Override
	public void addHall(Hall hall) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from Hall where halldesc = '"
							+ hall.getDescription() + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from Hall where hallid = '"
							+ hall.getHallId() + "'");
			result.next();
			int count1 = result.getInt(1);
			stmt.close();

			if (count == 0 && count1 == 0) {

				String query = "insert into Hall(HallId,HallDesc,TotalCapacity)"
						+ " values(?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, hall.getHallId());
				pstmt.setString(2, hall.getDescription());
				pstmt.setInt(3, hall.getHallCapacity());
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
	public List<Hall> getAllHalls() throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from Hall");
			List<Hall> hallList = new ArrayList<Hall>();

			while (result.next()) {

				Hall hall = new Hall();
				hall.setHallId(result.getInt(1));
				hall.setDescription(result.getString(2));
				hall.setHallCapacity(result.getInt(3));
				hallList.add(hall);

			}
			con.close();
			return hallList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public List<Hall> getHalls(String token) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select * from Hall where halldesc like '%"
							+ token + "%'");
			List<Hall> hallList = new ArrayList<Hall>();
			while (result.next()) {
				Hall hall = new Hall();
				hall.setHallId(result.getInt(1));
				hall.setDescription(result.getString(2));
				hall.setHallCapacity(result.getInt(3));
				hallList.add(hall);

			}
			con.close();
			return hallList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public Hall getHall(int hallId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from Hall where hallid = "
							+ hallId);
			result.next();
			int count = result.getInt(1);
			stmt.close();
			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt.executeQuery("select * from Hall where hallid = "
						+ hallId);
				Hall hall = new Hall();
				result.next();
				hall.setHallId(result.getInt(1));
				hall.setDescription(result.getString(2));
				hall.setHallCapacity(result.getInt(3));
				return hall;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public void updateHall(Hall hall) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			
			//Student code here
			
			result = stmt
					.executeQuery("select count(*) from Hall where halldesc = '"
							+ hall.getDescription() + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();
			if (count == 0) {

				String query = "update Hall set HallDesc = ? where HallId = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, hall.getDescription());
				pstmt.setInt(2, hall.getHallId());
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void deleteHall(int hallId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "delete from hall where hallid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hallId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void addHallCapacity(HallCapacity capacity) throws DaoException {
		/*
		 * try { Set<HallCapacity> capacities = FileUtil
		 * .getContent("hallcapacities.dat"); if (capacities.contains(capacity))
		 * { throw new DaoException("Hall capacity already exists"); }
		 * 
		 * capacities.add(capacity); FileUtil.putContent("hallcapacities.dat",
		 * capacities); } catch (FileUtilException e) { e.printStackTrace();
		 * throw new DaoException(e); }
		 */

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from Hallcapacity where hallid = '"
							+ capacity.getHallId()
							+ "' and seattypeid = '"
							+ capacity.getSeatTypeId() + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count == 0) {

				String query = "insert into HallCapacity(HallId,SeatTypeId,SeatCount)"
						+ " values(?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, capacity.getHallId());
				pstmt.setString(2, capacity.getSeatTypeId());
				pstmt.setInt(3, capacity.getSeatCount());
				pstmt.executeUpdate();
			} else
				System.out.println("Information already exists");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public HallCapacity getHallCapacity(int hallId, String seatTypeId)
			throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from Hallcapacity where hallid = '"
							+ hallId
							+ "' and seattypeid = '"
							+ seatTypeId + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();

			if (count > 0) {

				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from hallcapacity where hallId = "
								+ hallId
								+ " and seatTypeId = '"
								+ seatTypeId
								+ "'");
				HallCapacity hallCapacities = new HallCapacity();
				result.next();
				hallCapacities.setHallId(result.getInt(1));
				hallCapacities.setSeatTypeId(result.getString(2));
				hallCapacities.setSeatCount(result.getInt(3));
				con.close();
				return hallCapacities;
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
	public void updateHallCapacity(HallCapacity capacity) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			String query = "update HallCapacity set SeatCount = ? where HallId = ? and SeattypeID = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, capacity.getSeatCount());
			pstmt.setInt(2, capacity.getHallId());
			pstmt.setString(3, capacity.getSeatTypeId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public void deleteHallCapacity(int hallId, String seatTypeId)
			throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
            
            //Student code here
            
			String query = "delete from hallcapacity where hallid = ? and seattypeid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hallId);
			pstmt.setString(2, seatTypeId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public List<HallCapacity> getAllHallCapacities() throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from hallcapacity");
			List<HallCapacity> hallCapacitiesList = new ArrayList<HallCapacity>();

			while (result.next()) {

				HallCapacity hallCapacity = new HallCapacity();
				hallCapacity.setHallId(result.getInt(1));
				hallCapacity.setSeatTypeId(result.getString(2));
				hallCapacity.setSeatCount(result.getInt(3));
				hallCapacitiesList.add(hallCapacity);
			}
			con.close();
			return hallCapacitiesList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public void addSeatType(SeatType seatType) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from seattype where seattypeid = '"
							+ seatType.getSeatTypeId() + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from seattype where seattypedesc = '"
							+ seatType.getDescription() + "'");
			result.next();
			int count1 = result.getInt(1);
			stmt.close();

			if (count == 0 && count1 == 0) {

				String query = "insert into seattype(seattypeId,seattypedesc,seatfare)"
						+ " values(?,?,?)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, seatType.getSeatTypeId());
				pstmt.setString(2, seatType.getDescription());
				pstmt.setDouble(3, seatType.getFare());
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
	public SeatType getSeatType(String seatTypeId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from seattype where seattypeid = '"
							+ seatTypeId + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();
			if (count > 0) {
				stmt = (Statement) con.createStatement();
				result = stmt
						.executeQuery("select * from seattype where seattypeid = '"
								+ seatTypeId + "'");
				SeatType seatType = new SeatType();
				result.next();
				seatType.setSeatTypeId(result.getString(1));
				seatType.setDescription(result.getString(2));
				seatType.setFare(result.getDouble(3));
				return seatType;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}
	}

	@Override
	public void updateSeatType(SeatType seatType) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

            //student code here
            result = stmt
					.executeQuery("select count(*) from seattype where seattypeid = '"
							+ seatType.getSeatTypeId() + "'");
			result.next();
			int count = result.getInt(1);
			stmt.close();
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from seattype where seattypedesc = '"
							+ seatType.getDescription() + "'");
			result.next();
			int count1 = result.getInt(1);
			stmt.close();

			if (count == 0 && count1 == 0) {

				String query = "update seatType set seattypeId = ?,seattypedesc = ?,seatfare = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, seatType.getSeatTypeId());
				pstmt.setString(2, seatType.getDescription());
				pstmt.setDouble(3, seatType.getFare());
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
	public void deleteSeatType(String seatTypeId) throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);

			String query = "delete from seattype where seattypeid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, seatTypeId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.cleanup(con, stmt, null);
		}

	}

	@Override
	public List<Hall> getHallsBySeatType(int seatTypeId) throws DaoException {
		try {
			Set<HallCapacity> capacities = FileUtil
					.getContent("hallcapacities.dat");
			List<Hall> list = new ArrayList<Hall>();
			Set<Hall> halls = FileUtil.getContent("halls.dat");

			for (HallCapacity hc : capacities) {
				inner: for (Hall hall : halls) {
					if (hc.getHallId() == hall.getHallId()) {
						list.add(hall);
						break inner;
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
	public List<Hall> getHallsBySeatType(String seatTypeDescription)
			throws DaoException {
		try {
			Set<SeatType> seatTypes = FileUtil.getContent("seattypes.dat");
			Set<HallCapacity> hallCapacities = FileUtil
					.getContent("hallcapacities.dat");
			List<Hall> list = new ArrayList<Hall>();
			for (SeatType st : seatTypes) {
				if (st.getDescription().contains(seatTypeDescription)) {
					for (HallCapacity hc : hallCapacities) {
						if (hc.getSeatTypeId() == st.getSeatTypeId()) {
							list.add(getHall(hc.getHallId()));
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
	public List<Hall> getHallsByCapacity(int seatCount) throws DaoException {
		try {
			Set<HallCapacity> hallCapacities = FileUtil
					.getContent("hallcapacities.dat");
			List<Hall> list = new ArrayList<Hall>();
			for (HallCapacity hc : hallCapacities) {
				if (hc.getSeatCount() >= seatCount) {
					list.add(getHall(hc.getHallId()));
				}
			}
			return list;
		} catch (FileUtilException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<SeatType> getAllSeatTypes() throws DaoException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@mait-campus-oracledb-01.cmmtocbvzm4p.ap-southeast-1.rds.amazonaws.com:1521:MAITDB";
            String username = "test";
            String password = "test";
            con = DriverManager.getConnection(url, username, password);
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from SeatType");
			List<SeatType> seatTypeList = new ArrayList<SeatType>();

			while (result.next()) {

				SeatType seatType = new SeatType();
				seatType.setSeatTypeId(result.getString(1));
				seatType.setDescription(result.getString(2));
				seatType.setFare(result.getDouble(3));
				seatTypeList.add(seatType);
			}
			con.close();
			return seatTypeList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			ConnectionHelper.cleanup(con, stmt, result);
		}

	}

	@Override
	public List<SeatType> getSeatTypesForHall(int hallId) throws DaoException {
		List<HallCapacity> list = getAllHallCapacities();
		List<SeatType> seatTypes = new ArrayList<SeatType>();
		for (HallCapacity hc : list) {
			if (hc.getHallId() == hallId) {
				seatTypes.add(getSeatType(hc.getSeatTypeId()));
			}
		}
		return seatTypes;
	}

}
