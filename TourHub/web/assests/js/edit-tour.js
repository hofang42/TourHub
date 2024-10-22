/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Automatically call calculateDuration when the page loads
window.onload = function () {
    calculateDuration();
}
function reloadData() {
    var date = document.getElementById("date").value;
    $.ajax({
        url: "/Project_SWP/provider-analys",
        type: "POST",
        data: {
            date: date
        },
        success: function (data) {
            // Assuming 'data' is a JSON object
            document.querySelector("#totalVisitValue").innerHTML = data.totalVisitATour || 0;
            document.querySelector("#visitTodayValue").innerHTML = data.visitToday || 0;
            document.querySelector("#bookingThisMonthValue").innerHTML = data.bookingThisMonth || 0;
        }
    });
}
function calculateDuration() {
    // Get the values of the start and end dates
    var startDate = document.getElementById("start_Date").value;
    var endDate = document.getElementById("end_Date").value;

    console.log("Start Date:", startDate);
    console.log("End Date:", endDate);

    // Reset the fields by default
    document.getElementById("day").value = 0;
    document.getElementById("night").value = 0;

    // Check if both dates are provided
    if (startDate && endDate) {
        // Parse the dates into Date objects
        var start = new Date(startDate);
        var end = new Date(endDate);

        // Normalize to midnight to prevent issues with time zones
        start.setHours(0, 0, 0, 0);
        end.setHours(0, 0, 0, 0);

        // Calculate the difference in time (milliseconds)
        var diffTime = end - start;

        console.log("Time Difference (milliseconds):", diffTime);

        // Check if end date is before start date
        if (diffTime < 0) {
            alert("End date cannot be before start date.");
            return; // Exit the function early
        }

        // Convert the time difference to days (1 day = 24*60*60*1000 milliseconds)
        var diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

        console.log("Difference in Days:", diffDays);

        // Set the day and night values
        document.getElementById("day").value = diffDays; // Total days
        document.getElementById("night").value = Math.max(0, diffDays - 1); // Nights = days - 1
        console.log(diffDays);
    }
}


function removeImage(tourId, imageToRemove) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/Project_SWP/provider-management?action=remove-image", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                // Successfully removed the image, remove the element from the DOM
                const imageContainer = document.getElementById(`image-${imageToRemove}`);
                if (imageContainer) {
                    imageContainer.remove();
                }
            } else if (xhr.status === 404) {
                alert("Image not found or already removed.");
            } else if (xhr.status === 500) {
                alert("An error occurred while trying to remove the image.");
            }
        }
    };

    // Send AJAX request with tourId and imageToRemove parameters
    xhr.send(`tourId=${encodeURIComponent(tourId)}&imageToRemove=${encodeURIComponent(imageToRemove)}`);
}

