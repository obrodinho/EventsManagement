/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.dao.SystemConfigDAO;
import org.consultjr.mvc.model.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Service
@Transactional
public class SystemConfigService {

    @Autowired
    private SystemConfigDAO SystemDAO;

    public SystemConfigDAO getSystemDAO() {
        return SystemDAO;
    }

    public void setSystemDAO(SystemConfigDAO SystemDAO) {
        this.SystemDAO = SystemDAO;
    }

    @Transactional(readOnly = false)
    public void addConfig(SystemConfig sys) {
        getSystemDAO().addConfig(sys);
    }

    @Transactional(readOnly = false)
    public void deleteConfig(SystemConfig sys) {
        getSystemDAO().deleteConfig(sys);
    }

    @Transactional(readOnly = false)
    public void updateConfig(SystemConfig sys, int configID) {
        SystemConfig config = getConfigById(configID);

        config.setKey(sys.getKey());
        config.setValue(sys.getValue());
        config.setUpdated(new Date());

        getSystemDAO().updateConfig(config);
    }

    @Transactional(readOnly = false)
    public SystemConfig getConfigById(int configID) {
        return getSystemDAO().getConfigById(configID);
    }

    @Transactional(readOnly = false)
    public SystemConfig getConfigByKey(String configKey) {
        return getSystemDAO().getConfigByKey(configKey);
    }

    @Transactional(readOnly = false)
    public List<SystemConfig> getConfigs() {
        return getSystemDAO().getConfigs();
    }

    @Transactional
    public SystemConfig get(String key) {
        return this.getConfigByKey(key);
    }

    @Transactional
    public void set(String key, String value) {
        //TODO evolve to use Objects. Save straigh ahead to strings and serialize to other objects

        if (null != getConfigByKey(key)) {
            SystemConfig sys = getConfigByKey(key);
            sys.setValue(value);
            sys.setUpdated(new Date());
            updateConfig(sys, sys.getId());
        } 
        /* else if (value.equals("")) {
            deleteConfig(getConfigByKey(key));
        }*/ 
        else {
            addConfig(new SystemConfig(key, value));
        }
    }

}
