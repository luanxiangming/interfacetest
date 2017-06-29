/**
 * Created by Oliver on 29/06/2017.
 */
public class Extends {
    public static void main(String[] args) {
        Penguin pen = new Penguin();
        Penguin penguin = new Penguin("James", 7);
        penguin.introduction();

        SubClass subClass1 = new SubClass();
        SubClass subClass2 = new SubClass(100);

    }
}


class Animals {
    private String name;
    private int id;

    Animals() {
        System.out.println("Super constructor without args");
    }

    Animals(String myName, int myId) {
        name = myName;
        id = myId;
    }

    public void eat() {
        System.out.println(name + "正在吃");
    }

    public void sleep() {
        System.out.println(name + "正在睡");
    }

    public void introduction() {
        System.out.println("大家好！我是" + id + "号" + name + ".");
    }
}


class Penguin extends Animals {
    // 如果父类有无参构造器，则在子类的构造器中用super调用父类构造器不是必须的，如果没有使用super关键字，系统会自动调用父类的无参构造器。
    Penguin() {
        // super();
    }

    // 父类的构造器带有参数的，则必须在子类的构造器中显式地通过super关键字调用父类的构造器并配以适当的参数列表
    Penguin(String name, int id) {
        super(name, id);
    }

    public void introduction() {
        super.introduction();
    }
}

class SuperClass {
    int n = 1;

    public SuperClass() {
        System.out.println("SuperClass constructor without args");
    }

    public SuperClass(int n) {
        System.out.println("SuperClass constructor with args: " + n);
        this.n = n;
    }
}

class SubClass extends SuperClass {
    public SubClass() {
        super(300);
        System.out.println("SubClass constructor without args");
    }

    public SubClass(int n) {
        // super();  // 如果父类有无参构造器，则在子类的构造器中用super调用父类构造器不是必须的，如果没有使用super关键字，系统会自动调用父类的无参构造器。
        this();  // 调用当前类的构造方法
        System.out.println("SubClass constructor with args: " + n);

    }
}