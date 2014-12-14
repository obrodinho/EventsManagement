/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.text.ParseException;
import java.util.Locale;
import javax.annotation.Resource;
import org.consultjr.mvc.model.ActivityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rafael
 */
@Component
public class ActivityTypeFormatter implements Formatter<ActivityType> {

    private static ActivityTypeFormatter instance = null;

    private static final Logger logger = LoggerFactory.getLogger(ActivityTypeFormatter.class);

    @Resource
    private ActivityTypeService activityTypeService;

    private ActivityTypeFormatter() {
        logger.info("Creating Singleton...");
        this.activityTypeService = new ActivityTypeService();
    }

    public static ActivityTypeFormatter getInstance() {
        return ActivityTypeFormatter.instance == null ? new ActivityTypeFormatter() : instance;
    }

    @Override
    public String print(ActivityType t, Locale locale) {
        return Integer.toString(t.getId());
    }

    @Override
    public ActivityType parse(String s, Locale locale) throws ParseException {
        ActivityType activityType = new ActivityType();
//        int id = -1;
//        try {
        logger.info("Trying to convert String to ActivityType");
        logger.info("GREATEST GAMBIARRA on Earth. Can't reach Service to retrieve info from DB. So, return an empty object with the ID we must Grab from DB and return that object. Shame on you, Spring.");
        activityType.setId(Integer.parseInt(s));
        logger.info("object: {}", activityType);
        return activityType;

//            id = Integer.parseInt(source);
//            logger.info("ID: {}", id);
//            logger.info("ID string: {}, int: {}", source, id);
//            activityType = activityTypeService.getActivityTypeById(id);
//            logger.info("Grabbed from DB {}", activityType.getShortname());
//            return activityType;
//        } catch (NumberFormatException e) {
//            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(ActivityType.class), source, null);
//        }    
    }

}
