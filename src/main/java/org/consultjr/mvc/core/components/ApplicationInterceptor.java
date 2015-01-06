/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.components;

import javax.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.consultjr.mvc.service.SystemConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SystemConfigService sysConfigService = new SystemConfigService();
    
    private Logger logger = LoggerFactory
            .getLogger(ApplicationInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String queryString = request.getQueryString();
        String uri = request.getRequestURI();
        StringBuffer url = request.getRequestURL();       
        
        
        logger.info("URL: {}", url);
        logger.info("URI: {}", uri);
        logger.info("QUERY STRING: {}", queryString);
        
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
