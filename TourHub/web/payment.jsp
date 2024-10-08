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
        <link href="assests/css/tour-management.css" rel="stylesheet"/>   
        <link rel="stylesheet" href="assests/css/bootstrap.css" />



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
                    <li class="dropdown-btn">
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
                    <li class="active">
                        <a href="payment.jsp">
                            <i class='bx bxs-credit-card'></i>
                            <span class="text">Payment</span>
                        </a>
                    </li> 
                </c:if>

                <!-- Sub-menu -->
                <ul class="sub-menu">
                    <li><a href="add-tour.jsp">Add Tour</a></li>                    
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
                        <c:choose>
                            <c:when test="${sessionScope.currentUser == null}">
                                <c:redirect url="home" />
                            </c:when>
                            <c:otherwise>
                                <div class="container py-5">
                                    <!-- For demo purpose -->
                                    <div class="row mb-4">
                                    </div> <!-- End -->
                                    <div class="row">
                                        <div class="col-lg-6 mx-auto">
                                            <div class="card ">
                                                <div class="card-header">
                                                    <div class="bg-white shadow-sm pt-4 pl-2 pr-2 pb-2">
                                                        <!-- Credit card form tabs -->
                                                        <ul role="tablist" class="nav bg-light nav-pills rounded nav-fill mb-3">
                                                            <li class="nav-item"> <a data-toggle="pill" href="#credit-card" class="nav-link active "> <i class="fas fa-credit-card mr-2"></i> Credit Card </a> </li>
                                                            <li class="nav-item"> <a data-toggle="pill" href="#paypal" class="nav-link "> <i class="fab fa-paypal mr-2"></i> Paypal </a> </li>
                                                            <li class="nav-item"> <a data-toggle="pill" href="#net-banking" class="nav-link "> <i class="fas fa-mobile-alt mr-2"></i> Net Banking </a> </li>
                                                        </ul>
                                                    </div> <!-- End -->
                                                    <!-- Credit card form content -->
                                                    <div class="tab-content">
                                                        <!-- credit card info-->
                                                        <div id="credit-card" class="tab-pane fade show active pt-3">
                                                            <form role="form" onsubmit="event.preventDefault()">
                                                                <div class="form-group"> <label for="username">
                                                                        <h6>Card Owner</h6>
                                                                    </label> <input type="text" name="username" placeholder="Card Owner Name" required class="form-control "> </div>
                                                                <div class="form-group"> <label for="cardNumber">
                                                                        <h6>Card number</h6>
                                                                    </label>
                                                                    <div class="input-group"> <input type="text" name="cardNumber" placeholder="Valid card number" class="form-control " required>
                                                                        <div class="input-group-append"> <span class="input-group-text text-muted"> <i class="fab fa-cc-visa mx-1"></i> <i class="fab fa-cc-mastercard mx-1"></i> <i class="fab fa-cc-amex mx-1"></i> </span> </div>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-sm-8">
                                                                        <div class="form-group"> <label><span class="hidden-xs">
                                                                                    <h6>Expiration Date</h6>
                                                                                </span></label>
                                                                            <div class="input-group"> <input type="number" placeholder="MM" name="" class="form-control" required> <input type="number" placeholder="YY" name="" class="form-control" required> </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <div class="form-group mb-4"> <label data-toggle="tooltip" title="Three digit CV code on the back of your card">
                                                                                <h6>CVV <i class="fa fa-question-circle d-inline"></i></h6>
                                                                            </label> <input type="text" required class="form-control"> </div>
                                                                    </div>
                                                                </div>
                                                                <div class="card-footer"> <button type="button" class="subscribe btn btn-primary btn-block shadow-sm"> Confirm Payment </button>
                                                            </form>
                                                        </div>
                                                    </div> <!-- End -->
                                                    <!-- Paypal info -->
                                                    <div id="paypal" class="tab-pane fade pt-3">
                                                        <h6 class="pb-2">Select your paypal account type</h6>
                                                        <div class="form-group "> <label class="radio-inline"> <input type="radio" name="optradio" checked> Domestic </label> <label class="radio-inline"> <input type="radio" name="optradio" class="ml-5">International </label></div>
                                                        <p> <button type="button" class="btn btn-primary "><i class="fab fa-paypal mr-2"></i> Log into my Paypal</button> </p>
                                                        <p class="text-muted"> Note: After clicking on the button, you will be directed to a secure gateway for payment. After completing the payment process, you will be redirected back to the website to view details of your order. </p>
                                                    </div> <!-- End -->
                                                    <!-- bank transfer info -->
                                                    <div id="net-banking" class="tab-pane fade pt-3">
                                                        <div class="form-group "> <label for="Select Your Bank">
                                                                <h6>Select your Bank</h6>
                                                            </label> <select class="form-control" id="ccmonth">
                                                                <option value="" selected disabled>--Please select your Bank--</option>
                                                                <option>Bank 1</option>
                                                                <option>Bank 2</option>
                                                                <option>Bank 3</option>
                                                                <option>Bank 4</option>
                                                                <option>Bank 5</option>
                                                                <option>Bank 6</option>
                                                                <option>Bank 7</option>
                                                                <option>Bank 8</option>
                                                                <option>Bank 9</option>
                                                                <option>Bank 10</option>
                                                            </select> </div>
                                                        <div class="form-group">
                                                            <p> <button type="button" class="btn btn-primary "><i class="fas fa-mobile-alt mr-2"></i> Proceed Payment</button> </p>
                                                        </div>
                                                        <p class="text-muted">Note: After clicking on the button, you will be directed to a secure gateway for payment. After completing the payment process, you will be redirected back to the website to view details of your order. </p>
                                                    </div> <!-- End -->
                                                    <!-- End -->
                                                </div>
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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

                if (startDate && endDate) {
                    // Parse the dates into Date objects
                    var start = new Date(startDate);
                    var end = new Date(endDate);

                    // Calculate the difference in time (milliseconds)
                    var diffTime = end - start;

                    // Convert the time difference to days (1 day = 24*60*60*1000 milliseconds)
                    var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                    if (diffDays > 0) {
                        // Set the day value
                        document.getElementById("day").value = diffDays;

                        // Set the night value (days - 1)
                        document.getElementById("night").value = diffDays - 1;
                    } else {
                        // If the end date is before the start date, reset the fields
                        document.getElementById("day").value = 0;
                        document.getElementById("night").value = 0;
                    }
                } else {
                    // Reset the fields if either date is missing
                    document.getElementById("day").value = 0;
                    document.getElementById("night").value = 0;
                }
            }
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        </script>

        <script src="dist/js/theme.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
