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
        <style>


            /* Style the labels */
            .avatardiv label {
                margin-bottom: 10px; /* Space below the label */
                font-weight: bold; /* Bold text */
            }

            /* Style the file input */
            .avatardiv input[type="file"] {
                margin-bottom: 20px; /* Space below the input */
                padding: 10px; /* Add padding */
                border: 1px solid #ccc; /* Light border */
                border-radius: 4px; /* Rounded corners */
                font-size: 14px; /* Font size */
            }

            /* Style the submit button */
            .avatardiv button {
                padding: 10px 20px; /* Vertical and horizontal padding */
                border: none; /* Remove default border */
                border-radius: 4px; /* Rounded corners */
                background-color: #4CAF50; /* Green background */
                color: white; /* White text color */
                font-size: 16px; /* Font size */
                cursor: pointer; /* Pointer cursor on hover */
                transition: background-color 0.3s; /* Transition effect */
            }

            /* Change button color on hover */
            .avatardiv button:hover {
                background-color: #45a049; /* Darker green on hover */
            }

            .profile-card {
                display: flex; /* Sử dụng Flexbox để tạo cấu trúc cho phần card */
                justify-content: space-between; /* Căn giữa và phân chia không gian */
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 8px;
                background-color: #f9f9f9;
            }

            .profile-info-left,
            .profile-info-right {
                flex: 1; /* Chiếm 50% không gian của mỗi phần */
                margin-right: 20px; /* Thêm khoảng cách bên phải cho phần bên trái */
            }

            .profile-info-right {
                margin-right: 0; /* Đảm bảo không gian cho phần bên phải */
            }

            /* Có thể điều chỉnh khoảng cách giữa các phần và định dạng cho các phần khác trong profile */


            /* Optional: Responsive design */
            @media (max-width: 480px) {
                .avatardiv form {
                    width: 90%; /* Full width on small screens */
                }
            }

        </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <%@include file="includes/user-sidebar.jsp" %>
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
                    <img src="${currentUser.avatar}" alt="User Avatar" class="avatar">
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
                                    <div class="profile-info-left">
                                        <div class="profile-info">
                                            <label>Full Name:</label>
                                            <p><span>${currentUser.first_Name} ${currentUser.last_Name}</span></p>
                                        </div>
                                        <div class="profile-info">
                                            <label>Email:</label>
                                            <p><span>${sessionScope.currentUser.email}</span></p>
                                            <form class="changeform" action="user-updateinfo.jsp" method="get">
                                                <button type="submit" name="buttonChange" value="email">Change email</button>
                                            </form>
                                        </div>
                                        <div class="profile-info">
                                            <label>Phone Number:</label>
                                            <p><span>${currentUser.phone}</span></p>
                                        </div>
                                        <div class="profile-info">
                                            <label>Address:</label>
                                            <p><span>${currentUser.address}</span></p>
                                        </div>
                                        <div class="profile-info">
                                            <label>Password:</label>
                                            <p>
                                                <span id="passwordDisplay">********</span>
                                                <button onclick="togglePassword()">Show</button>
                                            </p>
                                            <form class="changeform" action="user-updateinfo.jsp" method="get">
                                                <button type="submit" name="buttonChange" value="pass">Change password</button>
                                            </form>
                                        </div>
                                        <c:if test="${sessionScope.currentUser.role == 'Customer'}">
                                            <div class="profile-info">
                                                <label>Birthday: </label>
                                                <p><span>${currentUser.cus_Birth}</span></p>
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.currentUser.role == 'Provider'}">
                                            <div class="profile-info">
                                                <label>Tax Code: </label>
                                                <p><span>${currentUser.tax_Code}</span></p>
                                            </div>
                                            <div class="profile-info">
                                                <label>Balance: </label>
                                                <p><span>${currentUser.balance}</span></p>
                                            </div>
                                            <div class="profile-info">
                                                <label>Bank Information: </label>
                                                <p><span>${currentUser.bank_Information}</span></p>
                                            </div>
                                        </c:if>

                                    </div>
                                    <div class="profile-info-right">
                                        <div class="profile-info">
                                            <img src="${currentUser.avatar}" style="width: 400px; height: 400px; border-radius: 50%;"/>
                                        </div>
                                        <div class="avatardiv">
                                            <form action="UploadAvatarServlet" method="post" enctype="multipart/form-data">
                                                <label>Upload Avatar:</label>
                                                <input type="file" name="avatar" accept="image/*" required>
                                                <button type="submit">Upload</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="change-info-button">
                                    <form action="user-updateinfo.jsp">
                                        <button type="submit">Change Information</button>
                                    </form>
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
                                                            passwordField.innerHTML = "${currentUser.password}";
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