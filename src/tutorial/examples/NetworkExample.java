package examples;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver on 10/07/2017.
 */
public class NetworkExample {
    public static void main(String[] args) {

        File output = new File("src/tutorial/examples/output.html");

        /* 获取指定主机的IP地址 */
        InetAddress("www.jd.com");

        /* 查看端口是否已使用 */
        socketPort("127.0.0.1");

        /* 获取本机ip地址及主机名 */
        InetAddress_localhost();

        /* 获取远程文件大小 */
        getContentLength("http://www.runoob.com/wp-content/themes/runoob/assets/img/newlogo.png");

        /* 使用 net.Socket 类的 getInetAddress() 方法来连接到指定主机 */
        getInetAddress("www.jd.com");

        /* 使用 net.URL 类的 URL() 构造函数来抓取网页 */
        url_openStream("http://www.baidu.com", output);

        /* 获取 URL 响应头信息 */
        URLConnection_getDate("http://www.jd.com");

        /* 获取 URL 响应头信息 */
        URLConnection_getHeaderFields("http://www.jd.com");

        /* 解析 URL */
        url_info("http://www.runoob.com/html/html-tutorial.html");

    }

    public static void InetAddress(String hostName) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(hostName);

        } catch (UnknownHostException u) {
            System.exit(2);
        }
        System.out.println(inetAddress.getHostName() + ": " + inetAddress.getHostAddress());
    }

    public static void socketPort(String host) {
        Socket Skt;
        for (int i = 8000; i < 8100; i++) {
            try {
                System.out.println("查看 " + i);
                Skt = new Socket(host, i);
                System.out.println("端口 " + i + " 已被使用");
            } catch (UnknownHostException e) {
                System.out.println("Exception occured" + e);
                break;
            } catch (IOException e) {
            }
        }
    }

    public static void InetAddress_localhost() {
        InetAddress inetAddress = null;
        try {
            // 使用 InetAddress 类的 getLocalAddress() 方法获取本机ip地址及主机名
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException u) {
            u.printStackTrace();
        }
        System.out.println("LocalHost Name: " + inetAddress.getHostName());
        System.out.println("LocalHost Address: " + inetAddress.getHostAddress());
    }

    public static void getContentLength(String URL) {
        try {
            URL url = new URL(URL);
            URLConnection urlConnection = url.openConnection();
            System.out.println("Content Length: " + urlConnection.getContentLength() + " Bytes");
        } catch (MalformedURLException m) {
            m.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void getInetAddress(String url) {
        try {
            Socket socket = new Socket(url, 80);
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("连接到: " + inetAddress);
            socket.close();
        } catch (IOException i) {
            System.out.println("无法连接到: " + url);
            i.printStackTrace();
        }
    }

    public static void url_openStream(String URL, File output) {
        try {
            URL url = new URL(URL);
            // InputStreamReader：是字节流通往字符流的桥梁，使用指定的charset读取字节并解码成字符
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            bufferedReader.close();
        } catch (MalformedURLException m) {
            m.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void URLConnection_getDate(String URL) {
        try {
            URL url = new URL(URL);
            URLConnection urlConnection = url.openConnection();
            long time = urlConnection.getDate();
            if (time == 0) {
                System.out.println("无法获取信息");
            } else {
                System.out.println("URLConnection.getDate: " + new Date(time));
            }
        } catch (MalformedURLException m) {
            m.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void URLConnection_getHeaderFields(String URL) {
        try {
            URL url = new URL(URL);
            URLConnection urlConnection = url.openConnection();
            Map<String, List<String>> fields = urlConnection.getHeaderFields();
            for (String key : fields.keySet()) {
                System.out.printf("%s: %s%n", key, fields.get(key));
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void url_info(String URL) {
        try {
            URL url = new URL(URL);
            System.out.println("URL: " + url.toString());
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("File: " + url.getFile());
            System.out.println("Host: " + url.getHost());
            System.out.println("Path: " + url.getPath());
            System.out.println("Port: " + url.getPort());
            System.out.println("Default port: " + url.getDefaultPort());
        } catch (MalformedURLException m) {
            m.printStackTrace();
        }
    }

}
