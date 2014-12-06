package org.consultjr.mvc.service;

import java.util.Date;
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
 * @author rgcs
 */
@Service
@Transactional
public class UserService {

    // UserDAO is injected...
    @Autowired
    private UserDAO userDAO;

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
     *
     * @param userView
     * @param id
     */
    @Transactional(readOnly = false)
    public void updateUser(User userView, int id) {
        User userBD = getUserById(id);
        
        userBD.setFirstname(userView.getFirstname());
        userBD.setLastname(userView.getLastname());
        userBD.setUsername(userView.getUsername());
        userBD.setUpdated(new Date());
        
        getUserDAO().updateUser(userBD);
    }

    /**
     * Get User
     *
     * @param id int User Id
     * @return 
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

    public User getUserByUsername(String username) {
        return this.userDAO.getUserByUsername(username);
    }
    
}
