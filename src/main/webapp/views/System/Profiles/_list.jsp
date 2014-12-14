<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : rgcs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../../templates/header.jspf" %>

<div class="page-header">
    <h2>System Profiles</h2>
</div>
<div>
    ${message}
</div>
<table class="table table-bordered table-condensed table-hover">
    <thead>
        <tr>
            <th width="">ID</th>
            <th width="">Shortname</th>
            <th width="">Description</th>
            <th width="">Operations</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="sp" items="${systemProfiles}">
            <tr>
                <td>${sp.id}</td>
                <td>${sp.shortname}</td>
                <td>${sp.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/System/Profile/edit/${sp.id}">
                        <button class="btn btn-default btn-warning"><span class="glyphicon glyphicon-edit"></span> Edit</button>
                    </a> | 
                    <a href="${pageContext.request.contextPath}/System/Profile/delete/${sp.id}">
                        <button class="btn btn-default btn-danger"><span class="glyphicon glyphicon-remove"></span> Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="../../../templates/footer.jspf" %>
