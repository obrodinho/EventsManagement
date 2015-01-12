/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.config;

import org.consultjr.mvc.core.config.security.ApplicationSecurityConfig;
import java.util.Properties;
import org.consultjr.mvc.core.components.ApplicationInterceptor;
import org.consultjr.mvc.core.components.formatters.ActivityTypeFormatter;
import org.consultjr.mvc.core.components.formatters.DateFormatter;
import org.consultjr.mvc.core.components.formatters.UserFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 *
 * @author rgcs
 */
@Configuration //Marks this class as configuration
@EnableTransactionManagement
@ComponentScan("org.consultjr.mvc")
@PropertySource("classpath:application.properties")
@EnableWebMvc
@Import({ApplicationSecurityConfig.class})
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;
    
    private Properties properties = new Properties();

    public ApplicationConfig() {
        properties = ApplicationProperties.load("application.properties");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new ApplicationInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry); //To change body of generated methods, choose Tools | Templates.
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(ActivityTypeFormatter.getInstance());
        registry.addFormatter(UserFormatter.getInstance());
    }

    @Bean
    public static PropertyPlaceholderConfigurer setupPropertyConfigurer() {
        return new PropertyPlaceholderConfigurer();
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
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(setupSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService conversionService = new FormattingConversionService();
        addFormatters(conversionService);
        return conversionService;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
