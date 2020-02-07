package com.bridgelabz.usermanagement.service;

import java.sql.Connection;
import java.sql.ResultSet;

import com.bridgelabz.usermanagement.model.UserModel;
import com.bridgelabz.usermanagement.repository.IUserDAO;
import com.bridgelabz.usermanagement.repository.UserDao;
/**
 * @author Bikash Mohanty
 * @Since 27th Dec 2019
 * @version 1.0
 * 
 * @purpose: Service Implementation
 * 
 */
public class UserServiceImplementation implements IService 
{
	
	static IUserDAO connectDatabase = new UserDao();
	static Connection connection = connectDatabase.connect();
	/**
	 * 
	 * @param userData => User type
	 * @return int Type
	 * 
	 * @purpose Read All User's Data from Database and checks for duplicate entries for mail, username and contact
	 */
	@Override
	public int readUserData(UserModel userData)
	{
		return connectDatabase.readData(userData);
	}
	
	
	/**
	 * 
	 * @param userData => User type
	 * @return boolean Type
	 * 
	 * @purpose Insert User's Data into Database
	 */
	@Override
	public boolean insertUserData(UserModel userData)
	{
		return connectDatabase.insert(userData);
	}
	
	
	/**
	 * 
	 * @param userModel => User type
	 * @return boolean type
	 * 
	 * @purpose validate user for login
	 */
	@Override
	public boolean userValidation(UserModel userModel)
	{
		
		return connectDatabase.validateUser(userModel);		
	}
	
	
	/**
	 * 
	 * @param username => String Type
	 * @param password => String type
	 * @return ResultSet
	 * 
	 * @purpose to fetch all data from DataBase Table based on UserName and Password
	 */
	@Override
	public ResultSet readAllData(String username, String password)
	{
		return connectDatabase.readAllData(username, password);
		
	}
}
