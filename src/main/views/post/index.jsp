<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>QR Code</title>
</head>
<body>

<h3>Post List</h3>
<table border="1" cellpadding="2" cellspacing="2">
    <tr>

        <th>Name</th>
        <th>username</th>
        <th>email</th>
        <th>password</th>
        <th>BarCode</th>
    </tr>
    <c:forEach var="post" items="${posts }">
        <tr>

            <td>${post.name }</td>
            <td>${post.username }</td>
            <td>${post.email }</td>
            <td>${post.password }</td>
            <td>
                <img src="${pageContext.request.contextPath }/post/barcode/${post.id }" width="200" height="50">
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>