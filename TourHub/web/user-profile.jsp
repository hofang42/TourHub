<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User"%>
<%@ page import="DAO.UserDB"%>
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

        <title>User Profile</title>
    </head>
    <body>


        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
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
                    <a href="#" class="logout">
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
                    <span class="num">8</span>
                </a>
                <a href="#" class="profile">
                    <img src="img/people.png">
                </a>
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
                        <!-- ========================= Main ==================== -->
                        <div class="main-admin">
                            <div class="information">
                                <jsp:useBean id="userDB" class="DAO.UserDB" scope="session" />
                                <% 
                                    // Gọi phương thức getUserFromSession từ UserDB
                                    User user = userDB.getUserFromSession(session, request);
                    
                                    if (user == null) {
                                        response.sendRedirect("index.jsp");
                                    } else {
                                        request.setAttribute("user", user);
                                    }
                                %>

                                <c:choose>
                                    <c:when test="${user == null}">
                                        <c:redirect url="index.jsp" />
                                    </c:when>
                                    <c:otherwise>
                                        <div class="profile-card">
                                            <div>
                                                <div class="profile-info">
                                                    <label>Username:</label>
                                                    <p><span>${user.username}</span></p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>Password:</label>
                                                    <p>
                                                        <span id="passwordDisplay">********</span>
                                                        <button onclick="togglePassword()">Show</button>
                                                    </p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>Email:</label>
                                                    <p><span>${user.email}</span></p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>FullName:</label>
                                                    <p><span>${user.fName} ${user.lName}</span></p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>Address:</label>
                                                    <p><span>${user.address}</span></p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>Phone Number:</label>
                                                    <p><span>${user.phone}</span></p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>Sex:</label>
                                                    <p><span>${user.sex}</span></p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>Date of Birth:</label>
                                                    <p><span>${user.dob}</span></p>
                                                </div>
                                                <div class="profile-info">
                                                    <label>Money left:</label>
                                                    <p><span>${user.money} $</span></p>
                                                </div>
                                            </div>
                                            <div class="change-info-button">
                                                <form action="updateinfo.jsp">
                                                    <button type="submit">Change Information</button>
                                                </form>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
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
                                                                    passwordField.innerHTML = "${user.password}";
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