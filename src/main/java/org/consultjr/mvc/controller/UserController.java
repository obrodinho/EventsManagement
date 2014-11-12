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

/**
 *
 * @author rgcs
 */
@Controller
@Scope("request")
public class UserController {

    @Autowired

    private UserService uService;

    public User getUserInformation() {

        return uService.getUserDetails();

    }

    public void setuService(final UserService uService) {

        this.uService = uService;

    }

}
