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

            /* Reply Section */
            .reply-section {
                margin-top: 20px;
                padding-left: 20px;
                border-left: 2px solid #f39c12;
                display: none; /* Ban đầu ẩn */
            }

            .reply-item {
                display: flex;
                align-items: flex-start;
                margin-bottom: 15px;
            }

            .reply-avatar {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                background-color: #e0e0e0;
                margin-right: 10px;
            }

            .reply-content {
                background-color: #f0f2f5;
                padding: 10px 15px;
                border-radius: 18px;
                max-width: 80%;
                font-size: 14px;
                color: #333;
                line-height: 1.5;
                position: relative;
            }

            .reply-content::after {
                content: '';
                position: absolute;
                top: 15px;
                left: -8px;
                border-width: 8px;
                border-style: solid;
                border-color: transparent #f0f2f5 transparent transparent;
            }

            .reply-info {
                font-size: 12px;
                color: #888;
                margin-top: 5px;
            }

            .reply-button, .view-reply-button {
                background-color: #007bff;
                color: #fff;
                padding: 8px 16px;
                border: none;
                border-radius: 20px;
                cursor: pointer;
                margin-top: 15px;
                font-size: 14px;
            }

            .reply-button:hover, .view-reply-button:hover {
                background-color: #0056b3;
            }

            .reply-form {
                display: none;
                margin-top: 10px;
                padding-left: 50px;
            }

            .reply-textarea {
                width: 100%;
                height: 60px;
                padding: 8px;
                border-radius: 18px;
                border: 1px solid #ddd;
                margin-bottom: 10px;
                font-size: 14px;
                resize: none;
            }

            .reply-submit {
                background-color: #28a745;
                color: #fff;
                padding: 8px 12px;
                border: none;
                border-radius: 20px;
                cursor: pointer;
                font-size: 14px;
            }

            .reply-submit:hover {
                background-color: #218838;
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

            .view-reply-section {
                text-align: right;
                margin-top: 10px;
            }
        </style>

        <script>
            $(document).ready(function () {
                // Khi click vào nút View Provider Reply, toggle hiển thị phần reply và đổi tên nút
                $(".view-reply-button").click(function () {
                    var replySection = $(this).closest('.review-item').find(".reply-section");
                    replySection.toggle();

                    if (replySection.is(":visible")) {
                        $(this).text("Hide Provider Reply");
                    } else {
                        $(this).text("View Provider Reply");
                    }
                });

                // Khi click vào nút Reply, toggle hiển thị form nhập liệu
                $(".reply-button").click(function () {
                    $(this).closest('.review-footer').find(".reply-form").toggle();
                });
            });
        </script>
    </head>
    <body>

        <div class="container-fluid">
            <div class="review-section">

                <!-- Thêm phần h1 hiển thị tiêu đề "Review From User" -->
                <h1>Review From User</h1>

                <c:choose>
                    <c:when test="${not empty reviews}">
                        <c:forEach var="review" items="${reviews}">
                            <div class="review-item">
                                <div class="review-header">
                                    <div class="review-header-left">
                                        <div class="review-avatar"></div>
                                        <div class="review-user-info">
                                            <!-- Hiển thị tên đầy đủ -->
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

                                <!-- Nút xem phần Provider Reply (Hiển thị cho tất cả mọi người) -->
                                <div class="view-reply-section">
                                    <button class="view-reply-button">View Provider Reply</button>
                                </div>

                                <!-- Phần Reply ban đầu ẩn đi, chỉ hiển thị khi nhấn nút -->
                                <div class="reply-section">
                                    <c:forEach var="reply" items="${review.replies}">
                                        <div class="reply-item">
                                            <div class="reply-avatar"></div>
                                            <div>
                                                <div class="reply-content">
                                                    <c:out value="${reply.reply_Content}" />
                                                </div>
                                                <div class="reply-info">
                                                    <em>By Provider</em>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <!-- Nút reply và form chỉ hiển thị cho Provider của tour -->
                                <c:if test="${isProviderOfTour}">
                                    <div class="review-footer">
                                        <button class="reply-button">Reply</button>
                                        <form class="reply-form" method="POST" action="ViewTourReview">
                                            <textarea class="reply-textarea" name="reply_Content" placeholder="Your reply..."></textarea>

                                            <!-- Hidden input cho reviewId và tourId để truyền giá trị đúng -->
                                            <input type="hidden" name="reviewId" value="${review.review_Id}" />
                                            <input type="hidden" name="tourId" value="${param.id}" />

                                            <input type="submit" class="reply-submit" value="Submit Reply" />
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="no-review">Không có review nào cho tour này.</div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </body>
</html>
