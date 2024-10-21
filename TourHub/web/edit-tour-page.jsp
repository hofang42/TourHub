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
        <style>
            body {
                background-color: #f4f4f4;
            }
            .form-container {
                margin-top: 50px;
                background: #fff;
                padding: 30px;
                border-radius: 5px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
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
                        <a href="user-booking.jsp">
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
                    <li><a href="payment.jsp">Payment</a></li>
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
                <c:choose>
                    <c:when test="${sessionScope.currentUser == null}">
                        <c:redirect url="home" />
                    </c:when>
                    <c:otherwise>
                        <h3 style="<c:if test='${requestScope.message.contains("successfully")}'>color: green;</c:if>
                            <c:if test='${requestScope.message.contains("Error")}'>color: red;</c:if>">
                            ${requestScope.message}
                        </h3>
                        <c:set value="${requestScope.tourEdit}" var="tour" />

                        <div class="table-data">
                            <div class="order">
                                <h3 class="head">Edit Tour</h3>
                                <form action="provider-management?action=save-edit-tour&tourId=${tour.tour_Id}" method="POST" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="tour_Name">Tour Name: <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" id="tour_Name" name="tour_Name" maxlength="255" value="${tour.tour_Name}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="tour_Description">Tour Description: <span style="color: red;">*</span></label>
                                        <textarea class="form-control" id="tour_Description" name="tour_Description" rows="4" required style="width: 100%; resize: vertical;">${tour.tour_Description}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="start_Date">Start Date: <span style="color: red;">*</span></label>
                                        <input type="date" class="form-control" id="start_Date" name="start_Date" value="${tour.start_Date}" required onchange="calculateDuration()">
                                    </div>
                                    <div class="form-group">
                                        <label for="end_Date">End Date: <span style="color: red;">*</span></label>
                                        <input type="date" class="form-control" id="end_Date" name="end_Date" value="${tour.end_Date}" required onchange="calculateDuration()">
                                    </div>
                                    <div class="form-group">
                                        <label for="total_Time">Duration</label>
                                        <div class="d-flex align-items-center">
                                            <div class="form-group">
                                                <label for="day">Days:</label>
                                                <input type="number" class="form-control" id="day" name="day" value="0" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label for="night">Nights:</label>
                                                <input type="number" class="form-control" id="night" name="night" value="0" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="location">Location: <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" id="location" name="location" value="${tour.location}" maxlength="50" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="price">Price: <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" id="price" name="price" value="${tour.price}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="slot">Slot: <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" id="slot" name="slot" value="${tour.slot}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="status">Status:</label>
                                        <input type="text" class="form-control" id="status" name="status" value="${tour.tour_Status}" readonly>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block">Save</button>
                                </form>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
            function calculateDuration() {
                // Get the values of the start and end dates
                var startDate = document.getElementById("start_Date").value;
                var endDate = document.getElementById("end_Date").value;

                console.log("Start Date:", startDate);
                console.log("End Date:", endDate);

                // Reset the fields by default
                document.getElementById("day").value = 0;
                document.getElementById("night").value = 0;

                // Check if both dates are provided
                if (startDate && endDate) {
                    // Parse the dates into Date objects
                    var start = new Date(startDate);
                    var end = new Date(endDate);

                    // Normalize to midnight to prevent issues with time zones
                    start.setHours(0, 0, 0, 0);
                    end.setHours(0, 0, 0, 0);

                    // Calculate the difference in time (milliseconds)
                    var diffTime = end - start;

                    console.log("Time Difference (milliseconds):", diffTime);

                    // Check if end date is before start date
                    if (diffTime < 0) {
                        alert("End date cannot be before start date.");
                        return; // Exit the function early
                    }

                    // Convert the time difference to days (1 day = 24*60*60*1000 milliseconds)
                    var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                    console.log("Difference in Days:", diffDays);

                    // Set the day and night values
                    document.getElementById("day").value = diffDays; // Total days
                    document.getElementById("night").value = Math.max(0, diffDays - 1); // Nights = days - 1
                    console.log(diffDays);
                }
            }
            // Automatically call calculateDuration when the page loads
            window.onload = function () {
                calculateDuration();
            }
        </script>

        <script src="dist/js/theme.min.js"></script>
    </body>
</html>
