package shelper.testng.screemshot;

import shelper.common.MyDate;
import shelper.environment.Environment;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *用来做动态截图的 IInvokedMethodListener
 * @author mingxing.sun
 */
public class ActiveScreemShot implements Runnable{
    private List bufferlist=new ArrayList(); 
    //截图的时间间隔，单位为秒；默认情况下每隔1秒执行一次抓屏操作
    private double snapInterval = Double.parseDouble(Environment.get("Testng.ActiveScreenShot.snapInterval"));
   //伪视频输出位置
    private String outputLocation =Environment.get("Testng.outputLocation");  
    //伪视频格式，即生成的动态图片格式。
    private String pseudoVideoFormat = ".gif";
    //伪视频的重放速度，可选值为1，2，3。分别表示快速、中速和慢速。
    private int replaySpeed =Integer.parseInt(Environment.get("Testng.replaySpeed"));
    //是否循环播放
    private boolean replayIndefinitely = false;
    //停止标记
    private boolean stopflag=false;

/**
 *  将新截图加入到buffer中
 */
    public void pushScreenshot()
    {
    //获得当前屏幕的截图对象
        BufferedImage image = getCurrentScreen();
        if(null==image)
            return;
        bufferlist.add(image);
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
            Logger.getLogger(ActiveScreemShot.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }    

    public void generatePseudoVideo(String filename)
    {
        int size = bufferlist.size();
        if(size<1)
            return;  
        try{ 			
            BufferedImage bi = null;
            //新建一个AnimatedGifEncoder对象，用来生成动态的GIF文件
            AnimatedGifEncoder pseudoVideoGenerator = new AnimatedGifEncoder(); 
            //设置GIF文件是否循环显示各帧
            int repeat = replayIndefinitely?0:1;
            pseudoVideoGenerator.setRepeat(repeat); 
            //设置输出位置，以当前时间为文件名
           pseudoVideoGenerator.start(outputLocation+filename+MyDate.getStringToday()+pseudoVideoFormat);
           //依次将缓冲区内的所有截图按指定间隔加入AnimatedGifEncoder对象
            for(int i=0;i<size;i++){ 			
                bi = (BufferedImage)(bufferlist.get(i));
                pseudoVideoGenerator.setDelay(200*replaySpeed); 
                pseudoVideoGenerator.addFrame(bi); 
            }
            //生成动态的GIF文件
            pseudoVideoGenerator.finish(); 	
        }catch(Exception e){ 
            Logger.getLogger(ActiveScreemShot.class.getName()).log(Level.SEVERE, null, e);
        } 
        //释放bufferlist
        bufferlist=null;
    }

    public void run() {
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
                 Logger.getLogger(ActiveScreemShot.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void stop(){
        stopflag=true;
    }
    public static void main(String args[]) throws InterruptedException{
        Environment.set();
        ActiveScreemShot a1=new ActiveScreemShot();
        Thread t1=new Thread(a1);
        t1.start();
        Thread.sleep(5000);
        a1.stop();
        a1.generatePseudoVideo("test3");
    }
}
