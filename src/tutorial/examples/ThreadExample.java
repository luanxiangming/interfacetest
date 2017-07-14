package examples;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oliver on 11/07/2017.
 */
public class ThreadExample extends Thread {
    public static String obj1 = "obj1";
    public static String obj2 = "obj2";
    private String threadName;


    public static final Semaphore s1 = new Semaphore(1);
    public static final Semaphore s2 = new Semaphore(1);

    public ThreadExample() {

    }

    public ThreadExample(String name) {
        this.setName(name);
    }


    public static void main(String[] args) {
        /* 通过继承 Thread 类并使用 isAlive() 方法来检测一个线程是否存活 */
        is_Alive();
        System.out.println();

        /* 使用 currentThread.getState() 方法来监测线程的状态 */
        get_State();
        System.out.println();

        /* 通过setPriority() 方法来设置线程的优先级 */
        set_Priority();
        System.out.println();

        /* 线程挂起 */
        thread_join();

        /* 终止线程 */
        thread_interrupt();

        /* 通过线程解决生产者/消费者问题 */
        consumer_producer();

        /* 死锁及解决方法 */
        lock_run();
        System.out.println();
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                printMsg();
            }
            Thread.sleep(3000);  //使用interrupt方法，sleep方法将抛出一个InterruptedException例外
        } catch (InterruptedException i) {
            System.out.println(i.getMessage());
        }

    }

    public static void printMsg() {
        Thread thread = Thread.currentThread();
        String name = thread.getName();  //使用 getName() 方法来获取当前线程名称
        System.out.println("Thread: " + name + ", ID: " + thread.getId());
    }

    public static void showThreadStatus(Thread thread) {
        System.out.printf("Thread %s status: %s%n", thread.getName(), thread.getState());
    }

    public static void is_Alive() {
        ThreadExample threadExample = new ThreadExample("Thread-isAlive");
        System.out.println(threadExample.getName() + " before start: " + threadExample.isAlive());
        threadExample.start();
        System.out.println(threadExample.getName() + " after start: " + threadExample.isAlive());
        for (int i = 0; i < 5; i++) {
            printMsg();
        }
    }

    public static void get_State() {
        try {
            ThreadExample threadExample = new ThreadExample("Thread-getState");
            showThreadStatus(threadExample);

            threadExample.start();
            Thread.sleep(1000);
            showThreadStatus(threadExample);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }

    public static void set_Priority() {
        try {
            ThreadExample threadExampleMax = new ThreadExample();
            ThreadExample threadExampleMin = new ThreadExample();
            threadExampleMax.setName("Thread-setPriority-max");
            threadExampleMin.setName("Thread-setPriority-min");
            threadExampleMax.setPriority(Thread.MAX_PRIORITY);
            threadExampleMin.setPriority(Thread.MIN_PRIORITY);
            threadExampleMax.start();
            threadExampleMin.start();
            showThreadStatus(threadExampleMax);
            showThreadStatus(threadExampleMin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void lock_run() {
        LockA lockA = new LockA();
        new Thread(lockA).start();
        LockB lockB = new LockB();
        new Thread(lockB).start();
    }

    public static void thread_join() {
        try {
            for (int i = 0; i < 5; i++) {
                ThreadExample threadExample = new ThreadExample();
                threadExample.setName("Thread-join");
                threadExample.start();
                threadExample.join();
            }
            System.out.println("线程已被挂起");
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }

    public static void thread_interrupt() {
        try {
            ThreadExample threadExample = new ThreadExample("Thread-interrupt");
            threadExample.start();
            System.out.println("在5秒之内按任意键中断线程!");
            System.in.read();
            threadExample.interrupt();
            threadExample.join();
            System.out.println("线程已经退出!");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }

    public static void consumer_producer() {
        CubbyHole cubbyHole = new CubbyHole();
        Producer producer = new Producer(cubbyHole, 1);
        Consumer consumer = new Consumer(cubbyHole, 1);
        producer.start();
        consumer.start();
    }
}

class LockA implements Runnable {
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockA 开始执行");
            while (true) {
                if (ThreadExample.s1.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(new Date().toString() + " LockA 锁住obj1");
                    Thread.sleep(3000);  // 此处等待是给B能锁住机会
                    if (ThreadExample.s2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockA 锁住obj2");
                        Thread.sleep(60 * 1000);  // 为测试，占用了就不放
                    } else {
                        System.out.println(new Date().toString() + " LockA 锁住obj2失败");
                    }
                } else {
                    System.out.println(new Date().toString() + " LockA 锁住obj1失败");
                }
                ThreadExample.s1.release();  //释放
                ThreadExample.s2.release();
                Thread.sleep(1000);  //马上进行尝试，现实情况下do something是不确定的
            }
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
}

class LockB implements Runnable {
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockB 开始执行");
            while (true) {
                if (ThreadExample.s2.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(new Date().toString() + " LockB 锁住obj2");
                    Thread.sleep(3000);  // 此处等待是给A能锁住机会
                    if (ThreadExample.s1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockB 锁住obj1");
                        Thread.sleep(60 * 1000);  // 为测试，占用了就不放
                    } else {
                        System.out.println(new Date().toString() + " LockB 锁住obj1失败");
                    }
                } else {
                    System.out.println(new Date().toString() + " LockB 锁住obj2失败");
                }
                ThreadExample.s1.release();
                ThreadExample.s2.release();
                Thread.sleep(1000);  //这里只是为了演示，所以tryAcquire只用1秒，而且B要给A让出能执行的时间，否则两个永远是死锁
            }
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }
}

class CubbyHole {
    private int contents;
    private boolean available = false;

    public synchronized int get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException i) {
                i.printStackTrace();
            }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(int value) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException i) {
                i.printStackTrace();
            }
        }
        contents = value;
        available = true;
        notifyAll();
    }

}

class Consumer extends Thread {
    private CubbyHole cubbyHole;
    private int number;

    Consumer(CubbyHole cubbyHole, int number) {
        this.cubbyHole = cubbyHole;
        this.number = number;
    }

    public void run() {
        int value;
        for (int i = 0; i < 10; i++) {
            value = cubbyHole.get();
            System.out.println("消费者 #" + this.number + " got: " + value);
        }
    }
}

class Producer extends Thread {
    private CubbyHole cubbyHole;
    private int number;

    Producer(CubbyHole cubbyHole, int number) {
        this.cubbyHole = cubbyHole;
        this.number = number;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                cubbyHole.put(i);
                System.out.println("生产者 #" + this.number + " put: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException i) {
            i.printStackTrace();
        }

    }
}

