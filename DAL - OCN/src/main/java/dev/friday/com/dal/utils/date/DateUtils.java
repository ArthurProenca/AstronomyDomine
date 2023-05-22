package dev.friday.com.dal.utils.date;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    public static Date getCurrentDateMinus(Long systemMilis) {
        return new Date(System.currentTimeMillis() - systemMilis);
    }

    public static Long getMinutesInSystemMilis(Long minutes) {
        return minutes * 60 * 1000;
    }

    public static String getTodayDateInUSAPattern() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(java.time.LocalDate.now());
    }
}
