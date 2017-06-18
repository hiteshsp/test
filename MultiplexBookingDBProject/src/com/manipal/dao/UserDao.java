package com.manipal.dao;

import java.util.List;

import com.manipal.entity.User;

public interface UserDao {
	// Entities involved: User

	// CRUD operations
	public void addUser(User user) throws DaoException;

	public User getUser(int userId) throws DaoException;

	public void updateUser(User user) throws DaoException;

	public void deleteUser(int userId) throws DaoException;

	// Queries

	public List<User> getAllUsers() throws DaoException;

    public List<User> getUsersByUserType(String userType) throws DaoException;
 
    public User getUserByUsername(String username) throws DaoException;   
    
    public User getUsersByEmailId(String emailId) throws DaoException;
    
    public User getUsersByMobileNumber(String mobileNumber) throws DaoException;
}
