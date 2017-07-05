package threading;

/**
 * Created by Oliver on 05/07/2017.
 */
public class ThreadDemo extends Thread {
    /**
     * 创建一个线程的第二种方法是创建一个新的类，该类继承 Thread 类，然后创建一个该类的实例。
     * 该方法尽管被列为一种多线程实现方式，但是本质上也是实现了 Runnable 接口的一个实例。
     */
    private Thread t;
    private String threadName;

    public ThreadDemo(String threadName) {
        this.threadName = threadName;
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo1 = new ThreadDemo("Thread-1");
        ThreadDemo threadDemo2 = new ThreadDemo("Thread=2");

        threadDemo1.start();
        threadDemo2.start();
    }

    /**
     * 继承类必须重写 run() 方法，该方法是新线程的入口点。它也必须调用 start() 方法才能执行。
     */
    public void run() {
        System.out.println("Running thread: " + threadName);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread: " + threadName + ": " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException i) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Exit thread: " + threadName);

    }

    public void start() {
        System.out.println("Start thread " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
