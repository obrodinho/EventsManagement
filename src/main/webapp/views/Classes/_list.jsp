<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : rgcs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Class</h2>
</div>
<div>
    ${message}
</div>
<table class="table table-bordered table-condensed table-hover">
    <thead>
        <tr>
            <th width="">Id</th>
            <th width="">Title</th>
            <th width="200">Description</th>
            <th width="">Operations</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="classes" items="${classes}">
            <tr>
                <td>${classes.id}</td>
                <td>${classes.title}</td>
                <td>${classes.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/Classes/edit/${classes.id}">Edit</a> | 
                    <a href="${pageContext.request.contextPath}/Classes/delete/${classes.id}">Delete</a> |
                    <a href="${pageContext.request.contextPath}/User/all/${classes.id}">Inscrever Usuário</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


<%@include file="../../templates/footer.jspf" %>

