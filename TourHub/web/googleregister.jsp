<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="includes/header.jsp" %> <!-- Reuse common header and styles -->
<html>
<head>
    <title>Complete Your Registration</title>
</head>
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
                            <select name="role" id="role" class="form-control" required>
                                <option value="provider">Provider</option>
                                <option value="customer">Customer</option>
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

    <%@include file="includes/footer.jsp" %> <!-- Reuse common footer and scripts -->
</body>
</html>
