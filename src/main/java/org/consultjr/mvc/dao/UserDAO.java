/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.Iterator;
import java.util.List;
import org.consultjr.mvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rgcs
 */
@Repository
public class UserDAO {

    private Configuration configuration;
    private StandardServiceRegistryBuilder builder;
    private StandardServiceRegistryBuilder toBuild;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get Hibernate Current Session
     *
     * @return
     */
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        //sessionFactory = (sessionFactory == null ? configuration.buildSessionFactory(builder.build()) : null);
        return sessionFactory;
    }

    /**
     * Set Hibernate Session Factory
     *
     * @param sessionFactory SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Add user
     *
     * @param user user
     */
    @Transactional
    public void addUser(User user) {
        getSessionFactory().getCurrentSession().save(user);

    }

    /**
     * Delete user
     *
     * @param user user
     */
    @Transactional
    public void deleteUser(User user) {
        getSessionFactory().getCurrentSession().delete(user);

    }

    /**
     * Update user
     *
     * @param user user
     */
    @Transactional
    public void updateUser(User user) {
        getSessionFactory().getCurrentSession().update(user);        
    }

    /**
     * Get user
     *
     * @param id integer
     * @return user
     */
    @Transactional
    public User getUserById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User  where id=?")
                .setParameter(0, id).list();
        return (User) list.get(0);
    }

    /**
     * Get user List
     *
     * @return List - user list
     */
    @Transactional
    public List<User> getUsers() {
        List list = getCurrentSession().createQuery("from User").list();
        System.out.print(list.size());
        for (Iterator u = list.iterator(); u.hasNext();) {
            System.out.println(u);                    
            
            Object next = u.next();
        }
        return list;
    }

    public User getUserByUsername(String username) {
        List list = getSessionFactory().getCurrentSession()
            .createQuery("from User  where username=?")
            .setParameter(0, username).list();
        if(list.isEmpty()){
            return null;
        }
        return (User) list.get(0);
    }
}
