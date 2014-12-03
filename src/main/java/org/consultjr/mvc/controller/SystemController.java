/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import org.consultjr.mvc.core.base.AppController;
import org.consultjr.mvc.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller For System Operations
 * @author Rafael
 */
@Controller
@Scope("request")
@RequestMapping("System")
public class SystemController extends AppController {
    
    @Autowired
    private SystemConfigService systemConfigService;
    
    public SystemConfigService getSystemConfigService() {
        return systemConfigService;
    }

    public void setSystemConfigService(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }
    
    @Override
    public ModelAndView index() {
        return this.admin();
    }
    
    @RequestMapping("admin")
    public ModelAndView admin() {
        ModelAndView sysView = new ModelAndView("System/admin");
        sysView.addObject("configs", systemConfigService.getConfigs());
        return sysView;
    }
    
    
}
