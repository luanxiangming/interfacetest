/**
 * Created by Oliver on 25/06/2017.
 */
public class StaticTest {
    // 类变量赋值: 无final修饰，声明时赋值，构造器中赋值，静态语句块或静态方法赋值
    private static int staticInt = 2;
    private int random = 2;

    public StaticTest() {
        staticInt++;
        random++;
    }

    public static void main(String[] args) {
        System.out.println("类变量与对象变量的值变化");
        StaticTest test = new StaticTest();
        System.out.println("  实例1：staticInt:" + test.staticInt + "----random:" + test.random);
        StaticTest test2 = new StaticTest();
        System.out.println("  实例2：staticInt:" + test2.staticInt + "----random:" + test.random);

        System.out.println("静态变量赋值");
        System.out.println("  静态语句块起作用:" + A.staticA);
        A a = new A();
        System.out.println("  构造器起作用:" + a.staticA);
        a.toChange();
        System.out.println("  静态方法1起作用:" + A.staticA);
        a.toChange2();
        System.out.println("  静态方法2起作用:" + A.staticA);

        System.out.println("常量赋值");
        System.out.println("  静态语句赋值:" + B.staticB);
    }
}

class A {
    public static String staticA = "A";

    //静态语句块修改值
    static {
        staticA = "A1";
    }

    //构造器修改值
    public A() {
        staticA = "A2";
    }
    //静态方法起作用

    public static void toChange() {
        staticA = "A3";
    }

    public static void toChange2() {
        staticA = "A4";
    }
}

class B {
    public static final String staticB;  // 声明与赋值分离

    static {
        staticB = "B";
    }
}
