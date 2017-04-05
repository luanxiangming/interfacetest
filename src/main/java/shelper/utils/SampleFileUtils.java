package shelper.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *正对同学们提出的需求提供简单的文件操作
 * @author mingxing.sun
 */
public class SampleFileUtils {
    /**
     * 向文件中追加一行。文件不存在的时候创建文件。
     * @param filepath  文件路径
     * @param line  要追加的内容
     */
    public static void appendLine(String filepath,String line){
        try {
            File f = new File(filepath);
             List<String> content=new ArrayList();
            if(f.exists()){
                //文件存在的话就读内容。
                content = FileUtils.readLines(f);
            }
            content.add(line);
            FileUtils.writeLines(f, content);
        } catch (IOException ex) {
            Logger.getLogger(SampleFileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * 返回指定文件中的指定行。返回后把这行删除掉。
 * @param filepath  文件
 * @param number    行号（从0开始）
 * @return
 */
    public static String cutLine(String filepath,int number){
        try {
            File f = new File(filepath);
            List<String> contents = FileUtils.readLines(f);
            if (contents.size() > number && number >= 0) {
                String result= contents.get(number);
                contents.remove(number);
                FileUtils.writeLines(f, contents);
                return result;
            } else {
                return "";
            }
        } catch (IOException ex) {
            Logger.getLogger(SampleFileUtils.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
/**
 * 返回指定文件中的首行。返回后把首行删除掉。
 * @param filepath  文件
 * @param number    行号（从0开始）
 * @return
 */
    public static String cutLine(String filepath){
        return cutLine(filepath,0);
    }
}
