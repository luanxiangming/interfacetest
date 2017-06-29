import java.io.*;
import java.rmi.RemoteException;

/**
 * Created by Oliver on 28/06/2017.
 */
public class Exceptions {
    public static void Exception_Single_catch() {
        int[] num = new int[2];
        try {
            System.out.println(num[5]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    public static void Exception_Multi_catch() {
        String path = System.getProperty("user.dir") + File.separator + "failCount.properties";

        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
            int x = reader.read();
            System.out.println(x);
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void Exception_throw() {
        try {
            throw new RemoteException();
        } catch (RemoteException r) {
            System.out.println("RemoteException happened: ");
            r.printStackTrace();
        }
    }

    public static void Exception_finally() {
        int[] nums = new int[2];
        try {
            System.out.println(nums[5]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException->> " + e);
        } finally {
            System.out.println("Finally block is executed.");
        }
    }

    static double balance = 100;

    public static void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance) {
            balance -= amount;
        } else {
            double deficiency = amount - balance;
            throw new InsufficientFundsException(deficiency);
        }
    }

    public static void Exception_InsufficientFundsException() {
        try {
            withdraw(500);
        } catch (InsufficientFundsException e) {
            System.out.println("Sorry, but you are short $"
                    + e.getDeficiency());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Exception_Single_catch();
        Exception_Multi_catch();
        Exception_throw();
        Exception_finally();
        Exception_InsufficientFundsException();
    }
}

class InsufficientFundsException extends Exception {
    private double deficiency;

    public InsufficientFundsException(double deficiency) {
        this.deficiency = deficiency;
    }

    public double getDeficiency() {
        return deficiency;
    }
}
