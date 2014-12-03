/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.consultjr.mvc.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Murilo
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    private RequestAnalyser rqAnalyser;
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object controller) throws Exception {
        String uri = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("usuarioLogado");
        //Se Usuario esta logado
        if(user != null) {
            return true;
            //Verifica se ele tem permissao para acessar a pagina
            /*if(rqAnalyser.checkPermission(user, request)){
                return true;
            } else {
                //se nao tiver manda de volta pra tela inicial dele
                response.sendRedirect("/menu");
                return false;
            }*/
        } else {
            //se nao ta logado volta pra tela de login
            if(uri.endsWith("/") || uri.endsWith("loginForm") || uri.endsWith("doLogin")){
                return true;
            }
            response.sendRedirect("/org.consultjr.eventsmanagement/loginForm");
            return false;
        }
        
        
    }
    
}
