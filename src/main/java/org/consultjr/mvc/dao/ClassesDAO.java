/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.consultjr.mvc.model.Classes;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario
 */
@Repository
public class ClassesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addClasses(Classes classes) {
        getSessionFactory().getCurrentSession().save(classes);
        getSessionFactory().getCurrentSession().refresh(classes);
    }

    @Transactional
    public void deleteClasses(Classes classes) {
        getSessionFactory().getCurrentSession().delete(classes);
    }

    @Transactional
    public void updateClasses(Classes classes) {
        getSessionFactory().getCurrentSession().update(classes);
    }

    @Transactional
    public Classes getClassesById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Classes where id=?")
                .setParameter(0, id).list();

        if (list.isEmpty()) {
            return null;
        }
        return (Classes) list.get(0);
    }

    @Transactional
    public List<Classes> getClasses() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Classes").list();
        return list;
    }

    @Transactional
    public List<Classes> getClassesByActivity(int activityId) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Classes where activity_id=?")
                .setParameter(0, activityId).list();
        return list;
    }

}
