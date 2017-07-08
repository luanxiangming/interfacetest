package examples;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * Created by Oliver on 06/07/2017.
 */
public class DataExample {
    private static int index = 0;
    private static long[] longArray = new long[10];

    public static void main(String[] args) {
        /* 数字求和运算 */
        System.out.println(sum(100));

        /* 在链表（LinkedList）的开头和结尾添加元素 */
        /* 获取链表（LinkedList）的第一个和最后一个元素 */
        first_last();

        /* 删除链表中的元素 */
        remove();

        /* 获取向量元素的索引值 */
        sort();

        /* 栈的实现 */
        push(100);
        push(200);
        push(300);
        push(400);
        push(500);
        pop();
        pop();
        pop();
        pop();
        pop();

        /* 链表元素查找 */
        indexOf();

        /* 队列（Queue）用法 */
        queue();

        /* 获取向量的最大元素 */
        max();

        /* 链表修改 */
        set();

        /* 交换向量 */
        swap();

    }

    public static int sum(int count) {
        int current = 0;
        int sum = 0;
        do {
            sum = current + sum;
            current++;
        } while (current <= count);
        return sum;
    }

    public static void first_last() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.addFirst("0");
        linkedList.addLast("3");
        System.out.println("LinkedList: " + linkedList);
        System.out.println("LinkedList getFirst: " + linkedList.getFirst());
        System.out.println("LinkedList getLast: " + linkedList.getLast());
    }

    public static void remove() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        System.out.println("LinkedList: " + linkedList);
        linkedList.subList(1, 3).clear();
        System.out.println("LinkedList after removal: " + linkedList);

    }

    public static void sort() {
        Vector vector = new Vector();
        vector.add("4");
        vector.add("2");
        vector.add("1");
        vector.add("3");
        System.out.println("Vector: " + vector);
        int index1 = Collections.binarySearch(vector, "3");
        System.out.println("Index of 3: " + index1);

        Collections.sort(vector);
        System.out.println("Vector sort: " + vector);

        int index2 = Collections.binarySearch(vector, "3");
        System.out.println("Index of 3: " + index2);
    }

    public static void printArray(long[] longs) {
        for (long l : longs) {
            System.out.print(l);
            System.out.print(", ");
        }
        System.out.println();
    }

    public static void push(long l) {
        longArray[index++] = l;
        printArray(longArray);
    }

    public static void pop() {
        long top = longArray[--index];
        System.out.printf("%d is popped%n", top);
    }

    public static void indexOf() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        System.out.println("LinkedList: " + linkedList);
        System.out.println("LinkedList index of C: " + linkedList.indexOf("C"));
        System.out.println("LinkedList index of B: " + Collections.binarySearch(linkedList, "B"));
    }

    public static void queue() {
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue queue = new LinkedList();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        System.out.println("queue: " + queue);
        System.out.println("queue.poll返回第一个元素，并在队列中删除: " + queue.poll());
        System.out.println("queue: " + queue);
        System.out.println("queue.element返回第一个元素: " + queue.element());
        System.out.println("queue: " + queue);
        System.out.println("queue.peek返回第一个元素: " + queue.peek());
        System.out.println("queue: " + queue);
    }

    public static void max() {
        Vector v = new Vector();
        v.add(new Double("3.14"));
        v.add(new Double("3.141"));
        v.add(new Double("3.1"));
        System.out.println("Vector.max: " + Collections.max(v));
    }

    public static void set() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("a");
        linkedList.add("x");
        linkedList.add("c");
        System.out.println("LinkedList: " + linkedList);
        linkedList.set(1, "b");
        System.out.println("LinkedList: " + linkedList);
    }

    public static void swap() {
        Vector v = new Vector();
        v.add("1");
        v.add("4");
        v.add("3");
        v.add("2");
        v.add("5");
        System.out.println("Vector before swap: " + v);
        Collections.swap(v, 1, 3);
        System.out.println("Vector after swap: " + v);
    }

}
