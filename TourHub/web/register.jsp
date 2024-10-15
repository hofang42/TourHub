<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="includes/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <style>
            body {
                background-image: url('https://golden-lotus-hotel.s3.ap-southeast-1.amazonaws.com/uploads/2021/04/013d407166ec4fa56eb1e1f8cbe183b9/images1089892_1.jpg');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .container {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 2rem;
                border-radius: 10px;
                box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            }

            .form-control, .form-floating {
                background-color: rgba(255, 255, 255, 0.8) !important;
            }
        </style>
    </head>
    <body>
        <section class="py-3 py-md-5 py-xl-8">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-lg-10 col-xl-8">
                        <div class="mb-5 text-center">
                            <h2 class="display-5 fw-bold">Sign Up</h2>
                        </div>
                        <form action="register" method="post">
                            <div class="row gy-3">
                                <!-- First Name -->
                                <div class="col-12 col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required>
                                        <label for="firstName">First Name</label>
                                    </div>
                                </div>

                                <!-- Last Name -->
                                <div class="col-12 col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
                                        <label for="lastName">Last Name</label>
                                    </div>
                                </div>

                                <!-- Email -->
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                                        <label for="email">Email</label>
                                    </div>
                                </div>

                                <!-- Phone -->
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="tel" class="form-control" id="phone" name="phone" placeholder="Phone" required>
                                        <label for="phone">Phone</label>
                                    </div>
                                </div>

                                <!-- Address -->
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="address" name="address" placeholder="Address" required>
                                        <label for="address">Address</label>
                                    </div>
                                </div>

                                <!-- Password -->
                                <div class="col-12 col-md-6">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                                        <label for="password">Password</label>
                                    </div>
                                </div>

                                <!-- Confirm Password -->
                                <div class="col-12 col-md-6">
                                    <div class="form-floating">
                                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                                        <label for="confirmPassword">Confirm Password</label>
                                    </div>
                                </div>

                                <!-- Role Selection -->
                                <div class="col-12">
                                    <div class="form-floating">
                                        <select class="form-select" name="role" id="role" required>
                                            <option value="Customer" selected>Customer</option>
                                            <option value="Provider">Provider</option>
                                        </select>
                                        <label for="role">Select Role</label>
                                    </div>
                                </div>
                                <!-- Error Handling (if applicable) -->
                                <div class="col-12">
                                    <c:if test="${not empty error}">
                                        <div class="alert alert-danger" role="alert">
                                            ${fn:escapeXml(error)}
                                        </div>
                                    </c:if>
                                </div>


                                <!-- Submit Button -->
                                <div class="col-12">
                                    <div class="d-grid">
                                        <button class="btn btn-primary btn-lg" type="submit">Register</button>
                                    </div>
                                </div>

                                <!-- Already Registered -->
                                <div class="col-12 text-center">
                                    <p>Already have an account? <a href="login.jsp">Login here</a>.</p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
