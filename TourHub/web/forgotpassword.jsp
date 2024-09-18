<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
        <style>
            html, body {
                height: 100%;
                margin: 0;
                font-family: 'Roboto', sans-serif;
            }
            .full-height {
                min-height: 100vh;
                display: flex;
                flex-direction: column;
            }
            .content {
                flex: 1;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .header {
                background-color: #f8f9fa;
                padding: 1rem;
                position: relative;
                border-bottom: 1px solid #ddd;
            }
            .login-form {
                position: absolute;
                top: 0;
                right: 0;
                padding: 0.5rem;
                background: #fff;
                border: 1px solid #ddd;
                border-top-left-radius: 0.5rem;
                display: flex;
                align-items: center;
            }
            .login-form .form-control {
                margin-right: 0.5rem;
            }
            .login-form button {
                margin-left: 0.5rem;
            }
            .footer {
                background-color: #f8f9fa;
                padding: 1rem;
                text-align: center;
                border-top: 1px solid #ddd;
            }
            .forgot-password-box {
                background: #fff;
                border: 1px solid #ddd;
                border-radius: 0.5rem;
                padding: 2rem;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                margin: auto;
            }
            .form-group {
                margin-bottom: 1.5rem;
            }
            .btn-primary {
                transition: background-color 0.3s ease;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="full-height">
            
            
            <!-- Main Content -->
            <section class="content">
                <div class="container">
                    <div class="row">
                        <div class="col-12 text-center">
                            <div class="forgot-password-box">
                                <h2 class="display-5 fw-bold text-center">Forgot Password</h2>
                                <p class="text-center">Please enter your email to receive an OTP for resetting your password.</p>
                                <form action="forgotPassword" method="post">
                                    <div class="form-group">
                                        <div class="form-floating">
                                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                                            <label for="email">Email address</label>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <button class="btn btn-primary btn-lg" type="submit">Send OTP</button>
                                    </div>
                                    <div class="text-danger mt-3">
                                        <p><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>
                                    </div>
                                    <div class="text-success mt-3">
                                        <p><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            
            <!-- Footer -->
            <footer class="footer">
                <div class="container">
                    <p>&copy; 2024 TourHub. All rights reserved.</p>
                </div>
            </footer>
        </div>
    </body>
</html>