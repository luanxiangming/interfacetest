import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Oliver on 28/06/2017.
 */
public class IO {
    public static void IO_BufferedReader() throws IOException {
        // 把 System.in 包装在一个 BufferedReader 对象中来创建一个字符流
        char c;
        String s;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input character, 'q' to exit");
        // 使用 BufferedReader 在控制台读取字符
        do {
            c = (char) reader.read();
            System.out.println(c);
        } while (c != 'q');

        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        do {
            s = reader.readLine();
            System.out.println(s);
        } while (!s.equals("end"));
    }

    public static void IO_write() {
        int b;
        b = 'A';
        // write() 方法不经常使用，因为 print() 和 println() 方法用起来更为方便
        System.out.write(b);
        System.out.write('\n');

    }

    public static void main(String[] args) throws IOException {
        IO_BufferedReader();
        IO_write();
    }
}
