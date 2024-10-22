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


    <body>
        <!-- Button to add a tour to the wishlist -->
        <form action="wishlist" method="post" onsubmit="return handleFormSubmit();">
            <input type="hidden" name="wishId" value="${wishlist.wish_Id}" />
            <input type="hidden" name="action" value="delete" />
            <button type="submit" class="action-link cancel" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
        </form>
        <script>
            function handleFormSubmit() {
                // Optional: Perform any additional logic here if needed.

                // After the form submission, go back to the previous page
                window.history.back();
                return false; // Prevent the default form submission
            }
        </script>

    </body>
</html>
