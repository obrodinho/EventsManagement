/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.model.UserSystemProfile;
import org.consultjr.mvc.service.SystemProfileService;
import org.consultjr.mvc.service.UserService;
import org.consultjr.mvc.service.UserSystemProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
public class IndexController extends ApplicationController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSystemProfileService uspService;
    @Autowired
    private SystemProfileService spService;

    @RequestMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView indexView = new ModelAndView("/User/signup");

        indexView.addObject("user", new User());

        return indexView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView addUserAsClient(@ModelAttribute User user) {
        ModelAndView indexView = new ModelAndView("/User/signup");

        //TESTS
        if (null != userService.getUserByUsername(user.getUsername())) {
            indexView.addObject("message", "This user already exists.");
            indexView.addObject("user", user);
        } else {
            userService.addUser(user);
            uspService.addUserSystemProfile(new UserSystemProfile(user, spService.getSystemProfileByShortname("client")));
            indexView.addObject("message", "Registration successfull!");
            return new ModelAndView("forward:/loginForm");
        }

        return indexView;
    }
}
