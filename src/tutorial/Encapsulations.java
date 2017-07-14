/**
 * Created by Oliver on 29/06/2017.
 */
public class Encapsulations {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(20);
        person.setName("Alice");
        System.out.printf("I am %s and %d years old.", person.getName(), person.getAge());
    }
}

class Person {
    /* 将 name 和 age 属性设置为私有的，只能本类才能访问，其他类都访问不了，如此就对信息进行了隐藏。
     */
    private String name;
    private int age;

    /* 公共方法访问，也就是创建一对赋取值方法，用于对私有属性的访问
    public方法是外部类访问该类成员变量的入口
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
