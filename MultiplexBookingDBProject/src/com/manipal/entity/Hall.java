package com.manipal.entity;

import java.io.Serializable;

public class Hall implements Serializable {

	private static final long serialVersionUID = 1L;
	private int hallId;
	private String description;
	private int hallCapacity;

	public Hall() {
	}

	public Hall(int hallId, String description,int hallCapacity) {
		this.hallId = hallId;
		this.description = description;
		this.setHallCapacity(hallCapacity);
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHallCapacity() {
		return hallCapacity;
	}

	public void setHallCapacity(int hallCapacity) {
		this.hallCapacity = hallCapacity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hallId;
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
		if (!(obj instanceof Hall)) {
			return false;
		}
		Hall other = (Hall) obj;
		if (hallId != other.hallId) {
			return false;
		}
		return true;
	}

}
