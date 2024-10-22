<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="DataAccess.UserDB"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="utils.MoneyFormatter" %>
<jsp:useBean id="currentUser" class="model.User" scope="session" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="assests/css/style_profile.css">       
        <link href="assests/css/customer.css" rel="stylesheet" />

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
                    <a href="pending-bookings">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">Manage Booking</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.currentUser.role == 'Customer'}">
                <li  class="active">
                    <a href="user-booking.jsp">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">Manage Booking</span>
                    </a>
                </li>
            </c:if>
            <li>
                <a href="user-chat.jsp">
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
                    <a href="my-tour">
                        <i class='bx bxs-briefcase-alt' ></i>
                        <span class="text">My Tour</span>
                    </a>
                </li>   
                <li>
                    <a href="provider-management?action=show-withdraw-page">
                        <i class='bx bxs-credit-card'></i>
                        <span class="text">Widthdraw</span>
                    </a>
                </li> 
                <li>
                    <a href="discount">
                        <i class='bx bxs-discount'></i>
                        <span class="text">Manage Discounts</span>
                    </a>
                </li>
            </c:if>                
            <c:if test="${sessionScope.currentUser.role == 'Customer'}">
                <li>
                    <a href="reviewtour.jsp">
                        <i class='bx bxs-star'></i>
                        <span class="text">Review Tours</span>
                    </a>
                </li>
            </c:if>
            <%-- <li>
                <a href="#">
                    <i class='bx bxs-doughnut-chart' ></i>
                    <span class="text">Analytics</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <i class='bx bxs-group' ></i>
                    <span class="text">Team</span>
                </a>
            </li> --%>
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
                        <h3>Booking</h3>
                        <input type="text" id="searchInput" placeholder="Search bookings...">
                    </div>
                    <!-- Message Display -->
                    <c:if test="${not empty requestScope.successMessage}">
                        <div class="alert alert-success">
                            ${requestScope.successMessage}
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.errorMessage}">
                        <div class="alert alert-danger">
                            ${requestScope.errorMessage}
                        </div>
                    </c:if>
                    <div class="recent_order">                           
                        <table id="bookingTable">
                            <thead>
                                <tr>
                                    <!--<th>ID  <i class='bx bx-filter'></i></th>-->  
                                    <th onclick="sortTable(0)">ID  <i class='bx bx-filter'></i></th>  
                                    <th onclick="sortTable(1)">Tour Name <i class='bx bx-filter'></i></th>                           
                                    <th onclick="sortTable(2)">Customer Name<i class='bx bx-filter'></i></th>  
                                    <th onclick="sortTable(3)">Booked Date<i class='bx bx-filter'></i></th>  
                                    <th onclick="sortTable(4)">Slot<i class='bx bx-filter'></i></th>                            
                                    <th onclick="sortTable(5)">Status</th>                            
                                    <th onclick="sortTable(6)">Total Cost<i class='bx bx-filter'></i></th>  
                                    <!--<th>Manage</th>-->  
                                </tr>
                            </thead>
                            <tbody id="product_list">
                                <c:forEach items="${sessionScope.bookings}" var="booking">
                                    <c:set var="id" value="${id + 1}"></c:set>
                                        <tr>   
                                            <td>${id}</td>                                        
                                        <td>${booking.tourName}</td>
                                        <td>${booking.customerName}</td>
                                        <td>${booking.bookDate}</td>
                                        <td>${booking.slotOrder}</td>
                                        <td style="color:
                                            <c:choose>
                                                <c:when test="${booking.bookStatus == 'Pending'}">#FFCC00</c:when>
                                                <c:when test="${booking.bookStatus == 'Booked'}">#00ff00</c:when>
                                                <c:when test="${booking.bookStatus == 'Cancelled'}">#FF0000</c:when>
                                                <c:when test="${booking.bookStatus == 'Refunded'}">#FFA500</c:when>
                                                <c:otherwise>black</c:otherwise>
                                            </c:choose>">
                                            ${booking.bookStatus}
                                        </td>
                                        <td>
                                            <c:if test="${booking.totalCost != null}">
                                                ${MoneyFormatter.formatToVietnameseCurrency(booking.totalCost)} <!-- Call the formatter -->
                                            </c:if>
                                        </td>

                                        <!--                                        <td>
                                                                                    <form action="pending-bookings" method="POST" style="display: inline;">
                                                                                        <input type="hidden" name="bookingId" value="${booking.bookId}">
                                                                                        <button type="submit" name="action" value="accept" class="btn-accept">Accept</button>
                                                                                        <button type="submit" name="action" value="reject" class="btn-reject">Reject</button>
                                                                                    </form>
                                                                                </td>-->
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

//                                        document.getElementById('searchInput').addEventListener('keyup', function () {
//                                            let input = removeDiacritics(document.getElementById('searchInput').value.toLowerCase());
//                                            let rows = document.getElementById('product_list').getElementsByTagName('tr');
//
//                                            for (let i = 0; i < rows.length; i++) {
//                                                let cells = rows[i].getElementsByTagName('td');
//                                                let rowMatches = false; // Flag to determine if the row should be shown
//
//                                                for (let j = 0; j < cells.length; j++) {
//                                                    let cellText = removeDiacritics(cells[j].textContent.toLowerCase());
//
//                                                    // Check if the cell contains the search input
//                                                    if (cellText.includes(input)) {
//                                                        rowMatches = true;
//                                                        break; // No need to check other cells if one matches
//                                                    }
//                                                }
//
//                                                // Show or hide the row based on whether it matched the search input
//                                                if (rowMatches) {
//                                                    rows[i].style.display = '';
//                                                } else {
//                                                    rows[i].style.display = 'none';
//                                                }
//                                            }
//                                        });
                                        document.getElementById('searchInput').addEventListener('keyup', function () {
                                            let input = removeDiacritics(document.getElementById('searchInput').value.toLowerCase());
                                            let rows = document.getElementById('product_list').getElementsByTagName('tr');

                                            // Loop through each row in the table body
                                            for (let i = 0; i < rows.length; i++) {
                                                let cells = rows[i].getElementsByTagName('td');

                                                // Check the specific column for "Slot" (column index 4)
                                                let slotCell = removeDiacritics(cells[4].textContent.toLowerCase());

                                                // Show the row if the input matches the slot, otherwise hide it
                                                if (slotCell.includes(input)) {
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

                                                // Handle different types of sorting
                                                if (columnIndex === 0) {  // ID column: numeric sorting
                                                    valA = parseInt(valA) || 0;
                                                    valB = parseInt(valB) || 0;
                                                } else if (columnIndex === 3) {  // Booked Date column: date sorting
                                                    valA = new Date(valA);
                                                    valB = new Date(valB);
                                                } else if (columnIndex === 6) {  // Total Cost column: numeric sorting
                                                    valA = parseFloat(valA.replace(/[^\d.-]/g, '')) || 0;  // Remove non-numeric characters for cost
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

                                        function refreshPage() {
                                            // Reload the current page
                                            window.location.reload();
                                        }

    </script>
</body>
</html>