package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.dao.ActivityDAO;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Activity Service
 *
 * @author kallenon
 */
@Service("ActivityService")
@Transactional(readOnly = true)
public class ActivityService {

    @Autowired
    ActivityDAO activityDAO;

    @Transactional(readOnly = false)
    public void addActivity(Activity activity) {
        getActivityDAO().addActivity(activity);
    }

    @Transactional(readOnly = false)
    public void deleteActivity(Activity activity) {
        getActivityDAO().deleteActivity(activity);
    }

    @Transactional(readOnly = false)
    public void updateActivity(Activity activity) {
        getActivityDAO().updateActivity(activity);
    }

    public Activity getActivityById(int id) {
        return getActivityDAO().getActivityById(id);
    }

    public List<Activity> getActivities() {
        return getActivityDAO().getActivities();
    }

    public ActivityDAO getActivityDAO() {
        return activityDAO == null ? new ActivityDAO() : activityDAO;
    }

    public void setActivityDAO(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }

    public Activity getActivityDetails() {
        Activity a = new Activity();
        
        Event event = new Event();
        event.setEvent_id(1);
        
        a.setId(1);
        a.setEvent(event);
//        a.setEvent_id(1);
        a.setDescription("TESTE DESCRIPTION");
        a.setType(1);
        a.setWorkload(123);
        a.setStart(new Date());

        return a;
    }
}
