package shelper.testng.screemshot;

import org.apache.commons.io.FileUtils;
import shelper.environment.Environment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *用来做动态截图的 IInvokedMethodListener
 * @author mingxing.sun
 */
public class ActiveScreemShotFolder implements Runnable{
    private String bufferfolderpath= Environment.get("Testng.ActiveScreenShot.outputLocation");
    //截图的时间间隔，单位为秒；默认情况下每隔1秒执行一次抓屏操作
    private double snapInterval = Double.parseDouble(Environment.get("Testng.ActiveScreenShot.snapInterval"));
    //伪视频格式，即生成的动态图片格式。
    private String pseudoVideoFormat = ".gif";
    //伪视频的重放速度，可选值为1，2，3。分别表示快速、中速和慢速。
    private int replaySpeed =Integer.parseInt(Environment.get("Testng.ActiveScreenShot.replaySpeed"));
    //是否循环播放
    private boolean replayIndefinitely = Boolean.parseBoolean(Environment.get("Testng.ActiveScreenShot.replayIndefinitely"));
    //停止标记
    private boolean stopflag=false;
   //压制结束停止标记
    private boolean yastopflag=false;
    //图片序号标记
    private int count=0;
    //文件名
    private String filename="";
    //方法返回的测试结果
    private boolean testresult=false;
    //运行状态
    private boolean finishstatus=false;
    public ActiveScreemShotFolder(String File){
       super();
       filename=File;
       String[] methodpa=filename.split("\\\\");
       filename=methodpa[methodpa.length-1];
       String methodpath="";
       for(int a=0;a<methodpa.length-1;a++){
           methodpath=methodpath+methodpa[a]+"\\";
       }
       bufferfolderpath=bufferfolderpath+methodpath;
       File temp=new File(bufferfolderpath);
       if(!temp.exists()){
           temp.mkdirs();
       }
       File temptemp=new File(bufferfolderpath+"temp\\");
       if(!temptemp.exists()){
           temptemp.mkdirs();
       }       
    }
/**
 * 获得结束状态
 */
    public boolean getFinishStatus(){
        return finishstatus;
    }

/**
 *  将新截图加入到buffer中
 */
    public void pushScreenshot()
    {
        try {
            //获得当前屏幕的截图对象
                BufferedImage image = getCurrentScreen();
                if(null==image)
                    return;
                File file=new File(bufferfolderpath+"temp\\"+count+pseudoVideoFormat);
                ImageIO.write(image,pseudoVideoFormat.substring(1),file);
                count++;
        } catch (IOException ex) {
            Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
/**
 *  获得当前的屏幕截图
 * @return BufferedImage - 屏幕的截图
 */
    public BufferedImage getCurrentScreen()
    {
    try{
            //获得屏幕大小
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;
            BufferedImage capture = null;
            //获得当前屏幕范围
            Rectangle area = new Rectangle(0, 0, width, height);
            Robot robot = new Robot();
            //截取当前屏幕范围内的内容，返回BufferedImage对象
            capture = robot.createScreenCapture(area);
            return capture;
        }
        catch (Exception e) {
            Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }    

    public void run() {
        new Thread(new GenFile(filename)).start();
        while(!stopflag) 
        { 
            try 
            { 				 
                //将当前屏幕的截图存入缓冲区
                pushScreenshot();
                //睡眠一段时间
                Thread.sleep(new Double(1000*this.snapInterval).longValue());
            }
            catch(Exception e) 
            { 
                 Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        //最后在来一次。将当前屏幕的截图存入缓冲区
        pushScreenshot();
        yastopflag=true;
    }
    
    public void stop(boolean result){
        this.testresult=result;
        stopflag=true;
    }
    
    private class GenFile implements Runnable{
        BufferedImage bi = null;
        //新建一个AnimatedGifEncoder对象，用来生成动态的GIF文件
        AnimatedGifEncoder pseudoVideoGenerator = new AnimatedGifEncoder();
        //设置GIF文件是否循环显示各帧
        int repeat = replayIndefinitely?0:1;
        //当前图片序号
        int nowcount=0;
        GenFile(String File){
            super();
            pseudoVideoGenerator.setRepeat(repeat); 
            //设置输出位置，以当前时间为文件名
            pseudoVideoGenerator.start(bufferfolderpath+File+pseudoVideoFormat);
        }
        
        
        public void run() {
            do{
                try {
                    //Thread.sleep(new Double(1000*(snapInterval+1)).longValue());
                    Thread.sleep(new Double(1000).longValue());
                } catch (InterruptedException ex) {
                    Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, null, ex);
                }
                    //int size = bufferlist.size();
                int size=new File(bufferfolderpath+"temp\\").list(new DirFilter(pseudoVideoFormat)).length;
                try{
                    //依次将缓冲区内的所有截图按指定间隔加入AnimatedGifEncoder对象
                    for(int i=nowcount;i<size;i++){
                        File   file   =   new   File(bufferfolderpath+"temp\\"+i+pseudoVideoFormat);
                        //System.out.println(bufferfolderpath+"temp\\"+i+pseudoVideoFormat);
                        try{
                        //System.out.println(file.getPath()+file.getName());
                        //等待文件生成
                        long lenth1;
                        long lenth2;
                        do{
                        lenth1=file.length();
                        //System.out.println(lenth1);
                        Thread.sleep(10);
                        lenth2=file.length();
                        //System.out.println(lenth2);
                        }while(lenth1!=lenth2);
                        bi   =   ImageIO.read(file);
                        pseudoVideoGenerator.setDelay(200*replaySpeed);
                        pseudoVideoGenerator.addFrame(bi);
                        }catch(EOFException e){
                             Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.INFO, "missing pic");
                        }
                    }
                    
                }catch(Exception e){
                    Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, "gif generate error", e);
                } 
                nowcount=size;
            }while(!yastopflag);
//在压制结束标记出现后在来一轮
                int size=new File(bufferfolderpath+"temp\\").list(new DirFilter(pseudoVideoFormat)).length;
                try{
                   //依次将缓冲区内的所有截图按指定间隔加入AnimatedGifEncoder对象
                    for(int i=nowcount;i<size;i++){
                        File   file   =   new   File(bufferfolderpath+"temp\\"+i+pseudoVideoFormat);
                        //System.out.println(bufferfolderpath+"temp\\"+i+pseudoVideoFormat);
                        bi   =   ImageIO.read(file);
                        pseudoVideoGenerator.setDelay(200*replaySpeed);
                        pseudoVideoGenerator.addFrame(bi);
                    }

                }catch(Exception e){
                    Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, null, e);
                } 

             //生成动态的GIF文件
            pseudoVideoGenerator.finish(); 	
            //清理temp文件
//           File temp=new File(bufferfolderpath+"temp\\");
//           if(temp.exists()){
//                try {
//                    FileUtils.deleteDirectory(temp);
//                } catch (IOException ex) {
//                    Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, null, ex);
//                }
//           }            
            //如果测试结果是对的那么清理所有文件
            if(testresult==true){
               File methodresult=new File(bufferfolderpath);
               if(methodresult.exists()){
                    try {
                        FileUtils.deleteDirectory(methodresult);
                    } catch (IOException ex) {
                        Logger.getLogger(ActiveScreemShotFolder.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }                   
            }
            //完全结束标记
            finishstatus=true;
        }
    }
    
   public static void main(String args[]) throws InterruptedException{
        Environment.set();
        ActiveScreemShotFolder a1=new ActiveScreemShotFolder("test\\test\\test");
        Thread t1=new Thread(a1);
        t1.start();
        Thread.sleep(6000);
        a1.stop(false);
//        System.out.println(Boolean.parseBoolean(Environment.get("Testng.ActiveScreemShot.replayIndefinitely")));

    }
}
