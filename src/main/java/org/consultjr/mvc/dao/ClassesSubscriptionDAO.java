/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.consultjr.mvc.core.base.ApplicationDAO;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.model.ClassesSubscription;
import org.consultjr.mvc.model.Payment;
import org.consultjr.mvc.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario
 */
@Repository
public class ClassesSubscriptionDAO extends ApplicationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addClassesSubscription(ClassesSubscription subscription) {
        getSessionFactory().getCurrentSession().save(subscription);
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().refresh(subscription);
    }

    @Transactional
    public void deleteClassesSubscription(ClassesSubscription subscription) {
        getSessionFactory().getCurrentSession().delete(subscription);
    }

    @Transactional
    public void updateClassesSubscription(ClassesSubscription subscription) {
        getSessionFactory().getCurrentSession().update(subscription);
    }

    @Transactional
    public ClassesSubscription getOneSubscriptionOfUser(int classesId, int userId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClassesSubscription where class_id=:cid and user_id=:uid")
                .setParameter("cid", classesId)
                .setParameter("uid", userId).list();
        if (list.isEmpty()) {
            return null;
        }

        return (ClassesSubscription) list.get(0);
    }

    @Transactional
    public List<ClassesSubscription> getClassesSubscription() {
        List list = getSessionFactory().getCurrentSession().createQuery("from ClassesSubscription").list();
        return list;
    }

    @Transactional
    public List<ClassesSubscription> getClassesSubscriptionByUser(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClassesSubscription where user_id=:id ")
                .setParameter("id", id).list();
        return list;
    }

    @Transactional
    public List<ClassesSubscription> getSubscriptionsOfClass(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClassesSubscription where class_id=:id ")
                .setParameter("id", id).list();
        return list;
    }

    @Transactional
    public ClassesSubscription getClassesSubscriptionById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClassesSubscription where id=:id ")
                .setParameter("id", id).list();
        if (list.isEmpty()) {
            return null;
        }
        return (ClassesSubscription) list.get(0);
    }

    @Transactional
    public ClassesSubscription getClassesSubscriptionByIdPayment(int idPayment) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClassesSubscription where payment_id=?")
                .setParameter(0, idPayment).list();
        if (list.isEmpty()) {
            return null;
        }
        return (ClassesSubscription) list.get(0);
    }

    @Transactional
    public List<User> getUsersOfClass(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("SELECT cs.user FROM ClassesSubscription cs INNER JOIN cs.user where cs.classes.id=:id ")
                .setParameter("id", id).list();
        return list;
    }

    @Transactional
    public List<Classes> getClassesOfUser(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("SELECT cs.classes FROM ClassesSubscription cs INNER JOIN cs.classes where cs.user.id=:id ")
                .setParameter("id", id).list();
        return list;
    }

    @Transactional
    public List<Classes> getPaidClassesOfUser(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("SELECT cs.classes FROM ClassesSubscription cs INNER JOIN cs.classes INNER JOIN cs.payment WHERE cs.user.id=:id AND cs.payment.status = 'paid'")
                .setParameter("id", id).list();
        return list;
    }

    @Transactional
    public List<Classes> getNonPaidClassesOfUser(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("SELECT cs.classes FROM ClassesSubscription cs INNER JOIN cs.classes INNER JOIN cs.payment WHERE cs.user.id=:id AND cs.payment.status <> 'paid'")
                .setParameter("id", id).list();
        return list;
    }
    
    
    @Transactional
    public List<Activity> getPaidActivitiesOfUser(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("SELECT c.activity FROM ClassesSubscription cs INNER JOIN cs.classes c INNER JOIN cs.classes.activity INNER JOIN cs.payment WHERE cs.user.id=:id AND cs.payment.status = 'paid'")
                .setParameter("id", id).list();
        return list;
    }

    @Transactional
    public List<Activity> getNonPaidActivitiesOfUser(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("SELECT c.activity FROM ClassesSubscription cs INNER JOIN cs.classes c INNER JOIN cs.classes.activity INNER JOIN cs.payment WHERE cs.user.id=:id AND cs.payment.status <> 'paid'")
                .setParameter("id", id).list();
        getLogger().debug("getNonPaidActivitiesOfUser: {}", list);
        return list;
    }
    
    @Transactional
    public Payment getPaymentOfNonPaidUserActivity(int activityID, int userID) {
        Payment p = (Payment) getSessionFactory().getCurrentSession()
                .createQuery("SELECT cs.payment FROM ClassesSubscription cs INNER JOIN cs.classes c INNER JOIN cs.classes.activity a INNER JOIN cs.payment WHERE cs.user.id=:uid AND cs.payment.status <> 'paid' AND a.id=:aid")
                .setParameter("uid", userID)
                .setParameter("aid", activityID)
                .uniqueResult();
        getLogger().debug("getPaymentOfNonPaidUserActivity: {}", p);
        return p;
    }
    
    @Transactional
    public Map<Activity, Payment> getNonPaidActivitiesAndPaymentInfoOfUser(int id) {
        List list = getNonPaidActivitiesOfUser(id);
        Map<Activity, Payment> activityPaymentMap = new HashMap<>();
        
        for (Object o : list) {
            Activity a = (Activity) o;
            Payment p = getPaymentOfNonPaidUserActivity(a.getId(), id);
            activityPaymentMap.put(a, p);            
        }
        
        return activityPaymentMap;
    }
    
}
