package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.core.base.ApplicationService;
import org.consultjr.mvc.dao.ActivityTypeDAO;
import org.consultjr.mvc.model.ActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * ActivityType Service
 *
 * @author rgcs
 */
@Service
public class ActivityTypeService extends ApplicationService {

    @Autowired
    ActivityTypeDAO activitytypeDAO;

    @Transactional(readOnly = false)
    public void addActivityType(ActivityType activitytype) {
        getActivityTypeDAO().addActivityType(activitytype);
    }

    @Transactional(readOnly = false)
    public void deleteActivityType(ActivityType activitytype) {
        getActivityTypeDAO().deleteActivityType(activitytype);
    }

    @Transactional(readOnly = false)
    public void updateActivityType(ActivityType activitytypeView, int id) {
        ActivityType activitytypeBD = getActivityTypeById(id);

        activitytypeBD.setTitle(activitytypeView.getTitle());
        activitytypeBD.setDescription(activitytypeView.getDescription());
        activitytypeBD.setShortname(activitytypeView.getShortname());

        activitytypeBD.setUpdated(new Date());

        getActivityTypeDAO().updateActivityType(activitytypeBD);
    }

    @Transactional(readOnly = false)
    public ActivityType getActivityTypeById(int id) {
        return this.activitytypeDAO.getActivityTypeById(id);
    }

    @Transactional(readOnly = false)
    public ActivityType getActivityTypeByShortname(String shortname) {
        return getActivityTypeDAO().getActivityTypeByShortname(shortname);
    }

    @Transactional(readOnly = false)
    public List<ActivityType> getActivityTypes() {
        return getActivityTypeDAO().getActivityTypes();
    }

    public ActivityTypeDAO getActivityTypeDAO() {
        return activitytypeDAO == null ? new ActivityTypeDAO() : activitytypeDAO;
    }

    public void setActivityTypeDAO(ActivityTypeDAO activitytypeDAO) {
        this.activitytypeDAO = activitytypeDAO;
    }
}
