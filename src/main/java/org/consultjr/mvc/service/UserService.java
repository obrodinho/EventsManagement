package org.consultjr.mvc.service;

import java.util.List;

import org.consultjr.mvc.dao.UserDAO;
import org.consultjr.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * User Service
 *
 * @author uday
 * @since 19 Nov 2013
 * @version 1.0.0
 *
 *
 */
@Service("UserService")
@Transactional(readOnly = true)
public class UserService {

    // UserDAO is injected...
    @Autowired
    UserDAO userDAO;

    /**
     * Add User
     *
     * @param user User
     */
    @Transactional(readOnly = false)
    public void addUser(User user) {
        getUserDAO().addUser(user);
    }

    /**
     * Delete User
     *
     * @param user User
     */
    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        getUserDAO().deleteUser(user);
    }

    /**
     * Update User
     *
     * @param user User
     */
    @Transactional(readOnly = false)
    public void updateUser(User user) {
        getUserDAO().updateUser(user);
    }

    /**
     * Get User
     *
     * @param id int User Id
     */
    public User getUserById(int id) {
        return getUserDAO().getUserById(id);
    }

    /**
     * Get User List
     *
     */
    public List<User> getUsers() {
        return getUserDAO().getUsers();
    }

    /**
     * Get User DAO
     *
     * @return userDAO - User DAO
     */
    public UserDAO getUserDAO() {
        return userDAO == null ? new UserDAO() : userDAO;
    }

    /**
     * Set User DAO
     *
     * @param userDAO - UserDAO
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Testing purposes
     *
     * @return User
     */
    public User getUserDetails() {
        User u = new User();

        u.setId(123);
        u.setFirstname("TEST");
        u.setLastname("TEST LAST NAME");
        u.setUsername("test.username");

        return u;
    }
    
    @Autowired
    public String greeting(String name){
    return name;
    }
}
