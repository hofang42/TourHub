<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Reset Password</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    </head>
    <body>
        <section class="py-3 py-md-5 py-xl-8">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="mb-5">
                            <h2 class="display-5 fw-bold text-center">Reset Your Password</h2>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-12 col-lg-8 col-xl-6">
                        <form action="newPassword" method="post">
                            <div class="row gy-3">
                                <!-- New Password Field -->
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control border-0 border-bottom rounded-0" name="password" id="password" placeholder="New Password" required>
                                        <label for="password" class="form-label">New Password</label>
                                    </div>
                                </div>
                                <!-- Confirm Password Field -->
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control border-0 border-bottom rounded-0" name="confPassword" id="confPassword" placeholder="Confirm Password" required>
                                        <label for="confPassword" class="form-label">Confirm Password</label>
                                    </div>
                                </div>
                                <!-- Submit Button -->
                                <div class="col-12">
                                    <div class="d-grid">
                                        <button class="btn btn-primary btn-lg" type="submit">Reset Password</button>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <% 
                            String status = request.getParameter("status");
                            if ("resetSuccess".equals(status)) {
                        %>
                            <div class="alert alert-success mt-3" role="alert">
                                Your password has been reset successfully! You can <a href="login.jsp" class="alert-link">login here</a>.
                            </div>
                        <% 
                            } else if ("resetFailed".equals(status)) {
                        %>
                            <div class="alert alert-danger mt-3" role="alert">
                                There was an error resetting your password. Please try again.
                            </div>
                        <% 
                            } else if ("passwordMismatch".equals(status)) {
                        %>
                            <div class="alert alert-warning mt-3" role="alert">
                                The passwords you entered do not match. Please try again.
                            </div>
                        <% 
                            } 
                        %>

                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
