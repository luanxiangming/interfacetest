/**
 * Created by Oliver on 29/06/2017.
 */
public class OverrideOverload {
    /*
    方法的重写(Overriding)和重载(Overloading)是java多态性的不同表现，
    重写是父类与子类之间多态性的一种表现，重载可以理解成多态的具体表现形式。
    */
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();
        animal.move();
        dog.move();
        dog.bark();
        dog.bark("woof!");
    }

}

class Animal {
    /* 子类和父类在同一个包中，那么子类可以重写父类所有方法，除了声明为private和final的方法。
       子类和父类不在同一个包中，那么子类只能够重写父类的声明为public和protected的非final方法。
     */
    public void move() {
        System.out.println("Animal.move()");
    }
}

class Dog extends Animal {
    /* 重写(override)规则
    参数列表必须完全与被重写方法的相同；
    返回类型必须完全与被重写方法的返回类型相同；
    访问权限不能比父类中被重写的方法的访问权限更低。例如：如果父类的一个方法被声明为public，那么在子类中重写该方法就不能声明为protected。
    父类的成员方法只能被它的子类重写。
    声明为final的方法不能被重写。
    声明为static的方法不能被重写，但是能够被再次声明。
    子类和父类在同一个包中，那么子类可以重写父类所有方法，除了声明为private和final的方法。
    子类和父类不在同一个包中，那么子类只能够重写父类的声明为public和protected的非final方法。
    重写的方法能够抛出任何非强制异常，无论被重写的方法是否抛出异常。但是，重写的方法不能抛出新的强制性异常，或者比被重写方法声明的更广泛的强制性异常，反之则可以。
    构造方法不能被重写。
    如果不能继承一个方法，则不能重写这个方法。
    */
    public void move() {
        super.move();
        System.out.println("Dog.move()");
    }

    public void bark() {
        System.out.println("Dog.bark()");
    }

    /* 重载(overloading)规则
    被重载的方法必须改变参数列表(参数个数或类型或顺序不一样)；
    被重载的方法可以改变返回类型；
    被重载的方法可以改变访问修饰符；
    被重载的方法可以声明新的或更广的检查异常；
    方法能够在同一个类中或者在一个子类中被重载。
    无法以返回值类型作为重载函数的区分标准。
    */
    public void bark(String s) {
        System.out.println(s);
    }

}