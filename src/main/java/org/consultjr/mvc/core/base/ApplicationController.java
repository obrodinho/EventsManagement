/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.base;

import org.consultjr.mvc.model.User;
import org.consultjr.mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base Controller Operations
 *
 * @author rgcs
 */
public class ApplicationController {

    private final Logger logger;
    /**
     * Spring Authentication Object
     */
    private Authentication auth;

    @Autowired
    private UserService userService;

    public Logger getLogger() {
        return logger;
    }

    public ApplicationController() {
        this.logger = LoggerFactory.getLogger(this.getClass());
        SecurityContext secutiryContext = SecurityContextHolder.getContext();
        this.auth = secutiryContext.getAuthentication();
    }

    public Authentication getAuth() {
        return auth == null ? SecurityContextHolder.getContext().getAuthentication() : auth;
    }

    public void setAuth(Authentication auth) {
        this.auth = auth;
    }

    public User getLoggedUser() {
        org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) getAuth().getPrincipal();
        if (u != null) {
            return userService.getUserByUsername(u.getUsername());
        }

        return null;
    }
}
