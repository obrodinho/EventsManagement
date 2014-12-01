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
        <meta charset="UTF-8">
        <title>All Users!!! :)</title>
    </head>
    <body>
        <h1>All Users!!! :)</h1>
        <div>
            ${message}
        </div>
        <table class="table table-striped" border="1px" cellpadding="15" cellspacing="0" >
            <thead>
                <tr>
                    <th width="">id</th>
                    <th width="">First name</th>
                    <th width="">Last name</th>
                    <th width="">Username</th>
                    <th width="">Password</th>
                    <th width="">Operations</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstname}</td>
                        <td>${user.lastname}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/User/edit/${user.id}">Edit</a> | 
                            <a href="${pageContext.request.contextPath}/User/delete/${user.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="${pageContext.request.contextPath}">Home page</a></p>
    </body>
</html>
