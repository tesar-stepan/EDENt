/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Stepan Tesar
 */
public class TimeFormatter {

    public static final String BDATE_PATTERN = "d.M.yyyy";
    public static final String APPT_PATTERN = "d.M.yyyy HH:mm";

    /**
     * Tries to recover the patients birth date in milliseconds from the given
     * string.
     *
     * @param bdate the string which should contain the birth date.
     * @return the birth date in seconds, or -1 if the parsing failes.
     */
    public static long getPatientsBDate(String bdate) {
        return getTimeFromString(BDATE_PATTERN, bdate);
    }
    
    /**
     * Creates a formated string from given timestamp (in milliseconds).
     *
     * @param bdate the timestamp.
     * @return string in format this.BDATE_PATTERN.
     */
    public static String getPatientsBDate(long bdate) {
        return getStringFromTime(BDATE_PATTERN, bdate);
    }

    public static long getAppointmentDate(String date) {
        return getTimeFromString(APPT_PATTERN, date);
    }
    
    public static String getAppointmentDate(long date) {
        return getStringFromTime(APPT_PATTERN, date);
    }
    
    /**
     * private methods
     */

    /**
     * 
     */
    private static long getTimeFromString(String pattern, String value) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            formatter.setLenient(true);
//            System.out.println("get bdate from string: " + bdate + " -> " + formatter.parse(bdate));
            return formatter.parse(value).getTime();
        } catch (ParseException ex) {
            //ex.printStackTrace();
            return -1;
        }
    }

    private static String getStringFromTime(String pattern, long value) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false);
//        System.out.println("set bdate to string: " + bdate + " -> " + formatter.format(bdate));
        return formatter.format(new Date(value));
    }
}
