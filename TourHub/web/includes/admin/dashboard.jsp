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
        <link rel="stylesheet" type="text/css" href="assests/css/dashboard.css">   

        <style>
            .head button {
                border-radius: 20px; /* Add border radius to the button */
                padding: 8px 15px; /* Optional: Adjust padding for better appearance */
                background-color: #ff5722; /* Optional: Set background color for button */
                color: #fff; /* Optional: Set text color for button */
                border: none; /* Optional: Remove default border */
                cursor: pointer; /* Optional: Change cursor to pointer on hover */
            }

            .head button:hover {
                background-color: #e64a19; /* Optional: Darken button on hover */
            }
        </style>
    </head>
    <body>
        <%@include file="../admin-sidebar.jsp" %>
        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <%@include file="../admin-navbar.jsp" %>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>My Bookings</h3>
                            <div>
                                <form method="post" action="exportCsv">
                                    <button type="submit">Export File</button>
                                </form>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${currentUser == null || (currentUser != null && currentUser.role != 'Admin')}">
                                <c:redirect url="home" />
                            </c:when>
                            <c:otherwise>
                                <div class="container">
                                    <div class="info-cards">
                                        <!-- Số lượng đặt tour -->
                                        <div class="info-card">
                                            <h3>Số lượng đặt tour</h3>
                                            <p>${totalBookings}</p>
                                        </div>

                                        <!-- Doanh thu -->
                                        <div class="info-card">
                                            <h3>Doanh thu</h3>
                                            <p>${totalRevenue} VND</p>
                                        </div>

                                        <!-- Tỷ lệ hủy tour -->
                                        <div class="info-card">
                                            <h3>Tỷ lệ hủy tour</h3>
                                            <p>${cancellationRate}%</p>
                                        </div>
                                    </div>

                                    <!-- Container cho cả hai biểu đồ -->
                                    <div class="charts-container">
                                        <div class="chart-container">
                                            <h3>Số Lượng Đặt Tour Theo Từng Tháng</h3>
                                            <canvas id="monthlyBookingsChart"></canvas>
                                        </div>

                                        <div class="chart-container">
                                            <canvas id="bookingsByLocationChart"></canvas>
                                        </div>
                                    </div>

                                    <!-- Liệt kê các user vừa đăng ký gần đây nhất -->
                                    <div class="recent-users-container">
                                        <h3>Danh Sách Người Dùng Đăng Ký Gần Đây</h3>
                                        <table class="users-table">
                                            <thead>
                                                <tr>
                                                    <th>Tên</th>
                                                    <th>Vai Trò</th>
                                                    <th>Thời Gian Đăng Ký</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty recentUsers}">
                                                    <c:forEach var="user" items="${recentUsers}">
                                                        <tr>
                                                            <td>${user.first_Name} ${user.last_Name}</td>
                                                            <td>${user.role}</td>
                                                            <td>${user.created_At}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty recentUsers}">
                                                    <tr>
                                                        <td colspan="3">Không có người dùng nào gần đây</td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
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


        <script>
            // JavaScript để vẽ biểu đồ
            var ctxLocation = document.getElementById('bookingsByLocationChart').getContext('2d');
            var locationLabels = [];
            var locationData = [];

            <c:if test="${not empty bookingsByLocation}">
                <c:forEach var="entry" items="${bookingsByLocation}">
            locationLabels.push('${entry.key}');
            locationData.push(${entry.value});
                </c:forEach>
            </c:if>

            var bookingsByLocationChart = new Chart(ctxLocation, {
                type: 'pie',
                data: {
                    labels: locationLabels,
                    datasets: [{
                            label: 'Số lượng đặt tour theo vùng',
                            data: locationData,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(54, 162, 235, 0.6)',
                                'rgba(255, 206, 86, 0.6)',
                                'rgba(75, 192, 192, 0.6)',
                                'rgba(153, 102, 255, 0.6)',
                                'rgba(255, 159, 64, 0.6)'
                            ],
                            borderWidth: 1
                        }]
                },
                options: {
                    responsive: true,
                }
            });

            var ctxMonth = document.getElementById('monthlyBookingsChart').getContext('2d');
            var monthlyLabels = [];
            var monthlyData = [];

            <c:if test="${not empty monthlyBookings}">
                <c:forEach var="entry" items="${monthlyBookings}">
            monthlyLabels.push('${entry.key}');
            monthlyData.push(${entry.value});
                </c:forEach>
            </c:if>

            var monthlyBookingsChart = new Chart(ctxMonth, {
                type: 'bar',
                data: {
                    labels: monthlyLabels,
                    datasets: [{
                            label: 'Số lượng đặt tour',
                            data: monthlyData,
                            backgroundColor: 'rgba(54, 162, 235, 0.6)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true,
                            title: {
                                display: true,
                                text: 'Số lượng'
                            }
                        },
                        x: {
                            title: {
                                display: true,
                                text: 'Tháng'
                            }
                        }
                    }
                }
            });
        </script>
        <script src="assests/js/script_profile.js"></script>
    </body>
</html>





