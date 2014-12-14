/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.base;

/**
 *
 * @author Rafael
 */
public abstract class ApplicationModel {
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract String toString();
    
    @Override
    public abstract boolean equals(Object object);
}
