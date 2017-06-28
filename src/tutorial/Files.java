import java.io.*;

/**
 * Created by Oliver on 28/06/2017.
 */
public class Files {
    public static void Files_FileOutputStream() throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "failCount.properties";

        FileOutputStream fos = new FileOutputStream(filePath);  // 构建FileOutputStream对象,文件不存在会自动新建
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");

        char[] forWrite = {'中', '文', '输', '入'};
        for (int i = 0; i < forWrite.length; i++) {
            writer.append(forWrite[i]);  // writes the bytes
        }
        writer.close();  //关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
        fos.close();  // 关闭输出流,释放系统资源
    }

    public static void Files_FileInputStream() throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "failCount.properties";

        FileInputStream fis = new FileInputStream(filePath);  // 构建FileInputStream对象
        // 构建InputStreamReader对象,编码与写入相同
        InputStreamReader reader = new InputStreamReader(fis, "UTF-8");

        StringBuffer buffer = new StringBuffer();
        while (reader.ready()) {
            buffer.append((char) reader.read());  // 转成char加到StringBuffer对象中
        }
        System.out.printf("FileInputStream.read(): %d%n", fis.read());
        System.out.println("StringBuffer append char from InputStreamReader: " + buffer.toString());
        reader.close();  // 关闭读取流
        fis.close();  // 关闭输入流,释放系统资源
    }

    public static void Files_mkdirs() {
        String filePath = System.getProperty("user.dir") + File.separator + "/tmp/tmp";
        File f = new File(filePath);
        System.out.println("mkdir: " + f.mkdir());
        System.out.println("mkdirs: " + f.mkdirs());  // mkdirs()方法创建一个文件夹和它的所有父文件夹
    }

    public static void Files_list() {
        String filePath = System.getProperty("user.dir") + File.separator + "src/tutorial";
        File f = new File(filePath);
        if (f.isDirectory()) {
            System.out.println("目录: " + f.getName());
            for (String fileName : f.list()) {
                File file = new File(filePath + "/" + fileName);
                if (!file.isDirectory()) {
                    System.out.println("文件: " + file.getName());
                } else {
                    System.out.println("目录: " + file.getName());
                }
            }
        }
    }

    public static void Files_delete() {
        String filePath = System.getProperty("user.dir") + File.separator + "/tmp/tmp";
        File file = new File(filePath);
        System.out.println("File.delete(): " + file.delete());
    }

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperties());
        Files_FileOutputStream();
        Files_FileInputStream();
        Files_mkdirs();
        Files_list();
        Files_delete();
    }
}
