/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.consultjr.mvc.core.formatters;

import org.consultjr.mvc.core.components.AppUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.format.Formatter;

/**
 *
 * @author Rafael
 */
public class DateFormatter implements Formatter<Date> {

    @Override
    public String print(Date object, Locale locale) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.format(object).toString();
        } catch (Exception ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        String srtDateFormated = text.replace(".0", "");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(srtDateFormated);
        } catch (ParseException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
