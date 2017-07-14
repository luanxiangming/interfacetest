/**
 * Created by Oliver on 25/06/2017.
 */
public class ModifierTest {
    /*
    默认的，也称为 default，在同一包内可见，不使用任何修饰符。
    私有的，以 private 修饰符指定，在同一类内可见。
    共有的，以 public 修饰符指定，对所有类可见。
    受保护的，以 protected 修饰符指定，对同一包内的类和所有子类可见。
    */
    private static int numInstance = 0;

    protected static int getCount() {
        return numInstance;
    }

    protected static void addInstance() {
        numInstance++;
    }

    public ModifierTest() {
        ModifierTest.addInstance();
    }

    public static void main(String[] args) {
        System.out.println("Starting with: " + ModifierTest.getCount());
        for (int i = 0; i < 10; ++i) {
            ModifierTest test = new ModifierTest();

        }
        System.out.println("Created: " + numInstance + " instances");

    }

}
