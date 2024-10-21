<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Booking Details</title>
        <style>
            .booking-container {
                width: 80%;
                margin: auto;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .booking-header {
                text-align: center;
                margin-bottom: 20px;
            }
            .booking-details {
                display: flex;
                flex-direction: column;
            }
            .booking-details div {
                margin-bottom: 10px;
            }
            .tour-images {
                margin-top: 20px;
            }
            .tour-images img {
                width: 100px;
                height: 100px;
                margin-right: 10px;
            }
        </style>
    </head>
    <body>

        <div class="booking-container">
            <h2 class="booking-header">Booking Details</h2>

            <div class="booking-details">
                <div><strong>Booking ID:</strong> ${booking.book_Id}</div>
                <div><strong>Total Cost:</strong> ${booking.total_Cost}</div>
                <div><strong>Status:</strong> ${booking.book_Status}</div>
                <div><strong>Customer ID:</strong> ${booking.cus_Id}</div>
                <div><strong>Tour Name:</strong> ${booking.tour_Name}</div>
                <div><strong>Tour Date:</strong> ${booking.tour_Date}</div>
                <div><strong>Cancel Date:</strong> ${booking.cancel_Date != null ? booking.cancel_Date : 'Not Canceled'}</div>
                <div><strong>Booking Details:</strong> ${booking.booking_Detail}</div>
                <div><strong>Option Name:</strong> ${booking.option_Name}</div>

                <!-- Displaying Tour Images -->
                <div class="tour-images">
                    <strong>Tour Images:</strong>
                    <br/>
                    <c:forEach var="imgUrl" items="${booking.tour_Img}">
                        <img src="${imgUrl}" alt="Tour Image" />
                    </c:forEach>
                </div>
            </div>
        </div>

    </body>
</html>
