<%-- 
    Document   : test.jsp
    Created on : Oct 22, 2024, 12:27:59 AM
    Author     : NOMNOM
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="model.User" %>
<%@ page import="DataAccess.UserDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        /* Style the form */
form {
    display: flex;
    flex-direction: column; /* Stack elements vertically */
    max-width: 400px; /* Set a maximum width for the form */
    margin: 20px auto; /* Center the form horizontally */
    padding: 20px; /* Add padding inside the form */
    border: 1px solid #ccc; /* Add a light border */
    border-radius: 8px; /* Rounded corners */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Shadow effect */
    background-color: #f9f9f9; /* Light background color */
}

/* Style the labels */
label {
    margin-bottom: 10px; /* Space below the label */
    font-weight: bold; /* Bold text */
}

/* Style the file input */
input[type="file"] {
    margin-bottom: 20px; /* Space below the input */
    padding: 10px; /* Add padding */
    border: 1px solid #ccc; /* Light border */
    border-radius: 4px; /* Rounded corners */
    font-size: 14px; /* Font size */
}

/* Style the submit button */
button {
    padding: 10px 20px; /* Vertical and horizontal padding */
    border: none; /* Remove default border */
    border-radius: 4px; /* Rounded corners */
    background-color: #4CAF50; /* Green background */
    color: white; /* White text color */
    font-size: 16px; /* Font size */
    cursor: pointer; /* Pointer cursor on hover */
    transition: background-color 0.3s; /* Transition effect */
}

/* Change button color on hover */
button:hover {
    background-color: #45a049; /* Darker green on hover */
}

/* Optional: Responsive design */
@media (max-width: 480px) {
    form {
        width: 90%; /* Full width on small screens */
    }
}

        </style>
        
    <body>
        <form action="UploadAvatarServlet" method="post" enctype="multipart/form-data">
            <label>Upload Avatar:</label>
            <input type="file" name="avatar" accept="image/*" required>
            <button type="submit">Upload</button>
        </form>

        <div class="image-container">
            <c:choose>
                <c:when test="${not empty sessionScope.currentUser.avatar}">
                    <img src="${pageContext.request.contextPath}/${sessionScope.currentUser.avatar}" 
                         alt="User Avatar" class="avatar">
                </c:when>
                <c:otherwise>
                    <img src="assests/images/default-avatar.jpg" alt="Default Avatar" class="avatar">
                </c:otherwise>
            </c:choose>
        </div>

    </body>
</html>
