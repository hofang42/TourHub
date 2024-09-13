<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome, <%= user.getUsername() %>!</h1>
    <p>Your email: <%= user.getEmail() %></p>
    <a href="logout">Logout</a>
</body>
</html>