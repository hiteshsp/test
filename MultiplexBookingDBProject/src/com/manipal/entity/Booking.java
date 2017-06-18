package com.manipal.entity;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;
	private int bookingId;
	private int showId;
	private int userId;
	private Date bookingDate;
	private Date showDate;

	public Booking() {
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
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
		if (!(obj instanceof Booking)) {
			return false;
		}
		Booking other = (Booking) obj;
		if (bookingId != other.bookingId) {
			return false;
		}
		return true;
	}

}
