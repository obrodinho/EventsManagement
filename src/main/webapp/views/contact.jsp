<%-- 
    Document   : contact
    Created on : 06/01/2015, 21:18:21
    Author     : Rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../templates/header.jspf" %>

<div class="page-header">
    <h2>Please, contact us!</h2>
</div>

<div class="row message">
    ${message}
</div>
<div class="row">

    <div class="col-md-6 col-md-offset-3 well">
        <form>

            <div class="row">
                <div class="form-group">
                    <label>Your name</label>
                    <input class="form-control" disabled="disabled" />
                </div>
                <div class="form-group">
                    <label>Your e-mail</label>
                    <input class="form-control" disabled="disabled" />
                </div>
                <div class="form-group">
                    <label>Subject</label>
                    <input class="form-control" disabled="disabled" />
                </div>
                <div class="form-group">
                    <label>Your message</label>
                    <textarea class="form-control" placeholder="Write your message here..." disabled="disabled"></textarea>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-default btn-embossed btn-primary" disabled="disabled" value="Contact us"/>
                </div>
            </div>

        </form>

    </div>

</div>
<%@include file="../templates/footer.jspf" %>
