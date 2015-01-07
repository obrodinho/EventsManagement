/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.components;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author Rafael
 */
public class ApplicationNav {

    private List<ApplicationMenu> menus;

    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<ApplicationMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<ApplicationMenu> menus) {
        this.menus = menus;
    }

    public ApplicationNav() {
        this.menus = new ArrayList<ApplicationMenu>();
    }

    public ApplicationNav(List<ApplicationMenu> menus) {
        this.menus = menus;
    }

    public ApplicationNav prepare(String prefix, Application app, List<String> loggedUserRoles) {

        List<ApplicationMenu> applicationMenus
                = new ArrayList<>();

        if (prefix.isEmpty()) {
            prefix = app.getContextPath();
        }

        ApplicationMenu aboutMenu = new ApplicationMenu(prefix + "/about", "About", "*");
        applicationMenus.add(aboutMenu);

        ApplicationMenu usersMenu = new ApplicationMenu(prefix + "#", "Users", "*");

        usersMenu.getSubmenus().add(new ApplicationMenu(prefix + "/User/panel", "User Panel", "*"));
        usersMenu.getSubmenus().add(new ApplicationMenu(prefix + "/User/add", "Add a New User", "admin"));
        usersMenu.getSubmenus().add(new ApplicationMenu(prefix + "/User/all", "List all Users", "*"));

        applicationMenus.add(usersMenu);

        if (app.supports("Events")) {
            ApplicationMenu eventsMenu = new ApplicationMenu(prefix + "#", "Events", "*");
            eventsMenu.getSubmenus().add(new ApplicationMenu(prefix + "/Event/add", "Add a New Event", "admin"));
            eventsMenu.getSubmenus().add(new ApplicationMenu(prefix + "/Event/all", "List all Events", "*"));
            applicationMenus.add(eventsMenu);
        }

        ApplicationMenu activitiesMenu = new ApplicationMenu(prefix + "#", "Activities", "*");
        if (!app.supports("Events")) {
            activitiesMenu.getSubmenus().add(new ApplicationMenu(prefix + "/Activity/add/1", "Add a New Activity", "admin"));
        }
        activitiesMenu.getSubmenus().add(new ApplicationMenu(prefix + "/Activity/all", "List All Activities", "*"));
        applicationMenus.add(activitiesMenu);

        ApplicationMenu activityTypesMenu = new ApplicationMenu(prefix + "#", "Activity Types", "*");
        activityTypesMenu.getSubmenus().add(new ApplicationMenu(prefix + "/ActivityType/add", "Add a  New Activity Type", "*"));
        activityTypesMenu.getSubmenus().add(new ApplicationMenu(prefix + "/ActivityType/all", "List All Activity Types", "*"));
        applicationMenus.add(activityTypesMenu);

        if (app.supports("Payments")) {
            ApplicationMenu paymentsMenu = new ApplicationMenu(prefix + "#", "Payments", "*");
            paymentsMenu.getSubmenus().add(new ApplicationMenu(prefix + "/Activity/paymentSubscription", "List Payments", "*"));
            applicationMenus.add(paymentsMenu);
        }

        if (app.supports("Support")) {
            ApplicationMenu supportMenu = new ApplicationMenu(prefix + "/support", "Support", "*");
            applicationMenus.add(supportMenu);
        }

        ApplicationMenu contactMenu = new ApplicationMenu(prefix + "/contact", "Contact", "*");
        applicationMenus.add(contactMenu);

        this.menus = applicationMenus;
        this.generateHTML(loggedUserRoles);
        return this;
    }

    public String generateHTML(List<String> roles) {
        /*
         <ul class='nav navbar-nav'>
         ...
         </ul>
         */
        String menuHTML = "\t<ul class='nav navbar-nav'>\n";
        Iterator<ApplicationMenu> appMenus = this.menus.iterator();
        while (appMenus.hasNext()) {
            ApplicationMenu menu = appMenus.next();

            menuHTML += menu.generateHTML(roles);

        }
        menuHTML += "\t</ul>\n";
        this.html = menuHTML;
        return menuHTML;
    }

}
