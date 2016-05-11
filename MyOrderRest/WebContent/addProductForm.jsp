<%@ page import="bg.swift.order.rest.utils.StringUtils"%>
<%@ page import="bg.swift.order.rest.entities.User"%>
<%@ page import="bg.swift.order.rest.utils.ValidateUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Add Product Form</title>
<link rel="stylesheet" href="resources/css/normalize.css" />
<link rel="stylesheet" href="resources/css/theme.css" />

</head>
<body>
	<%
		User user = (User) session.getAttribute("user");

		if (ValidateUtils.userIsNull(user)) {
	%>
	<div class="login">
		<p />
		<h2 class="login-header">
			<a href="loginForm.jsp">Login first</a>
		</h2>
	</div>
	<%
		} else {
	%>
	<div class="login">
		<h2 class="login-header">Add New Product</h2>
		<div class="login-triangle"></div>
		<legend class="login-header">Product details</legend>

		<form class="login-container" action="AddNewProductServlet"
			method="POST">
			<p>
				<label for="category" class="inputlabel">Category: </label> <input
					name="category" type="text" />
			</p>

			<p>
				<label for="manufacturer" class="inputlabel">Manufacturer: </label>
				<input name="manufacturer" type="text" />
			</p>

			<p>
				<label for="product" class="inputlabel">Product: </label> <input
					name="product" type="text" />
			</p>

			<p>
				<label for="price" class="inputlabel">Price: </label> <input
					name="price" type="text" />
			</p>

			<div class="inputField" id="submitField">
				<input type="submit" name="Add New Product" value="Add New Product" />
			</div>
		</form>

	</div>
	<%
		}
	%>

</body>
</html>
