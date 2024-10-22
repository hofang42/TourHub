<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User" %>
<%@include file="includes/header.jsp" %> <!-- Reuse common header and styles -->
<html>
<head>
    <title>Complete Your Registration</title>
    <script>
        function showError(message) {
            // Hiển thị popup thông báo lỗi
            alert(message);
        }
    </script>
</head>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (currentUser == null) {
        // If session doesn't have the user, redirect to login
        response.sendRedirect("login.jsp");
        return;  // Stop further processing
    }
%>
<body>
    <!-- Page Content -->
    <section class="section section-lg bg-default novi-background bg-cover">
        <div class="container container-wide">
            <div class="row justify-content-center">
                <div class="col-md-10 col-lg-8">
                    <h2 class="text-center">Complete Your Registration</h2>
                    <hr class="divider divider-decorate">

                    <form action="completeRegistration" method="POST" class="registration-form">
                        <div class="form-group">
                            <label for="phone">Phone Number:</label>
                            <input type="text" id="phone" name="phone" class="form-control" required/>
                        </div>
                        <div class="form-group">
                            <label for="address">Address:</label>
                            <input type="text" id="address" name="address" class="form-control" required/>
                        </div>
                        <div class="form-group">
                            <label for="password">Create Password:</label>
                            <input type="password" id="password" name="password" class="form-control" required/>
                        </div>
                        <div class="form-group">
                            <label for="role">Select Role:</label>
                            <select name="role" id="role" class="form-control" required style="height: 100px;">
                                <option value="Provider">Provider</option>
                                <option value="Customer">Customer</option>
                            </select>
                        </div>

                        <div class="form-group text-center">
                            <input type="submit" class="button button-sm button-secondary" value="Complete Registration"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <%-- Kiểm tra nếu có thông báo lỗi --%>
    <% if (errorMessage != null) { %>
        <script>
            showError('<%= errorMessage %>');
        </script>
    <% } %>

    <%@include file="includes/footer.jsp" %> <!-- Reuse common footer and scripts -->
</body>
</html>
