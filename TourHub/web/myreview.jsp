<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Review" %>
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
        <title>My Reviews</title>
        <style>
            body {
                background-color: #ffffff;
            }
            .star-rating {
                font-size: 1.5em;
                color: #ffd700;
            }
            .star {
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="index.jsp" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">TourHub</span>
            </a>
            <ul class="side-menu top">
                <li><a href="user-profile.jsp"><i class='bx bxs-dashboard'></i> User Information</a></li>
                <li><a href="user-booking.jsp"><i class='bx bxs-shopping-bag-alt'></i> My Booking</a></li>
                <li><a href="user-chat.jsp"><i class='bx bxs-message-dots'></i> Message</a></li>
                <li class="active"><a href="SubmitReview"><i class='bx bxs-star'></i> Review Tours</a></li>
            </ul>
            <ul class="side-menu">
                <li><a href="#"><i class='bx bxs-cog'></i> Settings</a></li>
                <li><a href="logout" class="logout"><i class='bx bxs-log-out-circle'></i> Logout</a></li>
            </ul>
        </section>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <nav>
                <i class='bx bx-menu'></i>
                <form action="#">
                    <div class="form-input">
                        <input type="search" placeholder="Searching for tour...">
                        <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                    </div>
                </form>
                <a href="#" class="notification"><i class='bx bxs-bell'></i> <span class="num">8</span></a>
                <a href="#" class="profile"><img src="img/people.png"></a>
            </nav>

            <div class="container mt-3">
                <ul class="nav nav-pills">
                    <li class="nav-item"><a class="nav-link" href="SubmitReview">Review Tours</a></li>
                    <li class="nav-item"><a class="nav-link active" href="myreviews">My Reviews</a></li>
                </ul>
            </div>

            <main class="container mt-5">
                <h2>Your Reviews</h2>

                <!-- Display Success or Error Messages with Auto-Hide -->
                <c:if test="${not empty reviewSuccess}">
                    <div class="alert alert-success" role="alert" id="alertMessage">${reviewSuccess}</div>
                </c:if>
                <c:if test="${not empty reviewError}">
                    <div class="alert alert-danger" role="alert" id="alertMessage">${reviewError}</div>
                </c:if>

                <c:if test="${empty userReviews}">
                    <p class="alert alert-info">You have no reviews to display.</p>
                </c:if>

                <c:if test="${not empty userReviews}">
                    <div class="row">
                        <c:forEach var="review" items="${userReviews}">
                            <div class="col-md-4">
                                <div class="tour-card">
                                    <img src="${tourImages[review.review_Id]}" class="tour-image" alt="Tour Image">
                                    <div class="tour-details">
                                        <h3 class="tour-title">${tourNames[review.review_Id]}</h3>
                                        <p class="tour-info">
                                            <strong>Rating:</strong> ${review.rating_Star} / 5<br>
                                            <strong>Comment:</strong> ${review.comment != null ? review.comment : "No comment"}<br>
                                        </p>
                                        <div class="d-flex justify-content-between">
                                            <button class="btn btn-primary" onclick="openEditReviewPopup('${review.review_Id}', '${review.comment}', ${review.rating_Star})">Edit</button>
                                            <form action="myreviews" method="post" style="display:inline;">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="reviewId" value="${review.review_Id}">
                                                <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this review?');">Delete</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <!-- Edit Review Popup with 5-Star Rating -->
                <div id="editReviewPopup" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeEditReviewPopup()">&times;</span>
                        <h2>Edit Review</h2>
                        <form action="myreviews" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="reviewId" id="editReviewId">

                            <!-- Star Rating Section -->
                            <label>Rating:</label>
                            <div class="star-rating">
                                <span class="star" data-value="1">&#9733;</span>
                                <span class="star" data-value="2">&#9733;</span>
                                <span class="star" data-value="3">&#9733;</span>
                                <span class="star" data-value="4">&#9733;</span>
                                <span class="star" data-value="5">&#9733;</span>
                            </div>
                            <input type="hidden" id="editRatingStar" name="ratingStar" value="0" required>

                            <label>Comment:</label>
                            <textarea name="comment" id="editComment" class="form-control" rows="3" required></textarea><br>
                            <button type="submit" class="btn btn-primary btn-block">Update</button>
                        </form>
                    </div>
                </div>
            </main>

            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const alertMessage = document.getElementById("alertMessage");
                    if (alertMessage) {
                        setTimeout(function () {
                            alertMessage.style.opacity = "0";
                        }, 5000); // Hide after 5 seconds
                    }
                });

                function openEditReviewPopup(reviewId, comment, ratingStar) {
                    document.getElementById('editReviewId').value = reviewId;
                    document.getElementById('editComment').value = comment;
                    document.getElementById('editRatingStar').value = ratingStar;

                    const stars = document.querySelectorAll(".star-rating .star");
                    stars.forEach(star => {
                        star.classList.remove("selected");
                        if (star.getAttribute("data-value") <= ratingStar) {
                            star.classList.add("selected");
                        }
                    });

                    const modal = document.getElementById('editReviewPopup');
                    modal.classList.add('show');
                }

                function closeEditReviewPopup() {
                    const modal = document.getElementById('editReviewPopup');
                    modal.classList.remove('show');
                }

                // Star rating click events
                document.querySelectorAll(".star-rating .star").forEach(star => {
                    star.addEventListener("click", function () {
                        const ratingValue = this.getAttribute("data-value");
                        document.getElementById("editRatingStar").value = ratingValue;

                        document.querySelectorAll(".star-rating .star").forEach(s => {
                            s.classList.remove("selected");
                            if (s.getAttribute("data-value") <= ratingValue) {
                                s.classList.add("selected");
                            }
                        });
                    });
                });
            </script>
        </section>
        <!-- CONTENT -->
    </body>
</html>
