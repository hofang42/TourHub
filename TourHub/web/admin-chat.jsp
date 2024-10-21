<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="model.Message"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard Admin</title>
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="assests/css/style_profile.css"> 
        <link rel="stylesheet" href="assests/css/chat.css"> 
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <section id="sidebar">
            <a href="home" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">TourHub</span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="dashboard">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="manage.jsp">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">System Management</span>
                    </a>
                </li>
                <li class="active">
                    <a href="admin-chat.jsp">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Message</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu">
                <li>
                    <a href="#">
                        <i class='bx bxs-cog' ></i>
                        <span class="text">Settings</span>
                    </a>
                </li>
                <li>
                    <a href="logout" class="logout">
                        <i class='bx bxs-log-out-circle' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->



        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <a href="#" class="nav-link"></a>
                <form action="#">
                    <div class="form-input">
                        <input type="search" placeholder="Searching for tour...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden>
                <label for="switch-mode" class="switch-mode"></label>
                <a href="#" class="notification">
                    <i class='bx bxs-bell' ></i>
                </a>

            </nav>
            <main>
                <div class="table-data">
                    <div class="order">
                        <c:choose>
                            <c:when test="${currentUser == null}">
                                <c:redirect url="home" />
                            </c:when>
                            <c:otherwise>
                                <div class="messenger-container">
                                    <div class="chat-section">
                                        <div id="messageContainer" class="chat-messages">
                                            <!-- Tin nhắn sẽ được hiển thị ở đây -->
                                        </div>
                                        <div id="messageInputContainer" class="chat-input">
                                            <input type="text" id="messageInput" placeholder="Nhập tin nhắn...">
                                            <button id="sendButton" onclick="sendMessage()">Gửi</button>
                                        </div>
                                    </div>

                                    <div class="sidebar">
                                        <div class="chat-list">
                                            <div id="activeUserList">
                                                <!-- Danh sách người dùng đang nhắn tin sẽ được thêm vào đây -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </main>
        </section>
        <script src="assests/js/script_profile.js"></script>
        <script src="assests/js/admin-chat.js"></script>
        <script>
                                                // Tự động tải lại tin nhắn mỗi 3 giây
                                                setInterval(function () {
                                                    fetchChatMessages(user1);
                                                }, 3000);
        </script>
    </body>
</html>





