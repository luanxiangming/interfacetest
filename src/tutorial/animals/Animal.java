package animals;

/**
 * Created by Oliver on 30/06/2017.
 */
public interface Animal {
    /*接口是隐式抽象的，当声明一个接口的时候，不必使用abstract关键字。
    接口中每一个方法也是隐式抽象的，声明时同样不需要abstract关键子。
    接口中的方法都是公有的。
     */

    void eat();

    void travel();
}
