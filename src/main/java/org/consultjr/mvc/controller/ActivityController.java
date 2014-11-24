package org.consultjr.mvc.controller;

import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * Activity Service
 *
 * @author kallenon
 */
@Controller
@Scope("request")
public class ActivityController {

    @Autowired
    private ActivityService aService;

    public Activity getActivityInformation() {
        return aService.getActivityDetails();
    }

    public void setaService(final ActivityService aService) {
        this.aService = aService;
    }

}
