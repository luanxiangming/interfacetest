package java8;

/**
 * Created by Oliver on 13/07/2017.
 */
public class DefaultMethod implements Bus, Jet {
    /**
     * 默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
     * 我们只需在方法名前面加个default关键字即可实现默认方法
     */
    public static void main(String[] args) {
        DefaultMethod defaultMethod = new DefaultMethod();
        defaultMethod.printSelf();
        Bus.blowHorn();
    }

    //使用 super 来调用指定接口的默认方法：
    public void printSelf() {
        Bus.super.printSelf();
    }
}

interface Bus {
    default void printSelf() {
        System.out.println("I'm bus");
    }

    /**
     * Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法
     */
    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}

interface Jet {
    default void printSelf() {
        System.out.println("I'm jet");
    }
}