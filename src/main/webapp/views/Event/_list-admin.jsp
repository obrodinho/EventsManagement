<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : kallenon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>
    <div class="page-header">
    <h2>Events</h2>
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
                <th width="">Start</th>
                <th width="">End</th>
                <th width="">Owner</th>
                <th width="">Operations</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="event" items="${events}">
                <tr>
                    <td>${event.id}</td>
                    <td>${event.title}</td>
                    <td>${event.description}</td>
                    <td>${event.start}</td>
                    <td>${event.end}</td>
                    <td>${event.owner.username}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/Event/edit/${event.id}" class="btn btn-primary btn-embossed">Edit</a> | 
                        <a href="${pageContext.request.contextPath}/Event/delete/${event.id}" class="btn btn-default btn-danger btn-embossed">Delete</a> | 
                        <a href="${pageContext.request.contextPath}/Activity/add/${event.id}" class="btn btn-primary btn-embossed">Add Activity</a> | 
                        <a href="${pageContext.request.contextPath}/Activity/all/${event.id}" class="btn btn-primary btn-embossed">List Activities</a> | 
                        <a href="${pageContext.request.contextPath}/Event/${event.id}/Activity/subscription" class="btn btn-primary btn-embossed">Subscription</a>
                        
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <%@include file="../../templates/footer.jspf" %>