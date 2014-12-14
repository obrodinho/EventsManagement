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
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.consultjr.mvc.core.base.ApplicationModel;
import org.consultjr.mvc.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 * @author Rafael
 */
@Entity
@Table(name = "system_configs")
public class SystemConfig extends ApplicationModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "config_id")
    private int id;
    
    @Column(name = "config_key", unique = true)
    private String key;

    @Column(name = "config_value")
    private String value;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updated;
    
    public SystemConfig() {
        this.created = new Date();
    }
    
    public SystemConfig(String key, String value) {
        this.created = new Date();
        this.key = key;
        this.value = value;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!(object instanceof SystemConfig)) {
            return false;
        }
        SystemConfig other = (SystemConfig) object;
        if ((this.id == 0 && other.id > 0) || ((this.id > 0) && (this.id != other.id))) {
            return false;
        }
        return true;
    }
}
