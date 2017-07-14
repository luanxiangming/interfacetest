package examples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Oliver on 11/07/2017.
 */
public class Server {
    /**
     * 建立服务器端
     * 服务器建立通信ServerSocket
     * 服务器建立Socket接收客户端连接
     * 建立IO输入流读取客户端发送的数据
     * 建立IO输出流向客户端发送数据消息
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("启动服务器...");
            Socket socket = serverSocket.accept();
            System.out.printf("客户端: %s 连接到服务器%n", socket.getInetAddress());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = bufferedReader.readLine();
            System.out.println("客户端: " + msg);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(msg + "\n");
            bufferedWriter.flush();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
