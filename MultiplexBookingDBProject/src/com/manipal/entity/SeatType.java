package com.manipal.entity;

import java.io.Serializable;

public class SeatType implements Serializable {

	private static final long serialVersionUID = 1L;
	private String seatTypeId;
	private String description;
	private double fare;

	public SeatType() {
	}

	public SeatType(String seatTypeId, String description, double fare) {
		this.seatTypeId = seatTypeId;
		this.description = description;
		this.fare = fare;
	}

	public String getSeatTypeId() {
		return seatTypeId;
	}

	public void setSeatTypeId(String seatTypeId) {
		this.seatTypeId = seatTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SeatType)) {
			return false;
		}
		SeatType other = (SeatType) obj;
		if (seatTypeId != other.seatTypeId) {
			return false;
		}
		return true;
	}

}
