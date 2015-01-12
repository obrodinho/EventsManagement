<%-- 
    Document   : index
    Created on : Nov 15, 2014, 12:23:05 AM
    Author     : rgcs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Project Base Actions</h2>
</div>
<div class="row">
    ${message}
</div>
<div class="row">
    System: ${productType}
    <h2>Panel</h2>
    <c:if test="${app.supports('Events')}">
    <h3>Last Events</h3>
    <table class="table table-bordered table-condensed">
        <tbody>
            <tr>
                <c:if test="${not empty eventsList}">
                <c:forEach var="eventsList" items="${eventsList}">
                    <td width="25%" align="center"><b>${eventsList.title}</b><br>${eventsList.description}<br><a href="${pageContext.request.contextPath}/Activity/all/${eventsList.id}">View activities</a></td>
                </c:forEach>
                </c:if>
                <c:if test="${empty eventsList}">
                    <td align="center"><b>Not exists events!</b></td>
                </c:if>
            </tr>
        </tbody>
    </table>
    </c:if>
    <h3>Last Pending Payments</h3>
    <table class="table table-bordered table-condensed">
        <tbody>
            <tr>
                <c:if test="${not empty classesSubscriptionPaymentPending}">
                    <c:forEach var="classesSubscriptionPaymentPending" items="${classesSubscriptionPaymentPending}">
                        <td width="25%" align="center"><b>${classesSubscriptionPaymentPending.classes.title}</b><br>${classesSubscriptionPaymentPending.classes.activity.description}<br><a href="${pageContext.request.contextPath}/Activity/confirmPaymentSubscription/${classesSubscriptionPaymentPending.payment.id}">Pay</a></td>
                    </c:forEach>
                </c:if>
                <c:if test="${empty classesSubscriptionPaymentPending}">
                    <td align="center"><b>Not exists pending payments!</b></td>
                </c:if>
            </tr>
        </tbody>
    </table>
    <h3>User's registration</h3>
    <p><b>First name:</b> ${loggedUser.firstname}</p>
    <p><b>Last name:</b> ${loggedUser.lastname}</p>
    <p><b>Username:</b> ${loggedUser.username}</p>
    <p><b>Password:</b> *****</p>
    <p><a href="${pageContext.request.contextPath}/User/edit/${loggedUser.id}">
        <button class="btn btn-default btn-primary btn-embossed">Edit</button>
    </a></p>
</div>
<%@include file="../../templates/footer.jspf" %>