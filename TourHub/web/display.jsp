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
                <li>
                    <a href="#">
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
                    <!-- <span class="num">8</span> -->
                </a>

            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="table-data">
                    <div class="order">
                        <c:choose>
                            <c:when test="${type == 'user'}">
                                <h3>User Management</h3>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>User ID</th>
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="user" items="${data}">
                                            <tr>
                                                <td>${user.userId}</td>
                                                <td>${user.firstName} ${user.lastName}</td>
                                                <td>${user.email}</td>
                                                <td>
                                                    <a href="deleteUser?id=${user.userId}">Ban</a>
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
                                                <td>
                                                    <a href="editTour.jsp?id=${tour.tour_Id}">Approve</a>
                                                    <a href="deleteTour?id=${tour.tour_Id}">Delete</a>
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
                                                    <a href="deleteReport?id=${report.report_Id}">Delete</a>
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
                                                <td>${booking.book_Date}</td>
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
    </body>
</html>





