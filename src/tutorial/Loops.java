/**
 * Created by Oliver on 25/06/2017.
 */
public class Loops {
    public static void test_while() {
        System.out.println("*****while*****");
        int x = 1;
        while (x < 10) {
            System.out.println("x in while: " + x);
            x++;
        }
    }

    public static void test_do_while() {
        System.out.println("*****do while*****");
        int x = 10;
        do {
            System.out.println("x in do while: " + x);
            x++;
        } while (x < 10);
    }

    public static void test_for() {
        System.out.println("*****for*****");
        for (int x = 10; x < 20; x++) {
            System.out.println("x in for: " + x);
        }
    }

    public static void test_enhanced_for() {
        System.out.println("*****enhanced for*****");
        int[] numbers = {20, 21, 22, 23, 24};
        for (int x : numbers) {
            System.out.println("x in enhanced for: " + x);
        }
    }

    public static void test_break() {
        System.out.println("*****break*****");
        int[] numbers = {25, 26, 27, 28, 29, 30};
        for (int x : numbers) {
            if (x == 27) {
                break;
            }
            System.out.println("x in break: " + x);
        }

    }

    public static void test_continue() {
        System.out.println("*****continue*****");
        int[] numbers = {31, 32, 33, 34, 35};
        for (int x : numbers) {
            if (x == 33) {
                continue;
            }
            System.out.println("x in continue: " + x);

        }
    }

    public static void test_nested_for() {
        System.out.println("*****nested for*****");
        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= 9; y++) {
                System.out.print(x + "x" + y + "=" + x * y + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        test_while();
        test_do_while();
        test_for();
        test_enhanced_for();
        test_break();
        test_continue();
        test_nested_for();
    }
}
