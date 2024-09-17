<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%@ page import="DAO.UserDB"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" class="DAO.UserDB" scope="session" />
<!DOCTYPE html>
<html lang="vn">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Update Information</title>
        <link href="assests/css/changeinfouser.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <div class="main-admin">
                <div class="information">
                    <h1>Update Your Information</h1>
                    <c:choose>
                        <c:when test="${user == null}">
                            <c:redirect url="index.jsp" />
                        </c:when>
                        <c:when test="${param.buttonChange == 'pass'}">
                            <form action="user" method="post" action="updatepass">
                                <input type="hidden" name="userId" value="${currentUser.userId}" />
                                <div class="profile-info">
                                    <label>Your current password:</label>
                                    <input type="text" name="password" value="${currentUser.password}" required/>
                                </div>
                                <div class="profile-info changepass">
                                    <label>Your new password:</label>
                                    <input type="text" name="password" required/>
                                </div>
                                <div class="profile-info">
                                    <label>Confirm new password:</label>
                                    <input type="text" name="password" required/>
                                </div>
                                <div class="change-info-button">
                                    <button type="submit" name="action" value="update">Update Information</button>
                                </div>
                            </form>
                        </c:when>
                        <c:when test="${param.buttonChange == 'email'}">
                            <form action="user" method="post" action="updateemail">
                                <input type="hidden" name="userId" value="${currentUser.userId}" />
                                <div class="profile-info">
                                    <label>Email:</label>
                                    <input type="email" name="email" value="${currentUser.email}" />
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
                    <% String error = request.getParameter("error");
                       if (error != null && error.equals("UpdateFailed")) { %>
                    <div class="error-message">
                        Update failed. Please try again.
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </body>
</html>
