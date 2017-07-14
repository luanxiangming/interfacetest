package data;

import java.util.*;

/**
 * Created by Oliver on 01/07/2017.
 */
public class Collection {
    /**
     * 集合接口
     *1	Collection 是最基本的集合接口，一个 Collection 代表一组 Object，Java不提供直接继承自Collection的类，只提供继承于的子接口(如List和set)。
     *2 List接口是一个有序的Collection，使用此接口能够精确的控制每个元素插入的位置，能够通过索引(元素在List中位置，类似于数组的小标)来访问List中的元素，而且允许有相同的元素。
     *3 Set 具有与 Collection 完全一样的接口，只是行为上不同，Set 不保存重复的元素。
     *4 SortedSet继承于Set保存有序的集合。
     *5	Map将唯一的键映射到值。
     *6	Map.Entry描述在一个Map中的一个元素（键/值对）。是一个Map的内部类。
     *7	SortedMap继承于Map，使Key保持在升序排列。
     *8	Enumeration这是一个传统的接口和定义的方法，通过它可以枚举（一次获得一个）对象集合中的元素。这个传统接口已被迭代器取代。
     */

    /**
     * 集合实现类
     * 1	AbstractCollection 实现了大部分的集合接口。
     * 2	AbstractList 继承于AbstractCollection 并且实现了大部分List接口。
     * 3	AbstractSequentialList 继承于 AbstractList ，提供了对数据元素的链式访问而不是随机访问。
     * 4	LinkedList该类实现了List接口，允许有null（空）元素。主要用于创建链表数据结构，该类没有同步方法，如果多个线程同时访问一个List，则必须自己实现访问同步，解决方法就是在创建List时候构造一个同步的List。
     * 例如：Listlist=Collections.synchronizedList(newLinkedList(...));LinkedList 查找效率低。
     * 5	ArrayList该类也是实现了List的接口，实现了可变大小的数组，随机访问和遍历元素时，提供更好的性能。该类也是非同步的,在多线程的情况下不要使用。ArrayList 增长当前长度的50%，插入删除效率低。
     * 6	AbstractSet 继承于AbstractCollection 并且实现了大部分Set接口。
     * 7	HashSet该类实现了Set接口，不允许出现重复元素，不保证集合中元素的顺序，允许包含值为null的元素，但最多只能一个。
     * 8	LinkedHashSet具有可预知迭代顺序的 Set 接口的哈希表和链接列表实现。
     * 9	TreeSet该类实现了Set接口，可以实现排序等功能。
     * 10 AbstractMap 实现了大部分的Map接口。
     * 11 HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。
     * 该类实现了Map接口，根据键的HashCode值存储数据，具有很快的访问速度，最多允许一条记录的键为null，不支持线程同步。
     * 12 TreeMap 继承了AbstractMap，并且使用一颗树。
     * 13 WeakHashMap 继承AbstractMap类，使用弱密钥的哈希表。
     * 14 LinkedHashMap 继承于HashMap，使用元素的自然顺序对元素进行排序.
     * 15 IdentityHashMap 继承AbstractMap类，比较文档时使用引用相等。
     */

    /**
     * 通过java.util包中定义的类
     * 1	Vector 该类和ArrayList非常相似，但是该类是同步的，可以用在多线程的情况，该类允许设置默认的增长长度，默认扩容方式为原来的2倍。
     * 2	Stack 栈是Vector的一个子类，它实现了一个标准的后进先出的栈。
     * 3	Dictionary 类是一个抽象类，用来存储键/值对，作用和Map类相似。
     * 4	Hashtable 是 Dictionary(字典) 类的子类，位于 java.util 包中。
     * 5	Properties 继承于 Hashtable，表示一个持久的属性集，属性列表中每个键及其对应值都是一个字符串。
     * 6	BitSet类创建一种特殊类型的数组来保存位值。BitSet中数组大小会随需要增加。
     */
    public static void main(String[] args) {
        traverse_ArrayList();
        traverse_map();
    }

    public static void traverse_ArrayList() {
        System.out.println("\n*****遍历 ArrayList*****:");
        List<String> list = new ArrayList<String>();
        list.add("Java");
        list.add("Python");
        list.add("Go");

        //第一种遍历方法使用foreach遍历List
        System.out.println("第一种遍历, 使用foreach遍历List:");
        for (String language : list) {
            System.out.println(language);
        }

        //第二种遍历，把链表变为数组相关的内容进行遍历
        System.out.println("第二种遍历，把链表变为数组相关的内容进行遍历:");
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        for (String language : strArray) {
            System.out.println(language);
        }

        //第三种遍历 使用迭代器进行相关遍历
        System.out.println("第三种遍历, 使用迭代器进行相关遍历:");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void traverse_map() {
        System.out.println("\n*****遍历 Map*****:");
        Map<String, String> map = new HashMap<String, String>();
        map.put("Java", "JAVA");
        map.put("Python", "PYTHON");
        map.put("Go", "GO");

        //第一种：普遍使用，二次取值
        System.out.println("第一种：普遍使用，二次取值:");
        for (String key : map.keySet()) {
            System.out.printf("%s: %s%n", key, map.get(key));  //通过Map.keySet遍历key和value
        }

        System.out.println("第二种：通过Map.entrySet使用iterator遍历key和value：");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());
        }

        System.out.println("第三种：通过Map.entrySet遍历key和value：");
        for (Map.Entry entry : map.entrySet()) {
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());
        }

        System.out.println("第四种: 通过Map.values()遍历所有的value，但不能遍历key：");
        for (String value : map.values()) {
            System.out.printf("%s%n", value);
        }

    }
}
