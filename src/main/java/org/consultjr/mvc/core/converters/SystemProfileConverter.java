/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.converters;

import org.consultjr.mvc.model.SystemProfile;
import org.consultjr.mvc.service.SystemProfileService;
import org.consultjr.mvc.service.SystemProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Rafael
 */
public class SystemProfileConverter implements Converter<Integer, SystemProfile> {
    @Autowired
    private SystemProfileService spService;
    @Override
    public SystemProfile convert(Integer source) {
        return spService.getSystemProfileById(source);
    }
    
}
