package shelper.gui;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class of the application.
 */
public class Shelper extends SingleFrameApplication {
    static ShelperView d1;
    static final String[] lock=new String[0];
    static Thread mainthread;
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        d1=new ShelperView(this);
        show(d1);
        d1.setMainThread(mainthread);
        d1.setLock(lock);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DesktopApplication2
     */
    public static Shelper getApplication() {
        return Application.getInstance(Shelper.class);
    }

    public static void start() {
        mainthread=Thread.currentThread();
        launch(Shelper.class, new String[0]);
    }
    
    public static void stop(){
        d1.getApplication().exit();
    }

    
    public static void print(String content){
        while(d1==null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Shelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        d1.refresh(content);
    }

    public static void pause(){

        synchronized(lock){
            try {
                lock.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Shelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
