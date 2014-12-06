/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.dao.EventDAO;
import org.consultjr.mvc.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Service
@Transactional
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    /**
     * Add Event
     *
     * @param event Event
     */
    @Transactional(readOnly = false)
    public void addEvent(Event event) {
        getEventDAO().addEvent(event);
    }

    /**
     * Delete Event
     *
     * @param event Event
     */
    @Transactional(readOnly = false)
    public void deleteEvent(Event event) {
        getEventDAO().deleteEvent(event);
    }

    /**
     *
     * @param eventView
     * @param id
     */
    @Transactional(readOnly = false)
    public void updateEvent(Event eventView, int id) {
        Event eventBD = getEventById(id);

        eventBD.setTitle(eventView.getTitle());
        eventBD.setDescription(eventView.getDescription());
        eventBD.setStart(eventView.getStart());
        eventBD.setEnd(eventView.getEnd());
        eventBD.setOwner(eventView.getOwner());
        eventBD.setDeleted(eventView.isDeleted());
        eventBD.setUpdated(new Date());

        getEventDAO().updateEvent(eventBD);
    }

    /**
     * Get Event
     *
     * @param id int Event Id
     * @return
     */
    public Event getEventById(int id) {
        return getEventDAO().getEventById(id);
    }

    /**
     * Get Event List
     *
     */
    public List<Event> getEvents() {
        return getEventDAO().getEvents();
    }

    /**
     * Get Event DAO
     *
     * @return eventDAO - Event DAO
     */
    public EventDAO getEventDAO() {
        return eventDAO == null ? new EventDAO() : eventDAO;
    }

    /**
     * Set Event DAO
     *
     * @param eventDAO - EventDAO
     */
    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

}
