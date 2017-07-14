package data;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Oliver on 30/06/2017.
 */

/**
 * 栈是Vector的一个子类，它实现了一个标准的后进先出的栈。
 * 堆栈只定义了默认构造函数，用来创建一个空栈。 堆栈除了包括由Vector定义的所有方法，也定义了自己的一些方法。
 */
public class Stacks {
    static void tryPush(Stack stack, int i) {
        stack.push(i);
        System.out.printf("Push %d to stack %s%n", i, stack);
    }

    static void tryPop(Stack stack) {
        Integer popped = (Integer) stack.pop();
        System.out.printf("Pop %d off stack %s%n", popped, stack);
    }


    public static void main(String[] args) {
        Stack stack = new Stack();
        tryPush(stack, 10);
        tryPush(stack, 20);
        tryPush(stack, 30);
        tryPush(stack, 40);
        tryPush(stack, 50);
        tryPop(stack);
        tryPop(stack);
        tryPop(stack);
        tryPop(stack);
        tryPop(stack);

        try {
            tryPop(stack);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
            e.printStackTrace();
        }
    }

}
