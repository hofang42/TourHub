<%-- 
    Document   : provider-analysis
    Created on : Sep 24, 2024, 9:35:52 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                            <i class='bx bxs-doughnut-chart'></i>
                            <span class="text">Analytics</span>
                        </a>
                    </li>   
                </c:if>

                </div>
                <!-- Sub-menu -->
                <ul class="sub-menu">
                    <li><a href="#">Tour Management</a></li>
                    <li><a href="#">Payment</a></li>
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
                <h1>Dashboard</h1>
                <div class="date">
                    <input type="date" id="date" onchange=" GetProductByDate(), GetTotalProduct()"/>
                </div>
                <!-- Start Insight -->
                <div class="insights">
                    <!-- Start selling  -->
                    <div class="sales" id="sale_total">
                        <div class="sales-title"><h3>Total visit</h3></div>
                        <div class="sales-content">
                            <h1>
                                ${sessionScope.totalVisitATour != null ? sessionScope.totalVisitATour : 0}
                            </h1>
                        </div>
                    </div>


                    <div class="sales" id="sale_total">
                        <div class="sales-title"><h3>Visit today</h3></div>
                        <div class="sales-content">
                            <h1>
                                ${sessionScope.visitToday != null ? sessionScope.visitToday : 0}
                            </h1>
                        </div>
                    </div>
                    <div class="sales" id="sale_total">
                        <div class="sales-title text"><h3>Number of booking this month</h3></div>
                        <div class="sales-content text">
                            <h1>
                                ${sessionScope.bookingThisMonth != null ? sessionScope.bookingThisMonth : 0}
                            </h1>
                        </div>
                    </div>
                    <!-- End Incomes  -->
                </div>
                <!-- End Insight -->
                <!-- Start recent order -->
                <div class="recent_oder">
                    <h1>Recent Orders</h1>
                    <table>
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Product ID</th>
                                <th>Amounts</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody id="product_list">
                            <c:forEach items="${data}" var="data">
                                <tr>
                                    <td>${data.product_name}</td>
                                    <td>${data.product_id}</td>
                                    <td>${data.price}</td>
                                    <td>${data.status}</td>
                                    <td class="primary">Details</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- End recent order -->
            </main>

            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="assests/js/script_profile.js"></script>      
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
                        var dropdown = document.getElementsByClassName("dropdown-btn");
                        for (var i = 0; i < dropdown.length; i++) {
                            dropdown[i].addEventListener("click", function () {
                                this.classList.toggle("active");

                                // Toggle next sibling of the dropdown button (which is outside the parent <li>)
                                var subMenu = document.querySelector('.sub-menu');
                                if (subMenu.style.display === "block") {
                                    subMenu.style.display = "none";
                                } else {
                                    subMenu.style.display = "block";
                                }
                            });
                        }

        </script>
        <script src="dist/js/theme.min.js"></script>
    </body>
</html>
