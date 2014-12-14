/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import org.consultjr.mvc.core.base.ApplicationController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
public class IndexController extends ApplicationController {

    @RequestMapping("about")
    protected ModelAndView about() {
        return new ModelAndView("about");
    }

    @RequestMapping("signup")
    protected ModelAndView signup() {
        return new ModelAndView("signup");
    }
}
