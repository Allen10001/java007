package com.tv.demo001.date;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 北京时间 - 8h = 格林尼治时间
 */
public class DateDemo007 {

    // 2014-03-31T14:11:29+02:00
    public static final String RFC3339 = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String yyyy_MM_dd_HH_mm_00 = "yyyy-MM-dd HH:mm:00";

    public static final String yyyyMMdd = "yyyyMMdd";

    public static final String yyyyMMdd000000 = "yyyyMMdd000000";

    public static final String yyyyMMddHH0000 = "yyyyMMddHH0000";

    public static final String yyyyMMddHHmm00 = "yyyyMMddHHmm00";



    public static void main(String[] args) {

        String time = "2020/12/13-15:30:36.917";
        String pattern1 = "yyyy/MM/dd'-'HH:mm:ss.SSS";
        String pattern2 = "yyyyMMddHHmm00";

        DateFormat dateFormat = new SimpleDateFormat(pattern1, Locale.US);
        Date date = null;
        Date date1 = null;
        Date date3 = null;
        Date date4 = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern1, Locale.CHINA);
            SimpleDateFormat format1 = new SimpleDateFormat(pattern1);
            SimpleDateFormat format3 = new SimpleDateFormat(pattern1, Locale.US);
            SimpleDateFormat format4 = new SimpleDateFormat(pattern1, Locale.ENGLISH);
            date = format.parse(time);
            date1 = format1.parse(time);
            date3 = format3.parse(time);
            date4 = format4.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(date.toString());
        System.out.println(date1.toString());
        System.out.println(date3.toString());
        System.out.println(date4.toString());

        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern2, Locale.US);
            SimpleDateFormat format2 = new SimpleDateFormat(pattern2, Locale.CHINA);
            SimpleDateFormat format3 = new SimpleDateFormat(pattern2);
            System.out.println(format.format(date));
            System.out.println(format2.format(date));
            System.out.println(format3.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

        long dateTimelong = date.getTime();
        //long dayTimelong1 = (dateTimelong+8*3600*1000) - (date.getTime()+8*3600*1000) % (24 * 3600 * 1000);
        System.out.println(dateTimelong);
        System.out.println(date4.getTime());

    }

    public static Date getDate(String dateStr, String pattern) {
        Date date = null;
        if (!StringUtils.isBlank(dateStr) && !StringUtils.isBlank(pattern)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
                date = format.parse(dateStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return date;
    }
}
