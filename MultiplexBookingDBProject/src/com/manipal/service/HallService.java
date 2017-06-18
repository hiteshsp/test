package com.manipal.service;

import java.util.List;

import com.manipal.entity.Hall;
import com.manipal.entity.HallCapacity;
import com.manipal.entity.SeatType;

public interface HallService {

	public void addHall(Hall hall) throws ServiceException;

	public List<Hall> getAllHalls() throws ServiceException;

	public Hall getHallById(int id) throws ServiceException;

	public void deleteHall(int id) throws ServiceException;

	public void updateHall(Hall hall) throws ServiceException;

	public void addSeatType(SeatType seatType) throws ServiceException;

	public SeatType getSeatType(String id) throws ServiceException;

	public void updateSeatType(SeatType seatType) throws ServiceException;

	public void deleteSeatType(String id) throws ServiceException;

	public List<SeatType> getAllSeatTypes() throws ServiceException;

	public void addHallCapacity(HallCapacity hallCapacity)
			throws ServiceException;

	public void updateHallCapacity(HallCapacity hallCapacity)
			throws ServiceException;

	public HallCapacity getHallCapacity(int hallId, String seatTypeId)
			throws ServiceException;

	public void deleteHallCapacity(int hallId, String seatTypeId)
			throws ServiceException;

	public List<HallCapacity> getAllHallCapacities() throws ServiceException;

	public List<String> getSeatTypeIds(int hallId) throws ServiceException;

}
