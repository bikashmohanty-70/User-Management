<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">

<title>Log_In_Page</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<!-- <div class="container para"> -->
	<div class="innertr">
		<h1>Photography</h1>
		<p>Photography is the art, application and practice of creating
			durable images by recording light or other electromagnetic radiation,
			either electronically by means of an image sensor, or chemically by
			means of a light-sensitive material such as photographic film.</p>
	</div>

	<!-- </div> -->
	<form action="LoginModule" method="post">
		<div class="login-box">

			<h1>Login</h1>
			<div class="textbox">
				<i class="fa fa-user" aria-hidden="true"></i> <input type="text"
					name="username" value="" placeholder="Username" required="required">
			</div>

			<div class="textbox">
				<i class="fa fa-lock" aria-hidden="true"></i> <input type="password"
					name="password" value="" placeholder="Password" required="required">
			</div>

			<input type="submit" class="btn" name="" value="Sign in">

			<div class="container">
				<div class="forpSign">
					Don't have an account? <a href="#" class="ml-2" id="myBtn">Sign
						Up</a>
				</div>

				<div class="forpSign forgot">
					<a href="ForgotPassword.jsp">Forgot your password?</a>
				</div>

			</div>

		</div>

	</form>


	<form action="RegisterationModule" method="post">
		<div id="myModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">

				<span class="close">&times;</span>
				<h1>Create Account</h1>

				<div class="textbox1">
					<i class="fa fa-user" aria-hidden="true"></i> <input type="text"
						name="firstname" value="" placeholder="First Name"
						required="required" /> <i class="fa fa-user" aria-hidden="true"></i>
					<input type="text" name="lastname" value="" placeholder="Last Name"
						required="required">
				</div>

				<div class="textbox1">
					<i class="fa fa-envelope" aria-hidden="true"></i> <input
						type="text" name="mail" value="" placeholder="E-mail"
						required="required">
				</div>

				<div class="textbox1">
					<i class="fa fa-user" aria-hidden="true"></i> <input type="text"
						name="username" value="" placeholder="Username"
						required="required" /> <i class="fa fa-lock" aria-hidden="true"></i>
					<input type="password" name="password" value=""
						placeholder="Password" required="required">
				</div>

				<div class="textbox1">
					<i class="fa fa-building" aria-hidden="true"></i> <input
						type="text" name="city" value="" placeholder="City"
						required="required">
				</div>
				<div class="textbox1">
					<i class="fa fa-phone" aria-hidden="true"></i> <input type="tel"
						name="contact" value="" placeholder="Contact" size="10"
						required="required">
				</div>

				<input type="submit" class="btnS" name="" value="Sign Up">
				<!-- <button type="submit" class="btn" name="" value="Sign Up" id="myBtn">Sign Up</button> -->
				<span style="color: red; align-content: center;">${errorMessage1}</span>
				<span style="color: red; align-content: center;">${errorMessage2}</span>
				<span style="color: red; align-content: center;">${errorMessage3}</span>

			</div>

		</div>
	</form>

	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>
