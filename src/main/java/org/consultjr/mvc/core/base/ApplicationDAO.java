/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rafael
 */
public class ApplicationDAO {

    private final Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public ApplicationDAO() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

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
}
