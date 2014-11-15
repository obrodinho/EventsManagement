/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.config;

import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * @author rgcs
 */
@Configuration //Marks this class as configuration
@EnableTransactionManagement
//Specifies which package to scan
@ComponentScan("org.consultjr.mvc")
//Enables Spring's annotations
@EnableWebMvc
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Resource
    private Environment env;
    private Properties properties = new Properties();
    private Properties hibernateProperties = new Properties();
    /*
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

    public ApplicationConfig() {
        properties.setProperty("jdbc.driverClassName", "com.mysql.jdbc.Driver");
        properties.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/events_management");
        properties.setProperty("jdbc.username", "root");
        properties.setProperty("jdbc.password", "mysql");

        hibernateProperties.setProperty("hibernate.connection.driver_class", properties.getProperty("jdbc.driverClassName"));
        hibernateProperties.setProperty("hibernate.connection.url", properties.getProperty("jdbc.url"));
        hibernateProperties.setProperty("hibernate.connection.username", properties.getProperty("jdbc.username"));
        hibernateProperties.setProperty("hibernate.connection.password", properties.getProperty("jdbc.password"));
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
    }

    @Bean
    public PropertyPlaceholderConfigurer setupPropertyConfigurer() {
        PropertyPlaceholderConfigurer propertyConfigurer = new PropertyPlaceholderConfigurer();

        propertyConfigurer.setProperties(properties);
        return propertyConfigurer;
    }

    @Bean
    public DriverManagerDataSource setupDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean setupSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(setupDatasource());
        sessionFactory.setAnnotatedPackages("org.consultjr.mvc.model");
        sessionFactory.setPackagesToScan("org.consultjr.mvc.model");
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(setupSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        //registry.addViewController("/services.htm").setViewName("services");
    }

    @Bean(name = "indexController")
    public ParameterizableViewController indexController() {
        ParameterizableViewController parameterizableViewController = new ParameterizableViewController();
        parameterizableViewController.setViewName("index");
        return parameterizableViewController;
    }

}
