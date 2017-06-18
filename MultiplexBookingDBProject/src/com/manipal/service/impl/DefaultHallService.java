package com.manipal.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.manipal.dao.DaoException;
import com.manipal.dao.DaoFactory;
import com.manipal.dao.HallDao;
import com.manipal.entity.Hall;
import com.manipal.entity.HallCapacity;
import com.manipal.entity.SeatType;
import com.manipal.service.HallService;
import com.manipal.service.ServiceException;

public class DefaultHallService implements HallService {

	@Override
	public void addHall(Hall hall) throws ServiceException {

		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.addHall(hall);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Hall> getAllHalls() throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			return dao.getAllHalls();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Hall getHallById(int id) throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			return dao.getHall(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteHall(int id) throws ServiceException {

		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.deleteHall(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void updateHall(Hall hall) throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.updateHall(hall);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addSeatType(SeatType seatType) throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.addSeatType(seatType);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SeatType getSeatType(String id) throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			return dao.getSeatType(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateSeatType(SeatType st) throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.updateSeatType(st);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteSeatType(String seatTypeId) throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.deleteSeatType(seatTypeId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SeatType> getAllSeatTypes() throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			return dao.getAllSeatTypes();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addHallCapacity(HallCapacity hallCapacity)
			throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.addHallCapacity(hallCapacity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateHallCapacity(HallCapacity hallCapacity)
			throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.updateHallCapacity(hallCapacity);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HallCapacity getHallCapacity(int hallId, String seatTypeId)
			throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			return dao.getHallCapacity(hallId, seatTypeId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteHallCapacity(int hallId, String seatTypeId)
			throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			dao.deleteHallCapacity(hallId, seatTypeId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<HallCapacity> getAllHallCapacities() throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			return dao.getAllHallCapacities();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<String> getSeatTypeIds(int hallId) throws ServiceException {
		try {
			HallDao dao = DaoFactory.getDaoFactory("file").getHallDao();
			List<SeatType> list = dao.getSeatTypesForHall(hallId);
			List<String> seatTypeIds = new ArrayList<String>();
			for (SeatType st : list) {
				seatTypeIds.add(st.getSeatTypeId());
			}
			return seatTypeIds;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
