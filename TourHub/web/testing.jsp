<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Notifications</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>Notifications</h1>
    <div id="notificationList">
        <!-- Notifications will be displayed here -->
    </div>

    <script>
        const userId = 11;  // Replace with the actual logged-in user ID

        // Function to fetch and display notifications
        function fetchNotifications() {
            $.ajax({
                url: 'checkNotifications',
                type: 'GET',
                dataType: 'json',  // Expect JSON response
                data: { user_Id: userId },
                success: function(response) {
                    $('#notificationList').empty();  // Clear the notification list
                    
                    if (response.length > 0) {
                        // If there are notifications, display them
                        response.forEach(notification => {
                            $('#notificationList').append(
                                `<div>
                                    <strong>ID:</strong> ${notification.notificationId} <br>
                                    <strong>Message:</strong> ${notification.message} <br>
                                    <strong>Date Sent:</strong> ${notification.dateSent} <br>
                                    <strong>Status:</strong> ${notification.isRead ? 'Read' : 'Unread'}
                                 </div><br>`
                            );
                        });
                    } else {
                        $('#notificationList').append('<p>No new notifications.</p>');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Error fetching notifications:', error);
                }
            });
        }

        // Poll for new notifications every 5 seconds
        setInterval(fetchNotifications, 5000);

        // Initial fetch on page load
        fetchNotifications();
    </script>
</body>
</html>

