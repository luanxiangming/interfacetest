import java.util.Scanner;

/**
 * Created by Oliver on 28/06/2017.
 */
public class Scanners {
    /*
        next():
        1、一定要读取到有效字符后才可以结束输入。
        2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
        3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
        next() 不能得到带有空格的字符串。
     */
    public static void Scanner_next() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scanner.next方式接收数据: ");
        do {
            String next = scanner.next();
            if (next.equals("q")) {
                return;
            }
            System.out.println("Scanner.next(): " + next);
        } while (scanner.hasNext());
    }

    /*
        1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
        2、可以获得空白。
     */
    public static void Scanner_nextLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scanner.nextLine方式接收数据: ");
        do {
            String line = scanner.nextLine();
            System.out.println("Scanner.nextLine(): " + line);
            if (line.equals("q")) {
                return;
            }
        } while (scanner.hasNextLine());
    }

    public static void sum() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        System.out.println("Input int number for sum: ");
        do {
            String s = scanner.next();
            if (s.equals("q")) {
                return;
            } else {
                int input = Integer.parseInt(s);
                sum += input;
                System.out.println("Input: " + input);
                System.out.println("Now the sum is: " + sum);
            }

        } while (scanner.hasNext());
    }

    public static void average() {
        Scanner scanner = new Scanner(System.in);
        double sum = 0d;
        double average;
        int count = 0;
        System.out.println("Input double number for average: ");
        do {
            String s = scanner.next();
            if (s.equals("q")) {
                return;
            } else {
                count++;
                double input = Double.parseDouble(s);
                sum += input;
                average = sum / count;
                System.out.println("Sum: " + sum);
                System.out.println("Average: " + average);
            }
        } while (scanner.hasNext());
    }


    public static void main(String[] args) {
        Scanner_next();
        Scanner_nextLine();
        sum();
        average();
    }
}
