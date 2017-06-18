package com.manipal.dao;

import java.util.List;

import com.manipal.entity.Hall;
import com.manipal.entity.HallCapacity;
import com.manipal.entity.SeatType;

public interface HallDao {
	// Entities involved: Hall, HallCapacity, SeatType

	// CRUD

	public void addHall(Hall hall) throws DaoException;

	public Hall getHall(int hallId) throws DaoException;

	public void updateHall(Hall hall) throws DaoException;

	public void deleteHall(int hallId) throws DaoException;

	public void addHallCapacity(HallCapacity capacity) throws DaoException;

	public HallCapacity getHallCapacity(int hallId, String seatTypeId)
			throws DaoException;

	public void updateHallCapacity(HallCapacity capacity) throws DaoException;

	public void deleteHallCapacity(int hallId, String seatTypeId)
			throws DaoException;

	public void addSeatType(SeatType seatType) throws DaoException;

	public SeatType getSeatType(String seatTypeId) throws DaoException;

	public void updateSeatType(SeatType seatType) throws DaoException;

	public void deleteSeatType(String seatTypeId) throws DaoException;

	// Queries

	public List<Hall> getAllHalls() throws DaoException;

	public List<Hall> getHalls(String token) throws DaoException;

	public List<Hall> getHallsBySeatType(int seatTypeId) throws DaoException;

	public List<Hall> getHallsBySeatType(String seatTypeDescription)
			throws DaoException;

	public List<Hall> getHallsByCapacity(int seatCount) throws DaoException;

	public List<SeatType> getAllSeatTypes() throws DaoException;

	public List<HallCapacity> getAllHallCapacities() throws DaoException;

	public List<SeatType> getSeatTypesForHall(int hallId) throws DaoException;

}
