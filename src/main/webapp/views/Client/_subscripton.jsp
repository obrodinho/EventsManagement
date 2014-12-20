<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : kallenon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Activities</h2>
</div>
<div>
    ${message}
</div>
<form:form method="POST" action="${pageContext.request.contextPath}/Activity/addSubscription">
    <table class="table table-bordered table-condensed table-hover">
        <thead>
            <tr>
                <th width="">Select</th>
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
                    <td style="text-align: center">
                        <label class="checkbox" for="checkbox${activity.id}">
                            <input type="checkbox" name="subscribeActivities" value="${activity.id}" id="checkbox${activity.id}" data-toggle="checkbox" class="custom-checkbox">
                        </label>
                    </td>
                    <td>${activity.title}</td>
                    <td>${activity.description}</td>
                    <td>${activity.type.title}</td>
                    <td>${activity.workload}</td>
                    <td>${activity.start}</td>
                    <td>${activity.end}</td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td>
                    <input type="submit" value="Register" class="btn btn-default btn-primary btn-embossed">
                </td>
            </tr>
        </tfoot>
    </table>
</form:form>

<script>
    $(document).ready(function () {
        $('[data-toggle="checkbox"]').radiocheck();
    });
</script>

<%@include file="../../templates/footer.jspf" %>

