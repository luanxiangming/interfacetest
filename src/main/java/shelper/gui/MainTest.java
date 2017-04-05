package shelper.gui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Administrator
 */
public class MainTest {
    public static void main(String args[]){
        MainTest m1=new MainTest();
        m1.test();
    }
    void test(){
        Shelper.start();
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        pause();
        System.out.println("1");
        Shelper.print("something");
        Shelper.pause();
        Shelper.print("something");
        Shelper.pause();
        Shelper.print("something");
        Shelper.pause();
        Shelper.print("something");
        Shelper.stop();
    }

    static void pause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}


