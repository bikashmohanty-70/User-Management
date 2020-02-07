package com.bridgelabz.usermanagement.controller;
/**
 * @author Bikash Mohanty
 * @since 28 Dec 20019
 * @version 1.0
 * 
 * Purpose: TO REGISTER USER'S DATA IN DATABASE USING SERVLET AND JSP
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bridgelabz.usermanagement.model.UserModel;
import com.bridgelabz.usermanagement.repository.IUserDAO;
import com.bridgelabz.usermanagement.repository.UserDao;
import com.bridgelabz.usermanagement.service.IService;
import com.bridgelabz.usermanagement.service.UserServiceImplementation;


/**
 * Servlet implementation class RegisterationModule
 */
//@WebServlet("/RegisterationModule")
public class UserRegistrationController extends HttpServlet 
{
	private static final long serialVersionUID = 2L;

	static IUserDAO connectivity = new UserDao();
	IService service = new UserServiceImplementation();
	static Connection connectionObj = connectivity.connect();
	PrintWriter printWriter = null;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		printWriter =  response.getWriter();
		RequestDispatcher requestDis = null;
		
		UserModel userModel = new UserModel();
		
		userModel.setFirstName(request.getParameter("firstname"));
		userModel.setLastname(request.getParameter("lastname"));
		userModel.setMail(request.getParameter("mail"));
		userModel.setUsername(request.getParameter("username"));
		userModel.setPassword(request.getParameter("password"));
		userModel.setCity(request.getParameter("city"));
		userModel.setContact(request.getParameter("contact"));
		
		int check = service.readUserData(userModel);
		if(check == 1)
		{
			request.setAttribute("errorMessage1", "The Mail You've Entered is already exist.");


			printWriter.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			printWriter.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			printWriter.println("<script>");
			printWriter.println("$(document).ready(function(){");
			printWriter.println("swal('Sorry..!', 'This mail is already registered.', 'error');");
			printWriter.println("});");
			printWriter.println("</script>");
			
			requestDis = request.getRequestDispatcher("Index.jsp");
			requestDis.include(request, response);
		}
		else if(check == 2)
		{
			request.setAttribute("errorMessage2", "Contact is already taken.");


			printWriter.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			printWriter.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			printWriter.println("<script>");
			printWriter.println("$(document).ready(function(){");
			printWriter.println("swal('Sorry..!', 'This Contact is already registered.', 'error');");
			printWriter.println("});");
			printWriter.println("</script>");
			
			requestDis = request.getRequestDispatcher("Index.jsp");
			try 
			{
				requestDis.include(request, response);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else if(check == 3)
		{
			request.setAttribute("errorMessage3", "Username is already registered");


			printWriter.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			printWriter.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			printWriter.println("<script>");
			printWriter.println("$(document).ready(function(){");
			printWriter.println("swal('Sorry..!', 'This Username is already registered.', 'error');");
			printWriter.println("});");
			printWriter.println("</script>");
			
			requestDis = request.getRequestDispatcher("Index.jsp");
			try 
			{
				requestDis.include(request, response);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else
		{

			boolean flag = service.insertUserData(userModel);
			if(flag)
			{

				printWriter.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
				printWriter.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
				printWriter.println("<script>");
				printWriter.println("$(document).ready(function(){");
				printWriter.println("swal('Thank You!', 'You have been successfully registered.', 'success');");
				printWriter.println("});");
				printWriter.println("</script>");
				request.setAttribute("resgistrationStatus", true);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Index.jsp");

				try 
				{
					requestDispatcher.include(request, response);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				request.setAttribute("resgistrationStatus", false);
				try 
				{
					request.getRequestDispatcher("Index.jsp").forward(request, response);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}

		}


	}
	
}
