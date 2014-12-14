/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

/**
 *
 * @author rgcs
 */
public class ApplicationService {

    private final Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public ApplicationService() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }
}
