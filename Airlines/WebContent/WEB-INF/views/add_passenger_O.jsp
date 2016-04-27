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

	<div class="container">
		<div class="title">Add a passenger</div>
		<fieldset>
			<legend>Passenger details</legend>

			<form action="AddPassenger" method="post">
			
			<div class="inputField">
				<label for="first-name" class="inputlabel">First name: </label>
				<input name="first-name" type="text" />
			</div>
			
			<div class="inputField">
				<label for="last-name" class="inputlabel">Last name: </label>
				<input name="last-name" type="text" />
			</div>
			
			<div class="inputField">
				<label for="dob" class="inputlabel">Date of birth </label>
				<input name="dob" type="text" />
			</div>
			
			<div class="inputField">
				<label for="first-name" class="inputlabel">Gender: </label>
				<select name="gender">
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</div>

		</fieldset>
		<div class="inputField" id="submitField">
			<input id="submitBtn" type="submit" value="Add new passenger" />
		</div>
			</form>

	</div>

</body>
</html>