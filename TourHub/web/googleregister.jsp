<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Complete Your Registration</title>
    </head>
    <body>
        <h2>Complete Your Registration</h2>
        <form action="completeRegistration" method="POST">
            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" required/><br/><br/>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required/><br/><br/>

            <label for="password">Create Password:</label>
            <input type="password" id="password" name="password" required/><br/><br/>

            <label for="role">Select Role:</label>
            <select name="role" id="role" required>
                <option value="provider">Provider</option>
                <option value="customer">Customer</option>
            </select><br/><br/>

            <input type="submit" value="Complete Registration"/>
        </form>
    </body>
</html>
