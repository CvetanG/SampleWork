<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register User</title>
    <link rel="stylesheet" href="resources/css/normalize.css" />
	<link rel="stylesheet" href="resources/css/theme.css" />
</head>
<body>
	<div class="login">
		<h2 class="login-header">Register New User</h2>

		<div class="login-triangle"></div>
		<legend class="login-header">Register New User</legend>
	
		
		<form class="login-container" action="RegisterServlet" method="POST">
			
			<p>
				<label for="username" class="inputlabel">Username: </label>
				<input type="text" name="username" />
			</p>
			
			<p>
				<label for="password" class="inputlabel">Password: </label>
				<input type="password" name="password" />
			</p>
			
			<p>
				<label for="password" class="inputlabel">Confirm Password: </label>
				<input type="password" name="cpassword" />
			</p>
			
			<p>
		    <input type="submit" name="Register" value="Register" />
			</p>
			
		</form>
	</div>


</body>
</html>