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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "classes_subscription")
public class ClassesSubscription implements Serializable{
    
    private static final long serialVersionUID = 1L;

    
    

    //@ManyToOne(targetEntity = Classes_Profiles.class))
    //@Column(name = "profile_id")
    //private Classes_Profiles profile;
    @Id
    @ManyToOne(targetEntity = Classes.class)
    @JoinColumn(name="class_id")
    private Classes classes;
    
    //@Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;
    
    //@Id
    @ManyToOne(targetEntity = SubscriptionProfile.class)
    @JoinColumn(name="profile_id")
    private SubscriptionProfile subscriptionProfile;
    
    @Column() 
    private Date associated;
    

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Date getAssociated() {
        return associated;
    }

    public void setAssociated(Date associated) {
        this.associated = associated;
    }

    public SubscriptionProfile getSubscriptionProfile() {
        return subscriptionProfile;
    }

    public void setSubscriptionProfile(SubscriptionProfile subscriptionProfile) {
        this.subscriptionProfile = subscriptionProfile;
    }
    @Override
    public String toString() {
        return "ClassesSubscription{" + "classes=" + classes + ", user=" + user + ", subscriptionProfile=" + subscriptionProfile + ", associated=" + associated + '}';
    }
}
