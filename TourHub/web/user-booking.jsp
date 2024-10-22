<%@ page contentType="text/html; charset=UTF-8" %> <%@ page import="model.User"
%> <%@ page import="DataAccess.UserDB"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="currentUser" class="model.User" scope="session" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- Boxicons -->
    <link
      href="https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css"
      rel="stylesheet"
    />
    <!-- My CSS -->
    <link rel="stylesheet" href="assests/css/style_profile.css" />
    <link href="assests/css/customer.css" rel="stylesheet" />

    <title>User Profile</title>
  </head>
  <body>
    <!-- SIDEBAR -->
    <%@include file="includes/user-sidebar.jsp" %>

    <!-- CONTENT -->
    <section id="content">
      <!-- NAVBAR -->
      <nav>
        <i class="bx bx-menu"></i>
        <a href="#" class="nav-link"></a>
        <form action="#">
          <div class="form-input">
            <input type="search" placeholder="Searching for tour..." />
            <button type="submit" class="search-btn">
              <i class="bx bx-search"></i>
            </button>
          </div>
        </form>
        <input type="checkbox" id="switch-mode" hidden />
        <label for="switch-mode" class="switch-mode"></label>
        <a href="#" class="notification">
          <i class="bx bxs-bell"></i>
          <!-- <span class="num">8</span> -->
        </a>
        <div class="image-container">
          <img
            src="assests/images/avatar.jpg"
            alt="User Avatar"
            class="avatar"
          />
        </div>
      </nav>
      <!-- NAVBAR -->

      <!-- MAIN -->
      <main>
        <div class="table-data">
          <div class="order">
            <div class="head">
              <h3>My Bookings</h3>
            </div>
            <c:choose>
              <c:when test="${currentUser == null}">
                <c:redirect url="index.jsp" />
              </c:when>
              <c:otherwise>
                <div class="profile-card">
                  <table>
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Tour Name</th>
                        <th>Created Date</th>
                        <th>Slot</th>
                        <th>Total Cost</th>
                        <th>Status</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:choose>
                        <c:when test="${empty bookings}">
                          <tr>
                            <td colspan="7">No bookings found.</td>
                          </tr>
                        </c:when>
                        <c:otherwise>
                          <c:forEach var="booking" items="${bookings}">
                            <tr>
                              <td>${booking.book_Id}</td>
                              <td>${booking.tour_Name}</td>
                              <td>${booking.created_At}</td>
                              <td>${booking.slot_Order}</td>
                              <td>${booking.total_Cost}</td>
                              <td>${booking.book_Status}</td>
                              <td>
                                <a href="displayTourDetail?id=T0000001">View Details</a>
                                <a href="#">Cancel</a>
                              </td>
                            </tr>
                          </c:forEach>
                        </c:otherwise>
                      </c:choose>
                    </tbody>
                  </table>
                </div>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </main>
      <!-- MAIN -->
    </section>
    <!-- CONTENT -->

    <script src="assests/js/script_profile.js"></script>
  </body>
</html>
