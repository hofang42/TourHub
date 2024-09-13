<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verify Email</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Verify Your Email</h2>
        <form action="verify" method="post">
            <div class="mb-3">
                <label for="code" class="form-label">Enter Verification Code</label>
                <input type="text" class="form-control" name="code" id="code" required>
            </div>
            <button type="submit" class="btn btn-primary">Verify</button>
        </form>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger mt-3"><%= request.getAttribute("error") %></div>
        <% } %>
    </div>
</body>
</html>
