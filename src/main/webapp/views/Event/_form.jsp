<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : kallenon
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Event</h2>
</div>
<div>
    ${message}
</div>

<div class="row">
<div class="col-md-4 col-md-offset-4">

    <form:form method="POST" commandName="event" action="${pageContext.request.contextPath}/Event/${empty eventID ? action : action.concat('/').concat(eventID)}">
    <input type="hidden" name="eventID" value="${eventID}" >
    <div class="form-group">
                <label>Title:</label>
                <div ><form:input path="title" cssClass="form-control"/></div>
            </div>
            <div class="form-group">
                <label>Description:</label>
                <div ><form:input path="description" cssClass="form-control"/></div>
            </div>
            <div class="form-group">
                <label>Start:</label>
                <div ><form:input path="start" cssClass="form-control"/> Ex: dd-mm-yyyy hh:mm:ss</div>
            </div>
            <div class="form-group">
                <label>End:</label>
                <div ><form:input path="end" cssClass="form-control"/> Ex: dd-mm-yyyy hh:mm:ss</div>
            </div>
            <div class="form-group">
                <label>Owner:</label>
                <div >
                    <form:select 
                        path="owner" 
                        name="owner"
                        cssClass="selectpicker" 
                        style="height: 35px; width: 197px"
                        items="${users}" 
                        itemLabel="username" 
                        itemValue="id" cssClass="form-control"/>
                </div>
            </div>
            <input type="submit" value="${empty eventID ? "Add" : "Save"}" class="btn btn-default btn-embossed btn-primary"/>
</form:form>
    </div>
</div>
<%@include file="../../templates/footer.jspf" %>

