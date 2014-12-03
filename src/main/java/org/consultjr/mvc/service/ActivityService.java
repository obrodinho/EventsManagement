package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.core.components.AppUtils;
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
        // create all strategies for assigning the event. 
        // The activity controller must be used only by managing a Event
        Event defaultEvent = new Event();
        defaultEvent.setId(1);
        activity.setEvent(defaultEvent);
        
        activity.setCreated(new Date());
        
        activity.setStart(AppUtils.StringToDate(activity.getDateStart()));
        activity.setEnd(AppUtils.StringToDate(activity.getDateEnd()));
        
        getActivityDAO().addActivity(activity);
    }

    @Transactional(readOnly = false)
    public void deleteActivity(Activity activity) {
        getActivityDAO().deleteActivity(activity);
    }

    @Transactional(readOnly = false)
    public void updateActivity(Activity activityView, int id) {
        Activity activityBD = getActivityById(id);
        
        activityBD.setTitle(activityView.getTitle());
        activityBD.setDescription(activityView.getDescription());
        activityBD.setType(activityView.getType());
        activityBD.setWorkload(activityView.getWorkload());
        activityBD.setStart(AppUtils.StringToDate(activityView.getDateStart()));
        activityBD.setEnd(AppUtils.StringToDate(activityView.getDateEnd()));      
        
        activityBD.setUpdated(new Date());

        getActivityDAO().updateActivity(activityBD);
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
}
