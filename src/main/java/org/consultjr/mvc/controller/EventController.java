package org.consultjr.mvc.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.model.Event;
import org.consultjr.mvc.service.EventService;
import org.consultjr.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Activity Service
 *
 * @author kallenon
 */
@Controller
@Scope("request")
@RequestMapping("/Event")
public class EventController extends ApplicationController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

//    private ClassesService classesService;
//    @Autowired
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("") // Index Method: => /PROJECT/Event
    public ModelAndView index() {
        return this.allEvents();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // GET: /PROJECT/Event/add
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Event/_form");
        modelAndView.addObject("event", new Event());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("eventID", null);
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // Save Method: POST /PROJECT/Event/add
    public ModelAndView addEvent(@ModelAttribute Event event, BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            getLogger().info("Binding Error");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:all");
        //event.setOwner(userService.getUserById(event.getUserID()));
        eventService.addEvent(event);
        String message = "Event was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Event/_form");
        Event event = eventService.getEventById(id);

//        event.setDateStart(AppUtils.FormatDate(activity.getStart()));
//        activity.setDateEnd(AppUtils.FormatDate(activity.getEnd()));
        modelAndView.addObject("event", event);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("eventID", event.getId());
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateEvent(@ModelAttribute Event event, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Event/_form");
        eventService.updateEvent(event, id);
        String message = "Event was successfully edited.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEvent(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Event/_list");
        //TODO checar se houve deleção!!!
        eventService.deleteEvent(eventService.getEventById(id));
        List<Event> events = eventService.getEvents();
        modelAndView.addObject("events", events);
        String message = "Event was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allEvents() {
        ModelAndView modelAndView = new ModelAndView("Event/_list");
        List<Event> events = eventService.getEvents();
        modelAndView.addObject("events", events);
        return modelAndView;
    }

}