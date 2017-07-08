package examples;

import java.util.*;

/**
 * Created by Oliver on 08/07/2017.
 */
public class CollectionsExample {
    public static void main(String[] args) {
        /* 数组转集合 */
        asList();

        /* 集合比较 */
        max_min();

        /* 使用 Collection 类的 iterator() 方法来遍历集合 */
        iterator();

        /* 集合长度 */
        size();

        /* 集合打乱顺序 */
        shuffle();

        /* 使用普通for，增强型的 for ，iterator 等方式来遍历list */
        traverse_list();

        /* 使用增强型的 for ，iterator 等方式来遍历set */
        traverse_set();

        /* Map类型集合的遍历 */
        traverse_map();

        /* 集合反转 */
        reverse();

        /* 删除集合中指定元素 */
        remove();

        /* 只读集合 */
        unmodifiableList();

        /* 集合输出 */
        mapOutput();

        /* list.add() 和 list.toArray() 方法将集合转为数组 */
        collectionsToArrays();

        /* List 循环移动元素 */
        rotate();

        /* 遍历 HashTable 的键值 */
        hashtableKeys();

        /* List 元素替换 */
        replace();

        /* List 截取 */
        indexOfSubList();
    }

    public static void asList() {
        String[] s = new String[5];
        for (int i = 0; i < 5; i++) {
            s[i] = String.valueOf(i);
            System.out.print(s[i] + ", ");
        }
        List list = new ArrayList(Arrays.asList(s));
        System.out.println("\nArrays.toList: " + list);
    }

    public static void max_min() {
        String[] coins = {"Penny", "nickel", "dime", "Quarter", "dollar"};
        TreeSet set = new TreeSet();
        for (String coin : coins) {
            set.add(coin);
        }
        System.out.println("TreeSet: " + set);
        System.out.println("Max: " + Collections.max(set));
        System.out.println("Min: " + Collections.min(set, String.CASE_INSENSITIVE_ORDER));
    }

    public static void iterator() {
        HashMap hashMap = new HashMap();
        hashMap.put("1", "1st");
        hashMap.put("2", "2nd");
        hashMap.put("3", "3rd");
        Collection c = hashMap.values();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void size() {
        HashSet hashSet = new HashSet();
        if (hashSet.isEmpty()) {
            System.out.println("HashSet是空的");
        }
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("c");
        System.out.println("HashSet size: " + hashSet.size());
    }

    public static void shuffle() {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("Before shuffle: " + list);
        Collections.shuffle(list);
        System.out.println("After shuffle: " + list);
    }

    public static void traverse_list() {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        // 使用传统for循环进行遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println();

        // 使用增强for循环进行遍历
        for (String s : list) {
            System.out.print(String.valueOf(s) + ", ");
        }
        System.out.println();

        // 使用iterator遍历
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();
    }

    public static void traverse_set() {
        HashSet<String> hashSet = new HashSet();
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("C");
        hashSet.add("A");

        // 使用增强for循环进行遍历
        for (String s : hashSet) {
            System.out.print(s + ", ");
        }
        System.out.println();

        // 使用iterator遍历
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();
    }

    public static void traverse_map() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("1", "1st");
        hashMap.put("2", "2nd");
        hashMap.put("3", "3rd");

        // 使用entrySet()方法，获取maps集合中的每一个键值对，
        for (Map.Entry s : hashMap.entrySet()) {
            System.out.println(s.getKey() + ":" + s.getValue());
        }

        for (String s : hashMap.values()) {
            System.out.print(s + ", ");
        }
        System.out.println();

        // 使用keySet()方法，获取maps集合中的所有键，遍历键取得所对应的值。
        for (String s : hashMap.keySet()) {
            System.out.print(s + ":" + hashMap.get(s) + ", ");
        }
        System.out.println();

        // 取得迭代器遍历出对应的值。
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void reverse() {
        String[] coins = {"A", "B", "C", "D", "E"};
        List<String> list = new ArrayList<>(Arrays.asList(coins));
        System.out.println("list before reverse: " + list);
        Collections.reverse(list);
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void remove() {
        HashSet hashSet = new HashSet();
        hashSet.add("a");
        hashSet.add("1");
        hashSet.add("b");
        System.out.println("HashSet before removal:" + hashSet);

        hashSet.remove("1");
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();
    }

    public static void unmodifiableList() {
        List stuff = new ArrayList(Arrays.asList(new String[]{"a", "b"}));
        System.out.println("Original list: " + stuff);
        stuff.add("c");
        System.out.println("Add c to list: " + stuff);

        // 使用 Collection 类的 Collections.unmodifiableList() 方法来设置集合为只读
        stuff = Collections.unmodifiableList(stuff);
        try {
            stuff.add("d");
        } catch (UnsupportedOperationException u) {
            System.out.println("List is set to unmodifiableList");
        }
        System.out.println("Add d to list: " + stuff);
    }

    public static void mapOutput() {
        // 使用 Java Util 类的 tMap.keySet(),tMap.values() 和 tMap.firstKey() 方法将集合元素输出
        TreeMap tMap = new TreeMap();
        tMap.put(0, "Sunday");
        tMap.put(1, "Monday");
        tMap.put(2, "Tuesday");
        tMap.put(3, "Wednesday");
        tMap.put(4, "Thursday");
        tMap.put(5, "Friday");
        tMap.put(6, "Saturday");
        System.out.println("TreeMap keySet: " + tMap.keySet());
        System.out.println("TreeMap values: " + tMap.values());
        System.out.println("TreeMap firstEntry: " + tMap.firstEntry());
        System.out.println("TreeMap firstKey: " + tMap.firstKey());
        System.out.println("TreeMap lastKey: " + tMap.lastKey());
        tMap.remove(tMap.firstKey());
        tMap.remove(tMap.lastKey());
        System.out.println("TreeMap firstEntry: " + tMap.firstEntry());
        System.out.println("TreeMap lastEntry: " + tMap.lastEntry());
    }

    public static void collectionsToArrays() {
        List<String> list = new ArrayList<>();
        list.add("J");
        list.add("a");
        list.add("v");
        list.add("a");
        System.out.println("List: " + list);
        String[] array = list.toArray(new String[0]);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    public static void rotate() {
        String[] arr = "I do love java".split(" ");
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        System.out.println("Before rotate: " + list);
        Collections.rotate(list, 2);
        System.out.println("After rotate: " + list);
    }

    public static void hashtableKeys() {
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        hashtable.put(1, "first");
        hashtable.put(2, "second");
        hashtable.put(3, "third");
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            System.out.println(String.valueOf(key) + ":" + hashtable.get(key));
        }
    }

    public static void replace() {
        String[] strings = "Python,Java,Ruby,Go,Java,Python".split(",");
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        System.out.println("Before replace: " + list);
        Collections.replaceAll(list, "Python", "Py");
        System.out.println("After replace: " + list);
    }

    public static void indexOfSubList() {
        // 使用 Collections 类的 indexOfSubList() 和 lastIndexOfSubList() 方法来查看子列表是否在列表中，并查看子列表在列表中所在的位置
        List<String> list = Arrays.asList("Python,Java,Ruby,Go,Java,Ruby,Python".split(","));
        System.out.println(list);
        List<String> subList = Arrays.asList("Java Ruby".split(" "));
        System.out.println(subList);
        System.out.println("IndexOfSubList: " + Collections.indexOfSubList(list, subList));
        System.out.println("LastIndexOfSubList: " + Collections.lastIndexOfSubList(list, subList));
    }
}
