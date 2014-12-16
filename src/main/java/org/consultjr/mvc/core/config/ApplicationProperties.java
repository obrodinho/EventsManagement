/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class ApplicationProperties {

    private static ApplicationProperties instance;

    private ApplicationProperties() {
        /* JDBC.PROPERTIES FILE
         # MYSQL JDBC
         jdbc.driverClassName = com.mysql.jdbc.Driver
         jdbc.url = jdbc:mysql://localhost:3306/events_management
         jdbc.username = root
         jdbc.password = mysql

         # Hibernate Config
         hibernate.dialect = org.hibernate.dialect.MySQLDialect
         hibernate.show_sql = true
         hibernate.format_sql = false
         hibernate.generate_statistics = false
         hibernate.dialect = org.hibernate.dialect.MySQLDialect
         hibernate.hbm2ddl.auto = update
         hibernate.current_session_context_class = thread
    
         */

    }

    public static ApplicationProperties getInstance() {
        return instance == null ? new ApplicationProperties() : instance;
    }

    public static Properties load(String filename) {
        InputStream fileStream = ApplicationProperties.getInstance().getClass().getClassLoader().getResourceAsStream(filename);
        Properties properties = new Properties();
        try {
            properties.load(fileStream);
            fileStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (null == fileStream) {
            System.err.println("property file '" + filename + "' not found in the classpath");
        }

        return properties;
    }
}
