package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;

import org.consultjr.mvc.dao.PaymentDAO;
import org.consultjr.mvc.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Payment Service
 *
 * @author rss
 */
@Service
@Transactional
public class PaymentService {

    // PaymentDAO is injected...
    @Autowired
    private PaymentDAO paymentDAO;

    /**
     * Add Payment
     *
     * @param payment Payment
     */
    @Transactional(readOnly = false)
    public void addPayment(Payment payment) {
        getPaymentDAO().addPayment(payment);
    }

    /**
     * Delete Payment
     *
     * @param payment Payment
     */
    @Transactional(readOnly = false)
    public void deletePayment(Payment payment) {
        getPaymentDAO().deletePayment(payment);
    }

    /**
     *
     * @param paymentView
     * @param id
     */
    @Transactional(readOnly = false)
    public void updatePayment(Payment paymentView, int id) {
        Payment paymentBD = getPaymentById(id);
        
        paymentBD.setStatus(paymentView.getStatus());
        paymentBD.setType(paymentView.getType());
        paymentBD.setPaid(new Date());
        
        getPaymentDAO().updatePayment(paymentBD);
    }

    /**
     * Get Payment
     *
     * @param id int Payment Id
     * @return 
     */
    public Payment getPaymentById(int id) {
        return getPaymentDAO().getPaymentById(id);
    }

    /**
     * Get P List
     *
     */
    public List<Payment> getPayments() {
        return getPaymentDAO().getPayments();
    }

    /**
     * Get Payment DAO
     *
     * @return paymentDAO - Payment DAO
     */
    public PaymentDAO getPaymentDAO() {
        return paymentDAO == null ? new PaymentDAO() : paymentDAO;
    }

    /**
     * Set Payment DAO
     *
     * @param paymentDAO - PaymentDAO
     */
    public void setPaymentDAO(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }
    
    /**
    public Payment getPaymentByUsername(String username) {
        return this.userDAO.getUserByUsername(username);
    }
    */
    
}
