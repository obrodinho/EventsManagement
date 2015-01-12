package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * ActivityType
 *
 * @author rgs
 */
@Entity
@Table(name = "activity_types")
public class ActivityType extends ApplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "activity_type_id")
    private int id;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "type")
    private List<Activity> activities;

    @Column(unique = true)
    private String shortname;

    private String title;

    private String description;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;

    public ActivityType() {
        this.created = new Date();
    }

    public ActivityType(String shortname, String title, String description) {
        this(); // to call up the empty constructor
        
        this.shortname = shortname;
        this.title = title;
        this.description = description;
    }   
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
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
    public String toString() {
        return "ActivityType{" + "id=" + id + ", activities=" + activities + ", shortname=" + shortname + ", title=" + title + ", description=" + description + ", created=" + created + ", updated=" + updated + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivityType other = (ActivityType) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.shortname, other.shortname)) {
            return false;
        }
        return true;
    }

}
