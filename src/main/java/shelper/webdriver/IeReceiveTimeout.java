package shelper.webdriver;

import org.apache.commons.io.FileUtils;
import shelper.autoit3.Autoit3;
import shelper.environment.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *修改注册表，设定ie的response超时
 * @author peter.sun
 */
public class IeReceiveTimeout {
    public static void set(int time){
        try {
            if(new File(Environment.get("Selenium.resource") + "receivetimesetting.reg2").exists()){
                FileUtils.forceDelete(new File(Environment.get("Selenium.resource") + "receivetimesetting.reg2"));
            }
            makeRegFile(time);
            Runtime.getRuntime().exec("cmd /C " + Environment.get("Selenium.resource") + "receivetimesetting.reg");
            Autoit3.run("注册表导入确认.au3");
            FileUtils.forceDeleteOnExit(new File(Environment.get("Selenium.resource") + "receivetimesetting.reg2"));
            Logger.getLogger(IeReceiveTimeout.class.getName()).log(Level.INFO,"设置IeReceiveTimeout：" + time + "秒" );
        } catch (IOException ex) {
            Logger.getLogger(IeReceiveTimeout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void makeRegFile(int time){
        try {
            String time1=Integer.toHexString(time*1000);
            List<String> l1=new ArrayList();
            l1.add("Windows Registry Editor Version 5.00");
            l1.add("[HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings]");
            l1.add("\"ReceiveTimeout\"=dword:"+time1);
            //System.out.println(Environment.get("Selenium.resource")+"receivetimesetting.reg");
            FileUtils.writeLines(new File(Environment.get("Selenium.resource")+"receivetimesetting.reg"),l1);
        } catch (IOException ex) {
            Logger.getLogger(IeReceiveTimeout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public static void main(String args[]){
//        Environment.set();
//        makeRegFile(1);
//    }
}
