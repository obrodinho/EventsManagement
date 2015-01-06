/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import java.security.Principal;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.model.UserSystemProfile;
import org.consultjr.mvc.service.SystemConfigService;
import org.consultjr.mvc.service.SystemProfileService;
import org.consultjr.mvc.service.UserService;
import org.consultjr.mvc.service.UserSystemProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends ApplicationController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSystemProfileService uspService;
    @Autowired
    private SystemProfileService spService;
    @Autowired
    private SystemConfigService sysService;

    @RequestMapping("/")
    public ModelAndView index(Principal principal) {

        /* It should be done by an Interceptor or an Aspect*/
        if (sysService.getConfigByKey("_installed") == null) {
            ModelAndView indexView = new ModelAndView("forward:/System/install");

            indexView.addObject("hideNav", (true));
            indexView.addObject("hideFooter", (true));

            //indexView.addObject("hideAll", (true)); // hide header is useless.
            return indexView;
        }

        ModelAndView indexView = new ModelAndView("index");
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication auth = sc.getAuthentication();
        getLogger().info(auth.getAuthorities().toString());
        if (principal != null) {
            getLogger().info(getLoggedUser().toString());
            if (sysService.getConfigByKey("_productType").getValue().equals("singleEvent")) {
                if (uspService.userHasRole(getLoggedUser().getId(), "admin")) {
                    getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "admin")));
                    ModelAndView modelAndView = new ModelAndView("index-admin-mono");
                    return modelAndView;
                }
                if (uspService.userHasRole(getLoggedUser().getId(), "client")) {
                    getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "client")));
                    ModelAndView modelAndView = new ModelAndView("index-client-mono");
                    return modelAndView;
                }

            }
            if (sysService.getConfigByKey("_productType").getValue().equals("multiEvents")) {
                if (uspService.userHasRole(getLoggedUser().getId(), "admin")) {
                    getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "admin")));
                    ModelAndView modelAndView = new ModelAndView("index-admin-multi");
                    return modelAndView;
                }
                if (uspService.userHasRole(getLoggedUser().getId(), "client")) {
                    getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "client")));
                    ModelAndView modelAndView = new ModelAndView("index-client-multi");
                    return modelAndView;
                }

            }

        }
        if (sysService.getConfigByKey("_productType") != null && sysService.getConfigByKey("_productType").getValue().equals("multiEvents")) {
            return new ModelAndView("index-multi");
        }
        return new ModelAndView("index-mono");
    }

    @RequestMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView indexView = new ModelAndView("/Client/signup");

        indexView.addObject("user", new User());

        return indexView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView addUserAsClient(@ModelAttribute User user) {
        ModelAndView indexView = new ModelAndView("/Client/signup");

        //TESTS
        if (null != userService.getUserByUsername(user.getUsername())) {
            indexView.addObject("message", "This user already exists.");
            indexView.addObject("user", user);
        } else {
            userService.addUser(user);
            uspService.addUserSystemProfile(new UserSystemProfile(user, spService.getSystemProfileByShortname("client")));
            indexView.addObject("message", "Registration successfull!");
            return new ModelAndView("forward:/login");
        }

        return indexView;
    }
}
