/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.util.Date;
import java.util.List;
import org.consultjr.mvc.core.components.AppUtils;
import org.consultjr.mvc.dao.ClassesDAO;
import org.consultjr.mvc.model.Classes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mario
 */
@Service("ClassesService")
@Transactional(readOnly = true)
public class ClassesService {

    @Autowired
    ClassesDAO classesDAO;

    @Transactional(readOnly = false)
    public void addClasses(Classes classes) {
        classes.setCreated(new Date());
        getClassesDAO().addClasses(classes);
    }

    @Transactional(readOnly = false)
    public void deleteClasses(Classes classes) {
        getClassesDAO().deleteClasses(classes);
    }

    @Transactional(readOnly = false)
    public void updateClasses(Classes classes) {
        classes.setUpdated(new Date());
        getClassesDAO().updateClasses(classes);
    }

    public Classes getClassesById(int id) {
        return getClassesDAO().getClassesById(id);
    }

    public List<Classes> getClasses() {
        return getClassesDAO().getClasses();
    }
    
    public List<Classes> getClassesByActivity(int activityId) {
        return getClassesDAO().getClassesByActivity(activityId);
    }

    public ClassesDAO getClassesDAO() {
        return classesDAO == null ? new ClassesDAO() : classesDAO;
    }

    public void setClassesDAO(ClassesDAO classesDAO) {
        this.classesDAO = classesDAO;
    }
}
