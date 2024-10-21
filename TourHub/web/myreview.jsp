<%@ page import="java.util.List" %>
<%@ page import="model.Review" %>
<%@ page import="model.Tour" %>
<%@ page import="model.User" %>
<%@ page import="DataAccess.ReviewDB" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="assests/css/style_profile.css">
        <link rel="stylesheet" href="assests/css/review.css">
        <title>Review Tours</title>
    </head>
    <style>
        .modal {
            display: none; /* ?n m?c ??nh */
            position: fixed; /* Gi? b?ng pop-up ? v? trí c? ??nh */
            z-index: 1; /* ??t lên trên các ph?n t? khác */
            left: 0;
            top: 0;
            width: 100%; /* Toàn b? chi?u r?ng */
            height: 100%; /* Toàn b? chi?u cao */
            overflow: auto; /* Cho phép cu?n n?u n?i dung v??t quá */
            background-color: rgb(0,0,0); /* Màu n?n ?en */
            background-color: rgba(0,0,0,0.4); /* N?n ?en v?i ?? trong su?t */
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% t? trên và c?n gi?a */
            padding: 20px;
            border: 1px solid #888;
            width: 80%; /* Có th? ?i?u ch?nh kích th??c */
        }

        .show {
            display: block; /* Hi?n b?ng pop-up khi có l?p "show" */
        }

    </style>
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
                    <a href="user-chat.jsp">
                        <i class='bx bxs-message-dots'></i>
                        <span class="text">Message</span>
                    </a>
                </li>
                <li class="active">
                    <a href="myreviews.jsp">
                        <i class='bx bxs-star'></i>
                        <span class="text">Review Tours</span>
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
            <div class="container mt-3">
                <ul class="nav nav-pills">
                    <li class="nav-item">
                        <a class="nav-link" href="reviewtour.jsp">Review Tours</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="myreview.jsp">My Reviews</a>
                    </li>
                </ul>
            </div>
            <main>
                <div class="container mt-5">
                    <h2>Your Reviews</h2>
                    <%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        // Redirect to login if the user is not logged in
        response.sendRedirect("login.jsp");
        return; // Stop further processing
    }

    ReviewDB reviewDB = new ReviewDB(); // Create instance of ReviewDB
    List<Review> userReviews = reviewDB.getUserReviews(currentUser.getUser_Id()); // Get user's reviews
                    %>
                    <div class="row">
                        <%
                            for (Review review : userReviews) {
                                Tour tour = reviewDB.getTourById(review.getTour_Id()); // Get the tour details
                        %>
                        <div class="col-md-4">
                            <div class="review-card">
                                <img src="<%= reviewDB.getTourImageUrl(review.getTour_Id()) %>" class="tour-image" alt="Tour Image"> 
                                <div class="tour-details">
                                    <h3 class="tour-title"><%= tour != null ? tour.getTour_Name() : "N/A" %></h3>
                                    <p class="tour-info">
                                        <strong>Rating:</strong> <%= review.getRating_Star() %> / 5<br>
                                        <strong>Comment:</strong> <%= review.getComment() != null ? review.getComment() : "No comment" %><br>
                                    </p>
                                    <div class="review-actions">
                                        <a href="javascript:void(0)" class="btn btn-primary" onclick="openEditReviewPopup(<%= review.getReview_Id() %>, '<%= review.getComment() %>', <%= review.getRating_Star() %>)">Edit</a>
                                        <a href="DeleteReview?reviewId=<%= review.getReview_Id() %>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this review?');">Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>

                    <!-- Edit Review Popup -->
                    <div id="editReviewPopup" class="modal">
                        <div class="modal-content">
                            <span class="close" onclick="closeEditReviewPopup()">&times;</span>
                            <h2>Edit Review</h2>
                            <form action="UpdateReview" method="post">
                                <input type="hidden" name="reviewId" id="editReviewId">
                                <label>Comment:</label>
                                <textarea name="comment" id="editComment" class="form-control" rows="3" required></textarea><br>
                                <label>Rating:</label>
                                <input type="number" name="ratingStar" id="editRatingStar" min="1" max="5" required>
                                <br>
                                <button type="submit" class="btn btn-primary btn-block">Update</button>
                            </form>
                        </div>
                    </div>

            </main>
            <script>
                function openEditReviewPopup(reviewId, comment, ratingStar) {
                    document.getElementById('editReviewId').value = reviewId;
                    document.getElementById('editComment').value = comment;
                    document.getElementById('editRatingStar').value = ratingStar;

                    const modal = document.getElementById('editReviewPopup');
                    modal.classList.add('show');
                }

                function closeEditReviewPopup() {
                    const modal = document.getElementById('editReviewPopup');
                    modal.classList.remove('show');
                }
            </script>
        </section>
        <!-- CONTENT -->
    </body>
</html>
