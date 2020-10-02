package csg.monthly.expensies.view.util;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Pattern: yyyy-MM-dd
 */
public class DateParser {

    public static Date stringToDate(String rawDate) {
        final LocalDate date = LocalDate.parse(rawDate);
        return Date.valueOf(date.plusDays(1));
    }

    public static String dateToString(Date date) {
        return date.toString();
    }
}
