/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author rgcs
 */
@Controller
@RequestMapping("/Hello")
public class HelloWorldController {

    @RequestMapping("")
    public String index() {
        return "Hello/index";
    }

    @RequestMapping("/sayHello")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "Hello/say-hello";
    }

}
