<%--
  Created by IntelliJ IDEA.
  User: klvdo
  Date: 13.05.2020
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>User Management</h1>
    <h2>
        <form action="/admin/list" method="GET">
            <input type="submit" value="Update users list">
        </form> &nbsp;&nbsp;&nbsp;
        <form action="/admin/new" method="GET">
            <input type="submit" value="Add user">
        </form>
    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <h2>
            <c:out value="${message}"/>
        </h2>
        <tr>
            <th>ID</th>
            <th>FirstName</th>
            <th>SecondName</th>
            <th>Email</th>
            <th>Password</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.secondName}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><a href="/admin/edit?id=<c:out value='${user.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/admin/delete?id=<c:out value='${user.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

