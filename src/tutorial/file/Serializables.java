package file;

import employee.Employee;

import java.io.*;

/**
 * Created by Oliver on 02/07/2017.
 */
public class Serializables {
    public static void main(String[] args) {
        serialize();
        deserialize();
    }

    public static void serialize() {
        /**
         * ObjectOutputStream 类用来序列化一个对象
         * 如下例子实例化了一个 Employee 对象，并将该对象序列化到一个文件中。
         */
        Employee employee = new Employee("Austin");
        employee.setAge(30);
        employee.setDesignation("Engineer");
        employee.setSalary(30000);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/tutorial/file/employee.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employee);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved as: src/tutorial/file/employee.ser");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserialize() {
        /**
         * 下面的 DeserializeDemo 程序实例了反序列化，employee.ser 存储了 Employee 对象。
         */
        Employee employee;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/tutorial/file/employee.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employee = (Employee) objectInputStream.readObject();  //readObject() 方法的返回值被转化成 Employee 引用
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            /**
             对于 JVM 可以反序列化对象，它必须是能够找到字节码的类。
             如果JVM在反序列化对象的过程中找不到该类，则抛出一个 ClassNotFoundException 异常
             */
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialize Employee...");
        System.out.println("Employee name: " + employee.getName());
        System.out.println("Employee age: " + employee.getAge());
        System.out.println("Employee designation: " + employee.getDesignation());
        System.out.println("Employee salary: " + employee.getSalary());

    }
}
