<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : rgcs
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
                <th width="">id</th>
                <th width="">Operations</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="event" items="${events}">
                <tr>
                    <td>${event.id}</td>

                    <td>
                        <a href="${pageContext.request.contextPath}/Event/edit/${event.id}">Edit</a> | 
                        <a href="${pageContext.request.contextPath}/Event/delete/${event.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <%@include file="../../templates/footer.jspf" %>