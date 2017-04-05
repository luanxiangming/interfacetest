package shelper.webdriver;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import shelper.environment.Environment;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Peter
 */
public class SwitchTo {
    public static void frame(WebDriver driver,int id){
        //隐式等待frame出现。通过id定位frame
        for(int i = 0; i<Integer.parseInt(Environment.get("Selenium.waittime")); i++){
            try{
                driver.switchTo().frame(id);
                return;
            }catch(org.openqa.selenium.NoSuchFrameException e){
                Logger.getLogger(SwitchTo.class.getName()).log(Level.INFO, "driver.switchTo().frame("+ id +") not found:. time :" + i);
            } 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new org.openqa.selenium.NoSuchFrameException("driver.switchTo().frame("+ id +")");
    } 

    
    //隐式等待frame出现。通过name定位frame
    public static void frame(WebDriver driver,String name){
        for(int i = 0; i<Integer.parseInt(Environment.get("Selenium.waittime")); i++){
            try{
                driver.switchTo().frame(name);
                return;
            }catch(org.openqa.selenium.NoSuchFrameException e){
                                Logger.getLogger(SwitchTo.class.getName()).log(Level.INFO, "driver.switchTo().frame("+ name +") not found:. time :" + i);
            } 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new org.openqa.selenium.NoSuchFrameException("driver.switchTo().frame("+ name +")");
    }    
    
    //通过windows的title完成switch到windows
    public static void window(WebDriver driver,String title){
        for(int i = 0; i<Integer.parseInt(Environment.get("Selenium.waittime")); i++){
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if(driver.getTitle().equals(title)){
                    return ;
                }
            }
            Logger.getLogger(SwitchTo.class.getName()).log(Level.INFO, "SwitchTo.window("+ title +"). No windows have the title :" + title+"- failed -time:" + i );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new org.openqa.selenium.NoSuchFrameException("SwitchTo.window("+ title +"). No windows have the title :" + title); 
    }        
    
     /*
     * 定位到包含具体element的windows
     */
    public static void window(WebDriver driver,By locater){
        
        //driver.switchTo().window("");
        
        for(int i = 0; i<Integer.parseInt(Environment.get("Selenium.waittime")); i++){
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if(Check.checkElementExist(driver, locater,0)){
                    return ;
                }
            }
        Logger.getLogger(SwitchTo.class.getName()).log(Level.INFO, "could not find the window that have the element :" + locater.toString()+" -time:" + i );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        throw new org.openqa.selenium.NoSuchFrameException("could not find the window that have the element :" + locater.toString() );      
    }    
     /*
     * 定位到包含body中具体字符串的的windows
     */
    public static void windowWithContent(WebDriver driver,String bodycontent){
        for(int i = 0; i<Integer.parseInt(Environment.get("Selenium.waittime")); i++){
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if(StringUtils.contains(driver.findElement(By.tagName("body")).getText(), bodycontent)){
                return;
                }
            }
            
        Logger.getLogger(SwitchTo.class.getName()).log(Level.INFO, "could not find the window thatbody have string:" + bodycontent+" -time:" + i );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        throw new org.openqa.selenium.NoSuchFrameException("could not find the window thatbody have string:" + bodycontent );      
    }       
     /*
     * 定位为指定url的windows
     */
    public static void windowWithUrl(WebDriver driver,String url){
        for(int i = 0; i<Integer.parseInt(Environment.get("Selenium.waittime")); i++){
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if(driver.getCurrentUrl().equalsIgnoreCase(url)){
                return;
                }
            }
            
        Logger.getLogger(SwitchTo.class.getName()).log(Level.INFO, "could not find the window that url is:" + url+" -time:" + i );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SwitchTo.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        throw new org.openqa.selenium.NoSuchFrameException("could not find the window that url is:" + url);      
    }    
}
