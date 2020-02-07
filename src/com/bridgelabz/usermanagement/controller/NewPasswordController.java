package com.bridgelabz.usermanagement.controller;
/**
 * @author Bikash Mohanty
 * @since 3rd jan 2020
 * @version 1.0
 * 
 * purpose: Updating Forgotten Password for User
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.usermanagement.repository.IUserDAO;
import com.bridgelabz.usermanagement.repository.UserDao;

/**
 * Servlet implementation class NewPassword
 */
//@WebServlet("/NewPassword")
public class NewPasswordController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	static IUserDAO connectDB = new UserDao();
	static PrintWriter printWriter = null;
	static Connection connectionObj = connectDB.connect();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletContext servletContext = getServletContext();
		String newPassword = request.getParameter("NPwd");
		String email = (String) servletContext.getAttribute("email");	//Getting Recipient Email Via ServletContext
		System.out.println(email);
		System.out.println(newPassword);
		try 
		{
			printWriter = response.getWriter();
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
		if(connectDB.readEmail(email, newPassword))	//connectDB() updates the Password of User whose email matches the String 
		{
			printWriter.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			printWriter.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			printWriter.println("<script>");
			printWriter.println("$(document).ready(function(){");
			printWriter.println("swal('Congratulations!', 'Password Updated SuccessFully.', 'success');");
			printWriter.println("});");
			printWriter.println("</script>");
			
//			HttpSession sessionForgotPass = request.getSession();
//			sessionForgotPass.removeAttribute("UserMail");
//			sessionForgotPass.invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("Index.jsp");
			try 
			{
				dispatcher.include(request, response);
				//response.sendRedirect("Index.jsp");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			printWriter.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			printWriter.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			printWriter.println("<script>");
			printWriter.println("$(document).ready(function(){");
			printWriter.println("swal('Sorry!', 'Password Cannot be Changed Now. Try Agin After Saometime.', 'error');");
			printWriter.println("});");
			printWriter.println("</script>");
			try 
			{
				response.sendRedirect("Index.jsp");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
	}

}
