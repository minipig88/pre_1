<%--
  Created by IntelliJ IDEA.
  User: klvdo
  Date: 13.05.2020
  Time: 22:08
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
            <input type="submit" value="List All Users">
        </form> &nbsp;&nbsp;&nbsp;
    </h2>
</div>
<div align="center">
    <form action="/admin/new" method="POST">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Add New User
                </h2>
            </caption>
            <tr>
                <th>User First Name:</th>
                <td>
                    <input type="text" name="firstName" size="45"
                           value="<c:out value='${user.firstName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>User Second Name:</th>
                <td>
                    <input type="text" name="secondName" size="45"
                           value="<c:out value='${user.secondName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>
                    <input type="text" name="email" size="15"
                           value="<c:out value='${user.email}' />"
                    />
                </td>
            </tr>
            <tr>
            <tr>
                <th>Password:</th>
                <td>
                    <input type="text" name="password" size="15"
                           value="<c:out value='${user.password}' />"
                    />
                </td>
            </tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
            </td>
            </tr>
        </table>
        <h2>
            <c:out value="${message}"/>
        </h2>
    </form>
</div>
</body>
</html>
