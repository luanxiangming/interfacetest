package examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 08/07/2017.
 */
public class MethodExample {
    public static void main(String[] args) {
        /* 斐波那契数列 */
        System.out.println("fibonacci: " + fibonacci(2));

        /* 阶乘 */
        System.out.println("factorial: " + factorial(5));

        /* instanceOf 关键字用法 */
        instanceOf(3.14f);
        instanceOf("String");

        /* continue 关键字用法 */
        continues();

        /* enum 和 switch 语句使用 */
        Language language = Language.Java;
        enum_switch(language);

        /* Enum（枚举）构造函数及方法的使用 */
        try_enum();

        /* Varargs 可变参数使用 */
        int[] intArrays = {1, 3, 5};
        System.out.println("Sum of variable args: " + varargs(intArrays));

        /* 重载(overloading)方法中使用 Varargs */
        double[] doubleArrays = {1.1, 2.2, 3.3};
        System.out.println("Sum of variable args: " + varargs(doubleArrays));
    }

    public static List<Long> fibonacci(long count) {
        List<Long> list = new ArrayList<>();
        if (count <= 1) {
            list.add(0l);
            return list;
        } else {
            list.add(0l);
            list.add(1l);
            long next;
            for (int i = 2; i < count; i++) {
                next = list.get(i - 2) + list.get(i - 1);
                list.add(next);
            }
        }
        return list;
    }

    public static long factorial(long num) {
        long result = 1;
        if (num > 1) {
            for (int i = 1; i <= num; i++) {
                result = result * i;
            }
        }
        return result;
    }

    public static void instanceOf(Object o) {
        if (o instanceof Integer) {
            System.out.println("Object is instanceOf Integer");
        } else if (o instanceof Float) {
            System.out.println("Object is instanceOf Float");
        } else if (o instanceof String) {
            System.out.println("Object is instanceOf String");
        }
    }

    public static void continues() {
        StringBuffer searchstr = new StringBuffer("hello how are you. ");
        int found = 0;
        for (int i = 0; i < searchstr.length(); i++) {
            if (searchstr.charAt(i) == 'h') {
                found++;
                continue;
            }
        }
        System.out.printf("%d 'h' found in 'hello how are you. '%n", found);
    }

    enum Language {
        Java(1), Python(2), Go(3), Javascript(4);
        private int id;

        Language(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    public static void enum_switch(Language language) {
        switch (language) {
            case Java:
                System.out.println("Language: Java");
                break;
            case Python:
                System.out.println("Language: Python");
                break;
            case Go:
                System.out.println("Language: Go");
                break;
            case Javascript:
                System.out.println("Language: Javascript");
                break;
        }
    }

    public static void try_enum() {
        for (Language l : Language.values()) {
            System.out.printf("%s: %d", l, l.getId());
            System.out.println();
        }
    }

    public static int varargs(int... varargs) {
        int sum = 0;
        for (int i = 0; i < varargs.length; i++) {
            sum += varargs[i];
        }
        return sum;
    }

    public static double varargs(double... varargs) {
        double sum = 0;
        for (double d : varargs) {
            sum += d;
        }
        return sum;
    }
}

