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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">


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
                    <li class="active dropdown-btn">

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

                <div class="table-data">
                    <div class="order">
                        <h3 class="head">Add Tour</h3>
                        <form action="addtour" method="POST" enctype="multipart/form-data"> <!-- Combined form with file upload -->
                            <div class="form-group">
                                <label for="tour_Name">Tour Name:</label>
                                <input type="text" class="form-control" id="tour_Name" name="tour_Name" maxlength="255" required>
                            </div>
                            <div class="form-group">
                                <label for="tour_Description">Tour Description:</label>
                                <textarea class="form-control" id="tour_Description" name="tour_Description" rows="4" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="start_Date">Start Date:</label>
                                <input type="date" class="form-control" id="start_Date" name="start_Date" required>
                            </div>
                            <div class="form-group">
                                <label for="end_Date">End Date:</label>
                                <input type="date" class="form-control" id="end_Date" name="end_Date" required>
                            </div>
                            <div class="form-group">
                                <label for="location">Location:</label>
                                <input type="text" class="form-control" id="location" name="location" maxlength="50" required>
                            </div>
                            <div class="form-group">
                                <label for="purchases_Time">Purchases Time (minutes):</label>
                                <input type="number" class="form-control" id="purchases_Time" name="purchases_Time" required>
                            </div>
                            <div class="form-group">
                                <label for="total_Time">Total Time:</label>
                                <input type="text" class="form-control" id="total_Time" name="total_Time" maxlength="10" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Price:</label>
                                <input type="number" class="form-control" id="price" name="price" required>
                            </div>
                            <div class="form-group">
                                <label for="slot">Slot:</label>
                                <input type="number" class="form-control" id="slot" name="slot" required>
                            </div>
                            <div class="form-group">
                                <label for="tour_Img">Tour Image:</label>
                                <input type="file" class="form-control-file" id="tour_Img" name="tour_Img" required>
                                <small class="form-text text-muted">Upload an image file (JPG, PNG, etc.) not exceeding 2MB.</small>
                            </div>

                            <button type="submit" class="btn btn-primary btn-block">Add Tour</button>
                            <h3>${requestScope.message}</h3>
                        </form>
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
            // Select all dropdown buttons
            var dropdowns = document.getElementsByClassName("dropdown-btn");

            for (var i = 0; i < dropdowns.length; i++) {
                dropdowns[i].addEventListener("click", function (event) {
                    event.preventDefault(); // Prevent the default action (navigation)

                    this.classList.toggle("active");

                    // Select the next sibling which is the sub-menu
                    var subMenu = this.nextElementSibling; // Change from querySelector to nextElementSibling

                    if (subMenu.style.display === "block") {
                        subMenu.style.display = "none";
                    } else {
                        subMenu.style.display = "block";
                    }
                });
            }


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
