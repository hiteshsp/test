package com.manipal.dao;

import java.util.Arrays;
import java.util.List;

import com.manipal.dao.impl.FileBookingDao;
import com.manipal.dao.impl.FileHallDao;
import com.manipal.dao.impl.FileMovieDao;
import com.manipal.dao.impl.FileUserDao;

public class DaoFactory {
	private String impl = "file";
	private static DaoFactory instance = null;

	private DaoFactory() {
	}

	public static DaoFactory getDaoFactory(String impl) throws DaoException {
		if (instance == null) {
			List<String> availableImpls = Arrays.asList("file");
			if (!availableImpls.contains(impl)) {
				throw new DaoException("Implementation type not avilable");
			}
			instance = new DaoFactory();
			instance.impl = impl.toLowerCase();
		}
		return instance;
	}

	public UserDao getUserDao() {
		if (impl.equals("file")) {
			return new FileUserDao();
		}
		return null;
	}

	public MovieDao getMovieDao() {
		if (impl.equals("file")) {
			return new FileMovieDao();
		}
		return null;
	}

	public HallDao getHallDao() {
		if (impl.equals("file")) {
			return new FileHallDao();
		}
		return null;
	}

	public BookingDao getBookingDao() {
		if (impl.equals("file")) {
			return new FileBookingDao();
		}
		return null;
	}
}
