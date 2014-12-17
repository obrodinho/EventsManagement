/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "classes_profiles")
public class SubscriptionProfile extends ApplicationModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column
    private String description;
    
    @Column(unique = true) 
    private String shortname;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
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

    public SubscriptionProfile() {
        this.created = new Date();
    }

    public SubscriptionProfile(String description, String name) {
        this();
        
        this.description = description;
        this.shortname = name;        
    }

    @Override
    public String toString() {
        return "SubscriptionProfile{" + "id=" + id + ", description=" + description + ", name=" + shortname + ", created=" + created + ", updated=" + updated + '}';
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubscriptionProfile)) {
            return false;
        }
        SubscriptionProfile other = (SubscriptionProfile) object;
        if ((this.id == 0 && other.id > 0) || ((this.id > 0) && (this.id != other.id))) {
            return false;
        }
        return true;
    }
}
