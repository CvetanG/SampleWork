<%@page import="bg.swift.order.rest.entities.Product"%>
<%@page import="bg.swift.order.rest.dao.ProductDAO"%>
<%@ page import="bg.swift.order.rest.entities.User" %>
<%@ page import="bg.swift.order.rest.utils.ValidateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
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
        <h1>Update Product</h1>

        <form action="UpdateProdcutServlet" method="POST">
            <input type="hidden" name="product_id" value="<%= product.getId() %>"/>
			
			Category: <input name="category" placeholder="category name" value="<%= product.getCategory() %>" />
        <br />

        Manufacturer: <input name="manufacturer" placeholder="manufacturer name" value="<%= product.getManufacturer() %>"/>
        <br />
        Product: <input name="product" placeholder="product" value="<%= product.getProduct() %>"/>
        <br />
        Price: <input name="price" placeholder="price" value="<%= product.getPrice() %>"/>
        <br />
			
            <input type="submit" name="Update Product" value="Update Product">
        </form>
    <% } else { %>
        <h1>No Such Product Found</h1>
    <% } %>
<% } %>

</body>
</html>
