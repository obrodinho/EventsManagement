/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.components;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Rafael
 */
@Interceptor
@Component
public class ApplicationInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory
            .getLogger(ApplicationInterceptor.class);

    private HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String queryString = request.getQueryString();
        String uri = request.getRequestURI();
        StringBuffer url = request.getRequestURL();
        HttpSession session = this.getSession(request);
        Enumeration attributes = session.getAttributeNames();
        Map<String, Object> sessionAttributes = new HashMap<>();

        logger.debug("URL: {}", url);
        logger.debug("URI: {}", uri);
        logger.debug("QUERY STRING: {}", queryString);

        logger.debug("Handler Object: {}", handler);

        while (attributes.hasMoreElements()) {
            String name = (String) attributes.nextElement();

            sessionAttributes.put(name, session.getAttribute(name));
        }

        logger.debug("Session: {}", sessionAttributes);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //modelAndView.getModelMap().addAttribute("_productType", sysConfigService.getConfigByKey("_productType"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex); //To change body of generated methods, choose Tools | Templates.
    }

}
