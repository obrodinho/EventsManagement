<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : rgcs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>All Users!!! :)</h1>

        <table class="table table-striped" border="1px" cellpadding="0" cellspacing="0" >
            <thead>
                <tr>
                    <th width="10%">id</th>
                    <th width="15%">First name</th>
                    <th width="10%">Last name</th>
                    <th width="10%">Username</th>
                    <th width="10%">Operations</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.username}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/User/edit/${user.id}">Edit</a><br/>
                        <a href="${pageContext.request.contextPath}/User/delete/${user.id}">Delete</a><br/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p><a href="${pageContext.request.contextPath}">Home page</a></p>
</body>
</html>
