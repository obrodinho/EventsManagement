/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @ManyToOne(targetEntity = Classes.class)
    @JoinColumn(name = "class_id")
    private Classes classes;

    @Id
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(targetEntity = SubscriptionProfile.class)
    @JoinColumn(name = "profile_id")
    private SubscriptionProfile subscriptionProfile;
    
    @Id
    @ManyToOne(targetEntity = Payment.class)
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
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.classes);
        hash = 73 * hash + Objects.hashCode(this.user);
        hash = 73 * hash + Objects.hashCode(this.subscriptionProfile);
        hash = 73 * hash + Objects.hashCode(this.payment);
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
        final ClassesSubscription other = (ClassesSubscription) obj;
        if (!Objects.equals(this.classes, other.classes)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.subscriptionProfile, other.subscriptionProfile)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        return true;
    }

    
}
