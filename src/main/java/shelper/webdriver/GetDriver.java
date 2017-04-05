/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shelper.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import shelper.autoit3.Autoit3;
import shelper.environment.Environment;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class GetDriver {
    public static WebDriver get(String drivername){
        if (drivername.equals("ie")){
            WebDriver dr=new InternetExplorerDriver();
            dr.manage().timeouts().implicitlyWait(Integer.parseInt(Environment.get("Selenium.waittime")), TimeUnit.SECONDS) ;
            return dr;
        }else if(drivername.equals("firefox")){
            WebDriver dr=new org.openqa.selenium.firefox.FirefoxDriver();
            dr.manage().timeouts().implicitlyWait(Integer.parseInt(Environment.get("Selenium.waittime")), TimeUnit.SECONDS) ;
            return dr;            
        }else{
            throw new IllegalStateException("selected explorer is not supported");
        }
    }
    public static void quit(WebDriver driver){
        Set<String> windows=driver.getWindowHandles();
        for(String a:windows){
            driver.switchTo().window(a).close();
        }
        Autoit3.run("closeallwindows.au3");
        Environment.stopStartTag();
        try {
            Thread.sleep(15);
        } catch (InterruptedException ex) {
            Logger.getLogger(GetDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
