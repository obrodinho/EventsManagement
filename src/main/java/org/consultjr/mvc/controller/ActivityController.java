package org.consultjr.mvc.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.core.components.AppUtils;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.service.ActivityService;
import org.consultjr.mvc.service.ActivityTypeService;
import org.consultjr.mvc.service.ClassesService;
import org.consultjr.mvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Activity Service
 *
 * @author kallenon
 */
@Controller
@RequestMapping("Activity")
@SessionAttributes("activity")
public class ActivityController extends ApplicationController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityTypeService activityTypeService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private EventService eventService;

    public void setActivityService(final ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping("") // Index Method: => /PROJECT/Activity
    public ModelAndView index() {
        return this.allActivities();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // GET: /PROJECT/Activity/add
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        modelAndView.addObject("activity", new Activity());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("activityID", null);
        modelAndView.addObject("activityTypes", activityTypeService.getActivityTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // Save Method: POST /PROJECT/Activity/add
    public ModelAndView addActivity(@ModelAttribute Activity activity, BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            getLogger().info("Binding Error");
        }
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/all");

        if (activity.getEvent() == null && eventService.getEvents().size() > 0) {
            activity.setEvent(eventService.getEvents().get(0));
        }
        activityService.addActivity(activity);

        Classes standardClasses = new Classes();
        standardClasses.setActivity(activity);
        standardClasses.setStandard(true);
        standardClasses.setDescription("Turma Padrão");
        standardClasses.setCreated(new Date());
        standardClasses.setTitle("Turma Padrao");
        classesService.addClasses(standardClasses);

        String message = "Activity was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        Activity activity = activityService.getActivityById(id);

        activity.setDateStart(AppUtils.FormatDate(activity.getStart()));
        activity.setDateEnd(AppUtils.FormatDate(activity.getEnd()));

        modelAndView.addObject("activity", activity);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("activityID", activity.getId());
        modelAndView.addObject("activityTypes", activityTypeService.getActivityTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateActivity(@ModelAttribute Activity activity, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/all");
        activityService.updateActivity(activity, id);
        String message = "Activity was successfully edited.";
        modelAndView.addObject("message", message);
        
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteActivity(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/all");
        //TODO checar se houve deleção!!!
        activityService.deleteActivity(activityService.getActivityById(id));
        List<Activity> activities = activityService.getActivities();
        modelAndView.addObject("activities", activities);
        String message = "Activity was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allActivities() {
        ModelAndView modelAndView = new ModelAndView("Activity/_list");
        List<Activity> activities = activityService.getActivities();
        modelAndView.addObject("activities", activities);
        return modelAndView;
    }

}
