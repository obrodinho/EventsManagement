package org.consultjr.mvc.core.components;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kal Lenon
 */
public class AppUtils {

    public static String FormatDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);

        month = month + 1;

        return day + "-" + month + "-" + year + " " + hour + ":" + minutes + "0:0" + seconds;
    }

    public static Date StringToDate(String strDate) {
        String srtDateFormated = strDate.replace(".0", "");
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            return formatter.parse(srtDateFormated);
        } catch (ParseException ex) {
            Logger.getLogger(AppUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
