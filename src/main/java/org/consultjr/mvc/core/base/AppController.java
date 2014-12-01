/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 * Base Controller Operations
 *
 * @author rgcs
 */
public abstract class AppController {

    @RequestMapping("")
    protected ModelAndView index() {
        return new ModelAndView("index");
    }

}
