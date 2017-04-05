package shelper.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import shelper.environment.Environment;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author peter.sun
 */
public class Check {
    /*
     * 检查是否正在指定title的页面上
     */
    public static void Title(WebDriver Driver,String Title){
       int tag=0;
       int index=0;
       while(tag==0 && index<Integer.parseInt(Environment.get("Selenium.waittime"))){
           if (Title.equals(Driver.getTitle())){
               return;
           }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Check.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
           index++;
       }
       throw new IllegalStateException("The page title is not "+ Title + "but :" + Driver.getTitle());
    }

    
    /*
     * 立即检查指定的locater元素是否存在当前driver的页面上
     */
    public static boolean checkElementExist(WebDriver dr,By locater){
            return checkElementExist(dr,locater,Integer.parseInt(Environment.get("Selenium.waittime")));
    }      
    /*
     * 检查指定的locater元素是否存在当前driver的页面上，在time秒后不存在的话返回false。如果存在则立即返回true
     */    
    public static boolean checkElementExist(WebDriver dr,By locater,int time){
        dr.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        for(int i=0;i<time;i++ ){
            try{
                dr.findElement(locater);
                dr.manage().timeouts().implicitlyWait(Integer.parseInt(Environment.get("Selenium.waittime")), TimeUnit.SECONDS);
                return true;
            }catch(Exception e){
                }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Check.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dr.manage().timeouts().implicitlyWait(Integer.parseInt(Environment.get("Selenium.waittime")), TimeUnit.SECONDS);
        return false;
    }    
}
