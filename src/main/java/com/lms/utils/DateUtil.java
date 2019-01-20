package com.lms.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static long differenceInDays(Date startDate,Date endDate){
        long duration = endDate.getTime()-startDate.getTime();
        long diffInDays= TimeUnit.MILLISECONDS.toDays(duration);
        return diffInDays;
    }
}
