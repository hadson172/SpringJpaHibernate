<%--
  Created by IntelliJ IDEA.
  User: Hadson
  Date: 2016-09-26
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register User</title>
</head>

<body>

<sf:form method="post" modelAttribute="user">
    Username: <sf:input path="username"/>
                <sf:errors path="username"/> <br>
    Password: <sf:password path="password"/>
              <sf:errors path="password"/> <br>
    E-mail:   <sf:input path="email" type="email"/>
              <sf:errors path="email"/> <br>
    <input type="submit" value="Register"> <br>
</sf:form>

</body>
</html>
