package com.bridgelabz.usermanagement.repository;

import java.sql.Connection;
import java.sql.ResultSet;

import com.bridgelabz.usermanagement.model.UserModel;
/**
 * @author Bikash Mohanty
 * @Since 27th Dec 2019
 * @version 1.0
 * 
 * @purpose: Database Connectivity Interfaces
 * 
 */
public interface IUserDAO 
{

	Connection connect();
	
	boolean insert(UserModel userModel);

	boolean validateUser(UserModel userModel);

	int readData(UserModel userModel);

	ResultSet readAllData(String uname, String pswrd);

	boolean readEmail(String mail, String pwd);


}