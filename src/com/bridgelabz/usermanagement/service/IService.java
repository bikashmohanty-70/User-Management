package com.bridgelabz.usermanagement.service;

import java.sql.ResultSet;

import com.bridgelabz.usermanagement.model.UserModel;

public interface IService {

	/**
	 * 
	 * @param userData => User type
	 * @return int Type
	 * 
	 * @purpose Read All User's Data from Database and checks for duplicate entries for mail, username and contact
	 */
	int readUserData(UserModel userData);

	/**
	 * 
	 * @param userData => User type
	 * @return boolean Type
	 * 
	 * @purpose Insert User's Data into Database
	 */
	boolean insertUserData(UserModel userData);

	/**
	 * 
	 * @param userModel => User type
	 * @return boolean type
	 * 
	 * @purpose validate user for login
	 */
	boolean userValidation(UserModel userModel);

	/**
	 * 
	 * @param username => String Type
	 * @param password => String type
	 * @return ResultSet
	 * 
	 * @purpose to fetch all data from DataBase Table based on UserName and Password
	 */
	ResultSet readAllData(String username, String password);

}