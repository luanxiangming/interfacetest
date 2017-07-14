package examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Oliver on 06/07/2017.
 */
public class DateExample {
    public static void main(String[] args) {
        /* 格式化时间 */
        /* 时间戳转换成时间 */
        SimpleDateFormat();

        /* 获取年份、月份等 */
        Calendar();
    }

    public static void SimpleDateFormat() {
        Date date = new Date();
        Long timestamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a zzz");
        System.out.println(simpleDateFormat.format(date));

        // 时间戳转换成时间
        String sd = simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(timestamp))));
        System.out.println(sd);
    }

    public static void Calendar() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar.YEAR: " + calendar.get(Calendar.YEAR));
        System.out.println("calendar.MONTH: " + calendar.get(Calendar.MONTH));
        System.out.println("calendar.WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("calendar.WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
        System.out.println("calendar.DATE: " + calendar.get(Calendar.DATE));
        System.out.println("calendar.DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println("calendar.DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("calendar.DAY_OF_WEEK_IN_MONTH: " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("calendar.DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
    }


}
