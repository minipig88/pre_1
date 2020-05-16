<%--
  Created by IntelliJ IDEA.
  User: klvdo
  Date: 15.05.2020
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Login</h1>
    <h2>
        <form action="/login" method="Post">
            E-mail: <input type="text" name="email">
            Password: <input type="password" name="password">
            <input type="submit" value="Login">
        </form> &nbsp;&nbsp;&nbsp;
    </h2>
    <h2>
    <c:out value="${message}"/>
    </h2>
</div>

</body>
</html>