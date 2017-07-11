package examples;

import java.io.*;
import java.net.Socket;

/**
 * Created by Oliver on 11/07/2017.
 */
public class Client {
    /**
     * 建立客户端
     * 创建Socket通信，设置通信服务器的IP和Port
     * 建立IO输出流向服务器发送数据消息
     * 建立IO输入流读取服务器发送来的数据消息
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            //构建IO
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //向服务器端发送一条消息
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
            bufferedWriter.flush();

            //读取服务器返回的消息
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg = bufferedReader.readLine();
            System.out.println("服务器: " + msg);

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
