package examples;

import java.util.Locale;

/**
 * Created by Oliver on 05/07/2017.
 */
public class StringExample {
    public static void main(String[] args) {
        /* 删除字符串中的一个字符 */
        System.out.println(removeCharAt("Google", 2));

        /* 字符串替换 */
        replace();

        /* 字符串反转 */
        System.out.println(reverse("Java"));

        /* 字符串搜索 */
        System.out.println(search("Java", "v"));

        /* 字符串分割 */
        split("1,2,3", ",");

        /* 字符串大小写转换 */
        upper_lower("Java", 1);
        upper_lower("Java", 2);

        /* 测试两个字符串区域是否相等 */
        regionMatches();

        /* 字符串性能比较测试 */
        comparePerformance();

        /* 字符串格式化 */
        format();

    }


    public static String removeCharAt(String s, Integer index) {
        return s.substring(0, index) + s.substring(index + 1);
    }

    public static void replace() {
        String str = "Hello World";
        System.out.println(str.replace('H', 'W'));
        System.out.println(str.replaceFirst("He", "Wa"));
        System.out.println(str.replaceAll("He", "Ha"));
    }

    public static String reverse(String s) {
        StringBuffer stringBuffer = new StringBuffer(s);
        String reversed = stringBuffer.reverse().toString();
        return reversed;
    }

    public static Integer search(String s, String match) {
        return s.indexOf(match);
    }

    public static void split(String s, String delimiter, Integer... limit) {
        for (String string : s.split(delimiter, limit[0])) {
            System.out.println(string);
        }
    }

    public static void split(String s, String delimiter) {
        split(s, delimiter, 0);
    }

    public static void upper_lower(String s, int uplow) {
        String changed = "";
        switch (uplow) {
            case 1:
                changed = s.toUpperCase();
                break;
            case 2:
                changed = s.toLowerCase();
                break;
        }
        System.out.println(changed);
    }

    public static void regionMatches() {
        String s1 = "Java is best";
        String s2 = "Best is java";
        boolean matches1 = s1.regionMatches(0, "Java", 0, 4);
        boolean matches2 = s2.regionMatches(true, 8, "Java", 0, 4);
        System.out.println(matches1);
        System.out.println(matches2);
    }

    public static void comparePerformance() {
        long st1 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            String s1 = "java";
        }
        long et1 = System.currentTimeMillis();
        System.out.println("Duration1: " + (et1 - st1));

        long st2 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            String s2 = new String("java");
        }
        long et2 = System.currentTimeMillis();
        System.out.println("Duration2: " + (et2 - st2));
    }

    public static void format() {
        System.out.format("Math.E: %f%n", Math.E);
        System.out.format(Locale.CHINA, "Math.E: %.4f%n", Math.E);  //指定本地为中国（CHINA）
    }

}
