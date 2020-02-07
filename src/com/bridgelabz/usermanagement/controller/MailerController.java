package com.bridgelabz.usermanagement.controller;

/**
 * @author Bikash Mohanty
 * @since 3rd Jan 2020
 * @Version 1.0
 * 
 * purpose: To send Mail to USer's Mail ID using java.mail API
 */
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailerController 
{
	public static void sendMail(String recepients) 
	{
		System.out.println("Preparing to send Email...");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true"); // If true, attempt to authenticate the user using the AUTH command.
													// Defaults to false.
		properties.put("mail.smtp.starttls.enable", "true"); // If true, enables the use of the STARTTLS command (if
																// supported by the server) to switch the connection to
																// a TLS-protected connection before issuing any login
																// commands. If the server does not support STARTTLS,
																// the connection continues without the use of TLS.
		
		properties.put("mail.smtp.host", "smtp.gmail.com"); 	// The SMTP server to connect to.
		
		properties.put("mail.smtp.port", "587"); 				/*The SMTP server port to connect to, if the connect() method doesn't		
																explicitly specify one. Defaults to 25.*/

		String myEmail = "vicky.riario.winchester@gmail.com"; 	// Sender Id
		String password = "Biki12@3"; 							// Sender's Password

		/**
		 * Creating java Mail Session
		 */

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(myEmail, password); // Authenticating Sender's MailID and Password
			}
		});

		Message message = prepareMessage(session, myEmail, recepients);
		try 
		{
			Transport.send(message);
			System.out.println("Message Sent");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param session   --> Session Object of JAVA MAIL
	 * @param myEmail   --> Sender's Email
	 * @param recepient --> Recipient Address
	 * @return message type Object
	 * 
	 * @purpose: It will check the session object for mail, takes SENDER'S MAIL & RECEPIENT'S ADDRESS(MAIL)  as String
	 * 			 
	 * 			MimeMessage: Class MimeMessage. This class represents a MIME style email message. It implements the Message abstract class and the MimePart interface.
	 * 					  	 Clients wanting to create new MIME style messages will instantiate an empty MimeMessage object and then fill it with appropriate attributes and content.
	 */

	private static Message prepareMessage(Session session, String myEmail, String recepient) {
		Message message = new MimeMessage(session);
		try 
		{
			message.setFrom(new InternetAddress(myEmail));	// Taking Mail Address of the Sender 
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient)); // Set the specified recipient type to the given addresses.
			message.setSubject("Change Password");
//			message.setText("Hey, \n Click on the link to Reset Your Password.");
			message.setContent(
					"<p>Hey there,<br>Kindly Click on the below link to Reset Your Password.</p><br><h3>Reset Password</h3><br><h4><a href='http://localhost:8080/LoginDemo/ResetPassword.jsp'>Local Users Click Here</a></h4><br><h4><a href='http://192.168.137.1:8080/LoginDemo/ResetPassword.jsp'>If Connected to Server, Click Here</a></h4>",
					"text/html");	//Sending message in HTML format
			return message;
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
