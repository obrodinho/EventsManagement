/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.dao.ClassesSubscriptionDAO;
import org.consultjr.mvc.dao.SubscriptionProfileDAO;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.model.ClassesSubscription;
import org.consultjr.mvc.model.SubscriptionProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario
 */
@Service("SubscritptionProfileService")
@Transactional(readOnly = true)
public class SubscriptionProfileService {
    @Autowired
    SubscriptionProfileDAO subscriptionProfileDAO;
    
    @Transactional(readOnly = false)
    public void addSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        subscriptionProfile.setCreated(new Date());
        getSubscriptionProfileDAO().addSubscriptionProfile(subscriptionProfile);
    }

    @Transactional(readOnly = false)
    public void deleteSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        getSubscriptionProfileDAO().deleteSubscriptionProfile(subscriptionProfile);
    }

    @Transactional(readOnly = false)
    public void updateSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        getSubscriptionProfileDAO().updateSubscriptionProfile(subscriptionProfile);
    }

    public SubscriptionProfile getSubscriptionProfileById(int id) {
        return (getSubscriptionProfileDAO().getSubscriptionProfilesFromId(id)).get(0);
    }
    
    public List<SubscriptionProfile> getSubscriptionProfile() {
        return getSubscriptionProfileDAO().getSubscriptionProfile();
    }
    

    public SubscriptionProfileDAO getSubscriptionProfileDAO() {
        return subscriptionProfileDAO == null ? new SubscriptionProfileDAO() : subscriptionProfileDAO;
    }

    public void setSubscriptionProfileDAO(SubscriptionProfileDAO subscriptionProfileDAO) {
        this.subscriptionProfileDAO = subscriptionProfileDAO;
    }
}
