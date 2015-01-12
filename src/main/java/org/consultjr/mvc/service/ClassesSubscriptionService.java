/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.consultjr.mvc.core.base.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.consultjr.mvc.dao.ClassesSubscriptionDAO;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.model.ClassesSubscription;
import org.consultjr.mvc.model.Payment;
import org.consultjr.mvc.model.User;

/**
 *
 * @author Mario
 */
@Service("ClassesSubscritptionService")
@Transactional(readOnly = true)
public class ClassesSubscriptionService extends ApplicationService {
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

    public ClassesSubscription getOneSubscriptionOfUser(int classesId, int userId) {
        return getClassesSubscriptionDAO().getOneSubscriptionOfUser(classesId, userId);
    }

    public List<ClassesSubscription> getClassesSubscription() {
        return getClassesSubscriptionDAO().getClassesSubscription();
    }
    
    public List<ClassesSubscription> getClassesSubscriptionByUser(int userId) {
        return getClassesSubscriptionDAO().getClassesSubscriptionByUser(userId);
    }

    public ClassesSubscriptionDAO getClassesSubscriptionDAO() {
        return subscriptionDAO == null ? new ClassesSubscriptionDAO() : subscriptionDAO;
    }

    public void setClassesSubscriptionDAO(ClassesSubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }
    
    public ClassesSubscription getClassesById(int id) {
        return getClassesSubscriptionDAO().getClassesSubscriptionById(id);
    }
    
    public ClassesSubscription getClassesSubscriptionByIdPayment(int idPayment) {
        return getClassesSubscriptionDAO().getClassesSubscriptionByIdPayment(idPayment);
    }
    
    public List<User> getUsersOfClass(int id) {
        return getClassesSubscriptionDAO().getUsersOfClass(id);
    }

    public List<Classes> getClassesOfUser(int id) {
        return getClassesSubscriptionDAO().getClassesOfUser(id);
    }

    public List<Classes> getPaidClassesOfUser(int id) {
        return getClassesSubscriptionDAO().getPaidClassesOfUser(id);
    }

    public List<Classes> getNonPaidClassesOfUser(int id) {
        return getClassesSubscriptionDAO().getNonPaidClassesOfUser(id);
    }
    
    public List<Activity> getPaidActivitiesOfUser(int id) {
        return getClassesSubscriptionDAO().getPaidActivitiesOfUser(id);
    }

    public List<Activity> getNonPaidActivitiesOfUser(int id) {
        return getClassesSubscriptionDAO().getNonPaidActivitiesOfUser(id);
    }
    
    public Payment getPaymentOfNonPaidUserActivity(int activityID, int userID) {
        return getClassesSubscriptionDAO().getPaymentOfNonPaidUserActivity(activityID, userID);
    }
    
    public Map<Activity, Payment> getNonPaidActivitiesAndPaymentInfoOfUser(int id) {
        return getClassesSubscriptionDAO().getNonPaidActivitiesAndPaymentInfoOfUser(id);
    }
}
