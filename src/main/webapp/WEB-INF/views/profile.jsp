<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Hadson
  Date: 2016-09-26
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>${user.username}</title>
</head>
<body>



<label>FirstName: </label> ${user.firstName} <br>
<label>LastName : </label> ${user.lastName} <br>
<label>Email    : </label> ${user.email}    <br>
<label>Age      : </label> ${user.details.age} <br>
<label>Height   : </label> ${user.details.HEIGHT} <br>
<label>Weight   : </label> ${user.details.WEIGHT} <br>
<label>Sex      : </label> ${user.details.sex} <br>
<a href="/users/${user.username}/settings">Settings</a> <br>


</body>
</html>
