/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(targetEntity = SystemProfile.class)
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
    public String toString() {
        return "UserSystemProfile{" + "user=" + user + ", profile=" + profile + ", associated=" + associated + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.user);
        hash = 53 * hash + Objects.hashCode(this.profile);
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
        final UserSystemProfile other = (UserSystemProfile) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        return true;
    }  

}
