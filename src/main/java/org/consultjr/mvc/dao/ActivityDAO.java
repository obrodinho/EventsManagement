package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.core.base.AppDAO;
import org.consultjr.mvc.model.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Activity Service
 *
 * @author kallenon
 */
@Repository
public class ActivityDAO extends AppDAO {

    @Transactional
    public void addActivity(Activity activity) {
        getSessionFactory().getCurrentSession().save(activity);
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().refresh(activity);
    }

    @Transactional
    public void deleteActivity(Activity user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    @Transactional
    public void updateActivity(Activity activity) {
        getSessionFactory().getCurrentSession().update(activity);
    }

    @Transactional
    public Activity getActivityById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Activity where id=?")
                .setParameter(0, id).list();
        return (Activity) list.get(0);
    }

    @Transactional
    public List<Activity> getActivities() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Activity").list();
        return list;
    }

}
