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
        <title>All Events!!! :)</title>
    </head>
    <body>
        <h1>All Events!!! :)</h1>
        <div>
            ${message}
        </div>
        <table class="table table-striped" border="1px" cellpadding="15" cellspacing="0" >
            <thead>
                <tr>
                    <th width="">id</th>
                    <th width="">First name</th>
                    <th width="">Last name</th>
                    <th width="">Eventname</th>
                    <th width="">Operations</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="event" items="${events}">
                    <tr>
                        <td>${event.id}</td>
                        <td>${event.firstname}</td>
                        <td>${event.lastname}</td>
                        <td>${event.eventname}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/Event/edit/${event.id}">Edit</a> | 
                            <a href="${pageContext.request.contextPath}/Event/delete/${event.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><a href="${pageContext.request.contextPath}">Home page</a></p>
    </body>
</html>
