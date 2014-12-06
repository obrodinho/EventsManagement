package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.core.base.AppDAO;
import org.consultjr.mvc.model.ActivityType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * ActivityType DAO
 *
 * @author rgcs
 */
@Repository
public class ActivityTypeDAO extends AppDAO {

    @Transactional
    public void addActivityType(ActivityType activitytype) {
        getSessionFactory().getCurrentSession().save(activitytype);
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().refresh(activitytype);
    }

    @Transactional
    public void deleteActivityType(ActivityType user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    @Transactional
    public void updateActivityType(ActivityType activitytype) {
        getSessionFactory().getCurrentSession().update(activitytype);
    }

    @Transactional
    public ActivityType getActivityTypeById(int id) {
        return (ActivityType) getSessionFactory().getCurrentSession()
                .createQuery("from ActivityType where id=:id")
                .setParameter("id", id).uniqueResult();
    }

    @Transactional
    public ActivityType getActivityTypeByShortname(String shortname) {
        return (ActivityType) getSessionFactory().getCurrentSession()
                .createQuery("from ActivityType where shortname=:sh")
                .setParameter("sh", shortname).uniqueResult();
    }

    @Transactional
    public List<ActivityType> getActivityTypes() {
        List list = getSessionFactory().getCurrentSession().createQuery("from ActivityType").list();
        return list;
    }

}
