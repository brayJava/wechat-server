package com.bray.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:wuzhiyuan
 * @description: 时间处理工具类
 * @date: Created in 13:57 2017/11/28
 * @modified By:
 */
public class DateUtil {
    public static final String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String PATTERN_yyyyMMdd = "yyyyMMdd";
    public static final String PATTERN_MMdd = "MM-dd";
    public static final String PATTERN_yyMMdd = "yy/MM/dd";
    public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String PATTERN_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String PATTERN_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_HH_mm_ss = "HH:mm:ss";
    public static final String PATTERN_HHmmss = "HHmmss";
    public static final String PATTERN_yyyyMMdd_HHmmss = "yyyy/MM/dd HH:mm:ss";
    public static final String PATTERN_yyyyMMdd2 = "yyyy/MM/dd";
    /**
     * 格式时间
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDate(Date date, String format) {
        if (date != null) {
            SimpleDateFormat f = new SimpleDateFormat(format);
            return f.format(date);
        } else {
            return null;
        }
    }
    public static String parseDateToStr(Date date, String pattern) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化日期
     * @param date 日期
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 格式化日期 格式为"yyyy-MM-dd"
     * @param date 日期
     * @return 日期字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期 格式为"yyyyMMdd"
     * @param date 日期
     * @return 日期字符串
     */
    public static String formatDate2(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    /**
     * 把字符串类型的日期转换成Date类型
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) {
        return parseDate(strDate, "yyyy-MM-dd");
    }

    /**
     * 把字符串类型的日期转换成Date类型
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseDate(String strDate, String pattern) {
        if (StringUtils.isEmpty(strDate))
            return null;
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 加减时间
     * @param strDate 原始时间字符串
     * @param time 增加/减少的时间数
     * @param fromPat 原始时间的转换格式
     * @param toPat 结果时间的转换格式
     * @param timePat 加减的时间域:秒,分,时,日等,从Calendar中获得
     * @return
     */
    public static String addTime(String strDate, int time, String fromPat, String toPat, Integer timePat) {
        if (StringUtils.isEmpty(strDate))
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(strDate, fromPat));
        cal.add(timePat, time);
        return formatDate(cal.getTime(), toPat);
    }

    /**
     * 加减时间
     * @param strDate 原始时间字符串
     * @param time 增加/减少的时间数
     * @param fromPat 原始时间的转换格式
     * @param timePat 加减的时间域:秒,分,时,日等,从Calendar中获得
     * @return
     */
    public static Date addTime(String strDate, int time, String fromPat, Integer timePat) {
        if (StringUtils.isEmpty(strDate))
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(strDate, fromPat));
        cal.add(timePat, time);
        return cal.getTime();
    }

    /**
     * 加减时间
     * @param time 增加/减少的时间数
     * @param timePat 加减的时间域:秒,分,时,日等,从Calendar中获得
     * @return
     */
    public static Date addTime(Date date, int time, Integer timePat) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(timePat, time);
        return cal.getTime();
    }

    /**
     * 加减时间
     * @param time 增加/减少的时间数
     * @param timePat 加减的时间域:秒,分,时,日等,从Calendar中获得
     * @return
     */
    public static String addTime(Date date, int time, String toPat, Integer timePat) {
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(timePat, time);
        return formatDate(cal.getTime(), toPat);
    }

    /**
     * 计算年龄
     * @param birthDay 生日
     * @return
     */

    public static Integer getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            return null;
        }

        if(birthDay == null){
            return null;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else if (monthNow < monthBirth) {
                age--;
            }
        }

        return age;
    }
    public static String getStartDate(String startDate){
        // String addTime = addTime(startDate, , PATTERN_yyyy_MM_dd, PATTERN_yyyy_MM_dd_HH_mm_ss, Calendar.DATE);
        return addTime(startDate, -1, PATTERN_yyyy_MM_dd, PATTERN_yyyy_MM_dd_HH_mm_ss, Calendar.SECOND);
    }
    public static String getEndDate(String endDate){
        return addTime(endDate, 86399, PATTERN_yyyy_MM_dd, PATTERN_yyyy_MM_dd_HH_mm_ss, Calendar.SECOND);
    }
    public static String getLastDate(String endDate){
        String addTime = addTime(endDate, 1, PATTERN_yyyy_MM_dd, PATTERN_yyyy_MM_dd_HH_mm_ss, Calendar.DATE);
        return addTime(addTime, -1, PATTERN_yyyy_MM_dd, PATTERN_yyyy_MM_dd_HH_mm_ss, Calendar.SECOND);
    }
}
