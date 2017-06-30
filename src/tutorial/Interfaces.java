import animals.MammalInt;

/**
 * Created by Oliver on 30/06/2017.
 */
public class Interfaces {
    public static void main(String[] args) {
        MammalInt m = new MammalInt();
        m.eat();
        m.travel();
    }

}

interface AnimalInterface {
    /*接口是隐式抽象的，当声明一个接口的时候，不必使用abstract关键字。
    接口中每一个方法也是隐式抽象的，声明时同样不需要abstract关键子。
    接口中的方法都是公有的。
     */
    void eat();

    void travel();

}

interface Sports {
    void setHomeTeam(String name);

    void setVisitingTeam(String name);
}

/* 实现Football接口的类需要实现五个方法，其中两个来自于Sports接口 */
interface Football extends Sports {
    void homeTeamScored(int points);

    void visitingTeamScored(int points);

    void endOfQuarter(int quarter);
}

/* Hockey接口自己声明了四个方法，从Sports接口继承了两个方法，这样，实现Hockey接口的类需要实现六个方法 */
interface Hockey extends Sports {
    void homeGoalScored();

    void visitingGoalScored();

    void endOfPeriod(int period);

    void overtimePeriod(int ot);
}

/* 标记接口主要用于以下两种目的：
1. 建立一个公共的父接口：
正如EventListener接口，这是由几十个其他接口扩展的Java API，你可以使用一个标记接口来建立一组接口的父接口。
例如：当一个接口继承了EventListener接口，Java虚拟机(JVM)就知道该接口将要被用于一个事件的代理方案。
2. 向一个类添加数据类型：
这种情况是标记接口最初的目的，实现标记接口的类不需要定义任何接口方法(因为标记接口根本就没有方法)，但是该类通过多态性变成一个接口类型。*/
interface FlagInterface {
}
