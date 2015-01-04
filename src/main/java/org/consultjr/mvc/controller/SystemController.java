/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import java.util.Date;
import org.consultjr.mvc.core.base.ApplicationController;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("redirect:/System/settings");
    }

    @RequestMapping("settings")
    public ModelAndView admin() {
        ModelAndView sysView = new ModelAndView("System/settings");
        sysView.addObject("configs", systemConfigService.getConfigs());
        return sysView;
    }

    @RequestMapping(value = "install", method = RequestMethod.GET)
    public ModelAndView install() {
        ModelAndView sysView = new ModelAndView("System/install");

        if (null != systemConfigService.getConfigByKey("_installed")) {
            sysView.addObject("message", "The system already was installed.");
            sysView.setViewName("forward:/login");
            return sysView;
        }

        sysView.addObject("install", new Object());
        return sysView;
    }

    @RequestMapping(value = "install", method = RequestMethod.POST)
    public ModelAndView setup(
            @RequestParam(value = "installKey", required = true) String installKey,
            @RequestParam(value = "adminUsername", required = false) String adminUsername,
            @RequestParam(value = "adminPassword", required = false) String adminPassword
    ) {

        ModelAndView setupView = new ModelAndView("forward:/");
        /* TODO make it on a hash
         KEY COULD BE LIKE XXXX-XXXX-9999-9999
         Where:  XXXX-XXXX = 2 hex groups from a hash string
         9999-9999 = 2 four-digit arbritary numbers (until now)
         ------------------------------------------------------------------------
         * org.consultjr.EventsManagement|productType=multiEvents|generated=2015-01-04|valid=2015-12-31
         ADLER32: 88B520F8 => 88B5-20F8
         ------------------------------------------------------------------------
         * org.consultjr.EventsManagement|productType=singleEvent|generated=2015-01-04|valid=2015-12-31
         ADLER32: 83FF20DC => 83FF-20DC
         ------------------------------------------------------------------------
         The very first group is enough to realize wich product will be set.
         We could "voyage" on this idea and the key possibly could hold great info about system's variance.
         Each group could hold 16 bit of information. So, up to 16 variance definitions, such like:
         General variances:
         Language;
         Timezone;
         Charset;
         Database schema;
         Default Template;
         ...
         Product-specific variances;
         Enviromental variances:
         Validation servers;
         Update-check time;
         ...
         And so on...
         Possibilities are enormous.
         Our limits is only ours.
         */
        String productType = null;
        getLogger().info(installKey);
        getLogger().info(String.valueOf(installKey.length()));

        if (19 == installKey.length()) {
            String[] keyParts = installKey.split("-");
            if (null != keyParts[0]) {
                switch (keyParts[0]) {
                    case "88B5":
                        productType = "multiEvents";
                        break;
                    case "83FF":
                        productType = "singleEvent";
                        break;
                }
            }
        } else {
            setupView.addObject("message", "Invalid KEY. Please inform us a valid one.");
            setupView.setViewName("System/install");
            return setupView;
        }

        if (null != productType && null == systemConfigService.getConfigByKey("_installed")) {
            /*
             Here we must test if the database is reachable, and decide to create it, or not.
             The database must be create on system instalation
             */

            SystemProfile adminProfile = new SystemProfile("admin", "Administrador do Sistema");
            spService.addSystemProfile(adminProfile);

            SystemProfile userProfile = new SystemProfile("client", "Usuario Final");
            spService.addSystemProfile(userProfile);

            SystemProfile guestProfile = new SystemProfile("guest", "Convidado");
            spService.addSystemProfile(guestProfile);

            /*
             TODO we must test the strongness and validity of username and password.
             */
            adminUsername = (!adminUsername.isEmpty() ? adminUsername : "admin");
            adminPassword = (!adminPassword.isEmpty() ? adminPassword : "admin@LPS");
            User defaultUser = new User(
                    "Administrador",
                    "do Sistema",
                    adminUsername,
                    adminPassword
            );

            userService.addUser(defaultUser);

            UserSystemProfile usp = new UserSystemProfile(userService.getUserByUsername(adminUsername), spService.getSystemProfileByShortname("admin"));
            uspService.addUserSystemProfile(usp);

            Event evt = new Event("Evento Padrão", "Padrão", userService.getUserByUsername(adminUsername), new Date(), new Date());
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
            subsProfileService.addSubscriptionProfile(new SubscriptionProfile("Monitor", "monitor"));

            systemConfigService.addConfig(new SystemConfig("_installed", "yes"));
            systemConfigService.addConfig(new SystemConfig("_configuredAt", new Date().toString()));

            systemConfigService.addConfig(new SystemConfig("_productType", productType));

            setupView.addObject("message", "Initial database objects has been created.");

        } else {
            setupView.addObject("message", "Installation failed. Check your product KEY or try contacting support.");
            setupView.setViewName("forward:/");
        }

        return setupView;
    }

}
