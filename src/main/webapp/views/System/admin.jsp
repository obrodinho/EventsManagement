<%-- 
    Document   : index
    Created on : Nov 15, 2014, 12:23:05 AM
    Author     : rgcs
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Events Management - Administração do Sistema</title>
    </head>
    <body>
        <h1>ADMIN</h1>
        <h2>
            8|
        </h2>
        <h2>Lista de configurações existentes</h2>
        <table>
            <thead>
            <th>ID</th>
            <th>Chave</th>
            <th>Valor</th>
            </thead>
            <tbody>
                <c:forEach var="config" items="${configs}">
                    <tr>
                        <td>${config.id}</td>
                        <td>${config.key}</td>
                        <td>${config.value}</td>                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
