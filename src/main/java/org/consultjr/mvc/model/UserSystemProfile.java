/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "user_system_profiles")
public class UserSystemProfile extends ApplicationModel implements Serializable {

    @Id
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(targetEntity = SystemProfile.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private SystemProfile profile;

    private Date associated;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SystemProfile getSystemProfile() {
        return profile;
    }

    public void setSystemProfile(SystemProfile systemProfile) {
        this.profile = systemProfile;
    }

    public Date getAssociated() {
        return associated;
    }

    public void setAssociated(Date associated) {
        this.associated = associated;
    }

    public UserSystemProfile() {
        this.associated = new Date();
    }

    public UserSystemProfile(User user, SystemProfile systemProfile) {
        this.user = user;
        this.profile = systemProfile;
        this.associated = new Date();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.profile.getId()*10 + this.user.getId() != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.getClass() + "[ " + user.getUsername() + ", " + profile.getShortname() + " ]";
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSystemProfile)) {
            return false;
        }
        UserSystemProfile other = (UserSystemProfile) object;
        if ((this.user.getId() != other.user.getId()) || (this.profile.getId() != other.profile.getId())) {
            return false;
        }
        return true;
    }

}
