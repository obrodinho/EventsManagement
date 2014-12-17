/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
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
    public ModelAndView login() {
        return new ModelAndView("login-form", "login", new Login());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(@ModelAttribute Login login, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("login-form");
        User user = userService.getUserByUsername(login.getUsername());
        if (user == null) {
            modelAndView.addObject("message", "User " + login.getUsername() + " doesn't exists");
            return modelAndView;
        } else {
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
                       
            if (pe.matches(login.getPassword(), user.getPassword())) {
                session.setAttribute("usuarioLogado", user);
                modelAndView = new ModelAndView("forward:/");
                modelAndView.addObject("message", "Welcome, " + user.getFirstname() + " " + user.getLastname()  + "!");
                return modelAndView;
            } else {
                modelAndView.addObject("message", "Incorrect password");
                return modelAndView;
            }
        }
    }
        
    @RequestMapping(value = "/403")
    public ModelAndView accessDenied() {
        return new ModelAndView("/403");
    }

}
