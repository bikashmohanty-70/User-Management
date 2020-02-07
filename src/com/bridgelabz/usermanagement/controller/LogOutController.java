package com.bridgelabz.usermanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
//@WebServlet("/Logout")
public class LogOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		session.removeAttribute("uname");	//Removing the session attribute
		session.invalidate();				//invalidating the session attribute
		try 
		{
			response.sendRedirect("Index.jsp");	//Sending the response to Index page. If Session is null then the user will automatically redirected to the Login Page 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
