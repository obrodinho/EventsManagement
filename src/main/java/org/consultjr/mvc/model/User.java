/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * @author rgcs
 */
@Entity
@Table(name = "users")
public class User extends ApplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     */
    @Column()
    private String firstname;

    /**
     *
     */
    @Column()
    private String lastname;

    /**
     *
     */
    @Column(unique = true)
    private String username;

    /**
     *
     */
    @Column()
    private String password;

    /**
     *
     */
    @Column(nullable = true)
    private Boolean deleted;

    /**
     *
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    /**
     *
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;

    @OneToMany(mappedBy = "user", targetEntity = UserSystemProfile.class)
    private List<UserSystemProfile> profiles;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public boolean isDeleted() {
        return deleted == null ? false : deleted.booleanValue();
    }

    public boolean isEnabled() {
        return !isDeleted();
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<UserSystemProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<UserSystemProfile> profiles) {
        this.profiles = profiles;
    }

    public User() {
        this.created = new Date();
    }

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.created = new Date();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password=" + password + ", deleted=" + deleted + ", created=" + created + ", updated=" + updated + ", profiles=" + profiles + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
