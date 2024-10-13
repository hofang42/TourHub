<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <style>

            .image-container {
                width: 60px; /* Set the width of the container */
                overflow: hidden; /* Hide any part of the image that goes outside the container */
                border-radius: 50%;
            }

            .image-container img {
                max-width: 100%; /* Ensure the image does not exceed the container's width */
                max-height: 100%; /* Ensure the image does not exceed the container's height */
                height: auto; /* Maintain the image's aspect ratio */
                width: auto; /* Maintain the image's aspect ratio */
            }

            .messenger-container {
                display: flex;
                height: 65vh;
            }

            .chat-section {
                width: 100%;
                display: flex;
                flex-direction: column;
                background-color: #ffffff; /* Màu nền trắng */
                border-radius: 10px;
            }

            .sidebar {
                width: 30%;
                background-color: #f7f7f7; /* Màu nền sáng */
                padding: 10px;
                color: #333;
                border-left: 1px solid #ddd; /* Biên màu xám nhạt */
            }

            .search input {
                width: 100%;
                padding: 10px;
                background-color: #ffebcc; /* Màu cam nhạt */
                border: none;
                color: #333;
                border-radius: 20px;
            }

            .chat-list {
                margin-top: 20px;
            }

            .chat-item {
                display: flex;
                align-items: center;
                padding: 10px;
                cursor: pointer;
                transition: background-color 0.3s;
                border-radius: 10px; /* Add this line for rounded corners */
            }

            .chat-item:hover {
                background-color: #ffebcc; /* Màu cam nhạt khi hover */
            }

            .chat-item.active {
                background-color: #ffcc80; /* Màu cam đậm hơn cho chat item đang hoạt động */
            }

            .chat-item img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                margin-right: 10px;
            }

            .chat-info {
                display: flex;
                flex-direction: column;
            }

            .chat-name {
                font-weight: bold;
                color: black; /* Màu cam cho tên người dùng */
            }

            .chat-status {
                color: #666; /* Màu chữ tối nhạt cho trạng thái */
            }

            .chat-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px;
                background-color: #ffffff;
                border-bottom: 1px solid #ddd;
            }

            .chat-info-header {
                display: flex;
                align-items: center;
            }

            .chat-info-header img {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                margin-right: 10px;
            }

            .chat-name-header {
                font-weight: bold;
                color: black;
            }

            .status-header {
                color: #666; /* Màu chữ tối nhạt cho trạng thái trong header */
                font-size: 14px;
            }

            .chat-tools i {
                color: #666; /* Màu chữ tối nhạt cho icon */
                font-size: 18px;
                margin-left: 15px;
            }

            .chat-messages {
                flex: 1;
                padding: 20px;
                overflow-y: auto;
                background-color: #ffffff; /* Màu nền trắng cho khu vực tin nhắn */

            }

            .message {
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 10px;
                max-width: 60%;
                width: 25%;
                display: block; /* Đổi từ inline-block sang block */
            }

            .message.received {
                background-color: orange;
                color: #333; /* Màu chữ tối */
                align-self: flex-start; /* Căn lề bên trái */
                margin-right: auto; /* Đẩy div của tin nhắn sang bên trái */
            }

            .message.sent {
                background-color: black; /* Màu cam đậm cho tin nhắn gửi */
                color: orange; /* Màu chữ trắng */
                align-self: flex-end; /* Căn lề bên phải */
                text-align: right; /* Đảm bảo văn bản căn bên phải */
                margin-left: auto; /* Đẩy div của tin nhắn sang bên phải */
            }

            .chat-input {
                display: flex;
                padding: 10px;
                background-color: white; /* Màu cam cho input */
                border-top: 1px solid #ddd;
            }

            .chat-input input {
                flex: 1;
                padding: 10px;
                border: none;
                background-color: #ffebcc; /* Màu cam nhạt cho input */
                color: #333;
                border-radius: 20px;
                margin-right: 10px;
            }

            .chat-input button {
                background-color: #ff5722; /* Màu cam đậm cho nút gửi */
                border: none;
                padding: 10px 15px;
                color: #fff;
                border-radius: 50%;
                cursor: pointer;
            }

        </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="home" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">TourHub</span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="user-profile.jsp">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">User Information</span>
                    </a>
                </li>
                <c:if test="${sessionScope.currentUser.role == 'Provider'}">
                    <li>
                        <a href="pending-bookings">
                            <i class='bx bxs-shopping-bag-alt' ></i>
                            <span class="text">Manage Booking</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.currentUser.role == 'Customer'}">
                    <li>
                        <a href="user-booking.jsp">
                            <i class='bx bxs-shopping-bag-alt' ></i>
                            <span class="text">My Booking</span>
                        </a>
                    </li>
                </c:if>
                <li  class="active">
                    <a href="user-chat.jsp" >
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Message</span>
                    </a>
                </li>    
                <c:if test="${sessionScope.currentUser.role == 'Provider' || sessionScope.currentUser.role == 'Admin'}">
                    <li class="">
                        <a href="${sessionScope.currentUser.role == 'Provider' ? '/Project_SWP/provider-analys' : 'admin-analysis.jsp'}">
                            <i class='bx bxs-dashboard' ></i>
                            <span class="text">Dashboard</span>
                        </a>
                    </li>   
                    <li class="dropdown-btn">
                        <a href="my-tour">
                            <i class='bx bxs-briefcase-alt' ></i>
                            <span class="text">My Tour</span>
                        </a>
                    </li>   
                    <li>
                        <a href="payment.jsp">
                            <i class='bx bxs-credit-card'></i>
                            <span class="text">Payment</span>
                        </a>
                    </li> 
                    <li>
                        <a href="discount">
                            <i class='bx bxs-discount'></i>
                            <span class="text">Manage Discounts</span>
                        </a>
                    </li>
                </c:if>                
                <c:if test="${sessionScope.currentUser.role == 'Customer'}">
                    <li>
                        <a href="reviewtour.jsp">
                            <i class='bx bxs-star'></i>
                            <span class="text">Review Tours</span>
                        </a>
                    </li>
                </c:if>
                <%-- <li>
                    <a href="#">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">Analytics</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Team</span>
                    </a>
                </li> --%>
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
                    <!-- <span class="num">8</span> -->
                </a>
                <div class="image-container">
                    <img src="assests/images/avatar.jpg" alt="User Avatar" class="avatar">
                </div>

            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="table-data">
                    <div class="order">
                        <c:choose>
                            <c:when test="${currentUser == null}">
                                <c:redirect url="index.jsp" />
                            </c:when>
                            <c:otherwise>
                                <div class="messenger-container">
                                    <div class="chat-section">
                                        <div class="chat-header">
                                            <div class="chat-info-header">
                                                <img src="assests/images/1.jpg" alt="Avatar">
                                                <span class="chat-name-header">Admin</span>
                                            </div>
                                        </div>
                                        <div class="chat-messages">
                                            <div class="message received">
                                                <p>Bạn có vấn đề gì cần tư vấn không ?</p>
                                            </div>
                                            <div class="message sent">
                                                <p>Không gì cả, cảm ơn.</p>
                                            </div>
                                        </div>
                                        <div class="chat-input">
                                            <input type="text" placeholder="Aa">
                                            <button>Gửi</button>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script src="assests/js/script_profile.js"></script>
    </body>
</html>





