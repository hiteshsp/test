package com.manipal.service;

import java.util.List;

import com.manipal.entity.User;

public interface UserService {

	public void addUser(User user) throws ServiceException;

	public List<User> getAllUsers() throws ServiceException;

	public void deleteUser(int id) throws ServiceException;

	public User getUserById(int id) throws ServiceException;

	public void updateUser(User user) throws ServiceException;

}
