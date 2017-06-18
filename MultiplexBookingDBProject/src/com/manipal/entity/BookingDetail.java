package com.manipal.entity;

import java.io.Serializable;

public class BookingDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private int bookingId;
	private String seatTypeId;
	private int numberOfSeats;

	public BookingDetail() {
	}

	public BookingDetail(int bookingId, String seatTypeId, int numberOfSeats) {
		this.bookingId = bookingId;
		this.seatTypeId = seatTypeId;
		this.numberOfSeats = numberOfSeats;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getSeatTypeId() {
		return seatTypeId;
	}

	public void setSeatTypeId(String seatTypeId) {
		this.seatTypeId = seatTypeId;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BookingDetail)) {
			return false;
		}
		BookingDetail other = (BookingDetail) obj;
		if (bookingId != other.bookingId) {
			return false;
		}
		return true;
	}

}
