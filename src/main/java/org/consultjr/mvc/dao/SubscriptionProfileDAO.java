/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.Date;
import java.util.List;
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
    public void addSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        /*subscriptionProfile.setId(1);
         subscriptionProfile.setDescription("É quem vai palestrar");
         subscriptionProfile.setName("Palestrante");
         subscriptionProfile.setCreated(new Date());
         getSessionFactory().getCurrentSession().save(subscriptionProfile);
        
         subscriptionProfile.setId(2);
         subscriptionProfile.setDescription("É quem se inscreveu na atividade");
         subscriptionProfile.setName("Participante");
         subscriptionProfile.setCreated(new Date());
         getSessionFactory().getCurrentSession().save(subscriptionProfile);
        
        
         subscriptionProfile.setId(3);
         subscriptionProfile.setDescription("É staff ou pessoa que auxilia o ministrante");
         subscriptionProfile.setName("Monitor");
         subscriptionProfile.setCreated(new Date());*/
        getSessionFactory().getCurrentSession().save(subscriptionProfile);
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

}
