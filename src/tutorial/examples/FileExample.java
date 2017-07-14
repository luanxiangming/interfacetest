package examples;

import java.io.*;
import java.util.Date;

/**
 * Created by Oliver on 09/07/2017.
 */
public class FileExample {
    public static void main(String[] args) {

        File dir = new File("src/tutorial/examples");
        File file = new File(dir.getPath() + File.separator + "file.txt");
        File copy = new File(dir.getPath() + File.separator + "copy.txt");
        File rename = new File(dir.getPath() + File.separator + "rename.txt");
        File newFile = new File(dir.getPath() + File.separator + "new.txt");

        /* 使用 write() 方法向文件写入内容 */
        bufferedWriter();

        /* 使用 readLine() 方法来读取文件内容 */
        bufferedReader();

        /* 使用 BufferedWriter 类的 read 和 write 方法将文件内容复制到另一个文件 */

        copy(file, copy);

        /* 使用 FileWriter 方法向文件中追加数据 */
        append();

        /* 使用 File 类的 createTempFile() 方法来创建临时文件 */
        tmpFile(dir);

        /* 使用 File 类的 fileToChange.lastModified() 和 fileToChange setLastModified() 方法来修改文件最后的修改日期 */
        fileToChange(copy);

        /* 使用 File 类的 file.exists() 和 file.length() 方法来获取文件大小，以字节计算（1KB=1024字节 ） */
        length(file);
        length(copy);

        /* 使用 File 类的 oldName.renameTo(newName) 方法来重命名文件 */
        rename(copy, rename);

        /* 使用 File 类的 file.setReadOnly() 和 file.canWrite() 方法来设置文件只读 */
        readOnly(rename);

        /* 使用 File 类的 File() 构造函数和 file.createNewFile() 方法来创建一个新的文件 */
        newFile(newFile);
        newFile(rename);

        /* 使用 File 类的 filename.compareTo (another filename) 方法来比较两个文件路径是否在同一个目录下 */
        compareTo(newFile, rename);
        compareTo(newFile, newFile);

        /* 删除文件 */
        delete(file);


    }

    public static void bufferedWriter() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/tutorial/examples/file.txt"));
            bufferedWriter.write("1. Java File Examples\n");
            bufferedWriter.close();
            System.out.println("文件创建成功！");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void bufferedReader() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/tutorial/examples/file.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void delete(File file) {
        try {
            if (file.delete()) {
                System.out.println(file.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy(File from, File to) {
        try {
            InputStream inputStream = new FileInputStream(from);
            OutputStream outputStream = new FileOutputStream(to);

            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
            inputStream.close();
            outputStream.close();

            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/tutorial/examples/copy.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("BufferedReader readLine: " + line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void append() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/tutorial/examples/file.txt", true));
            bufferedWriter.write("2. Append content to file");
            bufferedWriter.close();

            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/tutorial/examples/file.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void tmpFile(File dir) {
        try {
            File tmpFile = File.createTempFile("pattern", ".suffix", dir);
            tmpFile.deleteOnExit();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tmpFile));
            bufferedWriter.write("temp string");
            System.out.println("临时文件已创建: " + tmpFile.getName());
            bufferedWriter.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void fileToChange(File file) {
        Date time1 = new Date(file.lastModified());
        System.out.println("lastModified1: " + time1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        file.setLastModified(new Date().getTime());
        Date time2 = new Date(file.lastModified());
        System.out.println("lastModified2: " + time2);
    }

    public static void length(File file) {
        if (file.exists()) {
            System.out.println("file.length: " + file.length());
        }
    }

    public static void rename(File file, File rename) {
        try {
            file.renameTo(rename);
            System.out.printf("%s已重命名: %s%n", file.getName(), rename.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readOnly(File file) {
        System.out.printf("%s can be written: %s%n", file.getName(), file.canWrite());
        file.setReadOnly();
        System.out.printf("%s can be written: %s%n", file.getName(), file.canWrite());
    }

    public static void newFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("New file created: " + file.getName());
            } else {
                System.out.printf("File %s already existed!%n", file.getName());
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void compareTo(File f1, File f2) {
        int i = f1.compareTo(f2);
        if (i == 0) {
            System.out.printf("%s and %s share the file path%n", f1, f2);
        } else {
            System.out.printf("%s and %s have different file path%n", f1, f2);
        }
    }
}
