import java.util.Random;

/**
 * Created by Oliver on 25/06/2017.
 */
public class Numbers {
    public static void test_numbers() {
        // 当 x 被赋为整型值时，由于x是一个对象，所以编译器要对x进行装箱。然后，为了使x能进行加运算，所以要对x进行拆箱
        System.out.println("*****number*****");
        Integer x = 5;
        x++;
        System.out.println(x);

    }

    public static void test_valueOf() {
        // valueOf() 方法用于返回给定参数的原生 Number 对象值，参数可以是原生数据类型, String等
        System.out.println("*****valueOf(装箱)*****");
        Integer x = Integer.valueOf(9);
        Double c = Double.valueOf(5);
        Float f = Float.valueOf("80");
        Integer b = Integer.valueOf("444", 16);   // 使用 16 进制
        System.out.println(x);
        System.out.println(c);
        System.out.println(f);
        System.out.println(b);
    }

    public static void test_xxxValue() {
        // xxxValue() 方法用于将 Number 对象转换为 xxx 数据类型的值并返回
        System.out.println("*****xxxValue(拆箱)*****");
        Integer x = 5;
        // 返回 byte 原生数据类型
        System.out.println("byteValue: " + x.byteValue());

        // 返回 double 原生数据类型
        System.out.println("doubleValue: " + x.doubleValue());

        // 返回 long 原生数据类型
        System.out.println("longValue: " + x.longValue());
    }

    public static void test_compareTo() {
        System.out.println("*****compareTo*****");
        Integer x = 5;
        System.out.println("5 compared to 3: " + x.compareTo(3));
        System.out.println("5 compared to 8: " + x.compareTo(8));
        System.out.println("5 compared to 5: " + x.compareTo(5));
    }

    public static void test_equals() {
        /**
         注意 == 与 equals的区别
         == 它比较的是对象的地址
         equals 比较的是对象的内容
         */
        System.out.println("*****equals*****");
        Integer x = 5;
        Integer y = 10;
        Integer z = 5;
        short s = 5;
        System.out.println("Integer 5 equals Integer 10: " + x.equals(y));
        System.out.println("Integer 5 equals Integer 5: " + x.equals(z));
        System.out.println("Integer 5 equals short 5: " + x.equals(s));
    }

    public static void test_toString() {
        System.out.println("*****toString*****");
        Integer x = 5;
        System.out.println("5 toString: " + x.toString());
        System.out.println("Integer.toString(8): " + Integer.toString(8));

    }

    public static void test_parseInt() {
        // parseInt() 方法用于将字符串参数作为有符号的十进制整数进行解析。
        System.out.println("*****parseInt*****");
        System.out.println("Integer.parseInt('19'): " + Integer.parseInt("19"));
        System.out.println("Double.parseDouble('29'): " + Double.parseDouble("29"));
        System.out.println("Integer.parseInt('444', 16): " + Integer.parseInt("444", 16));
    }

    public static void test_math() {
        // Math 的方法都被定义为 static 形式，通过 Math 类可以在主函数中直接调用
        System.out.println("*****math*****");
        System.out.println("90 度的正弦值：" + Math.sin(Math.PI / 2));
        System.out.println("0度的余弦值：" + Math.cos(0));
        System.out.println("60度的正切值：" + Math.tan(Math.PI / 3));
        System.out.println("1的反正切值： " + Math.atan(1));
        System.out.println("π/2的角度值：" + Math.toDegrees(Math.PI / 2));
        System.out.println(Math.PI);
    }

    public static void test_abs() {
        System.out.println("*****abs*****");
        Integer x = -4;
        Float y = -1f;
        Double z = -2d;
        System.out.println("Math.abs(-4): " + Math.abs(x));
        System.out.println("Math.abs(-1f): " + Math.abs(y));
        System.out.println("Math.abs(-2d): " + Math.abs(z));
    }

    public static void test_ceil_floor_rint() {
        System.out.println("*****ceil*****");
        System.out.println("Math.ceil(100.675): " + Math.ceil(100.675));
        System.out.println("Math.ceil(-90.1): " + Math.ceil(-90.1));
        System.out.println("Math.floor(100.675)" + Math.floor(100.675));
        System.out.println("Math.floor(-90.1)" + Math.floor(-90.1));
        // 返回与参数最接近的整数。返回类型为double
        System.out.println("Math.rint(100.675): " + Math.rint(100.675));
        System.out.println("Math.rint(100.5): " + Math.rint(100.5));
        System.out.println("Math.rint(100.200): " + Math.rint(100.200));
        // 返回一个最接近的int、long型值
        System.out.println("Math.round(100.675): " + Math.round(100.675));
        System.out.println("Math.round(100.5): " + Math.round(100.5));
        System.out.println("Math.round(100.200): " + Math.round(100.200));

        System.out.println("Math.min(12.123, 12.456): " + Math.min(12.123, 12.456));
        System.out.println("Math.min(11, 12): " + Math.min(11, 12));
        System.out.println("Math.max(12.123, 12.456): " + Math.max(12.123, 12.456));
        System.out.println("Math.max(11, 12): " + Math.max(11, 12));

        double x = 11.635;
        double y = 2.76;
        System.out.printf("Math.E: %.4f%n", Math.E);
        System.out.printf("Math.pow(%.3f, %.3f): %.3f%n", x, y, Math.pow(x, y));

        System.out.printf("Math.random(): %.3f%n", Math.random());
        Random random = new Random();
        System.out.println("Random 0~99: " + random.nextInt(100));
    }

    public static void main(String[] args) {
        test_numbers();
        test_math();
        test_xxxValue();
        test_compareTo();
        test_equals();
        test_valueOf();
        test_toString();
        test_parseInt();
        test_abs();
        test_ceil_floor_rint();
    }

}
