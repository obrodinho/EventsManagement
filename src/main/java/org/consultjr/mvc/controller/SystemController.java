/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import java.util.Date;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.core.components.AppUtils;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.ActivityType;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.model.Event;
import org.consultjr.mvc.model.SubscriptionProfile;
import org.consultjr.mvc.model.SystemConfig;
import org.consultjr.mvc.model.SystemProfile;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.model.UserSystemProfile;
import org.consultjr.mvc.service.ActivityService;
import org.consultjr.mvc.service.ActivityTypeService;
import org.consultjr.mvc.service.ClassesService;
import org.consultjr.mvc.service.EventService;
import org.consultjr.mvc.service.SubscriptionProfileService;
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
public class SystemController extends ApplicationController {

    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemProfileService spService;
    @Autowired
    private UserSystemProfileService uspService;
    @Autowired
    private EventService evtService;
    @Autowired
    private ActivityTypeService actTypeService;
    @Autowired
    private ActivityService actService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private SubscriptionProfileService subsProfileService;

    public SystemConfigService getSystemConfigService() {
        return systemConfigService;
    }

    public void setSystemConfigService(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    public ModelAndView index() {
        return new ModelAndView("redirect:/System/admin");
    }

    @RequestMapping("admin")
    public ModelAndView admin() {
        ModelAndView sysView = new ModelAndView("System/admin");
        sysView.addObject("configs", systemConfigService.getConfigs());
        return sysView;
    }

    @RequestMapping("/install")
    public ModelAndView install() {
        ModelAndView sysView = new ModelAndView("forward:/loginForm");

        if (systemConfigService.getConfigByKey("_installed") == null) {

            SystemProfile adminProfile = new SystemProfile("admin", "Administrador do Sistema");
            spService.addSystemProfile(adminProfile);

            User defaultUser = new User("Administrador", "do Sistema", "admin", "admin@LPS");
            userService.addUser(defaultUser);

            UserSystemProfile usp = new UserSystemProfile(userService.getUserByUsername("admin"), spService.getSystemProfileByShortname("admin"));
            uspService.addUserSystemProfile(usp);

            Event evt = new Event("Evento Padrão", "Padrão", userService.getUserByUsername("admin"), new Date(), new Date());
            evtService.addEvent(evt);

            actTypeService.addActivityType(new ActivityType("Default", "default", "Example Description"));
            actTypeService.addActivityType(new ActivityType("Course", "course", "Example Description"));
            actTypeService.addActivityType(new ActivityType("Workshop", "workshop", "Example Description"));
            actTypeService.addActivityType(new ActivityType("Laboratory", "laboratory", "Example Description"));

            Activity act = new Activity(evt, "Default Activity", "Default", actTypeService.getActivityTypeByShortname("default"));
            actService.addActivity(act);
            Classes cls = new Classes(act, "Default Class", "Default", true);
            classesService.addClasses(cls);

            subsProfileService.addSubscriptionProfile(new SubscriptionProfile("Participante", "participante"));
            subsProfileService.addSubscriptionProfile(new SubscriptionProfile("Palestrante", "palestrante"));
            subsProfileService.addSubscriptionProfile(new SubscriptionProfile("Monitor", "Monitor"));

            systemConfigService.addConfig(new SystemConfig("_installed", "yes"));
            systemConfigService.addConfig(new SystemConfig("_configuredAt", new Date().toString()));

            sysView.addObject("message", "Initial database objects has been created.");

        } else {
            sysView.addObject("message", "Initial database objects has already been created.");
        }

        return sysView;
    }

}
