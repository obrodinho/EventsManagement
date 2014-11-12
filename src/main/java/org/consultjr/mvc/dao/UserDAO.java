/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.model.User;
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

    public UserDAO() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class).configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        StandardServiceRegistryBuilder toBuild = builder.applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(toBuild.build());
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
        Transaction tx = getSessionFactory().getCurrentSession().beginTransaction();
        getSessionFactory().getCurrentSession().save(user);
        getSessionFactory().getCurrentSession().flush();
        tx.commit();
    }

    /**
     * Delete user
     *
     * @param user user
     */
    @Transactional
    public void deleteUser(User user) {
        Transaction tx = getSessionFactory().getCurrentSession().beginTransaction();
        getSessionFactory().getCurrentSession().delete(user);
        getSessionFactory().getCurrentSession().flush();
        tx.commit();
    }

    /**
     * Update user
     *
     * @param user user
     */
    @Transactional
    public void updateUser(User user) {
        Transaction tx = getSessionFactory().getCurrentSession().beginTransaction();
        getSessionFactory().getCurrentSession().update(user);
        getSessionFactory().getCurrentSession().flush();
        tx.commit();
    }

    /**
     * Get user
     *
     * @param id integer
     * @return user
     */
    @Transactional
    public User getUserById(int id) {
        Transaction tx = getSessionFactory().getCurrentSession().beginTransaction();
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User  where id=?")
                .setParameter(0, id).list();
        getSessionFactory().getCurrentSession().flush();
        tx.commit();
        return (User) list.get(0);
    }

    /**
     * Get user List
     *
     * @return List - user list
     */
    @Transactional
    public List<User> getUsers() {
        Transaction tx = getSessionFactory().getCurrentSession().beginTransaction();
        List list = getSessionFactory().getCurrentSession().createQuery("from  User").list();
        getSessionFactory().getCurrentSession().flush();
        tx.commit();
        return list;
    }

}
