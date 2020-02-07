package com.bridgelabz.usermanagement.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bridgelabz.usermanagement.model.UserModel;

/**
 * @author Bikash Mohanty
 * @since 28 Dec 20019
 * @version 1.0
 * 
 * @Purpose: User's DAO class
 */
public class UserDao implements IUserDAO
{
	protected static String mysqlURL = "jdbc:mysql://localhost:3306/bridgelabz?autoReconnect=true&useSSL=false";
	protected static String databaseName = "root";
	protected static String databasePassword = "Biki12@3";
	static String sqlQuery = "SELECT r.fname, r.lname, r.mail, r.city, r.contact FROM user_management AS r JOIN login as l ON r.sid = l.sid WHERE l.username = ? and l.PASSWORD = ?";
	static String insertQueryForRegistrationTable = "INSERT INTO user_management (fname, lname, mail, city, contact) VALUES (?, ?, ?, ?, ?)";
	static String insertQueryForLoginTable = "INSERT INTO login (username, PASSWORD) VALUES (?, ?)";

	Connection connection = null;
	PreparedStatement preparedStatementForRegistration = null;
	PreparedStatement preparedStatementForLogin = null;
	ResultSet resultSetForRegistration = null;
	ResultSet resultSetForLogin = null;
	Statement statement = null;
	
	UserModel userModel = new UserModel();

	@Override
	public Connection connect()
	{

		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(mysqlURL, databaseName, databasePassword);
			statement = connection.createStatement();
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return connection;

	}

	@Override
	public boolean insert(UserModel userModel)
	{
		try 
		{
			preparedStatementForRegistration = connection.prepareStatement(insertQueryForRegistrationTable);
			preparedStatementForRegistration.setString(1, userModel.getFirstName());
			preparedStatementForRegistration.setString(2, userModel.getLastname());
			preparedStatementForRegistration.setString(3, userModel.getMail());
			preparedStatementForRegistration.setString(4, userModel.getCity());
			preparedStatementForRegistration.setString(5, userModel.getContact());

			int insertIntoUserManagement = preparedStatementForRegistration.executeUpdate();

			preparedStatementForLogin = connection.prepareStatement(insertQueryForLoginTable);
			preparedStatementForLogin.setString(1, userModel.getUsername());
			preparedStatementForLogin.setString(2, userModel.getPassword());

			int loginCheck = preparedStatementForLogin.executeUpdate();

			if(insertIntoUserManagement == 1 && loginCheck == 1)
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * The Following Method Will Validate User For Login By Taking UserName and Password as user's Credentials
	 * The Query Will return all the Table data and will Check for the credentials whether available in the database or not
	 * If available return TRUE else FLASE
	 */
	@Override
	public boolean validateUser(UserModel userModel)
	{
		ResultSet result = null;
		try 
		{
			result = statement.executeQuery("SELECT * FROM login");
			System.out.println(result);
			while(result.next())
			{
				if(result.getString(1).equals(userModel.getUsername()) && result.getString(2).equals(userModel.getPassword()))
					return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}


	//Reading Data from Tables
	/*
	 * If mail is already Exists in the database then the Below Method will return 1
	 * If UserName is already Exists in the database then the Below Method will
	 * return 2 If Contact is already Exists in the database then the Below Method
	 * will return 3
	 */
	@Override
	public int readData(UserModel userModel)
	{

		String sqlQueryForDuplicateEntryCheck = "SELECT r.mail, r.contact, l.username FROM user_management AS r JOIN login as l ON r.sid = l.sid ";
		
		try(ResultSet resultSetForReadData = statement.executeQuery(sqlQueryForDuplicateEntryCheck);)
		{
			while(resultSetForReadData.next())
			{
				if(resultSetForReadData.getString(1).equals(userModel.getMail()))
					return 1;
				else if(resultSetForReadData.getString(2).equals(userModel.getContact()))
					return 2;
				else if(resultSetForReadData.getString(3).equals(userModel.getUsername()))
					return 3;
				else
					return 0;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	/*
	 * The Following Method take UserName and Password as input of String type.
	 * and returns a ResultSet 
	 */
	
	@Override
	public ResultSet readAllData(String uname, String pswrd)
	{
		try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);)
		{
			
			preparedStatement.setString(1, uname);
			preparedStatement.setString(2, pswrd);
			
			return preparedStatement.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean readEmail(String mail, String pwd)
	{
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE login l INNER JOIN user_management r ON l.sid=r.sid SET l.PASSWORD=? WHERE r.mail=?";
		try 
		{
			System.out.println(connection);
			preparedStatement = connection.prepareStatement(sql);
		
			preparedStatement.setString(1, pwd);
			preparedStatement.setString(2, mail);
			int result = preparedStatement.executeUpdate();
			if(result > 0)
				return true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				if(preparedStatement != null)
					preparedStatement.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return false;
	}

}

