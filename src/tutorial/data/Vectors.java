package data;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Oliver on 30/06/2017.
 */

/**
 * Vector类实现了一个动态数组。和ArrayList和相似，但是两者是不同的：
 * Vector是同步访问的。
 * Vector包含了许多传统的方法，这些方法不属于集合框架。
 * Vector主要用在事先不知道数组的大小，或者只是需要一个可以改变大小的数组的情况。
 */
public class Vectors {
    public static void main(String[] args) {
        Vector v = new Vector(3, 2);
        System.out.printf("Initial size: %d%n", v.size());
        System.out.printf("Initial capacity: %d%n", v.capacity());

        v.addElement(new Integer(1));
        v.addElement(new Integer(2));
        v.addElement(new Integer(3));
        v.addElement(new Integer(4));
        System.out.printf("Capacity after 4 additions: %d%n", v.capacity());

        v.addElement(new Double(3.14));
        System.out.printf("Capacity after 5 additions: %d%n", v.capacity());

        v.addElement(new Double(6.08));
        v.addElement(new Integer(7));
        System.out.printf("Capacity after 7 additions: %d%n", v.capacity());

        v.addElement(new Float(9.4));
        v.addElement(new Integer(10));
        System.out.printf("Capacity after 9 additions: %d%n", v.capacity());

        v.addElement(new Integer(11));
        v.addElement(new Integer(12));
        System.out.printf("Capacity after 11 additions: %d%n", v.capacity());

        System.out.println("1st element: " + v.firstElement());
        System.out.println("last element: " + v.lastElement());

        if (v.contains(3)) {
            System.out.println("Vector contains 3");
        }

        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        System.out.printf("Size in the end: %d%n", v.size());
    }
}
