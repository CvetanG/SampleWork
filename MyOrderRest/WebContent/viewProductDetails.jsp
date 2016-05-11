<%@ page import="bg.swift.order.rest.entities.User" %>
<%@ page import="bg.swift.order.rest.entities.Product" %>
<%@ page import="bg.swift.order.rest.dao.ProductDAO" %>
<%@ page import="bg.swift.order.rest.utils.DateUtils" %>
<%@ page import="bg.swift.order.rest.utils.ValidateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Product Details</title>
</head>
<body>
<%
    User loggedUser = (User) session.getAttribute("user");

    if (ValidateUtils.userIsNull(loggedUser)) {
%>
    <a href="loginForm.jsp">Login first</a>
<% } else {
    ProductDAO productsDAO = new ProductDAO();

    String productId_row = request.getParameter("id_product");
    Integer productId = Integer.parseInt(productId_row);

    Product product = productsDAO.getById(productId);
    if (product != null) {
%>
        <h1>View Product Details</h1>

        Category: <%= product.getCategory()%>
        <br/>

        Manufacturer: <%= product.getManufacturer() %>
        <br/>
        Product: <%= product.getProduct() %>
        <br/>
        Price: <%= product.getPrice() %>
        <br/>
        <a href="updateProductForm.jsp?id=<%= product.getId()%>">Update Product</a>
    <% } else { %>
        <h1>No Such Product Found</h1>
    <% } %>
<% } %>
</body>
</html>
