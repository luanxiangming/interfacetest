package shelper.utils;/*package shelper.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang.StringUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.zip.ZipFile;
*//**
 *  zip 门面
 * @author mingxing.sun
 *//*
public class ZipUtils {
	*//**
	 * 解压zip包，使用gbk字符集
	 * @param orgzip	zip绝对路径
	 * @param destpath	解压的目标目录
	 * @throws Exception
	 *//*
	public static void unZip(String orgzip,String destpath){
		unZip(orgzip,destpath,"gbk");
	}
	*//**
	 * 解压zip包
	 * @param orgzip	zip绝对路径
	 * @param destpath	解压的目标目录
	 * @param encode	字符集
	 * @throws Exception
	 *//*
	public static void unZip(String orgzip,String destpath,String encode){
		try{
    	File f = new File(orgzip);
        ZipFile zf = new ZipFile(f, encode);
        File folder = new File(destpath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (Enumeration<ZipArchiveEntry> files = zf.getEntries(); files.hasMoreElements();) {
            ZipArchiveEntry zae = files.nextElement();
            String zipname = zae.getName();
            if (zipname.endsWith(".zip")) {
                String innerzip = StringUtils.removeEnd(zipname, ".zip");
                File innerfolder = new File(folder + File.separator + innerzip);
                if (!innerfolder.exists()) {
                    innerfolder.mkdirs();
                }
                ZipArchiveInputStream zais = new ZipArchiveInputStream(zf.getInputStream(zae), "GBK", true);
                FileOutputStream fos = null;
                ZipArchiveEntry innerzae = null;
                while ((innerzae = zais.getNextZipEntry()) != null) {
                    fos = new FileOutputStream(folder + File.separator + innerzip + File.separator + innerzae.getName());
                    IOUtils.copy(zais, fos);
                }
                zais.close();
                fos.flush();
                fos.close();
            } else {
                ZipArchiveEntry packinfo = zf.getEntry(zipname);
                String filename = folder + File.separator + zipname;
                if(filename.endsWith("/")){
                	String filepath=filename.substring(0, filename.length()-1);
                	File pathfile=	new File(filepath);
                	if(!pathfile.exists()){
                		pathfile.mkdir();
                	}
                }else{
	                FileOutputStream fos = new FileOutputStream(filename);
	                InputStream is = zf.getInputStream(packinfo);
	                IOUtils.copy(is, fos);
	                is.close();
	                fos.flush();
	                fos.close();
                }
            }
        } 
        zf.close();
		}catch(Exception ex){
                    Logger.getLogger(ZipUtils.class.getName()).log(Level.SEVERE, null, "unzip failed. source zip file is " + orgzip+". Dest path is "+destpath+"------"+ex);
		}
	}
//        public static void main(String args[]){
//            ZipUtils.unZip("c:\\123.zip","c:\\test");
//        }
}
*/