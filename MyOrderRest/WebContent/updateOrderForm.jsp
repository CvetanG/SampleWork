<%@ page import="bg.swift.order.rest.entities.User" %>
<%@ page import="bg.swift.order.rest.dao.OrderDAO" %>
<%@ page import="bg.swift.order.rest.entities.Order" %>
<%@ page import="bg.swift.order.rest.utils.DateUtils" %>
<%@ page import="bg.swift.order.rest.utils.ValidateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Order</title>
</head>
<body>
<%
    User loggedUser = (User) session.getAttribute("user");

    if (ValidateUtils.userIsNull(loggedUser)) {
%>
    <a href="loginForm.jsp">Login first</a>
<% } else {
    OrderDAO ordersDAO = new OrderDAO();

    String orderId = request.getParameter("id_order");

    Order order = ordersDAO.findOrderByIdAndUser(orderId, loggedUser);
    if (order != null) {
%>
        <h1>Update Order</h1>

        <form action="UpdateOrderServlet" method="POST">
            <input type="hidden" name="order_id" value="<%= order.getId() %>"/>

            Product#: <input name="product" placeholder="#" />
	        <br />
	        Pcs: <input name="pcs" placeholder="#" />
	        <br />
	        <label for="status" class="inputlabel">Status: </label>
				<select name="status">
					<option value="NEW">NEW</option>
					<option value="UPDATED">UPDATED</option>
					<option value="PROCESSED">PROCESSED</option>
					<option value="SEND">SEND</option>
					<option value="RECEIVED">RECEIVED</option>
					<option value="CANCELED">CANCELED</option>
				</select>
	        <br />

            <input type="submit" name="Update Task" value="Update Task">
        </form>
    <% } else { %>
        <h1>No Such Order Found</h1>
    <% } %>
<% } %>

</body>
</html>
