/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.core.base.ApplicationDAO;
import org.consultjr.mvc.model.Payment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rss
 */
@Repository
public class PaymentDAO extends ApplicationDAO {

    /**
     * Add payment
     *
     * @param payment payment
     */
    @Transactional
    public void addPayment(Payment payment) {
        getSessionFactory().getCurrentSession().save(payment);
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().refresh(payment);

    }

    /**
     * Delete payment
     *
     * @param payment payment
     */
    @Transactional
    public void deletePayment(Payment payment) {
        getSessionFactory().getCurrentSession().delete(payment);

    }

    /**
     * Update payment
     *
     * @param payment payment
     */
    @Transactional
    public void updatePayment(Payment payment) {
        getSessionFactory().getCurrentSession().update(payment);
    }

    /**
     * Get payment
     *
     * @param id integer
     * @return payment
     */
    @Transactional
    public Payment getPaymentById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Payment  where id=:id")
                .setParameter("id", id).list();
        if (list.isEmpty()) {
            return null;
        }
        return (Payment) list.get(0);
    }

    /**
     * Get payment List
     *
     * @return List - payment list
     */
    @Transactional
    public List<Payment> getPayments() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Payment").list();
        System.out.print(list.size());

        return list;
    }
    /**
    public Payment getPaymentByUsername(String username) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from User  where username=:u")
                .setParameter("u", username).list();
        if (list.isEmpty()) {
            return null;
        }
        return (User) list.get(0);
    }
    */
}
