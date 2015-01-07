/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.components;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Rafael
 */
@SessionAttributes("app")
public class Application implements Serializable {

    private String title;
    private String productKey;
    private String productType;
    private List<String> productCapabilities;
    private boolean installed = false;
    private Date installationDate;
    private Date upgradeDate;
    private Date expirationDate;
    private String contextPath;

    public Application() {
        installationDate = new Date();
        installed = false;
    }

    public Application(String productKey) {
        this();
        this.productKey = productKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<String> getProductCapabilities() {
        return productCapabilities;
    }

    public void setProductCapabilities(List<String> productCapabilities) {
        this.productCapabilities = productCapabilities;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public Date getUpgradeDate() {
        return upgradeDate;
    }

    public void setUpgradeDate(Date upgradeDate) {
        this.upgradeDate = upgradeDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public boolean supports(String capability) {    
        return this.productCapabilities.contains(capability);
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
