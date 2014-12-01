/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import static org.consultjr.mvc.model.Type.ADMIN;
import static org.consultjr.mvc.model.Type.CLIENT;
import static org.consultjr.mvc.model.Type.SPONSOR;
import org.consultjr.mvc.model.User;

/**
 *
 * @author Murilo
 */
public class RequestAnalyser {
    
    private List clientPages;
    private List sponsorPages;

    public RequestAnalyser() {
        clientPages = readPages("client.config");
        clientPages = readPages("sponsor.config");
    }

    public boolean checkPermission(User user, HttpServletRequest request){
        return true;
    /*    String uri = request.getRequestURI();
        if(user.getType() == CLIENT){
            for(Object s : clientPages){
                String str = (String) s;
                if(uri.endsWith(str)){
                    return true;
                }
            }
            return false;
        } else if(user.getType() == SPONSOR){
            for(Object s : sponsorPages){
                String str = (String) s;
                if(uri.endsWith(str)){
                    return true;
                }
            }
            return false;
        } else if(user.getType() == ADMIN){
            return true;
        }
        return false;*/
    }

    private List readPages(String clientconfig) {
        return new ArrayList();
    }
}
