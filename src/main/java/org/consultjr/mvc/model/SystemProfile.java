/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "system_profiles")
public class SystemProfile extends ApplicationModel implements Serializable {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String shortname;

    private String description;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;
    
    @OneToMany(mappedBy = "profile", targetEntity = UserSystemProfile.class)
    private List<UserSystemProfile> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
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

    public SystemProfile() {
        this.created = new Date();
    }

    public SystemProfile(String shortname, String description) {
        this.created = new Date();
        this.shortname = shortname;
        this.description = description;
       
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.getClass() + "[ " + id  + " ]";
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemProfile)) {
            return false;
        }
        SystemProfile other = (SystemProfile) object;
        if ((this.id == 0 && other.id > 0) || ((this.id > 0) && (this.id != other.id))) {
            return false;
        }
        return true;
    }
}
