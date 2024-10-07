<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="assests/css/style_profile.css">

        <title>Booking</title>
        <style>
            #searchInput {
                width: 250px; /* Adjust the width */
                padding: 10px;
                margin: 10px 0;
                font-size: 16px;
                border: 1px solid #ddd; /* Border color */
                border-radius: 4px; /* Rounded corners */
                box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
                outline: none;
            }

            #searchInput:focus {
                border-color: #007bff; /* Change border color when focused */
                box-shadow: 0px 4px 8px rgba(0, 123, 255, 0.3); /* Focused shadow */
            }



            /* Table styling */
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                font-size: 18px;
                text-align: left;
            }

            /* Table header styling */
            thead th {
                background-color: #007bff; /* Header background color */
                color: white; /* Text color */
                padding: 12px 15px;
                cursor: pointer;
                font-weight: bold;
            }

            thead th:hover {
                background-color: #0056b3; /* Hover color for header */
            }

            tbody td {
                padding: 12px 15px;
                border-bottom: 1px solid #ddd;
            }

            /* Zebra striping for table rows */
            tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }

            /* Table row hover effect */
            tbody tr:hover {
                background-color: #f1f1f1;
            }

            /* Table cell content alignment */
            td {
                text-align: center;
            }

            /* Table border */
            table, th, td {
                border: 1px solid #ddd;
                border-collapse: collapse;
            }
            /* Button styling */
            .btn-accept {
                background-color: #28a745; /* Green background */
                color: white; /* White text */
                padding: 10px 20px; /* Padding around the button */
                border: none; /* Remove default border */
                border-radius: 5px; /* Rounded corners */
                cursor: pointer; /* Cursor changes to pointer on hover */
                font-size: 16px; /* Button text size */
                font-weight: bold; /* Bold text */
                transition: background-color 0.3s ease, transform 0.2s ease; /* Smooth hover effects */
            }

            /* Hover effect for button */
            .btn-accept:hover {
                background-color: #218838; /* Darker green on hover */
                transform: scale(1.05); /* Slight zoom on hover */
            }

            /* Focus effect for button */
            .btn-accept:focus {
                outline: none; /* Remove default outline */
                box-shadow: 0 0 0 2px #218838, 0 0 5px 2px rgba(0, 0, 0, 0.2); /* Custom focus shadow */
            }
            .btn-reject {
                background-color: #dc3545; /* Red background */
                color: white; /* White text */
                padding: 10px 20px; /* Padding around the button */
                border: none; /* Remove default border */
                border-radius: 5px; /* Rounded corners */
                cursor: pointer; /* Cursor changes to pointer on hover */
                font-size: 16px; /* Button text size */
                font-weight: bold; /* Bold text */
                transition: background-color 0.3s ease, transform 0.2s ease; /* Smooth hover effects */
            }

            /* Hover effect for button */
            .btn-reject:hover {
                background-color: #c82333; /* Darker red on hover */
                transform: scale(1.05); /* Slight zoom on hover */
            }

            /* Focus effect for button */
            .btn-reject:focus {
                outline: none; /* Remove default outline */
                box-shadow: 0 0 0 2px #c82333, 0 0 5px 2px rgba(0, 0, 0, 0.2); /* Custom focus shadow */
            }

        </style>

    </style>
