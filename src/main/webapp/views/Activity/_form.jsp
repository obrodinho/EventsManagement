<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : kallenon
--%>
<%@page import="org.consultjr.mvc.model.ActivityType"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Activity</h2>
</div>    
<div>
    ${message}
</div>

<div class="row">
<div class="col-md-4 col-md-offset-4">
    
    <form:form method="POST" commandName="activity" modelAttribute="activity" action="${pageContext.request.contextPath}/Activity/${empty activityID ? action.concat('/').concat(eventID) : action.concat('/').concat(activityID)}">
        <input type="hidden" name="activityID" value="${activityID}" >
            
        <div class="form-group">
                <label>Title:</label>
                <div ><form:input path="title" cssClass="form-control"/></div>
            </div>
            <div class="form-group">
                <label>Description:</label>
                <div ><form:input path="description" cssClass="form-control"/></div>
            </div>
            <div class="form-group">
                <label>Type:</label>
                <div >
                    <form:select 
                        path="type"
                        cssClass="form-control" 
                        items="${activityTypes}" 
                        itemLabel="title" 
                        itemValue="id" />
                </div>
            </div>
            <div class="form-group">
                <label>Workload:</label>
                <div ><form:input path="workload" cssClass="form-control"/></div>
            </div>
            <div class="form-group">
                <label>Start:</label>
                <div >
                    <form:input path="start" cssClass="form-control"/> Ex: dd-mm-yyyy hh:mm:ss</div>
            </div>
            <div class="form-group">
                <label>End:</label>
                <div >
                    <form:input path="end" cssClass="form-control"/> Ex: dd-mm-yyyy hh:mm:ss</div>
            </div>
            <input type="submit" value="${empty activityID ? "Add" : "Save"}" class="btn btn-default btn-embossed btn-primary"/>
</form:form>
</div>
</div>
<p><a href="${pageContext.request.contextPath}">Home page</a></p>
<%@include file="../../templates/footer.jspf" %>

