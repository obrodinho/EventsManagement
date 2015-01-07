package org.consultjr.mvc.controller;

import org.consultjr.mvc.core.base.ApplicationController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kal Lenon
 */
@Controller
public class SupportController extends ApplicationController {

    @RequestMapping("/support")
    public ModelAndView support() {

        ModelAndView supportView = new ModelAndView("support");

        if (!getApplicationObject().suports("Support")) {
            supportView.setViewName("forward:/contact");
            supportView.addObject("message", "Hey, pirate! This solution is not supported by your Product. Contact us!");
        }

        return supportView;
    }

    @RequestMapping("/contact")
    public ModelAndView contact() {

        ModelAndView supportView = new ModelAndView("contact");

        return supportView;
    }
}