</head>
<body>


    <!-- SIDEBAR -->
    <section id="sidebar">
        <a href="home" class="brand">
            <i class='bx bxs-smile'></i>
            <span class="text">TourHub</span>
        </a>
        <ul class="side-menu top">
            <li>
                <a href="user-profile.jsp">
                    <i class='bx bxs-dashboard' ></i>
                    <span class="text">User Information</span>
                </a>
            </li>
            <c:if test="${sessionScope.currentUser.role == 'Provider'}">
                <li  class="active">
                    <a href="bookings">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">Manage Booking</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.currentUser.role == 'Customer'}">
                <li  class="active">
                    <a href="">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">My Booking</span>
                    </a>
                </li>
            </c:if>
            <li>
                <a href="#">
                    <i class='bx bxs-message-dots' ></i>
                    <span class="text">Message</span>
                </a>
            </li>    
            <c:if test="${sessionScope.currentUser.role == 'Provider' || sessionScope.currentUser.role == 'Admin'}">
                <li class="">
                    <a href="${sessionScope.currentUser.role == 'Provider' ? '/Project_SWP/provider-analys' : 'admin-analysis.jsp'}">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>   
                <li class="dropdown-btn">
                    <a href="tour-management.jsp">
                        <i class='bx bxs-briefcase-alt' ></i>
                        <span class="text">Tour Management</span>
                    </a>
                </li>   
                <li>
                    <a href="payment.jsp">
                        <i class='bx bxs-credit-card'></i>
                        <span class="text">Payment</span>
                    </a>
                </li> 
            </c:if>
        </ul>
        <ul class="side-menu">
            <li>
                <a href="#">
                    <i class='bx bxs-cog' ></i>
                    <span class="text">Settings</span>
                </a>
            </li>
            <li>
                <a href="logout" class="logout">
                    <i class='bx bxs-log-out-circle' ></i>
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
            <i class='bx bx-menu' ></i>
            <a href="#" class="nav-link"></a>
            <form action="#">
                <div class="form-input">
                    <input type="search" placeholder="Searching for tour...">
                    <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                </div>
            </form>
            <input type="checkbox" id="switch-mode" hidden>
            <label for="switch-mode" class="switch-mode"></label>
            <a href="#" class="notification">
                <i class='bx bxs-bell' ></i>
                <span class="num">8</span>
            </a>
            <a href="#" class="profile">
                <img src="img/people.png">
            </a>
        </nav>
        <!-- NAVBAR -->

        <!-- MAIN -->
        <main>

            <div class="table-data">
                <div class="order">
                    <div class="head">
                        <h3>Pending Booking</h3>
                        <input type="text" id="searchInput" placeholder="Search bookings...">

                    </div>
                    <div class="recent_order">                           
                        <table id="bookingTable">
                            <thead>
                                <tr>
                                    <th onclick="sortTable(0)">ID  <i class='bx bx-filter'></i></th>                          
                                    <th onclick="sortTable(1)">Tour Name <i class='bx bx-filter'></i></th>                           
                                    <th onclick="sortTable(2)">Customer Name<i class='bx bx-filter'></i></th>                            
                                    <th onclick="sortTable(3)">Slot<i class='bx bx-filter'></i></th>                            
                                    <th onclick="sortTable(4)">Status</th>                            
                                    <th onclick="sortTable(5)">Total Cost<i class='bx bx-filter'></i></th>  
                                    <th>Manage</th>  
                                </tr>
                            </thead>
                            <tbody id="product_list">
                                <c:forEach items="${sessionScope.bookings}" var="booking">
                                    <tr>
                                        <c:set var="id" value="${id + 1}" />
                                        <td>${id}</td>
                                        <td>${booking.tourName}</td>
                                        <td>${booking.customerName}</td>
                                        <td>${booking.slotOrder}</td>
                                        <td style="color:
                                            <c:choose>
                                                <c:when test="${booking.bookStatus == 'Pending'}">#FFCC00</c:when>
                                                <c:otherwise>black</c:otherwise>
                                            </c:choose>">
                                            ${booking.bookStatus}
                                        </td>
                                        <td>${booking.totalCost} VND</td>
                                        <td>
                                            <button class="btn-accept" onclick="acceptBooking(${booking.bookId})">Accept</button>
                                            <button class="btn-reject" onclick="acceptBooking(${booking.bookId})">Reject</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- Enter data here -->
                </div>
            </div>
        </main>
        <!-- MAIN -->
    </section>
    <!-- CONTENT -->


    <script src="assests/js/script_profile.js"></script>
    <script>
                                                document.addEventListener('DOMContentLoaded', function () {
                                                    const burger = document.querySelector('.burger');
                                                    const navigation = document.querySelector('.navigation-admin');
                                                    const main = document.querySelector('.main-admin');
                                                    const profileCard = document.querySelector('.profile-card'); // Select the profile card

                                                    burger.addEventListener('click', function () {
                                                        navigation.classList.toggle('active');
                                                        main.classList.toggle('active');
                                                        profileCard.classList.toggle('active'); // Toggle the active class on the profile card
                                                    });
                                                });
                                                // Store the current sort direction for each column
                                                let sortDirection = [true, true, true, true, true, true];

