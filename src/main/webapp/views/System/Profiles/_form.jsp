<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : rgcs
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../../templates/header.jspf" %>

<div class="page-header">
    <h2>System Profile</h2>
</div>
<div>
    ${message}
</div>
<div class="row">
    <form:form method="POST" commandName="systemProfile" action="${pageContext.request.contextPath}/System/Profile/${empty systemProfileID ? action : action.concat('/').concat(systemProfileID)}">
        <input type="hidden" name="systemProfile" value="${systemProfileID}" >
        <div class="form-group">
            <label>Shortname:</label>
            <div class="form-control"><form:input path="shortname" /></div>
        </div>
        <div class="form-group">
            <label>Description:</label>
            <div class="form-control"><form:input path="description" /></div>
        </div>
        <input type="submit" value="${empty systemProfileID ? "Add" : "Save"}" />

    </form:form>
</div>
<%@include file="../../../templates/footer.jspf" %>
