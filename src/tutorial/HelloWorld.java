/**
 * Created by Oliver on 25/06/2017.
 */
public class HelloWorld {
    /* 这是第一个Java程序
      它将打印Hello World
      这是一个多行注释的示例
    */
    public static void main(String[] args) {

        System.out.println("Hello World.");
        FreshJuice juice = new FreshJuice("OrangeJuice");
        juice.size = FreshJuice.FreshJuiceSize.MEDIUM;

    }

}

class FreshJuice {
    // 注意：枚举可以单独声明或者声明在类里面。方法、变量、构造函数也可以在枚举中定义
    enum FreshJuiceSize {
        SMALL, MEDIUM, LARGE
    }

    FreshJuiceSize size;

    // 显式地为类定义构造方法
    public FreshJuice(String name) {
        System.out.println("The juice name is: " + name);
    }
}

