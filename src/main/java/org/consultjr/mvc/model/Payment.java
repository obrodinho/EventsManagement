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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author rss
 */
@Entity
@Table(name = "payments")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
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
    private String status;

    /**
     *
     */
    @Column()
    private int type;

    /**
     *
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date paid_at;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getPaid() {
        return paid_at;
    }

    public void setPaid(Date paid_at) {
        this.paid_at = paid_at;
    }

    public Payment() {
        this.paid_at = new Date();
    }

    public Payment(String status, int type) {
        this();
        this.status = status;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", status=" + status + ", type=" + type + ", paid_at=" + paid_at + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Payment other = (Payment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

}
