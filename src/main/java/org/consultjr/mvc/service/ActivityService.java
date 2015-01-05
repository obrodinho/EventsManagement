package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.core.base.ApplicationService;
import org.consultjr.mvc.core.components.ApplicationUtils;
import org.consultjr.mvc.dao.ActivityDAO;
import org.consultjr.mvc.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
public class ActivityService  extends ApplicationService {

    @Autowired
    ActivityDAO activityDAO;

    @Autowired
    ActivityTypeService activityTypeService;

    @Transactional(readOnly = false)
    public void addActivity(Activity activity) {
        // create all strategies for assigning the event. 
        
        if (activity.getDateStart() != null) {
            activity.setStart(ApplicationUtils.StringToDate(activity.getDateStart()));
        }

        if (activity.getDateEnd() != null) {
            activity.setEnd(ApplicationUtils.StringToDate(activity.getDateEnd()));
        }

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
        activityBD.setStart(ApplicationUtils.StringToDate(activityView.getDateStart()));
        activityBD.setEnd(ApplicationUtils.StringToDate(activityView.getDateEnd()));

        activityBD.setUpdated(new Date());

        getActivityDAO().updateActivity(activityBD);
    }

    public Activity getActivityById(int id) {
        return getActivityDAO().getActivityById(id);
    }

    public List<Activity> getActivities() {
        return getActivityDAO().getActivities();
    }
    
    public List<Activity> getActivitiesByEventId(int idEvent) {
        return getActivityDAO().getActivitiesByEventId(idEvent);
    }

    public ActivityDAO getActivityDAO() {
        return activityDAO == null ? new ActivityDAO() : activityDAO;
    }

    public void setActivityDAO(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }
}
