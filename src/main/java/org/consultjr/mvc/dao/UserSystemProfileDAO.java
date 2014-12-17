/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.core.base.ApplicationDAO;
import org.consultjr.mvc.model.SystemProfile;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.model.UserSystemProfile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Repository
public class UserSystemProfileDAO extends ApplicationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void addUserSystemProfile(UserSystemProfile usp) {
        getSessionFactory().getCurrentSession().save(usp);
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().refresh(usp);
    }

    @Transactional
    public void deleteUserSystemProfile(UserSystemProfile usp) {
        getSessionFactory().getCurrentSession().delete(usp);
    }

    @Transactional
    public void updateUserSystemProfile(UserSystemProfile usp) {
        getSessionFactory().getCurrentSession().update(usp);
    }

    @Transactional
    public List<UserSystemProfile> getUserSystemProfilesOfUser(int userID) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from UserSystemProfile where user=?")
                .setParameter(0, userID).list();
        return list;
    }

    @Transactional
    public List<UserSystemProfile> getUserSystemProfilesOfProfile(int profileID) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from UserSystemProfile where profile=?")
                .setParameter(0, profileID).list();
        return list;
    }

    @Transactional
    public List<UserSystemProfile> getUserSystemProfiles() {
        List list = getSessionFactory().getCurrentSession().createQuery("from UserSystemProfile").list();
        return list;
    }

    @Transactional
    public List<SystemProfile> getSystemProfilesOfUser(int userID) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from UserSystemProfile usp JOIN SystemProfile sp ON usp.profile = sp.id where user=?")
                .setParameter(0, userID).list();
        return list;
    }
    
    @Transactional
    public List<User> getUsersOfSystemProfile(int profileID) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from UserSystemProfile usp JOIN User u ON usp.user = u.id where profile=?")
                .setParameter(0, profileID).list();
        return list;
    }

}
