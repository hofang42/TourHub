<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Discount" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="assests/css/style_profile.css">
    <link rel="stylesheet" href="assests/css/manage-discounts.css">
    <title>Create Discount</title>
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
                <a href="user-profile.jsp">
                    <i class='bx bxs-dashboard'></i>
                    <span class="text">User Information</span>
                </a>
            </li>
            <li>
                <a href="user-booking.jsp">
                    <i class='bx bxs-shopping-bag-alt'></i>
                    <span class="text">My Booking</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class='bx bxs-message-dots'></i>
                    <span class="text">Message</span>
                </a>
            </li>
            <li>
                <a href="discount">
                    <i class='bx bxs-discount'></i>
                    <span class="text">Manage Discounts</span>
                </a>
            </li>
        </ul>
        <ul class="side-menu">
            <li>
                <a href="#">
                    <i class='bx bxs-cog'></i>
                    <span class="text">Settings</span>
                </a>
            </li>
            <li>
                <a href="logout" class="logout">
                    <i class='bx bxs-log-out-circle'></i>
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
            <i class='bx bx-menu'></i>
            <a href="#" class="nav-link"></a>
            <form action="#">
                <div class="form-input">
                    <input type="search" placeholder="Searching for tour...">
                    <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <input type="checkbox" id="switch-mode" hidden>
            <label for="switch-mode" class="switch-mode"></label>
            <a href="#" class="notification">
                <i class='bx bxs-bell'></i>
                <span class="num">8</span>
            </a>
            <a href="#" class="profile">
                <img src="img/people.png">
            </a>
        </nav>
        <!-- NAVBAR -->

        <!-- MAIN -->
        <main>
            <div class="form-container">
                <h3>Create New Discount</h3>

                <!-- Hiển thị thông báo lỗi nếu có -->
                <%
                    String errorMessage = (String) request.getAttribute("error");
                    String successMessage = (String) request.getAttribute("message");
                    if (errorMessage != null && !errorMessage.trim().isEmpty()) {
                %> 
                    <div style="color:red; margin-bottom: 10px;">
                        <%= errorMessage %>
                    </div>
                <%
                    } else if (successMessage != null && !successMessage.trim().isEmpty()) {
                %> 
                    <div style="color:green; margin-bottom: 10px;">
                        <%= successMessage %>
                    </div>
                <%
                    }
                %>

                <form action="discount" method="post">
                    <input type="hidden" name="action" value="insert">
                    
                    <div class="form-group">
                        <label for="code">Discount Code:</label>
                        <input type="text" id="code" name="code" required>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="number" id="quantity" name="quantity" min="1" required>
                    </div>
                    <div class="form-group">
                        <label for="percentDiscount">Percent Discount:</label>
                        <input type="number" id="percentDiscount" name="percentDiscount" step="0.01" min="0" max="100" required>
                    </div>
                    <div class="form-group">
                        <label for="startDate">Start Date:</label>
                        <input type="date" id="startDate" name="startDate" required>
                    </div>
                    <div class="form-group">
                        <label for="endDate">End Date:</label>
                        <input type="date" id="endDate" name="endDate" required>
                    </div>
                    <div class="form-group">
                        <label for="require">Require:</label>
                        <input type="text" id="require" name="require" required>
                    </div>
                    <div class="form-group">
                        <label for="tourId">Tour ID:</label>
                        <input type="text" id="tourId" name="tourId" required>
                    </div>
                    
                    <button type="submit">Create</button>
                </form>
                <a href="discount" class="back-link">Back to Manage Discounts</a>
            </div>
        </main>
        <!-- MAIN -->
    </section>
    <!-- CONTENT -->

    <script src="assests/js/script_profile.js"></script>
</body>
</html>
