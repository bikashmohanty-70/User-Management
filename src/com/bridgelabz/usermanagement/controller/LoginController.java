package com.bridgelabz.usermanagement.controller;
/**
 * @author Bikash MOhanty
 * @since 27th Dec 2019
 * @version 1.0
 * 
 * purpose: To validate user's credentials with database
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bridgelabz.usermanagement.model.UserModel;
import com.bridgelabz.usermanagement.repository.IUserDAO;
import com.bridgelabz.usermanagement.repository.UserDao;
import com.bridgelabz.usermanagement.service.IService;
import com.bridgelabz.usermanagement.service.UserServiceImplementation;


/**
 * Servlet implementation class LoginModule
 */
//@WebServlet("/LoginModule")
public class LoginController extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	static IService service = new UserServiceImplementation();
	static IUserDAO connectivity = new UserDao();
	static UserModel userModel = new UserModel();
	static PrintWriter printWriter;
	static Connection connectionObj = connectivity.connect();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		userModel.setUsername(request.getParameter("username"));
		userModel.setPassword(request.getParameter("password"));

		try 
		{
			printWriter = response.getWriter();
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}

		String  firstname = null;
		String  lastname = null;
		String  mailId = null;
		String  contact = null;
		response.setContentType("text/html");

		if(service.userValidation(userModel))	//validateUser() validates username and Password of Users and allow them to login
		{
			
			//Creating Session Object by taking Username
			
			HttpSession session = request.getSession();
			session.setAttribute("uname", userModel.getUsername());
			try 
			{
				ResultSet resultset = service.readAllData(userModel.getUsername(), userModel.getPassword());
				while(resultset.next())
				{
					firstname = resultset.getString(1); 
					lastname = resultset.getString(2); 
					mailId = resultset.getString(3); 
					contact = resultset.getString(5); 
				}
				String name = firstname+" "+lastname;
				request.getSession().setAttribute("mail", mailId);
				request.getSession().setAttribute("name", name);
				request.getSession().setAttribute("phno", contact);
				
				response.sendRedirect("HomePage.jsp");
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
			printWriter.println("swal('Oooopsss...!', 'Wrong Credentials. Try Again', 'error');");
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

	}
}
