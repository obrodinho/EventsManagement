/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.consultjr.mvc.dao.SystemProfileDAO;
import org.consultjr.mvc.dao.UserDAO;
import org.consultjr.mvc.dao.UserSystemProfileDAO;
import org.consultjr.mvc.model.User;
import org.consultjr.mvc.model.UserSystemProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Service("UDService")
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserSystemProfileDAO uspDAO;

    @Autowired
    private SystemProfileDAO spDAO;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(string);
        List<GrantedAuthority> authorities = buildUserAuthority(uspDAO.getUserSystemProfilesOfUser(user.getId()));

        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
            List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                authorities
        );
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserSystemProfile> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserSystemProfile userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getSystemProfile().getShortname()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}
