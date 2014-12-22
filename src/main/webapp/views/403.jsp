<%-- 
    Document   : 403
    Created on : 22/12/2014, 12:45:18
    Author     : Rafael
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../templates/header.jspf" %>
<div class="page-header">
    <h2>Error 403</h2>
</div>
<div class="row alert alert-danger">
    <h4>Hey! You should not see this page</h4>
    <p>
        You're not authorized to see this page. How did you got here?
    </p>
    <p>
        Please come backhome, or try to use one of the menus on navbar.
    </p>
    <p>
        <a href="${pageContext.request.contextPath}" class="btn btn-embossed btn-large btn-primary"><span class="fui-home"></span> HOME</a>
    </p>
</div>
<%@include file="../templates/footer.jspf" %>
