package threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Oliver on 05/07/2017.
 */
public class CallableDemo implements Callable {
    /**
     * 创建 Callable 实现类的实例，使用 FutureTask 类来包装 Callable 对象，
     * 该 FutureTask 对象封装了该 Callable 对象的 call() 方法的返回值。
     */
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableDemo);
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "线程的循环变量i: " + i);
            if (i == 5) {
                // 使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程
                new Thread(futureTask, "有返回值的线程").start();
            }
        }
        try {
            //  调用 FutureTask 对象的 get() 方法来获得子线程执行结束后的返回值
            System.out.println("子线程的返回值：" + futureTask.get());
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 该 call() 方法将作为线程执行体，并且有返回值
     */
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
        return i;
    }
}
