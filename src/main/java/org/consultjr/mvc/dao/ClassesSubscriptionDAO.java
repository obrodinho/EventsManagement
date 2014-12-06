/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.model.ClassesSubscription;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario
 */
@Repository
public class ClassesSubscriptionDAO {

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
    public ClassesSubscription getClassesSubscription(int classesId, int userId) {
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
    public List<ClassesSubscription> getClassesSubscriptionByUser(int userId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClassesSubscription where user_id=? ")
                .setParameter(0, userId).list();
        return list;
    }

    @Transactional
    public List<ClassesSubscription> getClassesSubscriptionByClasses(int classesId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from ClassesSubscription where class_id=? ")
                .setParameter(0, classesId).list();
        return list;
    }
}
