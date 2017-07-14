/**
 * Created by Oliver on 28/06/2017.
 */
public class Methods {
    public static void swap(int i, int j) {
        System.out.printf("Before swap: i=%d, j=%d%n", i, j);
        int tmp = i;
        i = j;
        j = tmp;
        System.out.printf("After swap: i=%d, j=%d%n", i, j);
    }

    public static float max(float i, float j) {
        return Math.max(i, j);
    }

    public static void printMax(double... x) {
        if (x.length == 0) {
            System.out.println("No argument...");
            return;
        }
        double result = x[0];
        for (int i = 0; i < x.length; i++) {
            if (x[i] > result) {
                result = x[i];
            }
        }
        System.out.printf("PrintMax: " + result);
    }

    public static void Method_swap() {
        int x = 10;
        int y = 20;
        swap(x, y);
        System.out.printf("x=%d, y=%d%n", x, y);  // 方法被调用后，实参的值并没有改变
    }

    public static void Method_max() {
        float f = max(8.0f, 9.1f);
        System.out.printf("Max float: %f%n", f);
    }

    public static void Method_printMax() {
        printMax();
        printMax(9.12, 10.34, 1.34, 3.13);
    }

    // finalize( )在对象被垃圾收集器析构(回收)之前调用，它用来清除回收对象
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Everything is disposed.");
    }

    public static void main(String[] args) {
        Method_swap();
        Method_max();
        Method_printMax();

        System.gc();  //调用Java垃圾收集器

    }
}
