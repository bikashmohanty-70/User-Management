package com.bridgelabz.usermanagement.model;
/**
 * 
 * @author Bikash Mohanty
 * @since 27th Dec 2019
 * @version 1.0 
 * 
 * @purpose: User's Model Class
 *
 */
public class UserModel 
{
	private String firstName;
	private String lastname;
	private String mail;
	private String username;
	private String password;
	private String city;
	private String contact;
	
	/*FirstName getter and setter*/
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	/*LastName getter and setter*/
	public String getLastname() 
	{
		return lastname;
	}
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	
	/*Email getter and setter*/
	public String getMail() 
	{
		return mail;
	}
	public void setMail(String mail) 
	{
		this.mail = mail;
	}
	
	/*UserName getter and setter*/
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	/*Password getter and setter*/
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	/*City getter and setter*/
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	/*Contact getter and setter*/
	public String getContact() 
	{
		return contact;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}
}
