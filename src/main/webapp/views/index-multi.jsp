<%-- 
    Document   : index
    Created on : Nov 15, 2014, 12:23:05 AM
    Author     : rgcs
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../templates/header.jspf" %>
<div class="page-header">
    <h2>Project Base Actions</h2>
</div>
<div class="row">
    ${message}
</div>
<div class="row">
    <h2>Configurações do Sistema</h2>
    <ol>
        ${tipo}
    </ol>
    <h2>User</h2>
    <ol>
        <li>index-multi</li>
        <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
    </ol>
    <h2>Event</h2>
    <ol>
        <li><a href="${pageContext.request.contextPath}/Event/all">List</a></li> 
    </ol>
    <h2>Classes</h2>
    <ol>
        <li><a href="${pageContext.request.contextPath}/Classes/all">List</a></li> 
    </ol>
    <h2>Activity Type</h2>
    <ol>
        <li><a href="${pageContext.request.contextPath}/ActivityType/all">List</a></li> 
    </ol>
    <h2>Activity</h2>
    <ol>
        <li><a href="${pageContext.request.contextPath}/Activity/all">List</a></li> 
    </ol>
</div>
<%@include file="../templates/footer.jspf" %>
