/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.dao.ClassesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.consultjr.mvc.dao.ClassesSubscriptionDAO;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.model.ClassesSubscription;

/**
 *
 * @author Mario
 */
@Service("ClassesSubscritptionService")
@Transactional(readOnly = true)
public class ClassesSubscriptionService {
    @Autowired
    ClassesSubscriptionDAO subscriptionDAO;
    
    @Transactional(readOnly = false)
    public void addClassesSubscription(ClassesSubscription subscription) {
        subscription.setAssociated(new Date());
        getClassesSubscriptionDAO().addClassesSubscription(subscription);
    }

    @Transactional(readOnly = false)
    public void deleteClassesSubscription(ClassesSubscription subscription) {
        getClassesSubscriptionDAO().deleteClassesSubscription(subscription);
    }

    @Transactional(readOnly = false)
    public void updateClassesSubscription(ClassesSubscription subscription) {
        getClassesSubscriptionDAO().updateClassesSubscription(subscription);
    }

    public ClassesSubscription getClassesSubscription(int classesId, int userId) {
        return getClassesSubscriptionDAO().getClassesSubscription(classesId, userId);
    }

    public List<ClassesSubscription> getClassesSubscription() {
        return getClassesSubscriptionDAO().getClassesSubscription();
    }
    

    public ClassesSubscriptionDAO getClassesSubscriptionDAO() {
        return subscriptionDAO == null ? new ClassesSubscriptionDAO() : subscriptionDAO;
    }

    public void setClassesSubscriptionDAO(ClassesSubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }
}
