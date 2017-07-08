package examples;

import java.util.*;

/**
 * Created by Oliver on 05/07/2017.
 */
public class ArraysExample {
    public static void main(String[] args) {
        /* 数组排序及元素查找 */
        sort_search();

        /* 数组添加元素 */
        int[] array = {1, 2, 4};
        insertElement(array, 3, 2);

        /**数组反转
         * asList方法返回一个受指定数组支持的固定大小的列表，
         * 此方法同 Collection.toArray 一起，充当了基于数组的 API 与基于 collection 的 API 之间的桥梁
         */
        List<Integer> list = Arrays.asList(1, 3, 2);
        reverse(new ArrayList(list));

        /* 数组获取最大和最小值 */
        max_min();

        /* 数组合并 */
        String[] strings1 = {"a", "b"};
        String[] strings2 = {"c", "d"};
        concatenate(strings1, strings2);

        /* 数组填充 */
        fill();

        /* 数组扩容 */
        extend(strings1, strings2);

        /* 删除数组元素 */
        String[] strings3 = {"a", "b", "c"};
        remove(strings3, "b");

        /* 数组差集 */
        String[] strings4 = {"a", "b", "c"};
        String[] strings5 = {"a", "d", "e"};
        uncommon(strings4, strings5);

        /* 数组交集 */
        common(strings4, strings5);

        /* 在数组中查找指定元素 */
        contain(strings4, "c");
        contain(strings4, strings5);
        contain(strings4, strings1);

        /* 判断数组是否相等 */
        String[] strings6 = {"a", "b", "c"};
        String[] strings7 = {"a", "c", "b"};
        equal(strings4, strings6);
        equal(strings7, strings6);

        /* 数组并集 */
        union(strings4, strings5);

    }

    public static void printArray(String message, int[] array) {
        System.out.println(message + "length: " + array.length);
        for (int i : array) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println();
    }

    public static void printArray(String message, String[] array) {
        System.out.println(message + "length: " + array.length);
        for (String i : array) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println();
    }

    public static void sort_search() {
        int[] array = {-1, 0, -2, 7, 10, 9};
        Arrays.sort(array);
        printArray("sort_search ", array);
        int index = Arrays.binarySearch(array, 7);
        System.out.println("元素7的索引值（负数为不存在）: " + index);
    }

    public static void insertElement(int[] origin, int element, int index) {
        int length = origin.length;
        int[] destination = new int[length + 1];
        System.arraycopy(origin, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(origin, index, destination, index + 1, length - index);
        printArray("insertElement", destination);
    }

    public static void reverse(ArrayList arrayList) {
        System.out.println("\n反转前排序: " + arrayList.toString());
        Collections.reverse(arrayList);
        System.out.println("\n反转后排序: " + arrayList.toString());
    }

    public static void max_min() {
        Integer[] numbers = {1, 5, 7, 10};
        System.out.println("Max: " + Collections.max(Arrays.asList(numbers)));
        System.out.println("Min: " + Collections.min(Arrays.asList(numbers)));
    }

    public static void concatenate(String[] s1, String[] s2) {
        List<String> list1 = new ArrayList(Arrays.asList(s1));
        List<String> list2 = new ArrayList(Arrays.asList(s2));
        list1.addAll(list2);
        System.out.println("Concatenated: " + list1);
    }

    public static void fill() {
        int[] array = new int[6];
        Arrays.fill(array, 100);
        printArray("fill 100 ", array);

        Arrays.fill(array, 3, 6, 50);
        printArray("fill 100 50 ", array);
    }

    public static void extend(String[] origin, String[] extend) {
        String[] destination = new String[origin.length + extend.length];
        System.arraycopy(origin, 0, destination, 0, origin.length);
        System.arraycopy(extend, 0, destination, origin.length, extend.length);
        printArray("extend ", destination);
    }

    public static void remove(String[] origin, String element) {
        List<String> list = new ArrayList<>(Arrays.asList(origin));
        list.remove(element);
        System.out.printf("remove %s from %s: %s%n", element, origin, list);
    }

    public static void uncommon(String[] strings1, String[] strings2) {
        ArrayList arrayList1 = new ArrayList(Arrays.asList(strings1));
        ArrayList arrayList2 = new ArrayList(Arrays.asList(strings2));
        arrayList1.removeAll(arrayList2);
        System.out.println("uncommon: " + arrayList1);
    }

    public static void common(String[] strings1, String[] strings2) {
        ArrayList arrayList1 = new ArrayList(Arrays.asList(strings1));
        ArrayList arrayList2 = new ArrayList(Arrays.asList(strings2));
        arrayList1.retainAll(arrayList2);
        System.out.println("common: " + arrayList1);
    }

    public static void contain(String[] strings1, String element) {
        ArrayList arrayList1 = new ArrayList(Arrays.asList(strings1));
        System.out.printf("%s contains %s: %s%n", arrayList1, element, arrayList1.contains(element));
    }

    public static void contain(String[] strings1, String[] strings2) {
        ArrayList arrayList1 = new ArrayList(Arrays.asList(strings1));
        ArrayList arrayList2 = new ArrayList(Arrays.asList(strings2));
        System.out.printf("%s contains %s: %s%n", arrayList1, arrayList2, arrayList1.contains(arrayList2));
    }

    public static void equal(String[] strings1, String[] strings2) {
        System.out.printf("%s equals %s: %s%n", strings1, strings2, Arrays.equals(strings1, strings2));
    }

    public static void union(String[] strings1, String[] strings2) {
        /*
          求两个字符串数组的并集，利用set的元素唯一性
         */
        Set<String> stringSet = new HashSet<>();
        for (String s : strings1) {
            stringSet.add(s);
        }
        for (String s : strings2) {
            stringSet.add(s);
        }
        System.out.println("union: " + stringSet);
    }
}
