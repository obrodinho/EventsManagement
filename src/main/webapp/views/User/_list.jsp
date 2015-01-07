<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : rgcs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Users</h2>
</div>
<div>
    ${message}
</div>
<table class="table table-bordered table-condensed table-hover">
    <thead>
        <tr>
            <th width="">ID</th>
            <th width="">First name</th>
            <th width="">Last name</th>
            <th width="">Username</th>
            <th width="">Password</th>
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
                    <a href="${pageContext.request.contextPath}/User/edit/${user.id}" class="btn btn-primary btn-embossed">Edit</a> | 
                    <a href="${pageContext.request.contextPath}/User/delete/${user.id}" class="btn btn-default btn-danger btn-embossed">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="../../templates/footer.jspf" %>
