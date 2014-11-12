package org.consultjr.mvc.managedController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.consultjr.mvc.model.User;
import org.consultjr.mvc.service.UserService;
import org.springframework.dao.DataAccessException;

/**
 *
 * User Managed Bean
 *
 * @author rgcs
 */
@ManagedBean(name = "userMB")
@RequestScoped
public class UserManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    //Spring User Service is injected...
    @ManagedProperty(value = "#{UserService}")
    UserService userService;

    List<User> userList;

    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private Date created;

    /**
     * Add User
     *
     * @return String - Response Message
     */
    public String add() {
        try {
            User u = new User();

            u.setId(this.getId());
            u.setFirstname(this.getFirstname());
            u.setLastname(this.getLastname());
            u.setUsername(this.getUsername());

            getUserService().addUser(u);

            return SUCCESS;

        } catch (DataAccessException e) {
            System.err.println(e.getStackTrace());
            e.printStackTrace();

        }

        return ERROR;
    }

    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.setId(0);
        this.setFirstname("");
        this.setLastname("");
        this.setUsername("");
    }

    /**
     * Get User List
     *
     * @return List - User List
     */
    public List<User> getUserList() {
        userList = new ArrayList<User>();
        userList.addAll(getUserService().getUsers());
        return userList;
    }

    /**
     * Get User Service
     *
     * @return IUserService - User Service
     */
    public UserService getUserService() {

        return userService == null ? new UserService() : userService;
    }

    /**
     * Set User Service
     *
     * @param userService IUserService - User Service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Set User List
     *
     * @param userList List - User List
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Get User Id
     *
     * @return int - User Id
     */
    public int getId() {
        return id;
    }

    /**
     * Set User Id
     *
     * @param i int - User Id
     */
    public void setId(int i) {
        this.id = i;
    }

    /**
     * Get User Firstname
     *
     * @return String - User Firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set User Firstname
     *
     * @param n String - User Firstname
     */
    public void setFirstname(String n) {
        this.firstname = n;
    }

    /**
     * Get User Lastname
     *
     * @return String - User Lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set User Lastname
     *
     * @param ln String - User Lastname
     */
    public void setLastname(String ln) {
        this.lastname = ln;
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

}
