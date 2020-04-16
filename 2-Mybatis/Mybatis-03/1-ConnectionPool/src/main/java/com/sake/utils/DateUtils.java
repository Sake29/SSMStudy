package com.sake.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转毫秒
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param min
     * @param sec
     * @return
     */
    private static long date2Millis(Integer year,Integer month,Integer day,Integer hour,Integer min,Integer sec){
        String sYear = year.toString();
        String sMonth;
        String sDay;
        String sHour;
        String sMin;
        String sSec;

        if (month < 10) {
            sMonth = "0" + month;
        } else {
            sMonth = month.toString();
        }
        if (day < 10) {
             sDay = "0" + day;
        } else {
            sDay = day.toString();
        }
        if (hour < 10) {
            sHour = "0" + hour;
        } else {
            sHour = hour.toString();
        }
        if (min < 10) {
            sMin = "0" + min;
        } else {
            sMin = min.toString();
        }
        if (sec < 10) {
            sSec = "0" + sec;
        } else {
            sSec = hour.toString();
        }
        String date = sYear+sMonth+sDay+sHour+sMin+sSec;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        try{
            long millionSeconds = sdf.parse(date).getTime();//毫秒
            //System.out.println(millionSeconds);
            return millionSeconds;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 毫秒转日期
     * @param time
     * @return
     */
    private static Date millis2Date(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Date date = calendar.getTime();
        //System.out.println(date);
        return date;
    }

    /**
     * 重载
     * 生成日期
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param min
     * @param sec
     * @return
     */
    public static Date getDate(Integer year,Integer month,Integer day,Integer hour,Integer min,Integer sec)  {
        long millionSeconds = date2Millis(year, month, day, hour, min, sec);
        Date date = millis2Date(millionSeconds);
        return date;
    }


    /**
     * 重载
     * 生成日期
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDate(Integer year,Integer month,Integer day)  {
        long millionSeconds = date2Millis(year, month, day, 0,0,0);
        Date date = millis2Date(millionSeconds);
        return date;
    }

}
