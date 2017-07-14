/**
 * Created by Oliver on 25/06/2017.
 */
public class Operators {

    public static void test_math() {
        int a = 10;
        int b = 20;
        int c = 25;
        int d = 25;
        System.out.println("*****算术运算*****");
        System.out.println("10 + 20 = " + (a + b));
        System.out.println("10 - 20 = " + (a - b));
        System.out.println("10 * 20 = " + (a * b));
        System.out.println("20 / 10 = " + (b / a));
        System.out.println("20 % 10 = " + (b % a));
        System.out.println("25 % 10 = " + (c % a));
        System.out.println("10++   = " + (a++));
        System.out.println("10--   = " + (a--));
        // 查看  d++ 与 ++d 的不同
        System.out.println("25++   = " + (d++));
        System.out.println("++25   = " + (++d));
    }

    public static void test_relation() {
        int a = 10;
        int b = 20;
        System.out.println("*****关系运算*****");
        System.out.println("10 == 20 = " + (a == b));
        System.out.println("10 != 20 = " + (a != b));
        System.out.println("10 > 20 = " + (a > b));
        System.out.println("10 < 20 = " + (a < b));
        System.out.println("20 >= 10 = " + (b >= a));
        System.out.println("20 <= 10 = " + (b <= a));
    }

    public static void test_bitwise() {
        System.out.println("*****位运算*****");
        int a = 60; /* 60 = 0011 1100 */
        int b = 13; /* 13 = 0000 1101 */
        int c;
        c = a & b;       /* 12 = 0000 1100 */
        System.out.println("60 & 13 = " + c);

        c = a | b;       /* 61 = 0011 1101 */
        System.out.println("60 | 13 = " + c);

        c = a ^ b;       /* 49 = 0011 0001 */
        System.out.println("60 ^ 13 = " + c);

        c = ~a;          /*-61 = 1100 0011 */
        System.out.println("~60 = " + c);

        c = a << 2;     /* 240 = 1111 0000 */
        System.out.println("a << 2 = " + c);

        c = a >> 2;     /* 15 = 1111 */
        System.out.println("a >> 2  = " + c);

        c = a >>> 2;     /* 15 = 0000 1111 */
        System.out.println("a >>> 2 = " + c);
    }

    public static void test_logic() {
        System.out.println("*****逻辑运算*****");
        boolean a = true;
        boolean b = false;
        System.out.println("true && false: " + (a && b));
        System.out.println("true || false: " + (a || b));
        System.out.println("!(true && false): " + !(a && b));
    }

    public static void test_assign() {
        System.out.println("*****赋值运算*****");
        int a = 10;
        int b = 20;
        int c = 0;
        c = a + b;
        System.out.println("c = a + b = " + c);
        c += a;
        System.out.println("c += a  = " + c);
        c -= a;
        System.out.println("c -= a = " + c);
        c *= a;
        System.out.println("c *= a = " + c);
        a = 10;
        c = 15;
        c /= a;
        System.out.println("c /= a = " + c);
        a = 10;
        c = 15;
        c %= a;
        System.out.println("c %= a  = " + c);
        // 左移位赋值运算符, 相当与乘以2的2次方
        c <<= 2;
        System.out.println("c <<= 2 = " + c);
        // 右移位赋值运算符, 相当与除以2的2次方
        c >>= 2;
        System.out.println("c >>= 2 = " + c);
        c >>= 2;
        System.out.println("c >>= a = " + c);
        c &= a;
        System.out.println("c &= 2  = " + c);
        c ^= a;
        System.out.println("c ^= a   = " + c);
        c |= a;
        System.out.println("c |= a   = " + c);
    }

    public static void test_condition() {
        System.out.println("*****条件运算*****");
        int a = 10;
        int b = 0;
        b = (a >= 10) ? 20 : 10;
        System.out.println(b);
    }

    public static void test_instanceof() {
    /* 该运算符用于操作对象实例，检查该对象是否是一个特定类型（类类型或接口类型）*/
        System.out.println("*****instanceof运算*****");
        String name = "James";
        System.out.println(name instanceof String);
    }

    public static void main(String args[]) {
        test_math();
        test_relation();
        test_bitwise();
        test_logic();
        test_assign();
        test_condition();
        test_instanceof();
    }
}


