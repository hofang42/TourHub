<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    /* Đảm bảo phần review-section chiếm toàn bộ chiều rộng của trang */
    .review-section {
        padding: 20px;
        background-color: #f8f9fa;
        border-radius: 8px;
        border: 1px solid #e0e0e0;
        margin-bottom: 30px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        width: 100%; /* Chiếm toàn bộ chiều rộng */
        max-width: 100vw; /* Bỏ giới hạn chiều rộng */
        margin: 0 auto;
    }

    /* Đảm bảo mỗi review-item cũng rộng ra */
    .review-item {
        padding: 20px;
        background-color: #ffffff;
        border-radius: 8px;
        margin-bottom: 20px;
        border: 1px solid #e0e0e0;
        display: flex;
        flex-direction: column;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
        width: 100%; /* Chiếm toàn bộ chiều ngang */
        max-width: 100%; /* Loại bỏ giới hạn chiều ngang */
    }

    /* Phần tiêu đề của review */
    .review-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }

    /* Phần chứa avatar và tên người review */
    .review-header-left {
        display: flex;
        align-items: center;
    }

    /* Avatar của người review */
    .review-avatar {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        background-color: #e0e0e0;
        margin-right: 15px;
    }

    /* Thông tin người review */
    .review-user-info {
        font-size: 16px;
        font-weight: 700;
        color: #333333;
    }

    /* Xếp hạng sao của review */
    .review-rating {
        color: #f39c12;
        font-size: 18px;
        display: flex; /* Sử dụng flexbox để xếp các ngôi sao theo chiều ngang */
        gap: 4px; /* Khoảng cách giữa các ngôi sao */
    }

    /* Nội dung chính của review */
    .review-content {
        font-size: 15px;
        line-height: 1.7;
        margin-bottom: 10px;
        color: #333333;
    }

    /* Thông báo khi không có review */
    .no-review {
        text-align: center;
        font-size: 16px;
        color: #888;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        border: 1px solid #e0e0e0;
        margin-bottom: 30px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
    }

</style>

<div class="container-fluid"> <!-- Đảm bảo phần container chiếm toàn bộ chiều ngang -->
    <div class="review-section">
        <c:choose>
            <c:when test="${not empty reviews}">
                <c:forEach var="review" items="${reviews}">
                    <div class="review-item">
                        <div class="review-header">
                            <div class="review-header-left">
                                <!-- Avatar Placeholder (thay bằng ảnh thật nếu có) -->
                                <div class="review-avatar"></div>
                                <div class="review-user-info">
                                    <c:out value="${review.user_Id}" /> <!-- Tên người dùng -->
                                </div>
                            </div>
                            <div class="review-rating">
                                <!-- Hiển thị số sao từ trái sang phải -->
                                <c:forEach var="i" begin="1" end="${review.rating_Star}">
                                    ★
                                </c:forEach>
                            </div>
                        </div>

                        <div class="review-content">
                            <c:out value="${review.comment}" />
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
