/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Rafael
 */
@Configuration
@EnableWebMvcSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("UDService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        //                .jdbcAuthentication()
        //                .dataSource(dataSource)
        //                .withDefaultSchema()
        //                .usersByUsernameQuery(
        //                        "SELECT username, password, CASE WHEN deleted IS NOT TRUE THEN TRUE ELSE FALSE END as enabled"
        //                        + " FROM users;"
        //                )
        //                .authoritiesByUsernameQuery(
        //                        "SELECT username, sp.shortname as role \n"
        //                        + "FROM users u \n"
        //                        + "	INNER JOIN user_system_profiles usp ON u.user_id = usp.user_id\n"
        //                        + "    INNER JOIN system_profiles sp ON usp.profile_id = sp.profile_id;"
        //                );
        //        //.withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/signup", "/about", "/System/install", "/login").permitAll()
//                .anyRequest().authenticated()
//                //.antMatchers("/admin/**", "/System/**").access("hasRole('admin')")
//                //.antMatchers("/resources/**", "/signup", "/about", "/System/install").permitAll()
//                .and().formLogin()
//                .loginPage("/login").failureUrl("/login?error")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout")
//                .and()
//                 .exceptionHandling().accessDeniedPage("/403")
//                 .and()
//                 .csrf();
//                //.permitAll();

        http.authorizeRequests().antMatchers("/admin/**")
                .access("hasRole('admin')").and().formLogin()
                .loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutSuccessUrl("/login?logout")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
