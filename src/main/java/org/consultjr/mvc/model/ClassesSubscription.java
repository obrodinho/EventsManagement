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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "classes_subscription")
public class ClassesSubscription implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne(targetEntity = Classes.class)
    @JoinColumn(name = "class_id")
    private Classes classes;
    
    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(targetEntity = ClassesProfile.class)
    @JoinColumn(name = "profile_id")
    private ClassesProfile subscriptionProfile;

    @Column()
    private Date associated;

    public ClassesSubscription() {
        this.associated = new Date();
    }

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

    public ClassesProfile getSubscriptionProfile() {
        return subscriptionProfile;
    }

    public void setSubscriptionProfile(ClassesProfile subscriptionProfile) {
        this.subscriptionProfile = subscriptionProfile;
    }

    @Override
    public String toString() {
        return "ClassesSubscription{" + "classes=" + classes + ", user=" + user + ", subscriptionProfile=" + subscriptionProfile + ", associated=" + associated + '}';
    }
}
