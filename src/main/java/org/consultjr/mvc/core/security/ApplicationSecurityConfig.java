/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.security;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

/**
 *
 * @author Rafael
 */
@Configuration
@EnableWebMvcSecurity
@PropertySource("classpath:application.properties")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory
            .getLogger(ApplicationSecurityConfig.class);

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("UDService")
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // #3
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(environment.getProperty("security.rolesHierarchy"));

        logger.info("Roles Hierarchy: {}", environment.getProperty("security.rolesHierarchy"));

        return roleHierarchy;
    }

    @Bean
    public RoleVoter roleVoter() {
        RoleVoter roleVoter = new ApplicationRoleVoter(roleHierarchy());

        roleVoter.setRolePrefix("");
        logger.info("Executing roleVoter: {}", roleVoter.toString());

        return roleVoter;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Bean
    public AffirmativeBased accessDecisionVoter() {
        List<AccessDecisionVoter> decisionVoters = new ArrayList<AccessDecisionVoter>();
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(expressionHandler());
        decisionVoters.add(roleVoter());
        decisionVoters.add(webExpressionVoter);
        logger.info("Executing accessDecisionManager: {}", decisionVoters.toString());

        return new AffirmativeBased(decisionVoters);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.expressionHandler(expressionHandler())
                //.accessDecisionManager(accessDecisionVoter())
                .antMatchers("/", "/signup/**", "/about/**", "/System/install", "/login/**", "/Client/**").permitAll()
                .antMatchers("/admin/**", "/System/**", "/User/**").hasRole("admin")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .csrf()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
