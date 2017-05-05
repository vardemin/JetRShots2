package vardemin.com.jetrshots2.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.text.DateFormat.getDateTimeInstance;


public class DateUtil {
    /**
     * Date to localalized string converter
     * @param date date to convert
     * @return localized string
     */
    public static String dateToString(Date date) {
        getDateTimeInstance();
        DateFormat df = DateFormat.getDateTimeInstance();
        return df.format(date);
    }
}
