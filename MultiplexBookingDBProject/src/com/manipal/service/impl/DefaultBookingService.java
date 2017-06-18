package com.manipal.service.impl;

import java.util.List;

import com.manipal.dao.BookingDao;
import com.manipal.dao.DaoException;
import com.manipal.dao.DaoFactory;
import com.manipal.entity.Booking;
import com.manipal.entity.BookingDetail;
import com.manipal.service.BookingService;
import com.manipal.service.ServiceException;

public class DefaultBookingService implements BookingService {

	@Override
	public Booking getBooking(int bookingId) throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			return dao.getBooking(bookingId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addNewBooking(Booking booking) throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			dao.addBooking(booking);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteBooking(int bookingId) throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			dao.deleteBooking(bookingId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Booking> getAllBookings() throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			return dao.getAllBookings();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateBooking(Booking booking) throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			dao.updateBooking(booking);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addNewBookingDetail(BookingDetail bookingDetail)
			throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			dao.addBookingDetail(bookingDetail);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public BookingDetail getBookingDetail(int bookingId)
			throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			return dao.getBookingDetails(bookingId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateBookingDetail(BookingDetail bookingDetail)
			throws ServiceException {
		try {
			BookingDao dao = DaoFactory.getDaoFactory("file").getBookingDao();
			dao.updateBookingDetail(bookingDetail);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
