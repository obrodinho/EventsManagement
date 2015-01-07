package org.consultjr.mvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.consultjr.mvc.core.base.ApplicationController;
import org.consultjr.mvc.core.components.ApplicationUtils;
import org.consultjr.mvc.model.Activity;
import org.consultjr.mvc.model.Classes;
import org.consultjr.mvc.model.ClassesSubscription;
import org.consultjr.mvc.model.Payment;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.service.ActivityService;
import org.consultjr.mvc.service.ActivityTypeService;
import org.consultjr.mvc.service.ClassesService;
import org.consultjr.mvc.service.ClassesSubscriptionService;
import org.consultjr.mvc.service.EventService;
import org.consultjr.mvc.service.PaymentService;
import org.consultjr.mvc.service.SubscriptionProfileService;
import org.consultjr.mvc.service.SystemConfigService;
import org.consultjr.mvc.service.UserService;
import org.consultjr.mvc.service.UserSystemProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Activity Service
 *
 * @author kallenon
 */
@Controller
@RequestMapping("Activity")
public class ActivityController extends ApplicationController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityTypeService activityTypeService;
    @Autowired
    private ClassesService classesService;
    @Autowired
    private EventService eventService;
    @Autowired
    private SubscriptionProfileService subscriptionProfileService;
    @Autowired
    private ClassesSubscriptionService classeSubscriptionService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemConfigService sysService;
    @Autowired
    private UserSystemProfileService uspService;

    public void setActivityService(final ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping("") // Index Method: => /PROJECT/Activity
    public ModelAndView index(Principal principal) {
        return this.allActivities(principal);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET) // GET: /PROJECT/Activity/add
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        modelAndView.addObject("activity", new Activity());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("activityID", null);
        modelAndView.addObject("activityTypes", activityTypeService.getActivityTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST) // Save Method: POST /PROJECT/Activity/add
    public ModelAndView addActivity(@ModelAttribute Activity activity, BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            getLogger().info("Binding Error");
        }
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/all");

        if (activity.getEvent() == null && eventService.getEvents().size() > 0) {
            activity.setEvent(eventService.getEvents().get(0));
        }
        activityService.addActivity(activity);

        Classes standardClasses = new Classes();
        standardClasses.setActivity(activity);
        standardClasses.setStandard(true);
        standardClasses.setDescription("Turma Padrão");
        standardClasses.setCreated(new Date());
        standardClasses.setTitle("Turma Padrao");
        classesService.addClasses(standardClasses);

        String message = "Activity was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }
    
    @RequestMapping(value = "/add/{eventId}", method = RequestMethod.GET) // GET: /PROJECT/Activity/add
    public ModelAndView add(@PathVariable Integer eventId) {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        modelAndView.addObject("activity", new Activity());
        modelAndView.addObject("action", "add");
        modelAndView.addObject("activityID", null);
        modelAndView.addObject("eventID", eventId);
        modelAndView.addObject("activityTypes", activityTypeService.getActivityTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/add/{eventId}", method = RequestMethod.POST) // Save Method: POST /PROJECT/Activity/add
    public ModelAndView addActivity(@PathVariable Integer eventId ,@ModelAttribute Activity activity, BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            getLogger().info("Binding Error");
        }
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/all/"+eventId);

        if (activity.getEvent() == null && eventService.getEvents().size() > 1) {
           activity.setEvent(eventService.getEventById(eventId));
        }else{
           activity.setEvent(eventService.getEvents().get(0)); 
        }
        activityService.addActivity(activity);

        Classes standardClasses = new Classes();
        standardClasses.setActivity(activity);
        standardClasses.setStandard(true);
        standardClasses.setDescription("Turma Padrão");
        standardClasses.setCreated(new Date());
        standardClasses.setTitle("Turma Padrao");
        classesService.addClasses(standardClasses);

        String message = "Activity was succesfully added";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("Activity/_form");
        Activity activity = activityService.getActivityById(id);

        activity.setDateStart(ApplicationUtils.FormatDate(activity.getStart()));
        activity.setDateEnd(ApplicationUtils.FormatDate(activity.getEnd()));

        modelAndView.addObject("activity", activity);
        modelAndView.addObject("action", "edit");
        modelAndView.addObject("activityID", activity.getId());
        modelAndView.addObject("activityTypes", activityTypeService.getActivityTypes());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView updateActivity(@ModelAttribute Activity activity, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/all");
        activityService.updateActivity(activity, id);
        String message = "Activity was successfully edited.";
        modelAndView.addObject("message", message);
        
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteActivity(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/all");
        //TODO checar se houve deleção!!!
        activityService.deleteActivity(activityService.getActivityById(id));
        List<Activity> activities = activityService.getActivities();
        modelAndView.addObject("activities", activities);
        String message = "Activity was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allActivities(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("Activity/_list-client");
        List<Activity> activities = activityService.getActivitiesByEventId(1);
        
        if (principal != null) {
            if (uspService.userHasRole(getLoggedUser().getId(), "admin")){
                getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "admin")));
                 modelAndView = new ModelAndView("Activity/_list-admin");
            }
            if (uspService.userHasRole(getLoggedUser().getId(), "client")){
                getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "client")));
                modelAndView = new ModelAndView("Activity/_list-client");
            }
        }
        modelAndView.addObject("activities", activities);
        return modelAndView;
    }
    
    @RequestMapping(value = "/all/{eventId}")
    public ModelAndView allActivities(@PathVariable Integer eventId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("Activity/_list-client");
        List<Activity> activities = activityService.getActivitiesByEventId(eventId);
        if (principal != null) {
            if (uspService.userHasRole(getLoggedUser().getId(), "admin")){
                getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "admin")));
                 modelAndView = new ModelAndView("Activity/_list-admin");
            }
            if (uspService.userHasRole(getLoggedUser().getId(), "client")){
                getLogger().info(String.valueOf(uspService.userHasRole(getLoggedUser().getId(), "client")));
                modelAndView = new ModelAndView("Activity/_list-client");
            }
        }
        modelAndView.addObject("activities", activities);
        return modelAndView;
    }
    
    @RequestMapping(value = "/subscription")
    public ModelAndView subscriptionActivityMonoEvent() {
        ModelAndView modelAndView = new ModelAndView("Client/_subscription");
        List<Activity> activities = activityService.getActivitiesByEventId(1);
        modelAndView.addObject("activities", activities);
        return modelAndView;
    }
    
    @RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
    public ModelAndView addSubscriptionActivity(HttpServletRequest request, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("forward:/Activity/paymentSubscription");
        List<ClassesSubscription> classesSubscriptionPaymentPending = new ArrayList<>();
        String activityIDS[] = request.getParameterValues("subscribeActivities");
        User user = userService.getUserByUsername(principal.getName());
        for (String activityIDSaux : activityIDS) {
            Classes classDefault = classesService.getFirstClassOfActivity(Integer.parseInt(activityIDSaux));
            Payment payment = new Payment("pending", 1);
            paymentService.addPayment(payment);
            ClassesSubscription cs = new ClassesSubscription(classDefault, user, subscriptionProfileService.getSubscriptionProfileByShortname("participante"), payment);
            classeSubscriptionService.addClassesSubscription(cs);
            classesSubscriptionPaymentPending.add(cs);
        }
        if(getApplicationObject().supports("Payments") == false){
            modelAndView = new ModelAndView("forward:/");
            String message = "Activity registration succesfull.";
            modelAndView.addObject("message", message);
        }
        modelAndView.addObject("classesSubscriptionPaymentPending", classesSubscriptionPaymentPending);
        modelAndView.addObject("classesSubscriptionPaymentPaid", null);
        return modelAndView;
    }
    
    @RequestMapping(value = "/paymentSubscription")
    public ModelAndView payamentSubscriptionActivity(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("Client/_paymentSubscription");
        User user = userService.getUserByUsername(principal.getName());
        List<ClassesSubscription> classesSubscriptionPaymentPending = new ArrayList<>();
        List<ClassesSubscription> classesSubscriptionPaymentPaid = new ArrayList<>();
        List<ClassesSubscription> classesSubscription = classeSubscriptionService.getClassesSubscriptionByUser(user.getId());
        for (int i=0; i<classesSubscription.size(); i++){
            if (classesSubscription.get(i).getPayment() != null){
                if(classesSubscription.get(i).getPayment().getStatus().equals("paid")){
                    classesSubscriptionPaymentPaid.add(classesSubscription.get(i));
                } else if(classesSubscription.get(i).getPayment().getStatus().equals("pending")){
                    classesSubscriptionPaymentPending.add(classesSubscription.get(i));
                }
            }
        }
        String message = "Activity registration succesfull.";
        modelAndView.addObject("classesSubscriptionPaymentPending", classesSubscriptionPaymentPending);
        modelAndView.addObject("classesSubscriptionPaymentPaid", classesSubscriptionPaymentPaid);
        modelAndView.addObject("message", message);
        return modelAndView;
    }
    
    @RequestMapping(value = "/confirmPayamentSubscription/{id}", method = RequestMethod.GET)
    public ModelAndView confirmPayamentSubscriptionActivity(@PathVariable Integer id, HttpServletRequest request, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("Client/_paymentSubscription");
        
        Payment payment = paymentService.getPaymentById(id);
        payment.setStatus("paid");
        payment.setPaid(new Date());
        paymentService.updatePayment(payment, id);
        
        User user = userService.getUserByUsername(principal.getName());
        List<ClassesSubscription> classesSubscriptionPaymentPending = new ArrayList<>();
        List<ClassesSubscription> classesSubscriptionPaymentPaid = new ArrayList<>();
        List<ClassesSubscription> classesSubscription = classeSubscriptionService.getClassesSubscriptionByUser(user.getId());
        for (int i=0; i<classesSubscription.size(); i++){
            if(classesSubscription.get(i).getPayment().getStatus().equals("paid")){
                classesSubscriptionPaymentPaid.add(classesSubscription.get(i));
            } else if(classesSubscription.get(i).getPayment().getStatus().equals("pending")){
                classesSubscriptionPaymentPending.add(classesSubscription.get(i));
            }
        }
        String message = "Subscription paid succesfull.";
        modelAndView.addObject("classesSubscriptionPaymentPending", classesSubscriptionPaymentPending);
        modelAndView.addObject("classesSubscriptionPaymentPaid", classesSubscriptionPaymentPaid);
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
