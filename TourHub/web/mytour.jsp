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
        <link href="assests/css/edit-tour.css" rel="stylesheet"/>           
        <link rel="stylesheet" href="assests/css/bootstrap.css" />
        <link rel="stylesheet" href="assests/css/style.css" />

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
                <li>
                    <a href="#">
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
                    <li class="active dropdown-btn">                        
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
                </c:if>

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
                <c:choose>
                    <c:when test="${sessionScope.currentUser == null}">
                        <c:redirect url="home" />
                    </c:when>
                    <c:otherwise>
                        <!-- Search Form -->
                        <form action="SearchTourByIdServlet" method="POST">
                            <div class="form-input-custom">
                                <input type="search" name="tour-edit" class="search-field-custom tour-edit" 
                                       placeholder="Enter tourID to find tour you want to edit">
                                <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                            </div>
                        </form>
                        <!-- Add New Tour Button -->
                        <div style="margin-top: 10px;">
                            <form action="add-tour.jsp" method="GET">
                                <button type="submit" class="button button-primary">Add New Tour</button>
                            </form>
                        </div>
                        <!-- Error Message Display -->
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">
                                ${errorMessage}
                            </div>
                        </c:if>

                        <!-- Display a Single Tour to Edit if tourEdit is available -->
                        <c:if test="${not empty tourEdit}">
                            <div class="table-data">
                                <div class="order">              
                                    <div class="row row-50"> 
                                        <div class="col-md-6 col-xl-4">
                                            <article class="event-default-wrap">
                                                <div class="event-default">
                                                    <figure class="event-default-image" style="max-width: 250px; margin: auto;">
                                                        <img src="./assests/images/provinces/danang.jpg" alt="${tourEdit.tourName}" style="width: 100%; height: auto;">
                                                        <div class="event-default-caption">
                                                            <!-- Ensure tourId is valid -->
                                                            <a href="edit-tour?tourId=${tourEdit.tourId}" 
                                                               class="button button-xs button-secondary button-nina tour-visit-count" 
                                                               style="font-size: 12px; padding: 2px 5px; line-height: 1; width: 50px; display: inline-block; text-align: center;">
                                                                Edit
                                                            </a>
                                                        </div>
                                                    </figure>
                                                </div>
                                                <div class="event-default-inner">
                                                    <div>
                                                        <h5>
                                                            <a href="#" class="event-default-title">${tourEdit.tourName}</a>
                                                        </h5>
                                                    </div>
                                                </div>
                                            </article>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <!-- Display All Tours if providerTours is available -->
                        <c:if test="${not empty providerTours}">
                            <div class="table-data">
                                <div class="order">              
                                    <div class="row row-50"> 
                                        <c:forEach var="tour" items="${providerTours}">
                                            <div class="col-md-6 col-xl-4">
                                                <article class="event-default-wrap">
                                                    <div class="event-default">
                                                        <figure class="event-default-image" style="max-width: 250px; margin: auto;">
                                                            <img src="./assests/images/provinces/danang.jpg" alt="${tour.tourName}" style="width: 100%; height: auto;">
                                                            <div class="event-default-caption">
                                                                <!-- Ensure tourId is valid -->
                                                                <a href="edit-tour?tourId=${tour.tourId}" 
                                                                   class="button button-xs button-secondary button-nina tour-visit-count" 
                                                                   style="font-size: 12px; padding: 2px 5px; line-height: 1; width: 50px; display: inline-block; text-align: center;">
                                                                    Edit
                                                                </a>
                                                                <a href="hidden-tour?tourId=${tour.tourId}" 
                                                                   class="button button-xs button-secondary button-nina tour-visit-count" 
                                                                   style="font-size: 12px; padding: 2px 5px; line-height: 1; width: 50px; display: inline-block; text-align: center;">
                                                                    Hidden
                                                                </a>
                                                            </div>
                                                        </figure>
                                                    </div>
                                                    <div class="event-default-inner">
                                                        <div>
                                                            <h5>
                                                                <a href="#" class="event-default-title">${tour.tourName}</a>
                                                            </h5>
                                                        </div>
                                                    </div>
                                                </article>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                </div>
                </div>

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
