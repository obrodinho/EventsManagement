/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Murilo
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object controller) throws Exception {
      String uri = request.getRequestURI();
        if(uri.endsWith("/") ||
            uri.endsWith("loginForm") || 
              uri.endsWith("doLogin") || 
                 uri.contains("resources")){
            return true;
        }
      
        if(request.getSession().getAttribute("usuarioLogado") != null) {
            return true;
        }
      
        response.sendRedirect("loginForm");
        return false;
    }
    
}
