/**
 * Created by Oliver on 25/06/2017.
 */
public class IfElseSwitch {
    public static void test_if_else() {
        int x = 10;
        if (x < 10) {
            System.out.println("x is a low number");
        } else if (x > 10 && x < 50) {
            System.out.println("x is a medium number");
        } else if (x > 50 && x < 100) {
            System.out.println("x is a high number");
        } else {
            System.out.println("x is out of range");
        }
    }

    public static void test_switch() {
        String grade = "B";
        switch (grade) {
            case "A":
                System.out.println("Excellent grade");
                break;
            case "B":
                System.out.println("Good grade");
                break;
            case "C":
                System.out.println("Mediocre grade");
                break;
            default:
                System.out.println("It's not good");

        }
    }


    public static void main(String[] args) {
        test_if_else();
        test_switch();
    }

}
