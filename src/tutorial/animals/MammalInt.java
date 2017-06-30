package animals;

/**
 * Created by Oliver on 30/06/2017.
 */
public class MammalInt implements Animal {
    public void eat() {
        System.out.println("animals.Mammal eats");

    }

    public void travel() {
        System.out.println("animals.Mammal travels");

    }

    public int noOfLegs() {
        return 0;
    }

    public static void main(String[] args) {
        MammalInt mammalInt = new MammalInt();
        mammalInt.eat();
        mammalInt.travel();
    }
}
