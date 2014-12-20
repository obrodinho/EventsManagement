/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rafael
 */
public abstract class ApplicationModel {

    private final Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public ApplicationModel() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object object);
}
