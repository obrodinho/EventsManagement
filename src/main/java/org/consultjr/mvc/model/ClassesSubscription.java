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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * @author Mario
 */
@Entity
@Table(name = "classes_subscription")
public class ClassesSubscription extends ApplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(targetEntity = Classes.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @Id
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(targetEntity = SubscriptionProfile.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private SubscriptionProfile subscriptionProfile;
    
    @Id
    @ManyToOne(targetEntity = Payment.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
    @Column()
    private Date associated;

    public ClassesSubscription() {
        this.associated = new Date();
    }

    public ClassesSubscription(Classes classes, User user, SubscriptionProfile subscriptionProfile, Payment payment) {
        this();
        this.payment = payment;
        this.classes = classes;
        this.user = user;
        this.subscriptionProfile = subscriptionProfile;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classes.getId() + user.getId() * 10 + subscriptionProfile.getId() * 100 != 0 ? this.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassesSubscription)) {
            return false;
        }
        ClassesSubscription other = (ClassesSubscription) object;
        if ((this.user.getId() != other.user.getId()) || 
                (this.classes.getId() != other.classes.getId()) || 
                (this.subscriptionProfile.getId() != other.subscriptionProfile.getId())) {
            return false;
        }
        return true;
    }
}
