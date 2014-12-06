/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.core.base.AppDAO;
import org.consultjr.mvc.model.Event;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Repository
public class EventDAO extends AppDAO {

    /**
     * Add event
     *
     * @param event event
     */
    @Transactional
    public void addEvent(Event event) {
        getSessionFactory().getCurrentSession().save(event);
        getSessionFactory().getCurrentSession().refresh(event);

    }

    /**
     * Delete event
     *
     * @param event event
     */
    @Transactional
    public void deleteEvent(Event event) {
        getSessionFactory().getCurrentSession().delete(event);

    }

    /**
     * Update event
     *
     * @param event event
     */
    @Transactional
    public void updateEvent(Event event) {
        getSessionFactory().getCurrentSession().update(event);
    }

    /**
     * Get event
     *
     * @param id integer
     * @return event
     */
    @Transactional
    public Event getEventById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Event  where id=:id")
                .setParameter("id", id).list();

        if (list.isEmpty()) {
            return null;
        }
        return (Event) list.get(0);
    }

    /**
     * Get event List
     *
     * @return List - event list
     */
    @Transactional
    public List<Event> getEvents() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Event").list();
        System.out.print(list.size());

        return list;
    }
}
