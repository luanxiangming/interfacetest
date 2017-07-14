package data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 30/06/2017.
 */

/**
 * Map接口中键和值一一映射. 可以通过键来获取值。
 * 给定一个键和一个值，你可以将该值存储在一个Map对象. 之后，你可以通过键来访问对应的值。
 * 当访问的值不存在的时候，方法就会抛出一个NoSuchElementException异常.
 * 当对象的类型和Map里元素类型不兼容的时候，就会抛出一个 ClassCastException异常。
 * 当在不允许使用Null对象的Map中使用Null对象，会抛出一个NullPointerException 异常。
 * 当尝试修改一个只读的Map时，会抛出一个UnsupportedOperationException异常。
 */
public class Maps {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("Python", 1);
        map.put("Java", 2);
        map.put("Go", 3);
        System.out.println("map elements: " + map);
        System.out.println("map.size(): " + map.size());
        System.out.println("map.containsKey('Python'): " + map.containsKey("Python"));
        System.out.println("map.containsValue(3): " + map.containsValue(3));

        Set s = map.entrySet();
        System.out.println("映射中包含的映射关系的 Set 视图: " + s);

        Collection c = map.values();
        System.out.println("映射中包含的值的 Collection 视图: " + c);

    }

}
