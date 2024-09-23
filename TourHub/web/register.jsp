<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register Page</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
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
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="username" placeholder="Username" value="${username}" required>
                                    <label for="username">Username</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="password" class="form-control" name="password" placeholder="Password" required>
                                    <label for="password">Password</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" required>
                                    <label for="confirmPassword">Confirm Password</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="email" class="form-control" name="email" placeholder="Email" value="${email}" required>
                                    <label for="email">Email</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="firstName" placeholder="First Name" value="${firstName}" required>
                                    <label for="firstName">First Name</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="lastName" placeholder="Last Name" value="${lastName}" required>
                                    <label for="lastName">Last Name</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="phone" placeholder="Phone" value="${phone}" required>
                                    <label for="phone">Phone</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating">
                                    <input type="text" class="form-control" name="address" placeholder="Address" value="${address}" required>
                                    <label for="address">Address</label>
                                </div>
                            </div>

                            <!-- Display Error Messages -->
                            <c:if test="${not empty error}">
                                <div class="col-12">
                                    <div class="alert alert-danger">
                                        <c:out value="${error}"/>
                                    </div>
                                </div>
                            </c:if>

                            <div class="col-12">
                                <div class="d-grid">
                                    <button class="btn btn-primary btn-lg" type="submit">Register</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
