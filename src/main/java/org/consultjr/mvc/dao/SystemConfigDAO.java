/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.dao;

import java.util.List;
import org.consultjr.mvc.core.base.AppDAO;
import org.consultjr.mvc.model.SystemConfig;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rafael
 */
@Repository
public class SystemConfigDAO extends AppDAO {

    @Transactional
    public void addConfig(SystemConfig config) {
        getSessionFactory().getCurrentSession().save(config);
        getSessionFactory().getCurrentSession().refresh(config);
    }

    @Transactional
    public void deleteConfig(SystemConfig config) {
        getSessionFactory().getCurrentSession().delete(config);
    }

    @Transactional
    public void updateConfig(SystemConfig config) {
        getSessionFactory().getCurrentSession().update(config);
    }

    @Transactional
    public SystemConfig getConfigById(int id) {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from SystemConfig where id=?")
                .setParameter(0, id).list();
        return (SystemConfig) list.get(0);
    }

    @Transactional
    public SystemConfig getConfigByKey(String key) {

        return (SystemConfig) getSessionFactory().getCurrentSession()
                .createQuery("from SystemConfig where key=:k")
                .setParameter("k", key).uniqueResult();
    }

    @Transactional
    public List<SystemConfig> getConfigs() {
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from SystemConfig").list();
        return list;
    }
}
