/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.core.components.AppUtils;
import org.consultjr.mvc.dao.SystemProfileDAO;
import org.consultjr.mvc.model.SystemProfile;
import org.consultjr.mvc.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Service("SystemProfileService")
public class SystemProfileService {

    @Autowired
    private SystemProfileDAO spDAO;

    @Transactional(readOnly = false)
    public void addSystemProfile(SystemProfile sp) {
        // create all strategies for assigning the event. 
        getSystemProfileDAO().addSystemProfile(sp);
    }

    @Transactional(readOnly = false)
    public void deleteSystemProfile(SystemProfile sp) {
        getSystemProfileDAO().deleteSystemProfile(sp);
    }

    @Transactional(readOnly = false)
    public void updateSystemProfile(SystemProfile spView, int id) {
        SystemProfile spBD = getSystemProfileById(id);
        
        spBD.setShortname(spView.getShortname());
        spBD.setDescription(spView.getDescription());
        
        spBD.setUpdated(new Date());

        getSystemProfileDAO().updateSystemProfile(spBD);
    }

    public SystemProfile getSystemProfileById(int id) {
        return getSystemProfileDAO().getSystemProfileById(id);
    }
    
    public SystemProfile getSystemProfileByShortname(String shortname) {
        return getSystemProfileDAO().getSystemProfileByShortname(shortname);
    }

    public List<SystemProfile> getSystemProfiles() {
        return getSystemProfileDAO().getSystemProfiles();
    }

    public SystemProfileDAO getSystemProfileDAO() {
        return spDAO == null ? new SystemProfileDAO() : spDAO;
    }

    public void setSystemProfileDAO(SystemProfileDAO spDAO) {
        this.spDAO = spDAO;
    }
}
