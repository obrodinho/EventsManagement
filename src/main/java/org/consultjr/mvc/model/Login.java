/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.model;

import java.io.Serializable;
import org.consultjr.mvc.core.base.ApplicationModel;

/**
 *
 * @author Murilo
 */
public class Login extends ApplicationModel implements Serializable {

    private String username;
    private String password;

    public Login() {
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
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
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return this.getClass() + "[ " + this.getUsername() + " ]";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if (this.username == null ? other.username != null : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

}
