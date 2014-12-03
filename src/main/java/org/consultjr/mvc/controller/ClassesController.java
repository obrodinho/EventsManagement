/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import java.util.List;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.service.ClassesService;
import org.consultjr.mvc.service.ActivityService;
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
 * @author Mario
 */

@Controller
@Scope("request")
@RequestMapping("/Classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;
    @Autowired
    private ActivityService activityService;

    public void setClassesService(final ClassesService classesService) {
        this.classesService = classesService;
    }
    
    @RequestMapping("") // Index Method: => /PROJECT/Classes
    public ModelAndView index() {
        return this.allClasses();
    }
    
    

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET) // GET: /PROJECT/Classes/add
    public ModelAndView add(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Classes/_form");
        Classes classes = new Classes();       
        classes.setStandard(false);
        modelAndView.addObject("classes", classes);
        modelAndView.addObject("action", "add");
        modelAndView.addObject("classesID", null);
        modelAndView.addObject("activityID", id);
        return modelAndView;
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST) // Save Method: POST /PROJECT/Classes/add
    public ModelAndView addClasses(@ModelAttribute Classes classes, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Classes/_form");
        
        Activity defaultActivity = activityService.getActivityById(id);
        classes.setActivity(defaultActivity);
        
        //Activity defaultActivity = new Activity();
        //Activity defaultActivity = activityService.getActivityById(id);
        //classes.setActivity(defaultActivity);
        //defaultActivity.setId(1);        
        //classes.setActivity(defaultActivity);
        // create all strategies for assigning the event. 
        // The activity controller must be used only by managing a Event
        classesService.addClasses(classes);
        String message = "Class was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
   
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Classes/_form");
        Classes classes = classesService.getClassesById(id);
        modelAndView.addObject("classes", classes);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("classesID", classes.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateClasses(@ModelAttribute Classes classes, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Classes/_form");
        classesService.updateClasses(classes);
        String message = "Class was successfully edited.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteClasses(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Classes/_list");
        //TODO checar se houve deleção!!!
        classesService.deleteClasses(classesService.getClassesById(id));
        List<Classes> manyClasses = classesService.getClasses();
        if (manyClasses.size() > 1 ){
            for(int i = 0; i < manyClasses.size(); i++){  
                if (manyClasses.get(i).getStandard() == true){
                    manyClasses.remove(i);
                }
            }
        }
        modelAndView.addObject("classes", manyClasses);
        String message = "Class was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allClasses() {
        ModelAndView modelAndView = new ModelAndView("Classes/_list");
        List<Classes> manyClasses = classesService.getClasses();
        modelAndView.addObject("classes", manyClasses);
        return modelAndView;
    }
    
    @RequestMapping(value = "/all/{id}")
    public ModelAndView allClasses(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Classes/_list");
        List<Classes> manyClasses = classesService.getClassesByActivity(id);
        if (manyClasses.size() > 1 ){
            for(int i = 0; i < manyClasses.size(); i++){  
                if (manyClasses.get(i).getStandard() == true){
                    manyClasses.remove(i);
                }
            }
        }
        modelAndView.addObject("classes", manyClasses);
        return modelAndView;
    }

}
