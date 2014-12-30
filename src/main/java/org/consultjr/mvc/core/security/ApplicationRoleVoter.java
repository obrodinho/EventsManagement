/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.security;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Rafael
 */
public class ApplicationRoleVoter extends RoleHierarchyVoter {
    
    private Logger logger = LoggerFactory
            .getLogger(ApplicationRoleVoter.class);

    public ApplicationRoleVoter(RoleHierarchy roleHierarchy) {
        super(roleHierarchy);
        logger.info("Role Hierarchy: {}", roleHierarchy);
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        //return super.vote(authentication, object, attributes); //To change body of generated methods, choose Tools | Templates.
        logger.info("Authentication: {}", authentication);
        logger.info("Object: {}", object);
        logger.info("Attributes: {}", attributes);
        
        return ACCESS_GRANTED;
    }

    @Override
    public boolean supports(Class<?> clazz) {
//        return super.supports(clazz); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        //return super.supports(attribute); //To change body of generated methods, choose Tools | Templates.
        return true;
    }

}
