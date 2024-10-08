<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tour Options</title>
</head>
<body>
    <h2>Tour Options</h2>

    <table border="1">
        <tr>
            <th>Option Name</th>
            <th>Price</th>
            <th>Description</th>
        </tr>

        <c:forEach var="option" items="${tourOptions}">
            <tr>
                <td>${option.optionName}</td>
                <td>${option.price}</td>
                <td>${option.description}</td>
            </tr>
        </c:forEach>
    </table>

    <a href="tourList.jsp">Back to Tour List</a>
</body>
</html>
