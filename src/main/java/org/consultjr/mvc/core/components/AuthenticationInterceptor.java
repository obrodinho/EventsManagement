/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.consultjr.mvc.model.Type;
import org.consultjr.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Murilo
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private final RequestAnalyser rqAnalyser = new RequestAnalyser();

    private final SystemConfigService sysService = new SystemConfigService();

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object controller) throws Exception {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        User user = (User) request.getSession().getAttribute("usuarioLogado");
        //Se Usuario esta logado
        if (user != null) {
            //return true;
            //Verifica se ele tem permissao para acessar a pagina
            user.setType(Type.ADMIN);
            if (uri.endsWith(contextPath)/* || uri.endsWith("login") || uri.endsWith("doLogin")*/) {
                return true;
            }
            if (rqAnalyser.checkPermission(user, request)) {
                return true;
            } else {
                //se nao tiver manda de volta pra tela inicial dele
                response.sendRedirect(contextPath + "/menu");
                return false;
            }
//        } else if (user == null && sysService.getConfigByKey("_installed") == null) {
//            response.sendRedirect(contextPath + "/System/install");
//            return false;
        } else {
            //se nao ta logado volta pra tela de login
            if (uri.endsWith(contextPath)
                    || uri.endsWith("/login")
                    || uri.endsWith("/doLogin")
                    || uri.endsWith("/System/install")
                    || uri.endsWith("/signup")
                    || uri.endsWith("/about")) {
                return true;
            } else {
                response.sendRedirect(contextPath + "/login");
                return false;
            }
        }
    }
}
