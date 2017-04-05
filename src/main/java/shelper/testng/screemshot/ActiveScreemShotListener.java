package shelper.testng.screemshot;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import shelper.common.MyDate;
import shelper.environment.Environment;
import shelper.utils.IpUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mingxing.sun
 */
public class ActiveScreemShotListener  implements IInvokedMethodListener {
    ActiveScreemShotFolder a1;
    String filepath="";
    private static File linkfile;
    
    public void beforeInvocation(IInvokedMethod iim, ITestResult itr) {
        if(!iim.getTestMethod().isTest()){
            return;
        }
        // 目录\\目录\\文件 的输入规则.
        //如果方法描述存在则用方法描述，不然就用方法名
        String fname=iim.getTestMethod().getDescription();
        if(fname==null || fname.trim().equals("")){
            fname=iim.getTestMethod().getMethodName();
        }

        //设置存放连接的文件
        if(linkfile==null){
            String linkfilepath= Environment.get("Testng.ActiveScreenShot.outputLocation")+"errorlink";
            linkfile=new File(linkfilepath);
            try {
                List<String> content=new ArrayList();
                content.add("错误回放文件地址列表<br>");
                FileUtils.writeLines(linkfile, content);
            } catch (IOException ex) {
                Logger.getLogger(ActiveScreemShotListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        filepath=MyDate.getStringDateFloder()+"\\"+fname+"_"+MyDate.getStringTodayFloder()+"\\";
        a1=new ActiveScreemShotFolder(filepath+fname);
        //a1=new ActiveScreemShotFolder(iim.getTestMethod().getMethodName()+"\\"+iim.getTestMethod().getMethodName());
        Thread t1=new Thread(a1);
        t1.start();
    }

    public void afterInvocation(IInvokedMethod iim, ITestResult itr) {
        if(!iim.getTestMethod().isTest()){
            return;
        }
        a1.stop(itr.isSuccess());
        while(!a1.getFinishStatus()){
        }
        if(!itr.isSuccess()){
            String fname=iim.getTestMethod().getDescription();
            if(fname==null || fname.trim().equals("")){
                fname=iim.getTestMethod().getMethodName();
            }
            //如果是http连接，那么切换一下斜杠
            String tempfilepath="";
            if(Environment.get("Testng.ActiveScreenShot.viewLocation").contains("http")){
                tempfilepath= StringUtils.replaceChars(filepath, "\\", "/");
            }else{
                tempfilepath=filepath;
            }

            if(Environment.get("Testng.ActiveScreenShot.viewLocation").contains("localip")){
                String tempstr[]= Environment.get("Testng.ActiveScreenShot.viewLocation").split("localip");
                Environment.set("Testng.ActiveScreenShot.viewLocation",tempstr[0]+ IpUtils.getLocalHostIP() + tempstr[1]);
            }

            String errorlinkstr=fname+" has error .View Error replay please click --<br> <a href=\""+ Environment.get("Testng.ActiveScreenShot.viewLocation")+tempfilepath+"\">"+fname+"</a><br>";
            Reporter.log(errorlinkstr,true);
            //追加文件
            try {
                List<String> content;
                content = FileUtils.readLines(linkfile);
                content.add(errorlinkstr) ;
                FileUtils.writeLines(linkfile,content);
            } catch (IOException ex) {
                Logger.getLogger(ActiveScreemShotListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
