<%-- 
    Document   : install
    Created on : 03/01/2015, 16:44:10
    Author     : Rafael
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Hey, let's begin!</h2>
</div>

<div class="jumbotron">
    <p>You are getting on an upgradable software. 
        If you got a trial or an limited product key, don't be affraid. 
        You won't lose anything on features upgrade. Contact us and get newer software and upgrades.</p>
</div>
<form:form method="POST" action="${pageContext.request.contextPath}/System/install">
    <fieldset>
        <legend>
            Product KEY
        </legend>

        <label>Please, inform us your KEY.</label>
        <div class="form-group-hg">
            <input autocomplete="on" maxlength="19" pattern="[0-9A-Z]{4}-[0-9A-Z]{4}-[0-9]{4}-[0-9]{4}" name="installKey" id="installKey" class="form-control" placeholder="A1B2-C3D4-1234-5678" /> 
            <p class="help-block">
                Inform us your <abbr title="It's something like 'A1B2-C3D4-1234-5678'">KEY</abbr> to install your software.
            </p>
        </div>

    </fieldset>

    <fieldset>
        <legend>
            Administrative user data
        </legend>

        <label>Do you wanna define an administrative user username?</label>
        <div class="form-group-hg">
            <input autocomplete="off" maxlength="16" name="adminUsername" id="adminUsername" class="form-control" placeholder="admin? root? john.doe?" /> 
            <p class="help-block">
                Leave blank if you like to use the standard username for the adminstrative user. It's <strong>admin</strong>. We limit it to 16 characters.
            </p>
        </div>

        <label>Do you wanna define an administrative user password?</label>
        <div class="form-group-hg">
            <input type="password" autocomplete="off" maxlength="32" name="adminPassword" id="adminPassword" class="form-control" /> 
            <p class="help-block">
                Leave blank if you like to use the standard password for the adminstrative user. It's <strong>admin@LPS</strong>. We limit it to 32 characters.
            </p>
        </div>
    </fieldset>
    
    <fieldset>
        <legend>
            Application data
        </legend>

        <label>Do you wanna define an application-wide title?</label>
        <div class="form-group-hg">
            <input autocomplete="on" maxlength="128" name="appTitle" id="appTitle" class="form-control" placeholder="MyApp? TestSubject01? LOL?" /> 
            <p class="help-block">
                Leave blank if you like to use the standard title. It's <strong>org.consultjr.EventsManagement</strong>. We limit it to 128 characters. We use this title on 
            </p>
        </div>

    </fieldset>

    <hr />
    <div class="row footer">
        <input type="submit" value="Install" class="btn btn-default btn-embossed btn-primary btn-hg" />
    </div>
</form:form>
<%@include file="../../templates/footer.jspf" %>
