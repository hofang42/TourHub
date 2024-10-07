<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
</head>
<body>


    <jsp:useBean id="currentUser" class="model.User" scope="session" />
    <%
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <p>Your email:${currentUser.email}</p>
    <a href="logout">Logout</a>

</body>
</html>
