<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>World Adventures Airlines</title>

<link rel="stylesheet" href="resources/css/normalize.css" />
<link rel="stylesheet" href="resources/css/theme.css" />

</head>
<body>

	<div class="login">
		<p />
		<h2 class="login-header">Add a passenger</h2>
	
	<%
		if(request.getAttribute("errors") != null) {
	%>
		<fieldset class="login-error">
			<h2>Errors:</h2>
			<p></p>
			<ul>
				<% if(request.getAttribute("firstname_error") != null)  { %>
					<li class="error">First name error</li>
				<% } %>
				
				<% if(request.getAttribute("lastname_error") != null)  { %>
					<li class="error">Last name error</li>
				<% } %>
				
				<% if(request.getAttribute("date_format_error") != null)  { %>
					<li class="error">Date of birth invalid</li>
				<% } %>
			</ul>
		</fieldset>
	<%
		}
	%>
	
	<div class="login-triangle"></div>
			<legend class="login-header">Passenger details</legend>

			<form class="login-container" action="AddPassenger" method="post">
			
			<p>
				<label for="first-name" class="inputlabel">First name: </label>
				<input name="first-name" type="text" value="<%= request.getAttribute("first_name") %>" />
			</p>
			
			<p>
				<label for="last-name" class="inputlabel">Last name: </label>
				<input name="last-name" type="text" value="<%= request.getAttribute("last_name") %>" />
			</p>
			
			<p>
				<label for="dob" class="inputlabel">Date of birth </label>
				<input name="dob" type="date" placeholder="mm/dd/yyyy" value="<%= request.getAttribute("dob") %>"/>
			</p>
			
			<p>
				<label for="first-name" class="inputlabel">Gender: </label>
				<select name="gender">
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</p>

		<div class="inputField" id="submitField">
			<input id="submitBtn" type="submit" value="Add new passenger" />
		</div>
			</form>

	</div>

</body>
</html>