package employee;

/**
 * Created by Oliver on 25/06/2017.
 */
public class Employee {
    int age;
    double salary;
    String name;
    String designation;

    public Employee(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void printEmployee() {
        System.out.println("employee.Employee name: " + this.name);
        System.out.println("employee.Employee age: " + this.age);
        System.out.println("employee.Employee designation: " + this.designation);
        System.out.println("employee.Employee salary: " + this.salary);
    }

}