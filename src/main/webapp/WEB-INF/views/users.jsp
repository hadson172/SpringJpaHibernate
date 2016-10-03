<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hadson
  Date: 2016-10-02
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h1>Users:</h1>

<c:forEach items="${usersList}" var="user">
    <a href="users/${user}">${user}</a>
    <a href="users/${user}/remove">Remove</a> <br>
</c:forEach>

</body>
</html>
