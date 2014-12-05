/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import org.consultjr.mvc.core.base.AppController;
import org.consultjr.mvc.model.SystemProfile;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.model.UserSystemProfile;
import org.consultjr.mvc.service.SystemConfigService;
import org.consultjr.mvc.service.SystemProfileService;
import org.consultjr.mvc.service.UserService;
import org.consultjr.mvc.service.UserSystemProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller For System Operations
 *
 * @author Rafael
 */
@Controller
@Scope("request")
@RequestMapping("System")
public class SystemController extends AppController {

    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private UserService userService;

    @Autowired
    private SystemProfileService spService;

    @Autowired
    private UserSystemProfileService uspService;

    public SystemConfigService getSystemConfigService() {
        return systemConfigService;
    }

    public void setSystemConfigService(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    @Override
    public ModelAndView index() {
        return this.admin();
    }

    @RequestMapping("admin")
    public ModelAndView admin() {
        ModelAndView sysView = new ModelAndView("System/admin");
        sysView.addObject("configs", systemConfigService.getConfigs());
        return sysView;
    }

    @RequestMapping("/install")
    public ModelAndView install() {
        ModelAndView sysView = new ModelAndView("User/_list");

        SystemProfile adminProfile = new SystemProfile("admin", "Administrador do Sistema");
        spService.addSystemProfile(adminProfile);
        System.out.println(adminProfile);
        
        User defaultUser = new User("Administrador", "do Sistema", "admin", "admin@LPS");
        userService.addUser(defaultUser);
        System.out.println(defaultUser);
        
        UserSystemProfile usp = new UserSystemProfile(userService.getUserByUsername("admin"), spService.getSystemProfileByShortname("admin"));
        uspService.addUserSystemProfile(usp);
        System.out.println(usp);
        
        sysView.addObject("users", userService.getUsers());
        return sysView;
    }

}