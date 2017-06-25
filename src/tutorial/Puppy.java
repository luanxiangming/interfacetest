/**
 * Created by Oliver on 25/06/2017.
 */
public class Puppy {
    int age;

    public Puppy(String name) {
        System.out.println("My name is " + name);
    }

    public int getAge() {
        System.out.println("Puppy is " + age + " years old.");
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String []args) {
        Puppy puppy = new Puppy("Tommy");
        puppy.setAge(5);
        puppy.getAge();
        puppy.age = 10;
        System.out.println("Puppy is " + puppy.age + " years old.");
    }
}
