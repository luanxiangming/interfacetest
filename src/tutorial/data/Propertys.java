package data;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Oliver on 30/06/2017.
 */

/**
 * Properties 继承于 Hashtable. Properties 类表示了一个持久的属性集.属性列表中每个键及其对应值都是一个字符串。
 * Properties 类被许多Java类使用。例如，在获取环境变量时它就作为System.getProperties()方法的返回值。
 */
public class Propertys {
    public static void main(String[] args) {
        System.out.println(System.getProperties());

        Properties properties = new Properties();
        properties.put("JAVA", "java");
        properties.put("PYTHON", "python");
        properties.put("GO", "go");

        Set languages = properties.keySet();
        Iterator iterator = languages.iterator();
        while (iterator.hasNext()) {
            String language = (String) iterator.next();
            System.out.printf("%s turned to lowercase: %s%n", language, properties.getProperty(language, "C++"));
        }

        // 用指定的键在属性列表中搜索属性, 不存在就返回默认值
        System.out.printf("%s turned to lowercase: %s%n", "RUBY", properties.getProperty("RUBY", "c++"));

    }
}
