/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import cn.edu.sdut.softlab.test.DataApp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaoyisheng
 */
public class DateUtil {

    /**
     * .
     * Day, Month, Year
     *
     * @param date
     * @return
     */
    public static String formatDay(Date date) {
        if (date == null) {
            return ("");
        } else {
            return (String.format("%tA, %tB %te, %tY",
                    date, date, date, date));
        }
    }

    /**
     * .
     * "hh:mm:ss am on Day, Month, Year"
     *
     * @param date
     * @return
     */
    public static String formatTime(Date date) {
        if (date == null) {
            return ("");
        } else {
            return (String.format("%tl:%tM:%tS %tp on %tA, %tB %te, %tY",
                    date, date, date, date, date, date, date, date));
        }
    }

    /**
     * .
     * "YYYY-MM-DD HH:MM:SS" format date
     * 
     * @param str
     * @return
     */
    public static Date formatDate(String str) {

//        String str = "2017-08-14T01:55:00Z";
        int in = str.indexOf("-");
        String year = str.substring(in - 4, in + 6);
        String time = str.substring(in + 7, in + 15);

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

        try {
            Date date = sdf.parse(year + " " + time);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
