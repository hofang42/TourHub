<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Comment" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Tour Comments</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .comment-section {
                width: 100%;
                max-width: 700px;
                margin: 20px auto;
                padding: 15px;
            }

            /* Comment item styles */
            .comment-item {
                background-color: #f0f2f5;
                padding: 10px;
                border-radius: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                position: relative;
            }

            /* Reply styles (thụt lề so với comment) */
            .reply-item {
                margin-left: 50px; /* Tăng khoảng cách cho reply */
                background-color: #ffffff;
                padding: 10px;
                border-radius: 8px;
                margin-bottom: 10px;
                border: 1px solid #ddd;
                position: relative;
            }

            /* Đường nối từ comment đến reply */
            .reply-item::before {
                content: '';
                position: absolute;
                left: -30px;
                top: 0;
                bottom: 0;
                width: 2px;
                background-color: #ddd;
            }

            .comment-avatar {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                background-color: #ddd;
                margin-right: 10px;
            }

            .comment-body {
                flex: 1;
            }

            .comment-header {
                font-weight: bold;
                margin-bottom: 5px;
                font-size: 14px;
                text-align: left; /* Đặt phần tên căn về bên trái */
            }

            .comment-text {
                font-size: 14px;
                margin-bottom: 10px;
                text-align: left; /* Đặt nội dung bình luận căn về bên trái */
            }

            /* Reply button styling */
            .reply-btn {
                font-size: 12px;
                color: #007bff;
                cursor: pointer;
                display: inline-block;
                margin-top: 5px;
            }

            /* Reply input form, initially hidden */
            .reply-input {
                margin-top: 10px;
                display: none;
            }

            .reply-input textarea {
                width: 100%;
                height: 60px;
                margin-bottom: 10px;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }

            /* Submit button styles */
            .submit-btn {
                background-color: #1877f2;
                color: white;
                border: none;
                padding: 8px 12px;
                border-radius: 5px;
                font-size: 14px;
                cursor: pointer;
            }

            .submit-btn:hover {
                background-color: #145dbf;
            }

            /* No comment styling */
            .no-comment {
                text-align: center;
                margin-bottom: 20px;
                font-size: 14px;
                color: #888;
            }

            /* Divider between comments */
            .comment-divider {
                height: 1px;
                background-color: #ccc;
                margin: 10px 0;
            }
        </style>
    </head>
    <body>
        <div class="comment-section">
            <c:choose>
                <c:when test="${empty comments}">
                    <div class="no-comment">No comments yet. Be the first to comment!</div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="comment" items="${comments}">
                        <!-- Chỉ hiển thị các comment gốc (parentCommentId == null) -->
                        <c:if test="${comment.parentCommentId == null}">
                            <div class="comment-item">
                                <div class="d-flex">
                                    <div class="comment-avatar"></div> <!-- Placeholder for avatar -->
                                    <div class="comment-body">
                                        <div class="comment-header">${comment.firstName} ${comment.lastName}</div>
                                        <div class="comment-text">${comment.commentText}</div>

                                        <!-- Chỉ hiển thị nút reply nếu người dùng đã đăng nhập -->
                                        <c:choose>
                                            <c:when test="${empty currentUser}">
                                                <!-- Người dùng chưa đăng nhập, không hiển thị form reply -->
                                            </c:when>
                                            <c:otherwise>
                                                <div class="reply-btn" onclick="showReplyForm(${comment.commentId})">Reply</div>
                                                <div id="replyForm-${comment.commentId}" class="reply-input">
                                                    <textarea id="replyText-${comment.commentId}" placeholder="Write your reply..."></textarea>
                                                    <button class="submit-btn" onclick="submitReply('${tourId}', ${comment.commentId})">Submit Reply</button>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>

                                <!-- Hiển thị reply cho comment này -->
                                <c:forEach var="reply" items="${comments}">
                                    <c:if test="${reply.parentCommentId == comment.commentId}">
                                        <div class="reply-item d-flex">
                                            <div class="comment-avatar"></div> <!-- Placeholder for avatar -->
                                            <div class="comment-body">
                                                <div class="comment-header">${reply.firstName} ${reply.lastName}</div>
                                                <div class="comment-text">${reply.commentText}</div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>

                                <!-- Đường phân cách cho mỗi comment -->
                                <div class="comment-divider"></div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <!-- Form nhập bình luận mới -->
            <c:choose>
                <c:when test="${empty currentUser}">
                    <div class="no-comment">You need to login to comment.</div>
                </c:when>
                <c:otherwise>
                    <div class="comment-item">
                        <div class="d-flex">
                            <div class="comment-avatar"></div> <!-- Placeholder for avatar -->
                            <div class="comment-body">
                                <textarea id="newCommentText" placeholder="Write your comment..." rows="3" class="form-control"></textarea>
                                <button class="submit-btn mt-2" onclick="submitComment('${tourId}')">Submit Comment</button>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Bootstrap JS (optional for advanced Bootstrap components) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

        <script>
                                    function showReplyForm(commentId) {
                                        document.getElementById('replyForm-' + commentId).style.display = 'block';
                                    }

                                    function submitReply(tourId, parentCommentId) {
                                        var replyText = document.getElementById('replyText-' + parentCommentId).value;

                                        if (replyText.trim() !== '') {
                                            fetch('CommentServlet', {
                                                method: 'POST',
                                                headers: {
                                                    'Content-Type': 'application/x-www-form-urlencoded'
                                                },
                                                body: 'commentText=' + encodeURIComponent(replyText) +
                                                        '&parentCommentId=' + encodeURIComponent(parentCommentId) +
                                                        '&tourId=' + encodeURIComponent(tourId)
                                            }).then(response => response.text())
                                                    .then(result => {
                                                        if (result.trim() === "Success") {
                                                            alert('Reply submitted');
                                                            location.reload();
                                                        } else {
                                                            alert('Error submitting reply.');
                                                        }
                                                    }).catch(error => {
                                                console.error('Error submitting reply:', error);
                                            });
                                        } else {
                                            alert('Please enter a reply');
                                        }
                                    }

                                    function submitComment(tourId) {
                                        var commentText = document.getElementById('newCommentText').value;

                                        if (commentText.trim() !== '') {
                                            fetch('CommentServlet', {
                                                method: 'POST',
                                                headers: {
                                                    'Content-Type': 'application/x-www-form-urlencoded'
                                                },
                                                body: 'commentText=' + encodeURIComponent(commentText) + '&tourId=' + encodeURIComponent(tourId)
                                            }).then(response => response.text())
                                                    .then(result => {
                                                        if (result.trim() === "Success") {
                                                            alert('Comment submitted');
                                                            location.reload();
                                                        } else {
                                                            alert('Error submitting comment.');
                                                        }
                                                    }).catch(error => {
                                                console.error('Error submitting comment:', error);
                                            });
                                        } else {
                                            alert('Please enter a comment');
                                        }
                                    }
        </script>
    </body>
</html>
