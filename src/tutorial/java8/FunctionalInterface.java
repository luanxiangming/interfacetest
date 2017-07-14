package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Oliver on 13/07/2017.
 */
public class FunctionalInterface {
    /**
     * 函数式接口(Functional Interface)就是一个具有一个方法的普通接口。
     * 函数式接口可以被隐式转换为lambda表达式。
     * 函数式接口可以现有的函数友好地支持 lambda。
     */
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integerList.forEach(System.out::println);

        System.out.println("输出所有数据:");
        /**
         * Predicate<Integer> predicate = n -> true
         * n 是一个参数传递到 Predicate 接口的 test 方法
         * n 如果存在则 test 方法返回 true
         */
        eval(integerList, n -> true);

        System.out.println("输出所有偶数:");
        /**
         * Predicate<Integer> predicate1 = n -> n%2 == 0
         * n 是一个参数传递到 Predicate 接口的 test 方法
         * 如果 n%2 为 0 test 方法返回 true
         */
        eval(integerList, n -> n % 2 == 0);

        System.out.println("输出大于3的数字:");
        /**
         * Predicate<Integer> predicate1 = n -> n%2 == 0
         * n 是一个参数传递到 Predicate 接口的 test 方法
         * 如果 n大于3 test 方法返回 true
         */
        eval(integerList, n -> n > 3);

    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        /**
         * Predicate <T> 接口是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果。
         * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）。
         * 该接口用于测试对象是 true 或 false。
         */
        for (Integer i : list) {
            if (predicate.test(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

}
