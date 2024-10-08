<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="DataAccess.UserDB"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<jsp:useBean id="currentUser" class="model.User" scope="session" />--%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="assests/css/style_profile.css">       
        <link href="assests/css/customer.css" rel="stylesheet" />

        <title>User Profile</title>
    </head>
    <body>


        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="home" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">TourHub</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
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
                        <a href="booking">
                            <i class='bx bxs-shopping-bag-alt' ></i>
                            <span class="text">Manage Booking</span>
                        </a>
                    </li>
                </c:if>
                <li>
<<<<<<< HEAD
=======
                    <a href="booking">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">My Booking</span>
                    </a>
                </li>
                <li>
>>>>>>> e839fb6ab9a068e816cbec84d5f2d127cf3c3bd7
                    <a href="user-chat.jsp">
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
                    <li>
                        <a href="payment.jsp">
                            <i class='bx bxs-credit-card'></i>
                            <span class="text">Payment</span>
                        </a>
                    </li> 
                </c:if>
                </li>
                <c:if test="${sessionScope.currentUser.role == 'Provider'}">
                    <li>
                        <a href="discount">
                            <i class='bx bxs-discount'></i>
                            <span class="text">Manage Discounts</span>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a href="reviewtour.jsp">
                        <i class='bx bxs-star'></i>
                        <span class="text">Review Tours</span>
                    </a>
                </li>
                <c:if test="${sessionScope.currentUser.role == 'Provider'}">
                    <li>
                        <a href="discount">
                            <i class='bx bxs-discount'></i>
                            <span class="text">Manage Discounts</span>
                        </a>
                    </li>
                </c:if>
                <li>
                    <a href="reviewtour.jsp">
                        <i class='bx bxs-star'></i>
                        <span class="text">Review Tours</span>
                    </a>
                </li>
                <%-- <li>
                    <a href="#">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">Analytics</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Team</span>
                    </a>
                </li> --%>
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
                            <h3>User Information</h3>
                        </div>
                        <!-- Enter data here -->

                        <c:choose>
                            <c:when test="${sessionScope.currentUser == null}">
                                <c:redirect url="home" />
                            </c:when>
                            <c:otherwise>
                                <div class="profile-card">
                                    <div>
                                        <div class="profile-info">
<<<<<<< HEAD
<<<<<<< HEAD
                                            <label>Username:</label>
                                            <p><span>${sessionScope.currentUser.username}</span></p>
                                        </div>
                                        <div class="profile-info">
=======
>>>>>>> e839fb6ab9a068e816cbec84d5f2d127cf3c3bd7
=======
>>>>>>> e839fb6ab9a068e816cbec84d5f2d127cf3c3bd7
                                            <label>Password:</label>
                                            <p>
                                                <span id="passwordDisplay">********</span>
                                                <button onclick="togglePassword()">Show</button>
                                            </p>
                                            <form class="changeform" action="user-updateinfo.jsp" method="get">
                                                <button type="submit" name="buttonChange" value="pass">Change password</button>
                                            </form>
                                        </div>
                                        <div class="profile-info">
                                            <label>Email:</label>
                                            <p><span>${sessionScope.currentUser.email}</span></p>
                                            <form class="changeform" action="user-updateinfo.jsp" method="get">
                                                <button type="submit" name="buttonChange" value="email">Change email</button>
                                            </form>
                                        </div>
                                        <div class="profile-info">
                                            <label>Full Name:</label>
<<<<<<< HEAD
<<<<<<< HEAD
                                            <p><span>${sessionScope.currentUser.firstName} ${sessionScope.currentUser.lastName}</span></p>
=======
                                            <p><span>${currentUser.first_Name} ${currentUser.last_Name}</span></p>
>>>>>>> e839fb6ab9a068e816cbec84d5f2d127cf3c3bd7
=======
                                            <p><span>${currentUser.first_Name} ${currentUser.last_Name}</span></p>
>>>>>>> e839fb6ab9a068e816cbec84d5f2d127cf3c3bd7
                                        </div>
                                        <div class="profile-info">
                                            <label>Phone Number:</label>
                                            <p><span>${sessionScope.currentUser.phone}</span></p>
                                        </div>
                                        <div class="profile-info">
                                            <label>Address:</label>
                                            <p><span>${sessionScope.currentUser.address}</span></p>
                                        </div>
                                    </div>
                                    <div class="change-info-button">
                                        <form action="user-updateinfo.jsp">
                                            <button type="submit">Change Information</button>
                                        </form>
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
        <script>
                                                    function togglePassword() {
                                                        var passwordField = document.getElementById('passwordDisplay');
                                                        var button = event.target;

                                                        if (passwordField.innerHTML === "********") {
                                                            passwordField.innerHTML = "${sessionScope.currentUser.password}";
                                                            button.textContent = "Hide";
                                                        } else {
                                                            passwordField.innerHTML = "********";
                                                            button.textContent = "Show";
                                                        }
                                                    }

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
    </body>
</html>