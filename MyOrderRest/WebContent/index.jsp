<%@ page import="bg.swift.order.rest.entities.User" %>
<%@ page import="bg.swift.order.rest.entities.LoginStatus" %>
<%@ page import="bg.swift.order.rest.entities.Order" %>
<%@ page import="bg.swift.order.rest.entities.Product" %>
<%@ page import="bg.swift.order.rest.utils.DateUtils" %>
<%@ page import="bg.swift.order.rest.utils.ValidateUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order_Rest</title>
<link rel="stylesheet" href="resources/css/normalize.css" />
<link rel="stylesheet" href="resources/css/theme.css" />
</head>
<body>
<div class="login">
	<h2 class="login-header">Order_Rest</h2>
<%
    User user = (User) session.getAttribute("user");

    if (ValidateUtils.userIsNull(user)) {
%>
    <p >
    	<h2 class="login-header"><a href="loginForm.jsp">Login first</a> </h2>
    </p>
<% } else {
    user.updateOrders();
%>
<p >
	<h2 class="login-header">Welcome, <%= user.getUsername() %>!</h2>
</p>
    <% if (user.getOrders().isEmpty()) { %>
    <p>
        <h2 class="login-header">No current orders! <br />Please add some.</h2>
    </p>
    <% } else { %>
        <h2 class="login-header">Here is a list of your orders: </h2>
        <table>
	        <tr>
	        	<th>Status</th>
	        	<th>Category</th>
	        	<th>Manufacturer</th>
	        	<th>Product</th>
	        	<th>Price</th>
	        	<th>Pcs</th>
	        	<th>CreatedAt</th>
	        	<th>View</th>
	        </tr>	
	        <% for (Order currentOrder : user.getOrders()) { %>
	        <tr>
	            <td> <%= currentOrder.getStatus() %> </td>
	            <td> <%= currentOrder.getProduct().getCategory() %> </td>
	            <td> <%= currentOrder.getProduct().getManufacturer() %> </td>
	            <td> <%= currentOrder.getProduct().getProduct() %> </td>
	            <td> <%= currentOrder.getProduct().getPrice() %> </td>
	            <td> <%= currentOrder.getPcs() %> </td>
	            <td> <%= DateUtils.printDate(currentOrder.getCreatedAt()) %> </td>
	            <td> <a href="viewOrderDetails.jsp?id=<%= currentOrder.getId() %>">View</a> </td>
	          <tr>
	            <br/>
	        <% } %>
	    <% } %>
		</table>
    <p>
    	<h2 class="login-header"><a href="addOrderForm.jsp">Add Orders Form</a></h2>
    </p>
    <p>
    	<h2 class="login-header"><a href="addProductForm.jsp">Add Product Form</a></h2>
    </p>
    <p>
    	<h2 class="login-header"><a href="LogoutServlet">Logout</a></h2>
    </p>
<% } %>

</div>
</body>
</html>