package com.bridgelabz.usermanagement.controller;
/**
 * @author Bikash Mohanty
 * @since 3rd January 2020
 * @version 1.0
 * 
 * purpose: Forgot Password Implementation
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ForgotPassword
 */
//@WebServlet("/ForgotPassword")
public class ForgotPasswordController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter printWriter = null;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mailId = request.getParameter("email");
		ServletContext context = getServletContext();
		context.setAttribute("email", mailId);
		
		
		//Creating Session for Forgot Password
		
		
		HttpSession sessionForgotPassword = request.getSession();
		sessionForgotPassword.setAttribute("UserMail", mailId);
		try 
		{
			printWriter = response.getWriter();
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
		
		/**
		 * Calling Mailer Class Method by attaching Receiver's Mail id
		 */
		MailerController.sendMail(mailId);
		printWriter.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
		printWriter.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
		printWriter.println("<script>");
		printWriter.println("$(document).ready(function(){");
		printWriter.println("swal('Thank You!', 'Check Your Email To Reset Your Password.', 'success');");
		printWriter.println("});");
		printWriter.println("</script>");
		RequestDispatcher dispatcher = request.getRequestDispatcher("n.jsp");
		try 
		{
			dispatcher.include(request, response);	//Including Current Request and Response and dispatching to another Resource 
//			response.sendRedirect("Index.jsp");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
