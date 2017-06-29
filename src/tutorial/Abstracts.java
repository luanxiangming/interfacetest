/**
 * Created by Oliver on 29/06/2017.
 */
public class Abstracts {
    public static void main(String[] args) {
        Salary s = new Salary("James Salary", "Shanghai", 7, 20000);
        AbstractEmployee e = new Salary("John Employee", "Tokyo", 9, 30000);

        System.out.println("Call mailCheck using Salary reference --");
        s.mailCheck();

        System.out.println("\nCall mailCheck using Employee reference --");
        e.mailCheck();
    }

}


class Salary extends AbstractEmployee {
    private double salary;

    public Salary(String name, String address, int number, double salary) {
        super(name, address, number);
        setSalary(salary);
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }

    public double getSalary() {
        return this.salary;
    }

    // 任何子类必须重写父类的抽象方法，或者声明自身为抽象类
    public double computePay() {
        System.out.println("Inside Salary computePay");
        return this.salary;
    }

    public void mailCheck() {
        System.out.println("Within mailCheck of Salary class ");
        System.out.println("Mailing a check to " + getName()
                + " " + getAddress());
    }
}

/* 抽象类总结
1. 抽象类不能被实例化，如果被实例化，就会报错，编译无法通过。只有抽象类的非抽象子类可以创建对象。
2. 抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类。
3. 抽象类中的抽象方法只是声明，不包含方法体，就是不给出方法的具体实现也就是方法的具体功能。
4. 构造方法，类方法（用static修饰的方法）不能声明为抽象方法。
5. 抽象类的子类必须给出抽象类中的抽象方法的具体实现，除非该子类也是抽象类。
*/
abstract class AbstractEmployee {
    private String name;
    private String address;
    private int number;

    public AbstractEmployee(String name, String address, int number) {
        System.out.println("Constructing an Employee");
        this.name = name;
        this.address = address;
        this.number = number;
    }

    // 抽象方法只能在抽象类
    public abstract double computePay();

    public void mailCheck() {
        System.out.println("Mailing a check to " + this.name
                + " " + this.address);
    }

    public String toString() {
        return name + " " + address + " " + number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public int getNumber() {
        return number;
    }
}