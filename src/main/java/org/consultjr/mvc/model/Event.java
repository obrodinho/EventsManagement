package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * Event
 *
 * @author kallenon
 */
@Entity
@Table(name = "events")
public class Event extends ApplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date start;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date end;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="owner")
    private User owner;

    @Column
    private boolean deleted;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;

    @OneToMany(mappedBy = "event", targetEntity = Activity.class)
    private List<Event> activities;

    public Event() {
        this.created = new Date();
    }

    public Event(String title, String description) {
        this.title = title;
        this.description = description;

        this.created = new Date();
    }

    public Event(String title, String description, User owner, Date start, Date end) {
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.start = start;
        this.end = end;
        
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Event> getActivities() {
        return activities;
    }

    public void setActivities(List<Event> activities) {
        this.activities = activities;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == 0 && other.id > 0) || ((this.id > 0) && (this.id != other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass() + "[ " + id  + " ]";
    }


}
