/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.model.Login;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Murilo
 */
@Controller
public class LoginController extends ApplicationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView loginForm(Principal principal) {
        ModelAndView index = new ModelAndView();

        if (principal != null || getLoggedUser() != null) {
            index.setViewName("redirect:/");
        } else {
            index.setViewName("login-form");
            index.addObject("login", new Login());
        }

        return index;
    }

    @RequestMapping(value = "/403")
    public ModelAndView accessDenied() {
        return new ModelAndView("/403");
    }

}
