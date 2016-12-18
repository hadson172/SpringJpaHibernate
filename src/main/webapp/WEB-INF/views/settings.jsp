<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Hadson
  Date: 2016-10-03
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.username} settings</title>
</head>
<body>


<sf:form method="post" modelAttribute="user" acceptCharset="UTF-8">
    FirstName: <sf:input path="firstName"/> <br>
    LastName: <sf:input path="lastName"/> <br>
    Height: <sf:input path="details.HEIGHT"/> <br>
    Weight: <sf:input path="details.WEIGHT"/> <br>
    Age: <sf:input path="details.age"/> <br>
    Sex: <sf:select path="details.sex">
    <sf:options items="${user.details.sexList}"/>
</sf:select> <br>
    <input type="submit" value="Apply"> <br>
</sf:form>

</body>
</html>
