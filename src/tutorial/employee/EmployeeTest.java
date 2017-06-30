package employee;

/**
 * Created by Oliver on 25/06/2017.
 */
public class EmployeeTest {
    public static void main(String []args) {
        // java因强制要求类名（唯一的public类）和文件名统一，因此在引用其它类时无需显式声明。在编译时，编译器会根据类名去寻找同名文件。
        Employee A = new Employee("Thomas");
        Employee B = new Employee("Jane");

        A.age = 30;
        A.designation = "Programmer";
        A.salary = 30000;
        A.printEmployee();

        B.age = 28;
        B.designation = "HR";
        B.salary = 20000;
        B.printEmployee();
    }
}
