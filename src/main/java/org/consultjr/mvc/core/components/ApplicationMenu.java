/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rgcs
 */
public class ApplicationMenu {

    private String url;

    private String title;

    private boolean active;

    private List<String> visibleRoles;

    private List<ApplicationMenu> submenus;

    public ApplicationMenu(String url, String title, String roles) {
        this.active = false;
        this.url = url;
        this.title = title;
        this.submenus = new ArrayList<>();
        String[] visibles = roles.split(",");
        this.visibleRoles = Arrays.asList(visibles);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ApplicationMenu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(ArrayList<ApplicationMenu> submenus) {
        this.submenus = submenus;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getVisibleRoles() {
        return visibleRoles;
    }

    public void setVisibleRoles(ArrayList<String> visibleRoles) {
        this.visibleRoles = visibleRoles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.url);
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + (this.active ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApplicationMenu other = (ApplicationMenu) obj;
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    public boolean isVisibleTo(String role) {
        return this.visibleRoles.isEmpty()
                || this.visibleRoles.contains(role)
                || this.visibleRoles.contains("*");
    }

    public boolean isVisibleToOneOf(List<String> roles) {
        Iterator<String> it = roles.iterator();
        while (it.hasNext()) {
            String role = it.next();

            if (this.isVisibleTo(role)) {
                return true;
            }
        }

        return false;
    }

    public String generateHTML(List<String> roles) {
        /*
         <li>
         <a href='#'>Getting started</a>
         </li>
         <li class='dropdown'>
         <a href='#' class='dropdown-toggle' data-toggle='dropdown'>Dropdown <b class='caret'></b></a>
         <ul class='dropdown-menu'>
         <li><a href='#'>Action</a></li>
         <li><a href='#'>Another action</a></li>
         <li><a href='#'>Something else here</a></li>
         <li><a href='#'>Separated link</a></li>
         <li><a href='#'>One more separated link</a></li>
         </ul>
        
         */
        String result = "";

        if (this.isVisibleToOneOf(roles)) {

            String dropdown = "";
            String dropdownContent = "";

            if (!this.submenus.isEmpty()) {
                dropdown = "dropdown";
                dropdownContent += "<ul class='dropdown-menu'>";

                Iterator<ApplicationMenu> appMenus = this.submenus.iterator();
                while (appMenus.hasNext()) {
                    ApplicationMenu menu = appMenus.next();
                    dropdownContent += menu.generateHTML(roles);
                }
                dropdownContent += "</ul>";

                result += "<li class='dropdown'>\n"
                        + "\t\t<a href='#' class='dropdown-toggle' data-toggle='dropdown'>" + this.title + " <b class='caret'></b></a>\n"
                        + dropdownContent
                        + "\t\t</li>\n";

            } else {
                result = "<li class=''>\n"
                        + "\t\t<a href='"
                        + this.url + "'>"
                        + this.title
                        + "</a>\n"
                        + "\t\t</li>\n";
            }

        }
        return result;
    }

}
