/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.controller;

import java.util.List;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.core.base.CRUDable;
import org.consultjr.mvc.model.Payment;
import org.consultjr.mvc.service.ClassesService;
import org.consultjr.mvc.service.ClassesSubscriptionService;
import org.consultjr.mvc.service.SubscriptionProfileService;
import org.consultjr.mvc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rss
 */
@Controller
@Scope("request")
@RequestMapping("/Payment")
public class PaymentController extends ApplicationController implements CRUDable {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ClassesSubscriptionService subscriptionService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private SubscriptionProfileService subscriptionProfileService;

    @RequestMapping("") // Index Method: => /PROJECT/Payment
    public ModelAndView index() {
        return new ModelAndView("redirect:/Payment/all");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // GET: /PROJECT/Payment/add
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Payment/_form");
        modelAndView.addObject("payment", new Payment());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("paymentID", null);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // Save Method: POST /PROJECT/Payment/add
    public ModelAndView addPayment(@ModelAttribute Payment payment) {
        ModelAndView modelAndView = new ModelAndView("forward:/Payment/all");
        paymentService.addPayment(payment);
        String message = "Payment was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Payment/_form");
        Payment payment = paymentService.getPaymentById(id);
        modelAndView.addObject("payment", payment);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("paymentID", payment.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updatePayment(@ModelAttribute Payment payment, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/Payment/all");
        paymentService.updatePayment(payment, id);
        String message = "Payment was successfully edited.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deletePayment(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/Payment/all");
        paymentService.deletePayment(paymentService.getPaymentById(id));
        List<Payment> payments = paymentService.getPayments();
        modelAndView.addObject("payments", payments);
        String message = "Payment was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allPayments() {
        ModelAndView modelAndView = new ModelAndView("Payment/_list");
        List<Payment> payments = paymentService.getPayments();
        modelAndView.addObject("title", "All Payments :D");
        modelAndView.addObject("payments", payments);
        return modelAndView;
    }

    @RequestMapping(value = "/all/{id}")
    public ModelAndView allPayments(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("Payments/_listSubscription");
        List<Payment> payments = paymentService.getPayments();
        modelAndView.addObject("payments", payments);
        modelAndView.addObject("classId", id);
        modelAndView.addObject("profiles", subscriptionProfileService.getSubscriptionProfiles());
        return modelAndView;
    }
    
    /**
    @RequestMapping(value = "/subscription/{classId}/{userId}/{typeId}")
    public ModelAndView allUsers(@PathVariable int classId, @PathVariable int userId, @PathVariable int typeId) {
        ModelAndView modelAndView = new ModelAndView("User/_listSubscription");

        ClassesSubscription subs = new ClassesSubscription();
        subs.setUser(userService.getUserById(userId));
        subs.setClasses(classesService.getClassesById(classId));
        subs.setSubscriptionProfile(subscriptionProfileService.getSubscriptionProfileById(typeId));
        System.out.println("AQUI" + subs.toString());
        subscriptionService.addClassesSubscription(subs);

        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);
        modelAndView.addObject("message", "Congratulations! The system works");
        return modelAndView;
    }*/

    /*@RequestMapping(value = "/all/{id}")
     public ModelAndView allUsers(@PathVariable int id) {
     ModelAndView modelAndView = new ModelAndView("User/_listSubscription");
     List<User> users = userService.getUsers();
     modelAndView.addObject("users", users);
     modelAndView.addObject("classId", id);
     return modelAndView;
     }*/
}
