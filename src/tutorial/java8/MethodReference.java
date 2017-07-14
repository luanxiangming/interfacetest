package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Oliver on 13/07/2017.
 */
public class MethodReference {
    /**
     * 方法引用通过方法的名字来指向一个方法。
     * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
     * 方法引用使用一对冒号(::)。
     * 下面，我们以定义了4个方法的Car这个类作为例子，区分Java中支持的4种不同的方法引用
     */
    public static void main(String[] args) {
        // 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);

        // 静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car::collide);

        // 特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(Car::repair);

        // 特定对象的方法引用：它的语法是instance::method实例如下
        Car police = Car.create(Car::new);
        cars.forEach(police::follow);

        // 将 System.out::println 方法作为静态方法来引用。
        cars.forEach(System.out::println);
    }
}

class Car {
    static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }

    static void collide(Car car) {
        System.out.println("Collided: " + car.toString());
    }

    void follow(Car another) {
        System.out.println(this + " following: " + another.toString());
    }

    void repair() {
        System.out.println("Repairing: " + this.toString());
    }
}