package com.manipal.dao;

import java.util.Date;
import java.util.List;

import com.manipal.entity.Booking;
import com.manipal.entity.BookingDetail;

public interface BookingDao {
	// Entities involved: Booking, BookingDetail

	// CRUD

	public void addBooking(Booking booking) throws DaoException;

	public Booking getBooking(int bookingId) throws DaoException;

	public void updateBooking(Booking booking) throws DaoException;

	public void deleteBooking(int bookingId) throws DaoException;

	public void addBookingDetail(BookingDetail detail) throws DaoException;

	public void updateBookingDetail(BookingDetail detail) throws DaoException;

	// Queries

	public List<Booking> getAllBookings() throws DaoException;

	public List<Booking> getBookingsByShow(int showId) throws DaoException;

	public List<Booking> getBookingsByMovie(int movieId) throws DaoException;

	public List<Booking> getBookingsByUser(int userId) throws DaoException;

	public List<Booking> getBookingsByUser(String username) throws DaoException;

	public List<Booking> getBookingsByBookingDate(Date bookingDate)
			throws DaoException;

	public BookingDetail getBookingDetails(int bookingId)
			throws DaoException;

}
