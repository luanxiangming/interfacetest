import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Oliver on 28/06/2017.
 */
public class Calendars {
    public static void Calendar_set() {
        Calendar c = Calendar.getInstance(); //默认是当前日期
        System.out.println("Calendar.getInstance: " + c);

        c.set(2009, 6 - 1, 12);
        System.out.println("Calendar.set(2009, 5, 12): " + c);

        c.set(Calendar.DATE, 10);
        System.out.println("Calendar.set(Calendar.DATE, 10): " + c.get(Calendar.DATE));

        c.set(Calendar.YEAR, 2008);
        System.out.println("Calendar.set(Calendar.YEAR, 2008): " + c.get(Calendar.YEAR));
    }

    public static void Calendar_add() {
        Calendar c = Calendar.getInstance();

        c.add(Calendar.YEAR, 10);
        System.out.println("Calendar.add(Calendar.YEAR, 10): " + c.get(Calendar.YEAR));

        c.add(Calendar.DATE, -10);
        System.out.println("Calendar.add(Calendar.DATE, -10): " + c.get(Calendar.DATE));
    }

    public static void Gregorian_calendar() {
        String months[] = {
                "Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"};

        int year;
        // 初始化 Gregorian 日历
        // 使用当前时间和日期
        // 默认为本地时间和时区
        GregorianCalendar gcalendar = new GregorianCalendar();
        // 显示当前时间和日期的信息
        System.out.print("Date: ");
        System.out.print(months[gcalendar.get(Calendar.MONTH)]);
        System.out.print(" " + gcalendar.get(Calendar.DATE) + " ");
        System.out.println(year = gcalendar.get(Calendar.YEAR));
        System.out.print("Time: ");
        System.out.print(gcalendar.get(Calendar.HOUR) + ":");
        System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
        System.out.println(gcalendar.get(Calendar.SECOND));

        // 测试当前年份是否为闰年
        if (gcalendar.isLeapYear(year)) {
            System.out.println("当前年份是闰年");
        } else {
            System.out.println("当前年份不是闰年");
        }
    }

    public static void main(String[] args) {
        Calendar_set();
        Calendar_add();
        Gregorian_calendar();
    }

}
