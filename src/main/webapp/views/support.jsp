<%-- 
    Document   : support
    Created on : 06/01/2015, 21:18:11
    Author     : Rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../templates/header.jspf" %>

<div class="page-header">
    <h2>Support</h2>
</div>

<div class="row message">
    ${message}
</div>
<div class="row">
    
    <div class="col-md-6 col-md-offset-3 well">
        <div class="row">
            <div class="col-md-12" style="min-height: 200px; background: white">
                <p class="well thumbnail small">
                    Please wait until we reach you...
                </p>
            </div>
        </div>
        
        <div class="row">
        
            <form>
                <div class="col-md-8">
                    <textarea class="col-md-12" placeholder="Write your message here..." disabled="disabled">
                        
                    </textarea>
                </div>
                <div class="col-md-4">
                    <input type="submit" class="btn btn-default btn-embossed btn-primary btn-hg" disabled="disabled" value="Send"/>
                </div>
                    
            </form>
            
        </div>
        
        
    </div>
    
</div>
    
    
<%@include file="../templates/footer.jspf" %>
