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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="assests/css/style_profile.css">  
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastify-js/1.12.0/toastify.min.css">
        <!-- Toasify JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastify-js/1.12.0/toastify.min.js"></script>

        <style>
            .order .head {
                text-align: center;
                margin-bottom: 20px;
            }

            .order .head h3 {
                font-size: 1.8rem;
                color: #333;
                margin: 0;
            }

            /* Container styles for management sections */
            .container {
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                gap: 20px;
                margin-top: 20px;
            }

            .container div {
                background-color: orange;
                color: black;
                padding: 65px;
                text-align: center;
                border-radius: 6px;
                transition: background-color 0.3s, transform 0.3s;
            }

            .container div:hover {
                background-color: blue;
                transform: translateY(-5px);
                cursor: pointer;
            }

            /* Style cho bảng */
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
        <%@include file="includes/admin-sidebar.jsp" %>
        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <%@include file="includes/admin-navbar.jsp" %>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="table-data">
                    <div class="order">
                        <c:choose>
                            <c:when test="${currentUser == null || (currentUser != null && currentUser.role != 'Admin')}">
                                <c:redirect url="home" />
                            </c:when>
                            <c:when test="${type == 'user'}">
                                <h3>User Management</h3>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>User ID</th>
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>User Status</th>
                                            <th>Role</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${data}">
                                            <tr>
                                                <td>${user.user_Id}</td>
                                                <td>${user.first_Name} ${user.last_Name}</td>
                                                <td>${user.email}</td>
                                                <td>${user.user_Status}</td>
                                                <td>${user.role}</td>
                                                <td>
                                                    <div class="action-container">
                                                        <a href="manage?action=user-ban&id=${user.user_Id}" class="action-link cancel">Ban</a>
                                                        <a href="manage?action=user-unban&id=${user.user_Id}" class="action-link approve">UnBan</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>

                            <c:when test="${type == 'tour'}">
                                <h3>Tour Management</h3>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Tour ID</th>
                                            <th>Tour Name</th>
                                            <th>Location</th>
                                            <th>Start Date</th>
                                            <th>End Date</th>
                                            <th>Price</th>
                                            <th>Tour Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="tour" items="${data}">
                                            <tr>
                                                <td>${tour.tour_Id}</td>
                                                <td>${tour.tour_Name}</td>
                                                <td>${tour.location}</td>
                                                <td>${tour.start_Date}</td>
                                                <td>${tour.end_Date}</td>
                                                <td>${tour.price}</td>
                                                <td>${tour.tour_Status}</td>
                                                <td>
                                                    <div class="action-container">
                                                        <a href="manage?action=approve-tour&id=${tour.tour_Id}" class="action-link approve">Approve</a>
                                                        <a href="manage?action=cancel-tour&id=${tour.tour_Id}" class="action-link cancel">Cancel</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>

                            <c:when test="${type == 'report'}">
                                <h3>Report Management</h3>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Report ID</th>
                                            <th>Type</th>
                                            <th>Details</th>
                                            <th>Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="report" items="${data}">
                                            <tr>
                                                <td>${report.report_Id}</td>
                                                <td>${report.report_Type}</td>
                                                <td>${report.report_Details}</td>
                                                <td>${report.report_Date}</td>
                                                <td>
                                                    <div class="action-container">
                                                        <a href="manage?action=delete-report&id=${report.report_Id}" class="action-link approve">Delete</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>

                            <c:when test="${type == 'booking'}">
                                <h3>Booking Management</h3>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Booking ID</th>
                                            <th>Customer Name</th>
                                            <th>Tour Name</th>
                                            <th>Booking Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="booking" items="${data}">
                                            <tr>
                                                <td>${booking.book_Id}</td>
                                                <td>${booking.cus_Id}</td>
                                                <td>${booking.tour_Id}</td>
                                                <td>${booking.created_At}</td>
                                                <td>
                                                    <a href="editBooking.jsp?id=${booking.book_Id}">Approve</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script src="assests/js/script_profile.js"></script>
        <script>
            window.onload = function () {
                const message = '${message}';
                if (message) {
                    Toastify({
                        text: message,
                        duration: 3000, // Thời gian hiển thị (3 giây)
                        gravity: "top", // Vị trí hiển thị (top/bottom)
                        position: 'right', // Vị trí bên trái/bên phải
                        backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)", // Màu nền
                    }).showToast();

                    // Xóa message sau khi đã hiển thị
            <c:remove var="message" />
                }
            };
        </script>
    </body>
</html>





