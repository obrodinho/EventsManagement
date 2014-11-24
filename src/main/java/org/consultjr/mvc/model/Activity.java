package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * Activity
 *
 * @author kallenon
 */
@Entity
@Table(name = "activities")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_id")
    private int id;
    
//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name="event_id")
//    @JoinColumn(name="event_id", insertable=true, updatable=true)
//    @Fetch(FetchMode.JOIN)
//    @Cascade(CascadeType.SAVE_UPDATE)
    private Event event;
    
//    @ManyToOne
//    @JoinColumn(name ="event_id")
//    private int event_id;

    public Activity() {
        this.start = new Date();
        this.created = new Date();
    }

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
    
//    public int getEvent_id() {
//        return event_id;
//    }
//
//    public void setEvent_id(int event_id) {
//        this.event_id = event_id;
//    }   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Column()
    private String title;

    @Column()
    private String description;
    
    @Column()
    private Integer type;

    @Column()
    private Integer workload;

    @Column()
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date start;

    @Column()
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date end;

    @Column()
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;

    @Column()
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated;

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
