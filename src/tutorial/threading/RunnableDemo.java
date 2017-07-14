package threading;

/**
 * Created by Oliver on 05/07/2017.
 */
public class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    public RunnableDemo(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating thread: " + threadName);
    }

    public static void main(String[] args) {
        RunnableDemo threadDemo1 = new RunnableDemo("Thread-1");
        RunnableDemo threadDemo2 = new RunnableDemo("Thread-2");

        threadDemo1.start();
        threadDemo2.start();
    }

    /**
     * 为了实现 Runnable，一个类只需要执行一个方法调用 run()
     * run() 可以调用其他方法，使用其他类，并声明变量，就像主线程一样
     */
    public void run() {
        System.out.println("Running thread: " + threadName);
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread: " + threadName + ": " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException i) {
            System.out.println("Thread " + threadName + "interrupted");
        }
        System.out.println("Exit thread: " + threadName);
    }

    /**
     * Thread(Runnable threadOb,String threadName)
     * threadOb 是一个实现 Runnable 接口的类的实例，并且 threadName 指定新线程的名字
     */
    public void start() {
        System.out.println("***Starting thread " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();  // 新线程创建之后，你调用它的 start() 方法它才会运行

        }
    }

}
