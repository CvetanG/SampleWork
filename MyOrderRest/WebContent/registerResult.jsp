<%@ page import="bg.swift.order.rest.utils.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Result</title>
    <link rel="stylesheet" href="resources/css/normalize.css" />
	<link rel="stylesheet" href="resources/css/theme.css" />
    
</head>
<body>
<div class="login">
<% if (StringUtils.isNullOrEmpty(request.getParameter("error"))) { %>
   <h2 class="login-error">Registration Failed</h2>
<% } else { %>
   <h2 class="login-header">Registration Successful</h2>
<% }%>
</div>
</body>
</html>
