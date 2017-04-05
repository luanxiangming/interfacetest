package com.vipabc.interfacetest.utils;


import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddhhmmssSSS";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String HH_mm = "HH:mm";

    public static final String YYYY_NIAN_MM_YUE_DD_RI_HH_MM = "yyyy年MM月dd日 HH:mm";
    public static final String YYYY_NIAN_MM_YUE_DD_RI = "yyyy年MM月dd日";

    public static long getDateDiffByDay(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }

    public static int getDateDiffByDay(Date time1, Date time2) {
        long quot = 0;
        quot = time1.getTime() - time2.getTime();
        quot = quot / 1000 / 60 / 60 / 24;
        return (int) quot;
    }

    public static Date getDateDiffNDay(String strDate, int n) {
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date dDate = ft.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String sDate, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        if (StringUtils.isNotEmpty(sDate)) {
            try {
                return format.parse(sDate);
            } catch (ParseException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
                return null;
            }
        }
        return null;
    }

    public static Date parseCnDate(String sDate) {
        return parseDate(sDate, YYYY_NIAN_MM_YUE_DD_RI);
    }

    public static Date parseDate(String sDate) {
        return parseDate(sDate, YYYY_MM_DD);
    }

    public static String formatDate(Date sDate) {
        return formatDate(sDate, YYYY_MM_DD);
    }

    public static String formatCnDate(Date sDate) {
        return formatDate(sDate, YYYY_NIAN_MM_YUE_DD_RI);
    }

    public static String formatDate(Date sDate, String formatStr) {
        if (sDate == null)
            return "";
        DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(sDate);
    }

    public static String defaultFormatDate(Date sDate) {
        return formatDate(sDate, YYYY_MM_DD_HH_MM_SS);
    }

    public static boolean isValidDate(String dateStr, String pattern) {
        return formatDate(parseDate(dateStr, pattern), pattern).equals(dateStr);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addDays(String date, int amount) {
        return add(parseDate(date), Calendar.DAY_OF_MONTH, amount);
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static String getStringCurrentDateTime() {
        Calendar rightNow = Calendar.getInstance();
        int intYear = rightNow.get(Calendar.YEAR);

        int intMonth = rightNow.get(Calendar.MONTH) + 1;
        String strMonth = null;
        if (intMonth < 10) {
            strMonth = "0" + intMonth;
        } else {
            strMonth = "" + intMonth;
        }

        int intDate = rightNow.get(Calendar.DATE);
        String strDate = null;
        if (intDate < 10) {
            strDate = "0" + intDate;
        } else {
            strDate = "" + intDate;
        }

        int intHour = rightNow.get(Calendar.HOUR_OF_DAY);
        String strHour = null;
        if (intHour < 10) {
            strHour = "0" + intHour;
        } else {
            strHour = "" + intHour;
        }

        int intMinute = rightNow.get(Calendar.MINUTE);
        String strMinute = null;
        if (intMinute < 10) {
            strMinute = "0" + intMinute;
        } else {
            strMinute = "" + intMinute;
        }

        int intSecond = rightNow.get(Calendar.SECOND);
        String strSecond = null;
        if (intSecond < 10) {
            strSecond = "0" + intSecond;
        } else {
            strSecond = "" + intSecond;
        }

        return intYear + "-" + strMonth + "-" + strDate + " " + strHour + ":" + strMinute + ":" + strSecond;

    }

    /**
     * 用来转换日期格式的公用方法
     *
     * @param dateString       需要转换的时间
     * @param sourceDateFormat 原时间格式
     * @param destDateFormat   目的时间格式
     * @return 转换后的时间
     * @throws ParseException 日期格式错误时抛出的异常
     */
    public static String convertDateFormat(String dateString, String sourceDateFormat, String destDateFormat)
            throws ParseException {
        DateFormat dfOne = new SimpleDateFormat(sourceDateFormat);
        DateFormat dfTwo = new SimpleDateFormat(destDateFormat);
        Date date = dfOne.parse(dateString);
        return dfTwo.format(date);
    }

    public static String getthreeMothDate() {
        Calendar time = Calendar.getInstance();
        time.add(Calendar.MONTH, 2);
        int dayNum = time.getActualMaximum(Calendar.DAY_OF_MONTH);
        time.set(Calendar.DATE, dayNum);
        Date date = time.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(date);
        return format;
    }

    public static String getDateToString() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getDateToString_simple() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    public static String getNextDateToString() {
        Date date = new Date(new Date().getTime() + 1000 * 3600 * 24);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date getDateNotTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getNowDateWithOutTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static boolean isBeforeEquals(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Date d1 = DateUtils.parseDate(DateUtils.formatDate(date1));
        Date d2 = DateUtils.parseDate(DateUtils.formatDate(date2));
        return !d1.after(d2);
    }

    public static boolean isAfterEquals(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Date d1 = DateUtils.parseDate(DateUtils.formatDate(date1));
        Date d2 = DateUtils.parseDate(DateUtils.formatDate(date2));
        return !d1.before(d2);
    }

    public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        System.out.println(getDateToString_simple());

//		System.out.println(sdf.format(new Timestamp(System.currentTimeMillis())));
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR);
    }

    /**
     * 生成小班课订课时间
     *
     * @param day 当前时间延后的天数
     * @return
     */
    public static String getReservationTime(int day) {
        return DateUtils.defaultFormatDate(DateUtils.addDays(new Date(), day)).substring(0, 14) + "30:00";
    }

    /**
     * 预定在30分钟档的课程时间
     */
    public static String getReservationTimeIn30Min(int intervalHours) {
        Calendar calendar = Calendar.getInstance();
        int currentMin = Integer.valueOf(new SimpleDateFormat("mm").format(new Date()));

        if (intervalHours < 24) {
            intervalHours = 24; // 预约需一天后，不满一天，按一天处理
            if (currentMin >= 30) { //超过30分，小时需加1
                intervalHours++;
            }
        } else if (currentMin >= 30) {
            intervalHours++;     //超过30分，小时需加1
        }
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + intervalHours);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 00);
        Date date = calendar.getTime();
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }
}
