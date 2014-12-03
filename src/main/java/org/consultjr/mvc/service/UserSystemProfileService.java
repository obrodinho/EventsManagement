/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.List;
import org.consultjr.mvc.dao.UserSystemProfileDAO;
import org.consultjr.mvc.model.UserSystemProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Service
@Transactional
public class UserSystemProfileService {
    @Autowired
    private UserSystemProfileDAO uspDAO;
    
    @Transactional(readOnly = false)
    public void addUserSystemProfile(UserSystemProfile usp) {
        // create all strategies for assigning the event. 
        getUserSystemProfileDAO().addUserSystemProfile(usp);
    }

    @Transactional(readOnly = false)
    public void deleteUserSystemProfile(UserSystemProfile usp) {
        getUserSystemProfileDAO().deleteUserSystemProfile(usp);
    }

    
    public List<UserSystemProfile> getUserSystemProfileOfUser(int userID) {
        return getUserSystemProfileDAO().getUserSystemProfilesOfUser(userID);
    }
    
    public List<UserSystemProfile> getUserSystemProfileOfProfile(int profileID) {
        return getUserSystemProfileDAO().getUserSystemProfilesOfProfile(profileID);
    }

    public List<UserSystemProfile> getUserSystemProfiles() {
        return getUserSystemProfileDAO().getUserSystemProfiles();
    }

    public UserSystemProfileDAO getUserSystemProfileDAO() {
        return uspDAO == null ? new UserSystemProfileDAO() : uspDAO;
    }

    public void setUserSystemProfileDAO(UserSystemProfileDAO uspDAO) {
        this.uspDAO = uspDAO;
    }
    
}
