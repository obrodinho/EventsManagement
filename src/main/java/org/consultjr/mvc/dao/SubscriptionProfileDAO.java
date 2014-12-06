/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.model.ClassesSubscription;
import org.consultjr.mvc.model.ClassesProfile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario
 */
@Repository
public class SubscriptionProfileDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addSubscriptionProfile(ClassesProfile subscriptionProfile) {
        getSessionFactory().getCurrentSession().save(subscriptionProfile);
        getSessionFactory().getCurrentSession().refresh(subscriptionProfile);
    }

    @Transactional
    public void deleteSubscriptionProfile(ClassesProfile subscriptionProfile) {
        getSessionFactory().getCurrentSession().delete(subscriptionProfile);
    }

    @Transactional
    public void updateSubscriptionProfile(ClassesProfile subscriptionProfile) {
        getSessionFactory().getCurrentSession().update(subscriptionProfile);
    }

    @Transactional
    public List<ClassesProfile> getSubscriptionProfiles() {
        List list = getSessionFactory().getCurrentSession().createQuery("from SubscriptionProfile").list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Transactional
    public ClassesProfile getSubscriptionProfilesById(int profileId) {
        return (ClassesProfile) getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubscriptionProfile where id=:id")
                .setParameter("id", profileId).uniqueResult();
    }

}
