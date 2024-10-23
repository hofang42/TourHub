<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User" %>
<%@ include file="includes/header.jsp" %>

<html>
    <head>
        <title>Customer Information</title>
    </head>
    <%
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"Customer".equals(currentUser.getRole())) {
            // If session doesn't have the customer or role is not Customer, redirect to login
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <body>
        <section class="section section-lg bg-default novi-background bg-cover">
            <div class="container container-wide">
                <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-8">
                        <h2 class="text-center">Enter Customer Information</h2>
                        <hr class="divider divider-decorate">

                        <form action="updateCustomerInfo" method="POST" class="customer-info-form">
                            <div class="form-group">
                                <label for="birthDate">Date of Birth:</label>
                                <input type="date" id="birthDate" name="birthDate" class="form-control" required style="height: 50px;"/>
                            </div>
                        <div class="form-group text-center">
                            <input type="submit" class="button button-sm button-secondary" value="Submit"/>
                        </div>
                        </form>
                        
                    </div>
                </div>
            </div>
        </section>

        <%@ include file="includes/footer.jsp" %>
    </body>
</html>
