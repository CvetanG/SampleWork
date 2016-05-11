<%@ page import="bg.swift.order.rest.utils.StringUtils" %>
<%@ page import="bg.swift.order.rest.entities.User" %>
<%@ page import="bg.swift.order.rest.utils.ValidateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Order Form</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");

    if (ValidateUtils.userIsNull(user)) {
%>
    <a href="loginForm.jsp">Login first</a>
<% } else { %>

    <h1>Add New Order</h1>

    <form action="AddNewOrderServlet" method="POST">

        Product#: <input name="product" placeholder="#" />
        <br />
        Pcs: <input name="pcs" placeholder="#" />
        <br />
        
        <input type="submit" name="Add New Order" value="Add New Order" />
    </form>

<% } %>

</body>
</html>
