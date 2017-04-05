package shelper.autoit3;
import org.openqa.selenium.WebDriver;
import shelper.environment.Environment;
import shelper.webdriver.GetDriver;

/**
 * @author mingxing.sun
 */
public class AutoIttest {
    public static void main(String argsp[]){
			Environment.set();
			for(int i=0;i<3;i++){
                            System.out.println("before trun:" + i);
			WebDriver driver=GetDriver.get(Environment.get("Selenium.Explorer"));
			driver.get("www.baidu.com");
			//GetDriver.quit(driver);
                        System.out.println("11111111111111111111111111111111111111111111111111");
			Autoit3.run("closeallwindows.au3");
                        System.out.println("after trun:" + i);
			}
//        File endtag=new File("d:\\testtesttest.end");
//        System.out.println(endtag.exists());
    }
}
