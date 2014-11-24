package org.consultjr.mvc.managedController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Event;
import org.consultjr.mvc.service.ActivityService;

import org.springframework.dao.DataAccessException;

/**
 *
 * Activity Managed Bean
 *
 * @author kallenon
 */
@ManagedBean(name = "activityMB")
@RequestScoped
public class ActivityManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    @ManagedProperty(value = "#{ActivityService}")
    ActivityService activityService;

    List<Activity> activityList;

    private int id;
//    private int event_id;
    private Event event;
    private String title;
    private String description;
    private Integer type;
    private Integer workload;
    private Date start;
    private Date end;
    private Date created;
    private Date updated;

    public String add() {
        try {
            Activity a = new Activity();
            
            Event event = new Event();
            event.setEvent_id(1);

            a.setId(this.getId());
            a.setEvent(event);
//            a.setEvent_id(1);
            a.setTitle(this.getTitle());
            a.setDescription(this.getDescription());
            a.setType(1);
            a.setWorkload(this.getWorkload());
            a.setStart(this.getStart());

            getActivityService().addActivity(a);

            return SUCCESS;

        } catch (DataAccessException e) {
            System.err.println(e.getStackTrace());
            e.printStackTrace();

        }

        return ERROR;
    }

    public void reset() {
        this.setId(0);
        this.setTitle("");
        this.setDescription("");
        this.setWorkload(0);
    }

    public List<Activity> getActivityList() {
        activityList = new ArrayList<Activity>();
        activityList.addAll(getActivityService().getActivities());
        return activityList;
    }

    public ActivityService getActivityService() {
        return activityService == null ? new ActivityService() : activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

//    public int getId_event() {
//        return id_event;
//    }
//
//    public void setId_event(int id_event) {
//        this.id_event = id_event;
//    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
