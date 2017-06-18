package com.manipal.entity;

import java.io.Serializable;

public class HallCapacity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int hallId;
	private String seatTypeId;
	private int seatCount;

	public HallCapacity() {
	}

	public HallCapacity(int hallId, String seatTypeId, int seatCount) {
		this.hallId = hallId;
		this.seatTypeId = seatTypeId;
		this.seatCount = seatCount;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public String getSeatTypeId() {
		return seatTypeId;
	}

	public void setSeatTypeId(String seatTypeId) {
		this.seatTypeId = seatTypeId;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HallCapacity)) {
			return false;
		}
		HallCapacity other = (HallCapacity) obj;
		if (hallId != other.hallId) {
			return false;
		}
		if (seatTypeId != other.seatTypeId) {
			return false;
		}
		return true;
	}

}
