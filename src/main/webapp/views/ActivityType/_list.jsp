<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : kallenon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Activity Types</h2>
</div>
<div>
    ${message}
</div>
<table class="table table-bordered table-condensed table-hover">
    <thead>
        <tr>
            <th width="">Id</th>
            <th width="">Shortname</th>
            <th width="">Title</th>
            <th width="200">Description</th>
            <th width="">Operations</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="activityType" items="${activityTypes}">
            <tr>
                <td>${activityType.id}</td>
                <td>${activityType.shortname}</td>
                <td>${activityType.title}</td>
                <td>${activityType.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/ActivityType/edit/${activityType.id}" class="btn btn-primary btn-embossed">Edit</a> | 
                    <a href="${pageContext.request.contextPath}/ActivityType/delete/${activityType.id}" class="btn btn-default btn-danger btn-embossed">Delete</a>
                    
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


<%@include file="../../templates/footer.jspf" %>

