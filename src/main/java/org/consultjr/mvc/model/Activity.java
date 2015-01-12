package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.consultjr.mvc.core.base.ApplicationModel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * Activity
 *
 * @author kallenon
 */
@Entity
@Table(name = "activities")
public class Activity extends ApplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_id")
    private int id;

    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "activity")
    private List<Classes> classes;

    @Column()
    private String title;

    @Column()
    private String description;

    @ManyToOne(targetEntity = ActivityType.class)
    @JoinColumn(name = "type")
    private ActivityType type;
    
    @Transient
    private int typeID;

    @Column()
    private int workload;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date start;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date end;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.id == 0 && other.id > 0) || ((this.id > 0) && (this.id != other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.consultjr.mvc.model.Activity[ id=" + id + " ]";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id;
        return hash;
    }

    public Activity() {
        this.created = new Date();
    }

    public Activity(Date start, Date end) {
        this();
        this.start = start;
        this.end = end;
    }    

    public Activity(Event event, String title, String description, ActivityType type) {
        this(new Date(), new Date());

        this.event = event;
        this.title = title;
        this.description = description;
        this.type = type;
    }

}
