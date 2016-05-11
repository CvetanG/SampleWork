<%@ page import="bg.swift.order.rest.entities.User" %>
<%@ page import="bg.swift.order.rest.entities.Order" %>
<%@ page import="bg.swift.order.rest.dao.OrderDAO" %>
<%@ page import="bg.swift.order.rest.utils.DateUtils" %>
<%@ page import="bg.swift.order.rest.utils.ValidateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Order Details</title>
</head>
<body>
<%
    User loggedUser = (User) session.getAttribute("user");

    if (ValidateUtils.userIsNull(loggedUser)) {
%>
    <a href="loginForm.jsp">Login first</a>
<% } else {
    OrderDAO ordersDAO = new OrderDAO();

    String orderId = request.getParameter("id");

    Order order = ordersDAO.findOrderByIdAndUser(orderId, loggedUser);
    if (order != null) {
%>
        <h1>View Order Details</h1>

        Status: <%= order.getStatus() %>
        <br/>
        Product: <%= order.getProduct() %>
        <br/>
        Pcs: <%= order.getPcs() %>
        <br/>
        <a href="updateProductForm.jsp?id=<%= order.getProduct().getId()%>">Update Product</a>
        <br />
        <a href="updateOrderForm.jsp?id=<%= order.getId()%>">Update Order</a>
    <% } else { %>
        <h1>No Such Order Found</h1>
    <% } %>
<% } %>
</body>
</html>
