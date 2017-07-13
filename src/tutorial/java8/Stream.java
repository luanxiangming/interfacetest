package java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Oliver on 13/07/2017.
 */
public class Stream {
    public static void main(String[] args) {
        /**
         * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。
         * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
         * Stream API可以极大提供Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
         * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等
         * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
         *
         * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
         */


        // stream() − 为集合创建串行流。
        stream();

        /* Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数 */
        /* limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 5 条数据 */
        foreach_limit(5);

        /* map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数 */
        map();

        /*filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串 */
        filter();

        /* sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 5 个随机数进行排序 */
        sort(5);

        /* parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量 */
        parallelStream();

        /* Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串 */
        collectors();

        /* 另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果 */
        summaryStatistics();
    }

    public static void stream() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abd", "", "jkl");
        List<String> filtered = list.stream().filter(string -> !list.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    public static void foreach_limit(int limit) {
        Random random = new Random();
        random.ints().limit(limit).forEach(System.out::println);
    }

    public static void map() {
        List<Integer> list = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squares = list.stream().map(x -> x * x).distinct().collect(Collectors.toList());
        squares.forEach(System.out::println);
    }

    public static void filter() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7);
        List<Integer> filtered = list.stream().filter(i -> i == 7).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    public static void sort(int limit) {
        Random random = new Random();
        random.ints().limit(limit).sorted().forEach(System.out::println);

        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abd", "", "jkl");
        List<String> sorted = list.stream().sorted(String::compareTo).collect(Collectors.toList());
        System.out.println("sorted string list: " + sorted);
    }

    public static void parallelStream() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = list.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("Empty string: " + count);
    }

    public static void collectors() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List filtered = list.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("Collectors返回列表: " + filtered);

        String merged = list.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Collectors返回字符串: " + merged);
    }

    public static void summaryStatistics() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的数: " + intSummaryStatistics.getMax());
        System.out.println("列表中最小的数: " + intSummaryStatistics.getMin());
        System.out.println("所有数之和: " + intSummaryStatistics.getSum());
        System.out.println("平均数: " + intSummaryStatistics.getAverage());
    }

}
