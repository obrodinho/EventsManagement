package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * Event
 *
 * @author kallenon
 */
@Entity
@Table(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private int event_id;
    
    @OneToMany(mappedBy="event", targetEntity = Activity.class)
//    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
//    @Cascade(CascadeType.ALL)
    private List<Event> activities;

    public Event() {
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public List<Event> getActivities() {
        return activities;
    }

    public void setActivities(List<Event> activities) {
        this.activities = activities;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (event_id != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.event_id == 0 && other.event_id > 0) || ((this.event_id > 0) && (this.event_id != other.event_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.consultjr.mvc.model.Event[ id=" + event_id + " ]";
    }
    
}
