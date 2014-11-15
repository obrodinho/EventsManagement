/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import org.consultjr.mvc.model.User;
import org.consultjr.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author rgcs
 */
@Controller
@Scope("request")
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService uService;

    public void setuService(final UserService uService) {

        this.uService = uService;

    }

    public UserService getuService() {
        return uService;
    }

}
