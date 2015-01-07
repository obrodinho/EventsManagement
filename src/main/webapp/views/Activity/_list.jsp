<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : kallenon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Activities</h2>
</div>
        <div>
            ${message}
        </div>
        <table class="table table-bordered table-condensed table-hover">
            <thead>
                <tr>
                    <th width="">Id</th>
                    <th width="">Title</th>
                    <th width="">Description</th>
                    <th width="">Type</th>
                    <th width="">Workload</th>
                    <th width="">Start</th>
                    <th width="">End</th>
                    <th width="">Operations</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="activity" items="${activities}">
                    <tr>
                        <td>${activity.id}</td>
                        <td>${activity.title}</td>
                        <td>${activity.description}</td>
                        <td>${activity.type.title}</td>
                        <td>${activity.workload}</td>
                        <td>${activity.start}</td>
                        <td>${activity.end}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Activity/edit/${activity.id}" class="btn btn-primary btn-embossed">Edit</a> | 
                            <a href="${pageContext.request.contextPath}/Activity/delete/${activity.id}" class="btn btn-default btn-danger btn-embossed">Delete</a> |
                            <a href="${pageContext.request.contextPath}/Classes/add/${activity.id}">Add Class</a> |
                            <a href="${pageContext.request.contextPath}/Classes/all/${activity.id}">List Classes</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        
<%@include file="../../templates/footer.jspf" %>

