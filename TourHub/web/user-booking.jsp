<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User"%> <%@ page import="DataAccess.UserDB"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="currentUser" class="model.User" scope="session" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Boxicons -->
        <link
            href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css"
            rel="stylesheet"
            />
        <!-- My CSS -->
        <link rel="stylesheet" href="assests/css/style_profile.css" />
        <link href="assests/css/customer.css" rel="stylesheet" />

        <title>User Profile</title>
        <style>
            /* Style cho bảng */
            table {
                width: 100%; /* Chiếm toàn bộ chiều rộng */
                border-collapse: collapse; /* Loại bỏ khoảng cách giữa các ô */
                font-family: Arial, sans-serif;
                margin-bottom: 20px; /* Khoảng cách dưới bảng */
            }

            /* Style cho tiêu đề bảng */
            thead th {
                background-color: #4CAF50; /* Màu nền cho tiêu đề */
                color: white; /* Màu chữ trắng */
                padding: 12px 15px;
                text-align: center;
                font-weight: bold;
                border: 1px solid #ddd;
            }

            /* Style cho các hàng trong bảng */
            tbody td {
                padding: 10px 15px; /* Khoảng cách bên trong các ô */
                border: 1px solid #ddd; /* Đường viền */
                text-align: center; /* Căn giữa */
                vertical-align: middle; /* Canh giữa dọc */
                background-color: #f9f9f9; /* Màu nền sáng hơn cho nội dung */
            }

            /* Hiệu ứng hover cho hàng */
            tbody tr:hover {
                background-color: #f1f1f1; /* Thay đổi màu nền khi hover */
            }

            /* Container cho các nút hành động */
            .action-container {
                display: flex; /* Sử dụng flexbox để căn giữa */
                justify-content: center; /* Căn giữa các nút */
            }

            /* Style cho các nút hành động */
            a.action-link {
                display: inline-block;
                padding: 8px 12px;
                text-decoration: none;
                color: white;
                border-radius: 5px;
                margin: 0 5px; /* Khoảng cách giữa các nút */
                transition: background-color 0.3s ease;
                font-size: 14px;
            }

            /* Nút Approve */
            a.approve {
                background-color: #28a745; /* Màu xanh lá */
            }

            a.approve:hover {
                background-color: #218838; /* Màu xanh đậm hơn khi hover */
            }

            /* Nút Cancel */
            a.cancel {
                background-color: #dc3545; /* Màu đỏ */
            }

            a.cancel:hover {
                background-color: #c82333; /* Màu đỏ đậm hơn khi hover */
            }
            
            



            /* Responsive styles */
            @media (max-width: 768px) {
                .container {
                    grid-template-columns: 1fr;
                }
            }
        </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="index.jsp" class="brand">
                <i class="bx bxs-smile"></i>
                <span class="text">TourHub</span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="user-profile.jsp">
                        <i class="bx bxs-dashboard"></i>
                        <span class="text">User Information</span>
                    </a>
                </li>
                <li class="active">
                    <a href="booking">
                        <i class="bx bxs-shopping-bag-alt"></i>
                        <span class="text">My Booking</span>
                    </a>
                </li>
                <li>
                    <a href="user-chat.jsp">
                        <i class="bx bxs-message-dots"></i>
                        <span class="text">Message</span>
                    </a>
                </li>
                <c:if test="${sessionScope.currentUser.role == 'Provider'}">
                    <li>
                        <a href="discount">
                            <i class="bx bxs-discount"></i>
                            <span class="text">Manage Discounts</span>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a href="reviewtour.jsp">
                        <i class="bx bxs-star"></i>
                        <span class="text">Review Tours</span>
                    </a>
                </li>
                <%--
                <li>
                  <a href="#">
                    <i class="bx bxs-doughnut-chart"></i>
                    <span class="text">Analytics</span>
                  </a>
                </li>
                <li>
                  <a href="#">
                    <i class="bx bxs-group"></i>
                    <span class="text">Team</span>
                  </a>
                </li>
                --%>
            </ul>
            <ul class="side-menu">
                <li>
                    <a href="#">
                        <i class="bx bxs-cog"></i>
                        <span class="text">Settings</span>
                    </a>
                </li>
                <li>
                    <a href="logout" class="logout">
                        <i class="bx bxs-log-out-circle"></i>
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
                <i class="bx bx-menu"></i>
                <a href="#" class="nav-link"></a>
                <form action="#">
                    <div class="form-input">
                        <input type="search" placeholder="Searching for tour..." />
                        <button type="submit" class="search-btn">
                            <i class="bx bx-search"></i>
                        </button>
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden />
                <label for="switch-mode" class="switch-mode"></label>
                <a href="#" class="notification">
                    <i class="bx bxs-bell"></i>
                    <!-- <span class="num">8</span> -->
                </a>
                <div class="image-container">
                    <img
                        src="assests/images/avatar.jpg"
                        alt="User Avatar"
                        class="avatar"
                        />
                </div>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>My Bookings</h3>
                        </div>
                        <c:choose>
                            <c:when test="${currentUser == null}">
                                <c:redirect url="index.jsp" />
                            </c:when>
                            <c:otherwise>
                                <div class="profile-card">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tour Name</th>
                                                <th>Created Date</th>
                                                <th>Slot</th>
                                                <th>Total Cost</th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:choose>
                                                <c:when test="${empty bookings}">
                                                    <tr>
                                                        <td colspan="7">No bookings found.</td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="booking" items="${bookings}">
                                                        <tr>
                                                            <td>${booking.book_Id}</td>
                                                            <td>${booking.tour_Name}</td>
                                                            <td>${booking.created_At}</td>
                                                            <td>${booking.slot_Order}</td>
                                                            <td>${booking.total_Cost}</td>
                                                            <td>${booking.book_Status}</td>
                                                            <td>
                                                                <a class="approve action-link" href="#">View Details</a>
                                                                <a class="cancel action-link" href="#">Cancel</a>
                                                                <a class="approve action-link" href="#">Edit Tour Information</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </tbody>
                                    </table>
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
