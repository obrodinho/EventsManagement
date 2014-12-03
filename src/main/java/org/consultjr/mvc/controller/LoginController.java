/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import javax.servlet.http.HttpSession;
import org.consultjr.mvc.model.Login;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Murilo
 */
@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/menu")
    public ModelAndView menu(){
        return new ModelAndView("menu");
    }
    
    @RequestMapping(value = "/loginForm")
    public ModelAndView login(){
        return new ModelAndView("login-form","login",new Login());
    }
    
    @RequestMapping(value = "/doLogin") // Save Method: POST /PROJECT/User/add
    public ModelAndView doLogin(@ModelAttribute Login login,HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("login");
        User user = userService.getUserByUsername(login.getUsername());
        if(user == null){
            String message = "User"+login.getUsername()+ "doesn`t exists";
            modelAndView.addObject("message", message);
            return modelAndView;
        } else {
            if(user.getPassword().equals(login.getPassword())){
                session.setAttribute("usuarioLogado", user);
                modelAndView = new ModelAndView("menu");
                return modelAndView;
            } else {
               String message = "Incorrect password";
               modelAndView.addObject("message", message);
               return modelAndView;     
            }
        }
    }
}
