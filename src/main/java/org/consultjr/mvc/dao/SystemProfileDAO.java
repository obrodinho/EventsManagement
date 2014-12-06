/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.model.SystemProfile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Repository
public class SystemProfileDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addSystemProfile(SystemProfile sp) {
        getSessionFactory().getCurrentSession().save(sp);
        getSessionFactory().getCurrentSession().refresh(sp);
    }

    @Transactional
    public void deleteSystemProfile(SystemProfile sp) {
        getSessionFactory().getCurrentSession().delete(sp);
    }

    @Transactional
    public void updateSystemProfile(SystemProfile sp) {
        getSessionFactory().getCurrentSession().update(sp);
    }

    @Transactional
    public SystemProfile getSystemProfileById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from SystemProfile where id=?")
                .setParameter(0, id).list();
        if (list.isEmpty()) {
            return null;
        }
        return (SystemProfile) list.get(0);
    }

    @Transactional
    public SystemProfile getSystemProfileByShortname(String shortname) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from SystemProfile where shortname=:sh")
                .setParameter("sh", shortname).list();
        if (list.isEmpty()) {
            return null;
        }
        return (SystemProfile) list.get(0);
    }

    @Transactional
    public List<SystemProfile> getSystemProfiles() {
        List list = getSessionFactory().getCurrentSession().createQuery("from SystemProfile").list();
        return list;
    }

}
