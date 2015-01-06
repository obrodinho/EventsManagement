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
                    <th width="">Event</th>
                    <th width="">Title</th>
                    <th width="">Description</th>
                    <th width="">Type</th>
                    <th width="">Workload</th>
                    <th width="">Start</th>
                    <th width="">End</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="activity" items="${activities}">
                    <tr>
                        <td>${activity.id}</td>
                        <td>${activity.event.title}</td>
                        <td>${activity.title}</td>
                        <td>${activity.description}</td>
                        <td>${activity.type.title}</td>
                        <td>${activity.workload}</td>
                        <td>${activity.start}</td>
                        <td>${activity.end}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        
<%@include file="../../templates/footer.jspf" %>

