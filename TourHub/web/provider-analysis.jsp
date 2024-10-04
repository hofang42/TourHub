<%-- 
    Document   : provider-analysis
    Created on : Sep 24, 2024, 9:35:52 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="assests/css/style_profile.css">       
        <link href="assests/css/customer.css" rel="stylesheet" >      
        <link href="assests/css/provider_analysis.css" rel="stylesheet"/>


        <title>Analytic</title>
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
                <li>
                    <a href="user-booking.jsp">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">My Booking</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Message</span>
                    </a>
                </li>


                <c:if test="${sessionScope.currentUser.role == 'Provider' || sessionScope.currentUser.role == 'Admin'}">
                    <li class="active">
                        <a href="${sessionScope.currentUser.role == 'Provider' ? '/Project_SWP/provider-analys' : 'admin-analysis.jsp'}">
                            <i class='bx bxs-dashboard' ></i>
                            <span class="text">Dashboard</span>
                        </a>
                    </li>   
                    <li class="dropdown-btn">
                        <a href="tour-management.jsp">
                            <i class='bx bxs-briefcase-alt' ></i>
                            <span class="text">Tour Management</span>
                        </a>
                    </li>   
                    <!-- Sub-menu -->
                    <ul class="sub-menu">
                        <li><a href="add-tour.jsp" class="active">Add Tour</a></li>                    
                        <li><a href="#">Feature 3</a></li>
                    </ul>
                    <li>
                        <a href="payment.jsp">
                            <i class='bx bxs-credit-card'></i>
                            <span class="text">Payment</span>
                        </a>
                    </li> 
                </c:if>

                <!-- Sub-menu -->
                <ul class="sub-menu">
                    <li><a href="add-tour.jsp">Add Tour</a></li>
                    l<i><a href="payment.jsp">Payment</a></li>
                        <li><a href="#">Feature 3</a></li>
                </ul>

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
                        <div class="head">
                            <h3>Dashboard</h3>
                        </div>

                        <c:choose>
                            <c:when test="${sessionScope.currentUser == null}">
                                <c:redirect url="home" />
                            </c:when>
                            <c:otherwise>
                                <div class="date">
                                    <input type="date" id="date" name="date" onchange="reloadData()"/>
                                </div>
                                <!-- Start Insight -->
                                <div class="insights">
                                    <!-- Start selling  -->
                                    <div class="sales" id="sale_total">
                                        <div class="sales-title"><h3>Profit this month</h3></div>
                                        <div class="sales-content">
                                            <h1 id="totalVisitValue">
                                                ${sessionScope.totalProfitThisMonth != null ? sessionScope.totalProfitThisMonth : 0}
                                            </h1>
                                        </div>
                                    </div>


                                    <div class="sales" id="sale_total">
                                        <div class="sales-title"><h3>Visit today</h3></div>
                                        <div class="sales-content">
                                            <h1 id="visitTodayValue">
                                                ${sessionScope.visitToday != null ? sessionScope.visitToday : 0}
                                            </h1>
                                        </div>
                                    </div>
                                    <div class="sales" id="sale_total">
                                        <div class="sales-title text">
                                            <h3>
                                                <c:if test="${empty sessionScope.date}">
                                                    Number of bookings this month
                                                </c:if>
                                                <c:if test="${not empty sessionScope.date}">
                                                    Number of bookings month <fmt:formatDate value="${sessionScope.date}" pattern="MM/yyyy"/>
                                                </c:if>
                                            </h3>
                                        </div>
                                        <div class="sales-content text">
                                            <h1 id="bookingThisMonthValue">
                                                ${sessionScope.bookingThisMonth != null ? sessionScope.bookingThisMonth : 0}
                                            </h1>
                                        </div>
                                    </div>

                                    <!-- End Incomes  -->
                                </div>
                                <!-- End Insight -->
                                <!-- Start recent order -->
                                <div class="recent_order">
                                    <h1>Recent Tour Booking</h1>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tour Name</th>
                                                <th>Customer Name</th>
                                                <th>Slot</th>
                                                <th>Status</th>
                                                <th>Total Cost</th>
                                            </tr>
                                        </thead>
                                        <tbody id="product_list">
                                            <c:forEach items="${sessionScope.bookings}" var="booking">
                                                <tr>
                                                    <c:set var="id" value="${id + 1}" />
                                                    <td>${id}</td>
                                                    <td>${booking.tourName}</td>
                                                    <td>${booking.customerName}</td>
                                                    <td>${booking.slotOrder}</td>
                                                    <td style="color:
                                                        <c:choose>
                                                            <c:when test="${booking.bookStatus == 'confirmed'}">green</c:when>
                                                            <c:when test="${booking.bookStatus == 'canceled'}">red</c:when>
                                                            <c:when test="${booking.bookStatus == 'pending'}">#FFCC00</c:when>
                                                            <c:otherwise>black</c:otherwise>
                                                        </c:choose>
                                                        ">
                                                        ${booking.bookStatus}
                                                    </td>
                                                    <td>${booking.totalCost} VND</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <!-- End recent order -->
            </main>

            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="assests/js/script_profile.js"></script>     
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                                        document.addEventListener('DOMContentLoaded', function () {
                                            const burger = document.querySelector('.burger');
                                            const navigation = document.querySelector('.navigation-admin');
                                            const main = document.querySelector('.main-admin');
                                            const profileCard = document.querySelector('.profile-card'); // Select the profile card

                                            burger.addEventListener('click', function () {
                                                navigation.classList.toggle('active');
                                                main.classList.toggle('active');
                                                profileCard.classList.toggle('active'); // Toggle the active class on the profile card
                                            });
                                        });


        </script>
        <script>
            function reloadData() {
                var date = document.getElementById("date").value;
                $.ajax({
                    url: "/Project_SWP/provider-analys",
                    type: "POST",
                    data: {
                        date: date
                    },
                    success: function (data) {
                        // Assuming 'data' is a JSON object
                        document.querySelector("#totalVisitValue").innerHTML = data.totalVisitATour || 0;
                        document.querySelector("#visitTodayValue").innerHTML = data.visitToday || 0;
                        document.querySelector("#bookingThisMonthValue").innerHTML = data.bookingThisMonth || 0;
                    }
                });
            }
        </script>

        <script src="dist/js/theme.min.js"></script>
    </body>
</html>
