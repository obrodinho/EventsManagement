/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.components;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public boolean checkPermission(User user, HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (user.hasRole("client")) {
            /**
             * TODO Use Classes, DB or Session, to express these values
             */
            /* FreePages*/
            if (uri.contains("/Activity/subscription/")
                    || uri.contains("/Activity/paymentSubscription/")) {
                return true;
            }
            return false;
        } else if (user.hasRole("admin")) {
            return true;
        }
        return false;
    }
}
