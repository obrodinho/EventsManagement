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

    @Transient
    private Type type;

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

    public Type getType() {
        return type;
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

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Boolean isDeleted() {
        return deleted.booleanValue();
    }

    public Boolean isEnabled() {
        return !deleted.booleanValue();
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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == 0 && other.id > 0) || ((this.id > 0) && (this.id != other.id))) {
            return false;
    }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass() + "[ " + id + " ]";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != 0 ? this.hashCode() : 0);
        return hash;
    }
}
