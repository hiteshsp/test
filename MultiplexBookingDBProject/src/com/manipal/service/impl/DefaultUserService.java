package com.manipal.service.impl;

import java.util.List;

import com.manipal.dao.DaoException;
import com.manipal.dao.DaoFactory;
import com.manipal.dao.UserDao;
import com.manipal.entity.User;
import com.manipal.service.ServiceException;
import com.manipal.service.UserService;

public class DefaultUserService implements UserService {

	@Override
	public void addUser(User user) throws ServiceException {
		try {
			UserDao dao = DaoFactory.getDaoFactory("file").getUserDao();
			dao.addUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			UserDao dao = DaoFactory.getDaoFactory("file").getUserDao();
			return dao.getAllUsers();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		try {
			UserDao dao = DaoFactory.getDaoFactory("file").getUserDao();
			dao.deleteUser(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public User getUserById(int id) throws ServiceException {
		try {
			UserDao dao = DaoFactory.getDaoFactory("file").getUserDao();
			return dao.getUser(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {
			UserDao dao = DaoFactory.getDaoFactory("file").getUserDao();
			dao.updateUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}

}
