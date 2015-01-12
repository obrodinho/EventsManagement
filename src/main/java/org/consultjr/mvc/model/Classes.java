package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import javax.persistence.Transient;
import org.consultjr.mvc.core.base.ApplicationModel;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * Classes
 *
 * @author mario
 */
@Entity
@Table(name = "classes")
public class Classes extends ApplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_id")
    private int id;

    @ManyToOne(targetEntity = Activity.class)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Classes() {
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Column()
    private String title;

    @Column()
    private String description;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;

    @Column()
    private boolean standard;

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

    public boolean getStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    public Classes(Activity activity, String title, String description, boolean standard) {
        this.activity = activity;
        this.title = title;
        this.description = description;
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "Classes{" + "id=" + id + ", activity=" + activity + ", title=" + title + ", description=" + description + ", created=" + created + ", updated=" + updated + ", standard=" + standard + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.activity);
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
        final Classes other = (Classes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.activity, other.activity)) {
            return false;
        }
        return true;
    }

}
