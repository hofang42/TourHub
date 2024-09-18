<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%@ page import="dao.UserDB"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="currentUser" class="model.User" scope="session" />
<!DOCTYPE html>
<html lang="vn">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Update Information</title>
        <link href="assests/css/changeinfouser.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <div class="main-admin">
                <div class="information">
                    <h1>Update Your Information</h1>
                    <c:choose>
                        <c:when test="${currentUser == null}">
                            <c:redirect url="index.jsp" />
                        </c:when>
                        <c:when test="${param.buttonChange == 'pass'}">
                            <form action="user" method="post" onsubmit="return validatePassword();">
                                <input type="hidden" name="action" value="updatepass" />
                                <input type="hidden" name="userId" value="${currentUser.userId}" />

                                <div class="profile-info">
                                    <label>Your current password:</label>
                                    <div class="password-wrapper">
                                        <input type="password" name="password" id="currentPassword" required/>
                                        <i class="fa fa-eye" id="toggleCurrentPassword" onclick="togglePassword('currentPassword', 'toggleCurrentPassword')"></i>
                                    </div>
                                </div>

                                <div class="profile-info changepass">
                                    <label>Your new password:</label>
                                    <div class="password-wrapper">
                                        <input type="password" name="newPassword" id="newPassword" required/>
                                        <i class="fa fa-eye" id="toggleNewPassword" onclick="togglePassword('newPassword', 'toggleNewPassword')"></i>
                                    </div>
                                </div>

                                <div class="profile-info">
                                    <label>Confirm new password:</label>
                                    <div class="password-wrapper">
                                        <input type="password" name="confirmPassword" id="confirmPassword" required/>
                                        <i class="fa fa-eye" id="toggleConfirmPassword" onclick="togglePassword('confirmPassword', 'toggleConfirmPassword')"></i>
                                    </div>
                                </div>

                                <div class="change-info-button">
                                    <button type="submit" name="action" value="update">Update Information</button>
                                </div>
                            </form>
                        </c:when>
                        <c:when test="${param.buttonChange == 'email'}">
                            <form action="user" method="post" >

                                <input type="hidden" name="action" value="updateemail" />
                                <input type="hidden" name="userId" value="${currentUser.userId}" />
                                <div class="profile-info">
                                    <label>Email:</label>
                                    <input type="email" name="email"/>
                                </div>
                                <div class="change-info-button">
                                    <button type="submit" name="action" value="update">Update Information</button>
                                </div>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="user" method="post" action="update">
                                <input type="hidden" name="userId" value="${currentUser.userId}" />
                                <input type="hidden" name="password" value="${currentUser.password}" />
                                <input type="hidden" name="email" value="${currentUser.email}" />
                                <div class="row">
                                    <div class="profile-info">
                                        <label>Username:</label>
                                        <input type="text" name="username" value="${currentUser.username}" />
                                    </div>
                                    <div class="profile-info">
                                        <label>Phone Number:</label>
                                        <input type="text" name="phone" value="${currentUser.phone}" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="profile-info">
                                        <label>First Name:</label>
                                        <input type="text" name="firstName" value="${currentUser.firstName}" />
                                    </div>
                                    <div class="profile-info">
                                        <label>Last Name:</label>
                                        <input type="text" name="lastName" value="${currentUser.lastName}" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="profile-info">
                                        <label>Address:</label>
                                        <input type="text" name="address" value="${currentUser.address}" />
                                    </div>
                                </div>
                                <div class="change-info-button">
                                    <button type="submit" name="action" value="update">Update Information</button>
                                </div>
                            </form>
                        </c:otherwise>
                    </c:choose>
                    <%-- Kiểm tra và hiển thị thông báo lỗi nếu có --%>
                    <% String error = (String) request.getParameter("error");
                       if (error != null && error.equals("UpdateFailed")) { %>
                    <div class="error-message">
                        Update failed. Please try again.
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
      
        <script src="assests/js/script_profile.js"></script>
    </body>
</html>
