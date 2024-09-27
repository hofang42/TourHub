<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User OTP</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    </head>
    <body>
        <section class="py-3 py-md-5 py-xl-8">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="mb-5">
                            <h2 class="display-5 fw-bold text-center">Enter OTP</h2>
                            <p class="text-center">Please enter the OTP sent to your email address.</p>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-12 col-lg-6">
                        <form action="user" method="post">
                            <input type="hidden" name="action" value="checkotp" />
                            <div class="row gy-3">
                                <div class="col-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="otp" name="otp" placeholder="Enter OTP" required>
                                        <label for="otp">OTP</label>
                                    </div>
                                </div>
                                <div class="col-12 text-center">
                                    <button class="btn btn-primary btn-lg" type="submit">Verify OTP</button>
                                </div>
                            </div>
                            <div class="text-danger mt-3">
                                <p><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>