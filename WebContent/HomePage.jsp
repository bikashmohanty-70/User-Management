<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/StyleHomePage.css">
<title>Insert title here</title>
</head>
<body>

	<!-- Using Java Codes -->
	<%
		if (session.getAttribute("uname") == null)
			response.sendRedirect("loginAnonymous.jsp");
	%>

	<div class="navbar">
		<a class="active" href="#"><i class="fa fa-fw fa-home"></i> Home</a> <a
			href="#"><i class="fa fa-fw fa-search"></i> Search</a> <a href="#"><i
			class="fa fa-fw fa-envelope"></i> Contact</a> <a href="#"><i
			class="fa fa-fw fa-user"></i>About</a>
	</div>
	<form action="LogoutPage" method="post">
		<div class="card">
			<img src="img/tyrion.jpg" alt="John" style="width: 100%">
			<h1>${name}</h1>
			<p class="title">${mail}</p>
			<p>${phno}</p>
			<div style="margin: 24px 0;">
				<a href="#"><i class="fa fa-dribbble"></i></a> <a href="#"><i
					class="fa fa-twitter"></i></a> <a href="#"><i
					class="fa fa-linkedin"></i></a> <a href="#"><i
					class="fa fa-facebook"></i></a>
			</div>
			<p>
				<button type="submit">Logout</button>
			</p>
		</div>
	</form>
</body>
</html>