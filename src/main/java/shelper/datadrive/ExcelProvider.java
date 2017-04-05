package shelper.datadrive;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import shelper.environment.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Peter Sun
 */
public class ExcelProvider implements Iterator<Object[]>{
  private String datatype;
  //excel绝对路径位置
  private String excel;
  //sheet名称
  private String sheetname;
  private FileInputStream fis ;
  private POIFSFileSystem fs ;
  private HSSFWorkbook wb ;
  private HSSFSheet sheet;
  //sheet的行数
  private int  linenumber;
  //sheet列数
  private int rownumber=0;
  //sheet行标
  private int numberindex=0;
  //列名数组
  private String rowname[];
  //单行调试的行号
  private int singleline=0;
  //单行调试结束标记
  private boolean singletag=false;

  //全表执行
  public ExcelProvider(Object aimob,String aimmathod){
    //设置环境变量
    //Environment.set();
    //获取测试类得DataDriven注解。从中获得excelfile名称，sheet名称。
    getInfo(aimob,aimmathod);
    //打开目标sheet获得sheet行数。
    openSheet();
    //获得列名数组
    getRowname();
    //System.out.println(this.excel);
  }

  //单行调试
  public ExcelProvider(Object aimob,String aimmathod,int linenumber){
    this(aimob,aimmathod);
    this.singleline=linenumber;
  }

  private void getRowname(){
      HSSFRow row = sheet.getRow(numberindex);
      while(row.getCell(this.rownumber)!=null){
        this.rownumber++;
      }
      rowname=new String[this.rownumber];
      for(int i =0; i<this.rownumber;i++){
//        rowname[i]=row.getCell(i).toString();
        rowname[i]= StringUtils.remove(row.getCell(i).toString(), "\n");
      }
      numberindex++;
  }

    public boolean hasNext(){
        if(singleline==0){
            //全表
            if(this.numberindex<this.linenumber){
                return true;
            }else{
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }
        }else{
            //单行调试
            if(singletag==false){
                this.numberindex=singleline;
                //如果要调试的行不存在
                if(0>this.numberindex || this.numberindex>=this.linenumber){
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return false;
                }
                //把单行调试结束标记设置为true；表明结束了
                singletag=true;
                return true;
            }else{
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }
        }
    }

    public Object[] next() {
        HSSFRow row = sheet.getRow(this.numberindex);
        Map<String,String> result=new HashMap();
        for(int j=0;j<this.rownumber;j++){
            String temp="";
            if(null!=row.getCell(j)){
                temp=row.getCell(j).toString();
            }
            result.put(this.rowname[j], temp);
        }
        Object objresult[]=new Object[1];
        objresult[0]=result;
        this.numberindex++;
        return objresult;
    }

    @Override
	public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
/**
 * 获取测试类得DataDriven注解。从中获得excelfile名称，sheet名称。
 * @param aimob
 * 测试用例对象
 * @param aimmathod
 * 测试方法名
 */
  private void getInfo(Object aimob,String aimmathod){
    DataDriven dd=locateTestMethod(aimob,aimmathod).getAnnotation(DataDriven.class);
    if(dd==null){
        this.excel=modifydata(aimob,"auto");
        this.sheetname=modifysheet(aimmathod,"auto");
        this.datatype=modifyType("excel");
    }else{
        this.excel=modifydata(aimob,dd.excel());
        this.sheetname=modifysheet(aimmathod,dd.sheet());
        this.datatype=modifyType(dd.type());
    }
  }
/**
 * 打开目标sheet获得sheet行数。
 */
  private void openSheet(){
    try{
    fis = new FileInputStream(this.excel);
    fs = new POIFSFileSystem( fis );
    wb = new HSSFWorkbook(fs);
    sheet = wb.getSheet(this.sheetname);
    this.linenumber=sheet.getPhysicalNumberOfRows();
    }catch(IOException ex){
        Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
            try {
                fis.close();
            } catch (IOException ex1) {
                Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex1);
            }
    }
  }

  private String modifysheet(String method,String sheetname){
    if(sheetname.equals("auto")){
        return method;
    }else{
        return sheetname;
    }
  }

  private String modifydata(Object o,String data){
    if(data.equals("auto")){
        return Environment.get("Selenium.DatadrivenRoot")+o.getClass().getName().replaceAll("\\.", "/")+".xls";
    }else{
        return data;
    }
  }

  private Method locateTestMethod(Object objectname, String methodname)
  {
    try
    {
      Method[] arrayOfMethod1 = objectname.getClass().getDeclaredMethods();
      Method[] arrayOfMethod2 = arrayOfMethod1;
      int j = arrayOfMethod2.length;
      int k = 0;
      while(k<=j ){
          Method localMethod=arrayOfMethod2[k];
          if(localMethod.getName().equals(methodname)){
            return localMethod;
          }
          ++k;
      }
    }
    catch (Throwable ex)
    {
      Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
  
//预留
  private String modifyType(String type){
    return type;
  }


}
