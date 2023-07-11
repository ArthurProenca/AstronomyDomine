package dev.friday.com.dal.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateUtils {

    public static Date getCurrentDateMinus(Long systemMilis) {
        return new Date(System.currentTimeMillis() - systemMilis);
    }

    public static Long getMinutesInSystemMilis(Long minutes) {
        return minutes * 60 * 1000;
    }

    public static String getTodayDateInUSAPattern() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return formatter.format(java.time.LocalDate.now());
    }

    public static String getTodayDateInUSAPatternWithTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(java.time.LocalDateTime.now());
    }

    public static Date fromString(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getThisYear() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return formatter.format(java.time.LocalDate.now());
    }

    public static String getThisMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return formatter.format(java.time.LocalDate.now());
    }

    public static String getThisDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        return formatter.format(java.time.LocalDate.now());
    }

    public static String getMonthAsString(int monthValue) {
        if(monthValue < 10) {
            return "0" + monthValue;
        }
        return String.valueOf(monthValue);
    }

    public static String getDayAsString(int dayOfMonth) {
        if(dayOfMonth < 10) {
            return "0" + dayOfMonth;
        }
        return String.valueOf(dayOfMonth);
    }
}
