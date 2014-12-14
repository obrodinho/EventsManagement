package org.consultjr.mvc.controller;

import java.util.List;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.model.ActivityType;
import org.consultjr.mvc.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
@RequestMapping("ActivityType")
public class ActivityTypeController extends ApplicationController {

    @Autowired
    private ActivityTypeService activityTypeService;

    public void setActivityTypeService(final ActivityTypeService activityTypeService) {
        this.activityTypeService = activityTypeService;
    }

    @RequestMapping("") // Index Method: => /PROJECT/ActivityType
    public ModelAndView index() {
        return new ModelAndView("redirect:/ActivityType/all");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // GET: /PROJECT/ActivityType/add
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("ActivityType/_form");
        modelAndView.addObject("activityType", new ActivityType());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("activityTypeID", null);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // Save Method: POST /PROJECT/ActivityType/add
    public ModelAndView addActivityType(@ModelAttribute ActivityType activityType) {
        ModelAndView modelAndView = new ModelAndView("ActivityType/_form");
        activityTypeService.addActivityType(activityType);
        String message = "ActivityType was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("ActivityType/_form");
        ActivityType activityType = activityTypeService.getActivityTypeById(id);
        modelAndView.addObject("activityType", activityType);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("activityTypeID", activityType.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateActivityType(@ModelAttribute ActivityType activityType, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/ActivityType/all");
        activityTypeService.updateActivityType(activityType, id);
        String message = "ActivityType was successfully edited.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteActivityType(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/ActivityType/all");
        //TODO checar se houve deleção!!!
        activityTypeService.deleteActivityType(activityTypeService.getActivityTypeById(id));
        List<ActivityType> activityTypes = activityTypeService.getActivityTypes();
        modelAndView.addObject("activityTypes", activityTypes);
        String message = "ActivityType was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allActivitiesType() {
        ModelAndView modelAndView = new ModelAndView("ActivityType/_list");
        List<ActivityType> activityTypes = activityTypeService.getActivityTypes();
        modelAndView.addObject("activityTypes", activityTypes);
        return modelAndView;
    }

}
