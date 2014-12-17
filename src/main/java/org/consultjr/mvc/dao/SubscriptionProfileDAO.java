/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.core.base.ApplicationDAO;
import org.consultjr.mvc.model.ClassesSubscription;
import org.consultjr.mvc.model.SubscriptionProfile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario
 */
@Repository
public class SubscriptionProfileDAO extends ApplicationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        getSessionFactory().getCurrentSession().save(subscriptionProfile);
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().refresh(subscriptionProfile);
    }

    @Transactional
    public void deleteSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        getSessionFactory().getCurrentSession().delete(subscriptionProfile);
    }

    @Transactional
    public void updateSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        getSessionFactory().getCurrentSession().update(subscriptionProfile);
    }

    @Transactional
    public List<SubscriptionProfile> getSubscriptionProfiles() {
        List list = getSessionFactory().getCurrentSession().createQuery("from SubscriptionProfile").list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Transactional
    public SubscriptionProfile getSubscriptionProfilesById(int profileId) {
        return (SubscriptionProfile) getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubscriptionProfile where id=:id")
                .setParameter("id", profileId).uniqueResult();
    }
    
    @Transactional
    public SubscriptionProfile getSubscriptionProfilesByShortname(String profile) {
        return (SubscriptionProfile) getSessionFactory()
                .getCurrentSession()
                .createQuery("from SubscriptionProfile where shortname=:sh")
                .setParameter("sh", profile).uniqueResult();
    }

}
