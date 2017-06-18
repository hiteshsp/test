package com.manipal.service;

import java.util.List;

import com.manipal.entity.Booking;
import com.manipal.entity.BookingDetail;

public interface BookingService {

	public Booking getBooking(int bookingId) throws ServiceException;

	public void addNewBooking(Booking booking) throws ServiceException;

	public void deleteBooking(int bookingId) throws ServiceException;

	public List<Booking> getAllBookings() throws ServiceException;

	public void updateBooking(Booking booking) throws ServiceException;

	public void addNewBookingDetail(BookingDetail bookingDetail)
			throws ServiceException;

	public BookingDetail getBookingDetail(int bookingId)
			throws ServiceException;

	public void updateBookingDetail(BookingDetail bookingDetail)
			throws ServiceException;

}
