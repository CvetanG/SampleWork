<%@ page import="bg.swift.order.rest.utils.StringUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Login Form</title>
<link rel="stylesheet" href="resources/css/normalize.css" />
<link rel="stylesheet" href="resources/css/theme.css" />
</head>
<body>

	<div class="login">
		<h2 class="login-header">Login</h2>

		<div class="login-triangle"></div>
		<legend class="login-header">Login</legend>

		<form class="login-container" action="LoginServlet" method="POST">

			<p>
				<label for="username" class="inputlabel">Username: </label> <input
					type="text" name="username"
					value="<%=StringUtils.setNotNull(request.getParameter("username"))%>" />

			</p>
			<p>
				<label for="username" class="inputlabel">Password: </label> <input
					type="password" name="password" />
			</p>
			<div class="inputField" id="submitField">
				<input id="submitBtn" type="submit" name="Login" value="Login" />
			</div>
		</form>
	</div>
</body>
</html>
