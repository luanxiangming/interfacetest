package java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Oliver on 12/07/2017.
 */
public class Lambda {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Google");
        names.add("Bing");
        names.add("JD");

        sort_java7(names);
        sort_java8(names);

        tryLambda();
    }

    public static void sort_java7(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list);
    }

    // Lambda允许把函数作为一个方法的参数
    public static void sort_java8(List<String> list) {
        Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
//        Collections.sort(list, String::compareTo);
        System.out.println(list);
    }

    /**
     * Lambda 表达式主要用来定义行内执行的方法类型接口. 例如，一个简单方法接口。
     * 在下面例子中，我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。
     * 然后我们定义了sayMessage的执行。
     */
    public static void tryLambda() {
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtract = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiply = (a, b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation divide = (a, b) -> a / b;

        System.out.println("10 + 20 = " + operate(10, 20, addition));
        System.out.println("20 - 10 = " + operate(20, 10, subtract));
        System.out.println("10 * 20 = " + operate(10, 20, multiply));
        System.out.println("20 / 10 = " + operate(20, 10, divide));

        GreetingService greetingService1 = message -> System.out.println("Hi, " + message);  // 不用括号
        GreetingService greetingService2 = (message) -> System.out.println("Greetings, " + message);  // 用括号
        greetingService1.sayMessage("Google");
        greetingService2.sayMessage("Apple");

    }

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }


}
