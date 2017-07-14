import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Oliver on 27/06/2017.
 */
public class Dates {
    public static void Date_date() {
        Date date = new Date();
        System.out.println("date: " + date);
    }

    public static void Date_getTime() {
        Date date = new Date();
        System.out.println("date.getTime: " + date.getTime());
    }

    public static void Date_SimpleDateFormat() throws ParseException {
        /*
        G	纪元标记	AD
        y	四位年份	2001
        M	月份	July or 07
        d	一个月的日期	10
        h	 A.M./P.M. (1~12)格式小时	12
        H	一天中的小时 (0~23)	22
        m	分钟数	30
        s	秒数	55
        S	毫秒数	234
        E	星期几	Tuesday
        D	一年中的日子	360
        F	一个月中第几周的周几	2 (second Wed. in July)
        w	一年中第几周	40
        W	一个月中第几周	1
        a	A.M./P.M. 标记	PM
        k	一天中的小时(1~24)	24
        K	 A.M./P.M. (0~11)格式小时	10
        z	时区	Eastern Standard Time
        '	文字定界符	Delimiter
        "	单引号	`
        */
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("SimpleDateFormat.format(): " + ft.format(date));

        SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date t = ft2.parse("1983-07-14");
            System.out.println(t);
        } catch (ParseException e) {
            System.out.println("Unparsable using " + ft2);
        }
    }

    public static void Date_printf() {
        /*
        c  包括全部日期和时间信息  星期六 十月 27 14:21:20 CST 2007
        F  "年-月-日"格式  2007-10-27
        D  "月/日/年"格式  10/27/07
        r  "HH:MM:SS PM"格式（12时制）  02:25:51 下午
        T  "HH:MM:SS"格式（24时制）   14:28:16
        R  "HH:MM"格式（24时制）  14:28
        */
        Date date = new Date();
        System.out.printf("全部日期和时间信息: %tc%n", date);
        System.out.printf("\"年-月-日\"格式: %tF%n", date);
        System.out.printf("\"月/日/年\"格式: %tD%n", date);
        System.out.printf("\"HH:MM:SS PM\"格式（12时制): %tr%n", date);
        System.out.printf("\"HH:MM:SS\"格式（24时制）: %tT%n", date);
        System.out.printf("\"HH:MM\"格式（24时制）: %tR%n", date);

        // 用一个格式化字符串指出要被格式化的参数的索引, 索引必须紧跟在%后面，而且必须以$结束
        System.out.printf("%1$s %2$tB %2$td, %2$tY%n", "Due date:", date);
        // 使用 < 标志, 它表明先前被格式化的参数要被再次使用
        System.out.printf("%s %tB %<te, %<tY%n", "Due date:", date);
    }

    public static void Date_stringFormat() {
        Date date = new Date();
        //b的使用，月份简称
        String str = String.format(Locale.US, "英文月份简称b：%tb", date);
        System.out.println(str);
        System.out.printf("本地月份简称b：%tb%n", date);
        //B的使用，月份全称
        str = String.format(Locale.US, "英文月份全称B：%tB", date);
        System.out.println(str);
        System.out.printf("本地月份全称B：%tB%n", date);
        //a的使用，星期简称
        str = String.format(Locale.US, "英文星期的简称a：%ta", date);
        System.out.println(str);
        //A的使用，星期全称
        System.out.printf("本地星期的全称A：%tA%n", date);
        //C的使用，年前两位
        System.out.printf("年的前两位数字（不足两位前面补0）C：%tC%n", date);
        //y的使用，年后两位
        System.out.printf("年的后两位数字（不足两位前面补0）y：%ty%n", date);
        //j的使用，一年的天数
        System.out.printf("一年中的天数（即年的第几天）j：%tj%n", date);
        //m的使用，月份
        System.out.printf("两位数字的月份（不足两位前面补0）m：%tm%n", date);
        //d的使用，日（二位，不够补零）
        System.out.printf("两位数字的日（不足两位前面补0）d：%td%n", date);
        //e的使用，日（一位不补零）
        System.out.printf("月份的日（前面不补0）e：%te%n", date);
    }

    public static void Thread_sleep() throws InterruptedException {
        System.out.println("Current: " + new Date());
        Thread.sleep(3000);
        System.out.println("Thread.sleep(3000): " + new Date());

    }

    public static void System_currentTimeMillis() throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println(new Date());
        Thread.sleep(3000);
        System.out.println(new Date());
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Duration: " + duration);
    }

    public static void main(String[] args) throws ParseException, InterruptedException {
        Date_date();
        Date_getTime();
        Date_SimpleDateFormat();
        Date_printf();
        Date_stringFormat();
        Thread_sleep();
        System_currentTimeMillis();
    }
}
