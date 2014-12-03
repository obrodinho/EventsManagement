<%-- 
    Document   : index
    Created on : Nov 15, 2014, 12:23:05 AM
    Author     : rgcs
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Admin</h2>
</div>
        <h2>Existent Configs</h2>
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
<%@include file="../../templates/footer.jspf" %>

