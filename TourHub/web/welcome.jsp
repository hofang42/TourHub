<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
</head>
<body>

    <jsp:useBean id="user" scope="session" class="model.User" />
    <%
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    
    <h1>Welcome, <jsp:getProperty name="user" property="username" />!</h1>
    <p>Your email: <jsp:getProperty name="user" property="email" /></p>
    <a href="logout">Logout</a>

</body>
</html>
