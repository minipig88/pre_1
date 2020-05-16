<%--
  Created by IntelliJ IDEA.
  User: klvdo
  Date: 15.05.2020
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Data</title>
</head>
<body>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>UserInfo</h2></caption>
        <tr>
            <th>FirstName</th>
            <th>SecondName</th>
            <th>Email</th>
        </tr>
        <tr>
            <td><c:out value="${loginUser.firstName}"/></td>
            <td><c:out value="${loginUser.secondName}"/></td>
            <td><c:out value="${loginUser.email}"/></td>
        </tr>
    </table>
</div>
</body>
</html>
