/**
 * Created by Oliver on 26/06/2017.
 */
public class Strings {
    public static void String_length() {
        String site = "www.google.com";
        System.out.printf("Length of site is %d%n", site.length());
    }

    public static void String_concat() {
        String a = "Hello, ";
        System.out.println(a.concat("Java"));
    }

    public static void String_format() {
        String s = String.format("浮点型变量的的值为 " +
                "%f, 整型变量的值为 " +
                "%d, 字符串变量的值为 " +
                "%s", 1.23, 2, "Java");
        System.out.println(s);
    }

    public static void String_charAt() {
        System.out.println("'ABC'.charAt(1): " + "ABC".charAt(1));
    }

    public static void String_compareTo() {
        System.out.println("ABC compareTo ABC: " + "ABC".compareTo("ABC"));
        System.out.println("ABC compareTo ABC123: " + "ABC".compareTo("ABC123"));
        System.out.println("ABC123 compareTo ABC: " + "ABC123".compareTo("ABC"));
        System.out.println("ABC compareToIgnoreCase abc: " + "ABC".compareToIgnoreCase("abc"));
    }

    public static void String_contentEquals() {
        // 用于将将此字符串与指定的 StringBuffer 比较
        StringBuffer buffer = new StringBuffer("abc");
        System.out.println("ABC contentEquals StringBuffer(\"abc\"): " + "ABC".contentEquals(buffer));
        System.out.println("abc contentEquals StringBuffer(\"abc\"): " + "abc".contentEquals(buffer));
    }

    public static void String_copyValueOf() {
        char[] c = {'G', 'o', 'o', 'g', 'l', 'e'};
        String s = "";
        System.out.println("copyValueOf({'G', 'o', 'o', 'g', 'l', 'e'}): " + s.copyValueOf(c));
        System.out.println("copyValueOf({'G', 'o', 'o', 'g', 'l', 'e'}, 1, 3): " + s.copyValueOf(c, 1, 3));
        System.out.println("valueOf({'G', 'o', 'o', 'g', 'l', 'e'}): " + s.valueOf(c));
    }

    public static void String_endsWith() {
        System.out.println("Google endsWith e: " + "Google".endsWith("e"));
        System.out.println("Google endsWith G: " + "Google".endsWith("G"));
    }

    public static void String_equals() {
        System.out.println("Google equals Google: " + "Google".equals("Google"));
        System.out.println("Google equals google: " + "Google".equals("google"));
        System.out.println("Google equalsIgnoreCase google: " + "Google".equalsIgnoreCase("google"));
    }

    public static void String_getChars() {
        // 将字符从此字符串复制到目标字符数组
        String s = new String("Google");
        char[] c = new char[10];
        s.getChars(1, 4, c, 0);
        System.out.println(c);
    }

    public static void String_hashCode() {
        /*
        字符串对象的哈希码根据以下公式计算：
        s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
        使用 int 算法，这里 s[i] 是字符串的第 i 个字符，n 是字符串的长度，^ 表示求幂。空字符串的哈希值为 0
         */
        System.out.println("www.runoob.com hashCode: " + "www.runoob.com".hashCode());
    }

    public static void String_indexOf() {
        System.out.println("Google indexOf('o'): " + "Google".indexOf('o'));
        System.out.println("Google indexOf('g') fromIndex 2: " + "Google".indexOf('g', 2));
        System.out.println("Google indexOf('gle'): " + "Google".indexOf("gle"));
        System.out.println("Google indexOf('gle' fromIndex 2): " + "Google".indexOf("gle", 2));
    }

    public static void String_lastIndexOf() {
        System.out.println("Google lastIndexIf('o'): " + "Google".lastIndexOf('o'));
        System.out.println("Google lastIndexIf('oog'): " + "Google".lastIndexOf("oog"));
    }

    public static void String_matches() {
        System.out.println("Google matches('(.*)oog(.*)'): " + "Google".matches("(.*)oog(.*)"));
        /* 检测两个字符串在一个区域内是否相等
        ignoreCase -- 如果为 true，则比较字符时忽略大小写。
        toffset -- 此字符串中子区域的起始偏移量。
        other -- 字符串参数。
        ooffset -- 字符串参数中子区域的起始偏移量。
        len -- 要比较的字符数。
         */
        System.out.println("Google regionMatches(1, 'oog', 0, 3): " + "Google".regionMatches(1, "oog", 0, 3));
        System.out.println("Google regionMatches(ignoreCase, 1, 'oog', 0, 3): " + "Google".regionMatches(true, 1, "OOG", 0, 3));
    }

    public static void String_replace() {
        System.out.println("Google replace o to O: " + "Google".replace('o', 'O'));
        System.out.println("Google replaceAll to Apple: " + "Google".replaceAll("(.*)oog(.*)", "Apple"));
        System.out.println("Google failed to replaceAll to Apple: " + "Google".replaceAll("(,*)OOG(.*)", "Apple"));
        System.out.println("Google replaceFirst o to O: " + "Google".replaceFirst("o", "O"));
    }

    public static void String_split() {
        System.out.println("- 分隔符返回值 :");
        for (String name : "www-google-com".split("-")) {
            System.out.println(name);
        }
        System.out.println("- 分隔符设置分割份数返回值 :");
        for (String name : "www-google-com".split("-", 2)) {
            System.out.println(name);
        }
        System.out.println("转义字符返回值 :");
        for (String name : "www.google.com".split("\\.")) {
            System.out.println(name);
        }
        System.out.println("多个分隔符返回值 :");
        for (String name : "www.google-com".split("\\.|-")) {
            System.out.println(name);
        }
    }

    public static void String_startsWith() {
        System.out.println("www.google.com startsWith www: " + "www.google.com".startsWith("www"));
        System.out.println("www.google.com startsWith com: " + "www.google.com".startsWith("com"));
        System.out.println("www.google.com startsWith com offset 11: " + "www.google.com".startsWith("com", 11));
    }

    public static void String_subSequence() {
        System.out.println("www.google.com subSequence(4, 10): " + "www.google.com".subSequence(4, 10));
    }

    public static void String_subString() {
        System.out.println("www.google.com subString(4): " + "www.google.com".substring(4));
        System.out.println("www.google.com subString(4, 10): " + "www.google.com".substring(4, 10));
    }

    public static void String_toCharArray() {
        System.out.println("将此字符串转换为一个新的字符数组 :");
        String s = new String("google");
        for (char c : s.toCharArray()) {
            System.out.print(c);
        }
    }

    public static void String_toLowerCase() {
        System.out.println("AMD toLowerCase: " + "AMD".toLowerCase());
    }

    public static void String_toString() {
        System.out.println("hao123 toString: " + "hao123".toString());
    }

    public static void String_toUpperCase() {
        System.out.println("apple toUpperCase: " + "apple".toUpperCase());
    }

    public static void String_trim() {
        System.out.println("' google  ' trim: " + " google  ".trim());
    }

    public static void main(String[] args) {
        String_length();
        String_concat();
        String_format();
        String_charAt();
        String_compareTo();
        String_contentEquals();
        String_copyValueOf();
        String_endsWith();
        String_equals();
        String_getChars();
        String_hashCode();
        String_indexOf();
        String_lastIndexOf();
        String_matches();
        String_replace();
        String_split();
        String_startsWith();
        String_subSequence();
        String_subString();
        String_toCharArray();
        String_toLowerCase();
        String_toString();
        String_toUpperCase();
        String_trim();
    }
}
