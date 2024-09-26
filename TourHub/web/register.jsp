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
                            <!-- Other form fields remain the same -->

                            <!-- Role Selection -->
                            <div class="col-12">
                                <div class="form-floating">
                                    <select class="form-select" name="role" id="role" required>
                                        <option value="customer" selected>Customer</option>
                                        <option value="provider">Provider</option>
                                    </select>
                                    <label for="role">Select Role</label>
                                </div>
                            </div>

                            <!-- Error handling remains the same -->

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
