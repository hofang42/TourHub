<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tour Reviews</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <style>
            /* Review Section Styling */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
            }

            .review-section {
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                border: 1px solid #e0e0e0;
                margin-bottom: 30px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 800px;
                margin: 20px auto;
            }

            .review-item {
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                margin-bottom: 20px;
                border: 1px solid #e0e0e0;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
                width: 100%;
            }

            .review-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 10px;
            }

            .review-avatar {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                background-color: #e0e0e0;
                margin-right: 15px;
            }

            .review-user-info {
                font-size: 16px;
                font-weight: bold;
                color: #333333;
            }

            .review-rating {
                color: #f39c12;
                font-size: 18px;
                display: flex;
                gap: 4px;
            }

            .review-content {
                font-size: 15px;
                line-height: 1.7;
                margin-bottom: 10px;
                color: #333333;
            }

            .like-button {
                background-color: #007bff;
                color: #fff;
                padding: 5px 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
                margin-top: 10px;
            }

            .like-button:hover {
                background-color: #0056b3;
            }

            h1 {
                font-size: 24px;
                color: #333;
                text-align: center;
                margin-bottom: 30px;
            }

            .no-review {
                text-align: center;
                font-size: 16px;
                color: #888;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <div class="review-section">

                <h1>Review From User</h1>

                <c:choose>
                    <c:when test="${not empty reviews}">
                        <c:forEach var="review" items="${reviews}">
                            <div class="review-item">
                                <div class="review-header">
                                    <div class="review-header-left">
                                        <div class="review-avatar"></div>
                                        <div class="review-user-info">
                                            <c:out value="${review.first_Name}" />
                                            <c:out value="${review.last_Name}" />
                                        </div>
                                    </div>
                                    <div class="review-rating">
                                        <c:forEach var="i" begin="1" end="${review.rating_Star}">
                                            ★
                                        </c:forEach>
                                    </div>
                                </div>

                                <div class="review-content">
                                    <c:out value="${review.comment}" />
                                </div>

                                <div class="like-section">
                                    <span id="like-count-${review.review_Id}">${review.likeCount}</span> Likes
                                    <button class="like-button" onclick="likeReview(${review.review_Id})">Like</button>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="no-review">Không có review nào cho tour này.</div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <script>
            function likeReview(reviewId) {
                $.ajax({
                    type: 'POST',
                    url: 'LikeReviewServlet', // Ensure this servlet handles the like functionality
                    data: {reviewId: reviewId},
                    success: function (response) {
                        if (response.trim() === "Success") {
                            // Fetch updated like count from the server
                            $.ajax({
                                type: 'GET',
                                url: 'GetLikeCountServlet', // New servlet to get the like count
                                data: {reviewId: reviewId},
                                success: function (likeCountResponse) {
                                    // Update the like count on the page
                                    $('#like-count-' + reviewId).text(likeCountResponse);
                                }
                            });
                            alert('You liked this review!');
                        } else {
                            alert('Failed to like the review. Please try again.');
                        }
                    },
                    error: function (xhr, status, error) {
                        alert('Error liking review: ' + error);
                    }
                });
            }
        </script>

    </body>
</html>
