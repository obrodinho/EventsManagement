/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import static org.consultjr.mvc.model.Type.ADMIN;
import static org.consultjr.mvc.model.Type.CLIENT;
import static org.consultjr.mvc.model.Type.SPONSOR;
import org.consultjr.mvc.model.User;

/**
 *
 * @author Murilo
 */
public class RequestAnalyser {
    
    private Properties clientProperties;
    private Properties sponsorProperties;

    public RequestAnalyser() {
       clientProperties = this.loadProperties("client.properties");
       sponsorProperties = this.loadProperties("sponsor.properties");
    }

    public boolean checkPermission(User user, HttpServletRequest request){
        String uri = request.getRequestURI();
        if(user.getType() == CLIENT){
            String permitPages = clientProperties.getProperty("pages.permit");
            return uri.endsWith(permitPages);
        } else if(user.getType() == SPONSOR){
            String permitPages = sponsorProperties.getProperty("pages.permit");
            return uri.endsWith(permitPages);
        } else if(user.getType() == ADMIN){
            return true;
        }
        return false;
    }

       private Properties loadProperties(String filename) {
        Properties properties = new Properties();
        InputStream fileStream = getClass().getClassLoader().getResourceAsStream(filename);

        try {
            properties.load(fileStream);
            fileStream.close();
        } catch (IOException ex) {
            Logger.getLogger(RequestAnalyser.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (null == fileStream) {
            System.err.println("property file '" + filename + "' not found in the classpath");
        }
        return properties;
    }
}
