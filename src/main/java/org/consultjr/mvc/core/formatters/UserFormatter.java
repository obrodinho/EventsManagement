/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.formatters;

import java.text.ParseException;
import java.util.Locale;
import org.consultjr.mvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

/**
 *
 * @author Rafael
 */
public class UserFormatter implements Formatter<User> {

    private static UserFormatter instance = null;

    private static final Logger logger = LoggerFactory.getLogger(UserFormatter.class);

    private UserFormatter() {

    }

    public static UserFormatter getInstance() {
        return UserFormatter.instance == null ? new UserFormatter() : instance;
    }

    public User convert(String s) {
        User u = new User();
        logger.info("Trying to convert String to User");
        logger.info("GREATEST GAMBIARRA on Earth. Can't reach Service to retrieve info from DB. So, return an empty object with the ID we must Grab from DB and return that object. Shame on you, Spring.");
        u.setId(Integer.parseInt(s));
        logger.info("object: {}", u);
        return u;
    }

    @Override
    public String print(User t, Locale locale) {
        return Integer.toString(t.getId());
    }

    @Override
    public User parse(String s, Locale locale) throws ParseException {
        User u = new User();        
        u.setId(Integer.parseInt(s));        
        return u;    
    }

}
