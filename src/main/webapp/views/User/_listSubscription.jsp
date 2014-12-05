<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Subscriptions</h2>
</div>
<div>
    ${message}
</div>
<table class="table table-bordered table-condensed table-hover">
    <thead>
        <tr>
            <th width="">id</th>
            <th width="">First name</th>
            <th width="">Last name</th>
            <th width="">Username</th>
            <th width="">Tipo Inscrição</th>
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
                    <a href="${pageContext.request.contextPath}/User/subscription/${classId}/${user.id}/${empty profiles ? "#" : profiles.get(0).getId()}">Inscrever Participante?</a>
                    <a href="${pageContext.request.contextPath}/User/subscription/${classId}/${user.id}/${empty profiles ? "#" : profiles.get(1).getId()}">Inscrever Palestrante?</a>
                    <a href="${pageContext.request.contextPath}/User/subscription/${classId}/${user.id}/${empty profiles ? "#" : profiles.get(2).getId()}">Inscrever Monitor?</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@include file="../../templates/footer.jspf" %>