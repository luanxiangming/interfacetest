package shelper.testng.screemshot;

import java.io.File;
import java.io.FilenameFilter;

public class DirFilter implements FilenameFilter{
    private String type;
    public DirFilter(String tp){
        this.type=tp;
    }
    public boolean accept(File dir, String path) {
        File file=new File(path);
        String filename=file.getName();
        return filename.indexOf(type)!=-1;
    }
}