/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.consultjr.mvc.core.base.ApplicationService;
import org.consultjr.mvc.dao.UserSystemProfileDAO;
import org.consultjr.mvc.model.SystemProfile;
import org.consultjr.mvc.model.User;
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
public class UserSystemProfileService extends ApplicationService {

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

    @Transactional
    public List<SystemProfile> getSystemProfilesOfUser(int userID) {
        return uspDAO.getSystemProfilesOfUser(userID);
    }

    @Transactional
    public List<User> getUsersOfSystemProfile(int profileID) {
        return uspDAO.getUsersOfSystemProfile(profileID);
    }

    @Transactional
    public boolean userHasRole(int userID, String roleName) {
        getLogger().info("User: {} | Role: {}", userID, roleName);

        Iterator<SystemProfile> it = this.getSystemProfilesOfUser(userID).iterator();

        while (it.hasNext()) {
            SystemProfile sp = it.next();
            getLogger().info("User Role: {}", sp.getShortname());
            if (sp.getShortname().equals(roleName)) {
                return true;
            }
        }

        return false;
    }

    @Transactional
    public List<String> getRolesOfUser(int userID) {
        List<SystemProfile> list = uspDAO.getSystemProfilesOfUser(userID);
        Iterator<SystemProfile> it = list.listIterator();
        List<String> roles = new ArrayList<>();
        while (it.hasNext()) {
            SystemProfile sp = it.next();
            roles.add(sp.getShortname());
        }
        return roles;
    }

}
