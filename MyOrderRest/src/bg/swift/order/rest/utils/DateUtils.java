package bg.swift.order.rest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public final static String DATE_PATTERN = "dd/MM/yyyy HH:mm";
    public final static String MY_SQL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {};

    public static Date parseDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isDateValidFormat(String dateStr) {
        return parseDate(dateStr) != null;
    }

    public static String toMySQLDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(MY_SQL_DATE_PATTERN);
        return formatter.format(date);
    }

    public static String printDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        return formatter.format(date);
    }
    
    public static java.sql.Timestamp getCurrentTimeStamp() {
 		java.util.Date today = new java.util.Date();
 		return new java.sql.Timestamp(today.getTime());
 	}
    
}
