package data;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Oliver on 30/06/2017.
 */

/**
 * Hashtable是原始的java.util的一部分， 是一个Dictionary具体的实现 。
 * 然而，Java 2 重构的Hashtable实现了Map接口，因此，Hashtable现在集成到了集合框架中。它和HashMap类很相似，但是它支持同步。
 * 像HashMap一样，Hashtable在哈希表中存储键/值对。当使用一个哈希表，要指定用作键的对象，以及要链接到该键的值。
 * 然后，该键经过哈希处理，所得到的散列码被用作存储在该表中值的索引。
 */
public class Hashtables {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("Java", new Double(1.11));
        hashtable.put("Python", new Double(2.22));
        hashtable.put("Go", new Double(3.33));

        Enumeration languages = hashtable.keys();  // 返回此哈希表中的键的枚举
        while (languages.hasMoreElements()) {
            String language = (String) languages.nextElement();
            System.out.println(language + ": " + hashtable.get(language));
        }

        Double d = (Double) hashtable.get("Python") + 7.77;
        hashtable.put("Python", d);
        System.out.println("Python's new value: " + hashtable.get("Python"));

    }
}
