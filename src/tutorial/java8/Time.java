package java8;

import java.time.*;

/**
 * Created by Oliver on 14/07/2017.
 */
public class Time {
    /**
     * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
     * Local(本地) − 简化了日期时间的处理，没有时区的问题。
     * Zoned(时区) − 通过制定的时区处理日期时间。
     * <p>
     * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
     */
    public static void main(String[] args) {

        /* 本地化日期时间 API */
        localDateTime();

        /* 使用时区的日期时间API */
        zonedDateTime();
    }

    public static void localDateTime() {
        // LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDataTime.now: " + localDateTime);
        System.out.println("Month: " + localDateTime.getMonth());
        System.out.println("Day: " + localDateTime.getDayOfMonth());
        System.out.println("Minute: " + localDateTime.getMinute());
        System.out.println("Seconds: " + localDateTime.getSecond());
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(15).withYear(2018);
        System.out.println("LocalDateTime1: " + localDateTime1);

        // LocalDate
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println("LocalDate: " + localDate);
        LocalDate localDate1 = LocalDate.of(2019, 8, 15);
        System.out.println("LocalDate1: " + localDate1);

        // LocalTime
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime.now: " + localTime);
        LocalTime localTime1 = LocalTime.of(12, 15);
        System.out.println("LocalTime1: " + localTime1);
        LocalTime localTime2 = LocalTime.parse("13:15:30");
        System.out.println("Parse local time hour: " + localTime2.getHour());
    }

    public static void zonedDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime: " + zonedDateTime);

        ZonedDateTime zonedDateTime1 = ZonedDateTime.parse("2018-08-15T13:24:46.506+08:00[Asia/Shanghai]");
        System.out.println("Parse zoned dat time zone: " + zonedDateTime1.getZone());

        ZoneId zoneId = ZoneId.of("Europe/Paris");
        System.out.println("ZonedId: " + zoneId);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("ZonedId.systemDefault: " + currentZone);
    }
}
