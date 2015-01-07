/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.model.ClassesSubscription;
import org.consultjr.mvc.model.Event;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.service.ClassesService;
import org.consultjr.mvc.service.ClassesSubscriptionService;
import org.consultjr.mvc.service.EventService;
import org.consultjr.mvc.service.SubscriptionProfileService;
import org.consultjr.mvc.service.UserService;
import org.consultjr.mvc.service.UserSystemProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rgcs
 */
@Controller
@RequestMapping("/User")
public class UserController extends ApplicationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClassesSubscriptionService subscriptionService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private SubscriptionProfileService subscriptionProfileService;
    @Autowired
    private ClassesSubscriptionService classeSubscriptionService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserSystemProfileService uspService;

    @RequestMapping("") // Index Method: => /PROJECT/User
    public ModelAndView index() {
        return new ModelAndView("redirect:/User/all");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // GET: /PROJECT/User/add
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("User/_form");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("userID", null);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // Save Method: POST /PROJECT/User/add
    public ModelAndView addUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("forward:/User/all");
        userService.addUser(user);
        String message = "User was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("User/_form");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("userID", user.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute User user, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/User/all");
        if (uspService.userHasRole(getLoggedUser().getId(), "client")){
            modelAndView = new ModelAndView("forward:/User/panel");
        }
        userService.updateUser(user, id);
        String message = "User was successfully edited.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/User/all");
        //TODO checar se houve deleção do usuário!
        userService.deleteUser(userService.getUserById(id));
        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);
        String message = "User was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView("User/_list");
        List<User> users = userService.getUsers();
        modelAndView.addObject("title", "All Users :D");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = "/all/{id}")
    public ModelAndView allUsers(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("User/_listSubscription");
        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);
        modelAndView.addObject("classId", id);
        modelAndView.addObject("profiles", subscriptionProfileService.getSubscriptionProfiles());
        return modelAndView;
    }

    @RequestMapping(value = "/subscription/{classId}/{userId}/{typeId}")
    public ModelAndView allUsers(@PathVariable int classId, @PathVariable int userId, @PathVariable int typeId) {
        ModelAndView modelAndView = new ModelAndView("User/_listSubscription");

        ClassesSubscription subs = new ClassesSubscription();
        subs.setUser(userService.getUserById(userId));
        subs.setClasses(classesService.getClassesById(classId));
        subs.setSubscriptionProfile(subscriptionProfileService.getSubscriptionProfileById(typeId));
        System.out.println("AQUI" + subs.toString());
        subscriptionService.addClassesSubscription(subs);

        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);
        modelAndView.addObject("classId", classId); 
        modelAndView.addObject("profiles", subscriptionProfileService.getSubscriptionProfiles());
        modelAndView.addObject("message", "Congratulations! The system works");
        return modelAndView;
    }
    
    @RequestMapping(value = "/panel")
    public ModelAndView payamentSubscriptionActivity(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("Client/control-panel");
        int cont = 0;
        
        List<Event> eventsList = new ArrayList<>();
        if(getProductType().equals("multiEvents")){
            List<Event> events = eventService.getEvents();
            if(events != null && events.size() > 1){
                for (int i=events.size()-1; i >= 0; i--){
                    if (cont < 4){
                        eventsList.add(events.get(i));
                        cont++;
                    }
                }
            } else if (events != null && events.size() == 1){
                Event event = new Event();
                event.setId(-1);
                eventsList.add(event);
            }
        }
        
        User user = userService.getUserByUsername(principal.getName());
        List<ClassesSubscription> classesSubscriptionPaymentPending = new ArrayList<>();
        cont = 0;
        List<ClassesSubscription> classesSubscription = classeSubscriptionService.getClassesSubscriptionByUser(user.getId());
        for (int i=classesSubscription.size()-1; i >= 0; i--){
            if (cont < 4){
                if(classesSubscription.get(i).getPayment().getStatus().equals("pending")){
                    classesSubscriptionPaymentPending.add(classesSubscription.get(i));
                    cont++;
                }
            }
        }
        
        modelAndView.addObject("eventsList", eventsList);
        modelAndView.addObject("classesSubscriptionPaymentPending", classesSubscriptionPaymentPending);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /*@RequestMapping(value = "/all/{id}")
     public ModelAndView allUsers(@PathVariable int id) {
     ModelAndView modelAndView = new ModelAndView("User/_listSubscription");
     List<User> users = userService.getUsers();
     modelAndView.addObject("users", users);
     modelAndView.addObject("classId", id);
     return modelAndView;
     }*/
}