// Search Functionality
// Function to remove diacritics from a string
                                                function removeDiacritics(str) {
                                                    const diacriticsMap = {
                                                        'à': 'a', 'á': 'a', 'ả': 'a', 'ã': 'a', 'ạ': 'a',
                                                        'â': 'a', 'ầ': 'a', 'ấ': 'a', 'ẩ': 'a', 'ẫ': 'a', 'ậ': 'a',
                                                        'ă': 'a', 'ằ': 'a', 'ắ': 'a', 'ẳ': 'a', 'ẵ': 'a', 'ặ': 'a',
                                                        'è': 'e', 'é': 'e', 'ẻ': 'e', 'ẽ': 'e', 'ẹ': 'e',
                                                        'ê': 'e', 'ề': 'e', 'ế': 'e', 'ể': 'e', 'ễ': 'e', 'ệ': 'e',
                                                        'ì': 'i', 'í': 'i', 'ỉ': 'i', 'ĩ': 'i', 'ị': 'i',
                                                        'ò': 'o', 'ó': 'o', 'ỏ': 'o', 'õ': 'o', 'ọ': 'o',
                                                        'ô': 'o', 'ồ': 'o', 'ố': 'o', 'ổ': 'o', 'ỗ': 'o', 'ộ': 'o',
                                                        'ơ': 'o', 'ờ': 'o', 'ớ': 'o', 'ở': 'o', 'ỡ': 'o', 'ợ': 'o',
                                                        'ù': 'u', 'ú': 'u', 'ủ': 'u', 'ũ': 'u', 'ụ': 'u',
                                                        'ư': 'u', 'ừ': 'u', 'ứ': 'u', 'ử': 'u', 'ữ': 'u', 'ự': 'u',
                                                        'ỳ': 'y', 'ý': 'y', 'ỷ': 'y', 'ỹ': 'y', 'ỵ': 'y',
                                                        'Đ': 'D', 'đ': 'd'
                                                    };

                                                    return str.split('').map(char => diacriticsMap[char] || char).join('');
                                                }

// Search Functionality with Diacritics Removal
                                                document.getElementById('searchInput').addEventListener('keyup', function () {
                                                    let input = removeDiacritics(document.getElementById('searchInput').value.toLowerCase());
                                                    let rows = document.getElementById('product_list').getElementsByTagName('tr');

                                                    for (let i = 0; i < rows.length; i++) {
                                                        let tourName = removeDiacritics(rows[i].getElementsByTagName('td')[1].textContent.toLowerCase());
                                                        let customerName = removeDiacritics(rows[i].getElementsByTagName('td')[2].textContent.toLowerCase());
                                                        let status = removeDiacritics(rows[i].getElementsByTagName('td')[4].textContent.toLowerCase());
                                                        let totalCost = rows[i].getElementsByTagName('td')[5].textContent.replace(/[^\d.-]/g, ''); // Extract numbers for cost search

                                                        // Check if input matches tour name, customer name, status, or total cost
                                                        if (tourName.includes(input) || customerName.includes(input) || status.includes(input) || totalCost.includes(input)) {
                                                            rows[i].style.display = '';
                                                        } else {
                                                            rows[i].style.display = 'none';
                                                        }
                                                    }
                                                });

// Sorting Functionality
                                                function sortTable(columnIndex) {
                                                    let table = document.getElementById("bookingTable");
                                                    let rows = Array.from(table.getElementsByTagName("tr")).slice(1); // Get all rows except the header
                                                    let isAscending = sortDirection[columnIndex]; // Check current sort direction for this column

                                                    let sortedRows = rows.sort((a, b) => {
                                                        let valA = a.getElementsByTagName("td")[columnIndex].textContent.trim();
                                                        let valB = b.getElementsByTagName("td")[columnIndex].textContent.trim();

                                                        // If sorting by numbers (like total cost or slot order)
                                                        if (columnIndex === 3 || columnIndex === 5) {
                                                            valA = parseFloat(valA.replace(/[^\d.-]/g, '')) || 0; // Remove non-numeric characters
                                                            valB = parseFloat(valB.replace(/[^\d.-]/g, '')) || 0;
                                                        }

                                                        // Compare values
                                                        if (valA < valB) {
                                                            return isAscending ? -1 : 1;
                                                        }
                                                        if (valA > valB) {
                                                            return isAscending ? 1 : -1;
                                                        }
                                                        return 0;
                                                    });

                                                    // Append sorted rows back into the table
                                                    let tbody = table.getElementsByTagName("tbody")[0];
                                                    for (let row of sortedRows) {
                                                        tbody.appendChild(row);
                                                    }

                                                    // Toggle the sort direction for the next click
                                                    sortDirection[columnIndex] = !isAscending;
                                                }

                                                function acceptBooking(bookId) {
                                                    console.log("Accepting booking ID:", bookId); // Debugging line

                                                    if (!bookId) {
                                                        console.error('No booking ID provided');
                                                        return;
                                                    }

                                                    fetch(`/Project_SWP/bookings?bookId=${bookId}`, {
                                                        method: 'POST',
                                                        headers: {
                                                            'Content-Type': 'application/json'
                                                        }
                                                    })
                                                            .then(response => {
                                                                if (!response.ok) {
                                                                    throw new Error('Network response was not ok. Status: ' + response.status);
                                                                }
                                                                return response.json();
                                                            })
                                                            .then(data => {
                                                                console.log(data.message);
                                                                // Optionally refresh the table or update the UI
                                                            })
                                                            .catch(error => {
                                                                console.error('There was a problem with the fetch operation:', error);
                                                            });
                                                }



    </script>
</body>
</html>