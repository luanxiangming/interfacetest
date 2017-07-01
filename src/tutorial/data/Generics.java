package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 01/07/2017.
 */
public class Generics {
    public static void main(String[] args) {
        Integer[] intArray = {2, 4, 6};
        String[] strArray = {"Python", "Java", "Go"};
        Character[] charArray = {'a', 'b', 'c'};
        System.out.println("整型数组元素为:");
        printArray(intArray);
        System.out.println("\n双精度型数组元素为:");
        printArray(strArray);
        System.out.println("\n字符型数组元素为:");
        printArray(charArray);

        System.out.printf("Max int: %d%n", maximum(1, 2, 3));
        System.out.printf("Max str: %s%n", maximum("apple", "banana", "pineapple"));
        System.out.printf("Max double: %f%n", maximum(0.12, 2.12, 10.3));

        Box<Integer> integerBox = new Box<Integer>();
        Box<String> stringBox = new Box<String>();
        Box<Double> doulbeBox = new Box<Double>();
        integerBox.setT(10);
        stringBox.setT("Python");
        doulbeBox.setT(10.10);

        System.out.printf("Box<Integer>: %d%n", integerBox.getT());
        System.out.printf("Box<String>: %s%n", stringBox.getT());
        System.out.printf("Box<Double>: %f%n", doulbeBox.getT());

        List<Integer> integerArrayList = new ArrayList<>();
        List<String> stringArrayList = new ArrayList<>();
        List<Double> doubleArrayList = new ArrayList<>();
        integerArrayList.add(7);
        stringArrayList.add("Python");
        doubleArrayList.add(7.7);
        getData(integerArrayList);  //类型通配符List<?>，在逻辑上是List<String>,List<Integer> 等所有List<具体类型实参>的父类
        getData(stringArrayList);
        getData(doubleArrayList);

        getNumber(integerArrayList);
        // getNumber(stringArrayList); // 参数泛型上限为Number，所以泛型为String是不在这个范围之内，所以会报错
        getNumber(doubleArrayList);


    }

    /**
     * 泛型方法 printArray
     */
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.println(element);
        }
    }

    /**
     * 有界的类型参数
     * 可能有时候，你会想限制那些被允许传递到一个类型参数的类型种类范围。
     * 例如，一个操作数字的方法可能只希望接受Number或者Number子类的实例。这就是有界类型参数的目的。
     * 要声明一个有界的类型参数，首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界
     * 该例子中的泛型方法返回三个可比较对象的最大值
     */
    public static <T extends Comparable<T>> T maximum(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0) {
            max = b;
        }
        if (c.compareTo(max) > 0) {
            max = c;
        }
        return max;
    }

    /**
     * 类型通配符
     * 因为getDate()方法的参数是List类型的，所以integerArrayList，stringArrayList，doubleArrayList都可以作为这个方法的实参，这就是通配符的作用
     */
    public static void getData(List<?> data) {
        System.out.printf("Data Class: %s, Data Value: %s%n", data.get(0).getClass(), data.get(0));
    }

    /**
     * 类型通配符上限通过形如List来定义，如此定义就是通配符泛型值接受Number及其下层子类类型
     * <? extends T>表示该通配符所代表的类型是T类型的子类。
     * <? super T>表示该通配符所代表的类型是T类型的父类。
     */
    public static void getNumber(List<? extends Number> number) {
        System.out.printf("Number Class: %s, Number Value: %s%n", number.get(0).getClass(), number.get(0));
    }
}

/**
 * 定义一个泛型类
 * 泛型类的声明和非泛型类的声明类似，除了在类名后面添加了类型参数声明部分。
 * 和泛型方法一样，泛型类的类型参数声明部分也包含一个或多个类型参数，参数间用逗号隔开。
 * 一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符。
 * 因为他们接受一个或多个参数，这些类被称为参数化的类或参数化的类型。
 */
class Box<T> {
    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}

