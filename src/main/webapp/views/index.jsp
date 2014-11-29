<%-- 
    Document   : index
    Created on : Nov 15, 2014, 12:23:05 AM
    Author     : rgcs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Events Management - JSP version</title>
    </head>
    <body>
        <h1>Testing Basic Operations</h1>
        <h2>User</h2>
        <ol>
            <li><a href="${pageContext.request.contextPath}/User/all">List</a></li> 
            <li><a href="${pageContext.request.contextPath}/User/add">Create</a></li>
        </ol>
        <h2>Activity</h2>
        <ol>
            <li><a href="${pageContext.request.contextPath}/Activity/all">List</a></li> 
            <li><a href="${pageContext.request.contextPath}/Activity/add">Create</a></li>
        </ol>
    </body>
</html>
