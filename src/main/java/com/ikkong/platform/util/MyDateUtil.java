package com.ikkong.platform.util;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMdd");
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    // 日期格式化
    private static DateFormat dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
    public static String now() {
        return sdf.format(new Date());
    }

    public static String DateString() {
        return dateSdf.format(new Date());
    }

    public static String format(Date date) {
        if (null == date)
            return null;
        return sdf.format(date);
    }

    /**
     * 统计两个日期之间包含的天数。
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDayDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException("date1 and date2 cannot be null!");
        }
        long millSecondsInOneDay = 24 * 60 * 60 * 1000;
        return (int) ((date1.getTime() - date2.getTime()) / millSecondsInOneDay);
    }

    //取得一天的时间
    public static String getBeforeOneDay()
    {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime=formatter.format(c.getTime());
        return mDateTime.substring(0, 16);
    }

    public static String get_yyyyMMdd_later(int days)
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String mDateTime=formatter.format(c.getTime());
        return mDateTime.substring(0, 8);
    }

    public static String get180later(String date,int days)
    {
        try {
            Date date1=stringToDate(date,"yyyyMMdd");
            Calendar c=Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DATE, days);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String mDateTime=formatter.format(c.getTime());
            return mDateTime.substring(0, 8);
        } catch (ParseException e) {
            return null;

        }
    }


    //取得一周的时间
    public static String getWeekDay()
    {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -7);//得到前5
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime=formatter.format(c.getTime());
        return  mDateTime.substring(0, 16);//2007-10-24 09:30
    }

    //取得一周的时间
    public static String getDiDay(int count)
    {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -count);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mDateTime=formatter.format(c.getTime());
        return  mDateTime.substring(0, 16);//2007-10-24 09:30
    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * @Author: zry
     * @Update: 添加获取当前时间
     * @param;  * @param null
     * @Description: yyyy-MM-dd
     * @Date: 10:57 2017/10/20 0020
     */

    public static Date getNowDate(){
        return getDateFormat(dateFormat.format(new Date()));
    }

    /**
     * @Author: zry
     * @Update: 添加获取当前时间
     * @param;  * @param null
     * @Description:
     * @Date: 10:57 2017/10/20 0020
     */

    public static Date getDateFormat(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ParseException {
        Date date = sdf.parse("1970-1-01 00:00:00");

        dateToLong(date);
        System.out.println(dateToLong(date));


        System.out.println(MyDateUtil.get180later("20170911",180));

    }

}

