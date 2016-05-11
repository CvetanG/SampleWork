<%@ page import="bg.swift.order.rest.utils.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Order Result</title>
    <link rel="stylesheet" href="resources/css/normalize.css" />
	<link rel="stylesheet" href="resources/css/theme.css" />
</head>
<body>
	<div class="login">
		<% if (StringUtils.isNullOrEmpty(request.getParameter("error"))) { %>
		<h2 class="login-header">Update Order Successful</h2>
		<% } else { %>
		<h2 class="login-error">Update Order Failed</h2>
		<% } %>
		<p />
		<h2 class="login-header"><a href="index.jsp"> Go to index page</a> </h2>
	</div>
</body>
</html>
