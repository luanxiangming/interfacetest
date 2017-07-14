package data;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Oliver on 30/06/2017.
 */

/**
 * 枚举（Enumeration）接口虽然它本身不属于数据结构,但它在其他数据结构的范畴里应用很广。 枚举（The Enumeration）接口定义了一种从数据结构中取回连续元素的方式。
 * 例如，枚举定义了一个叫nextElement 的方法，该方法用来得到一个包含多元素的数据结构的下一个元素。
 *
 * boolean hasMoreElements( )  测试此枚举是否包含更多的元素。
 * Object nextElement( )  如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素。
 */
public class Enumerations {
    public static void main(String[] args) {
        Enumeration days;
        Vector dayNames = new Vector();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();
        while (days.hasMoreElements()) {
            System.out.println(days.nextElement());
        }
    }
}
