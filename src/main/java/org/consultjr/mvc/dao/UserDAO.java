/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.Iterator;
import java.util.List;
import org.consultjr.mvc.core.base.AppDAO;
import org.consultjr.mvc.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rgcs
 */
@Repository
public class UserDAO extends AppDAO {

    /**
     * Add user
     *
     * @param user user
     */
    @Transactional
    public void addUser(User user) {
        getSessionFactory().getCurrentSession().save(user);
        getSessionFactory().getCurrentSession().refresh(user);

    }

    /**
     * Delete user
     *
     * @param user user
     */
    @Transactional
    public void deleteUser(User user) {
        getSessionFactory().getCurrentSession().delete(user);

    }

    /**
     * Update user
     *
     * @param user user
     */
    @Transactional
    public void updateUser(User user) {
        getSessionFactory().getCurrentSession().update(user);
    }

    /**
     * Get user
     *
     * @param id integer
     * @return user
     */
    @Transactional
    public User getUserById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User  where id=:id")
                .setParameter("id", id).list();
        if (list.isEmpty()) {
            return null;
        }
        return (User) list.get(0);
    }

    /**
     * Get user List
     *
     * @return List - user list
     */
    @Transactional
    public List<User> getUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from User").list();
        System.out.print(list.size());

        return list;
    }

    public User getUserByUsername(String username) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User  where username=:u")
                .setParameter("u", username).list();
        if (list.isEmpty()) {
            return null;
        }
        return (User) list.get(0);
    }
}
