package shelper.db;

import org.testng.Reporter;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author peter.sun
 */
public class Pg {

        //数据库连接
        private  Connection con;

        public Pg(String connect, String username, String pass){
            connect(connect,username,pass);
        }

        private Boolean connect(String connect,String username,String pass){
            try
            {
              Class.forName("org.postgresql.Driver");
            }
            catch (ClassNotFoundException e1) {
              Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, e1);
            }
            String url = "jdbc:postgresql://" + connect ;
            try {
              con = DriverManager.getConnection(url, username, pass);
              return con.isClosed()==false;
            }
            catch (SQLException e2) {
              Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, e2);
            }
            return false;
          }
        /*
         * sql的执行语句的返回结果是否为exceped。是返回true。否返回false
         */
    public boolean queryCheck(String sql,String excepted){
        return excepted.equals(query(sql));
    }
        /*
         * 在规定的time秒内sql的执行语句的返回结果是否为exceped。如果返回了，则立即返回true。否则，在time秒到后返回false  ,每次查询的等待时间间隔为1 秒
         */
    public boolean queryCheckInTime(String sql,String excepted,int time){
        long starttime=System.currentTimeMillis();
        long endtime=System.currentTimeMillis()+time*1000;
        boolean result=excepted.equals(query(sql));

        while(System.currentTimeMillis()<endtime && result == false ){
            result=excepted.equals(query(sql));
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Reporter.log("queryCheckInTime:\nSQL:"+sql+"\nexcepted result:"+excepted+"\nwith in:" +time+ "seconds"+"\nResult:"+result,true);
        return result;
    }
        /*
         * 在规定的time秒内sql的执行语句的返回结果是否为exceped。如果返回了，则立即返回true。否则，在time秒到后返回false  ,每次查询的等待时间间隔为interval 秒
         */
    public boolean queryCheckInTime(String sql,String excepted,int time,int interval){
        long starttime=System.currentTimeMillis();
        long endtime=System.currentTimeMillis()+time*1000;
        boolean result=excepted.equals(query(sql));
        while(System.currentTimeMillis()<endtime && result == false ){
            result=excepted.equals(query(sql));
            try {
                Thread.sleep(interval*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Reporter.log("queryCheckInTime:\nSQL:"+sql+"\nexcepted result:"+excepted+"\nwith in:" +time+ "seconds"+"\n interval:"+interval+"seconds"+"\nResult:"+result,true);
        return result;
    }

    /*
     * 数据库检查（查询）。结果按照 值1；值2 这种形式的字符串.字段为空时返回null
     */
        public String query(String sql){
        try {
            //改动部分
            Statement statement = con.createStatement(1004,1008);
            //Statement statement = con.createStatement();
            if (statement!=null){
            ResultSet res = statement.executeQuery(sql);
            ResultSetMetaData rsmd = res.getMetaData() ;
            int columnCount = rsmd.getColumnCount();
            String result="";
            while(res.isLast()==false){
                res.next();
                //当结果集为空的时候，next这个方法往下一条还是0.并且isLast一直是false。真是太奇怪了。这里判断一下next之后getRow是不是大于0了
                if(res.getRow()>=0){
                    for(int i=1;i<=columnCount;i++){
                        //result=result+String.valueOf(res.getObject(i))+";";
                        String tempresult=String.valueOf(res.getObject(i));
                        if(tempresult.contains("oracle.sql.CLOB@")){
                            Clob clob = res.getClob(i);
                            Reader inStreamDoc = clob.getCharacterStream();
                            char[] tempDoc = new char[(int) clob.length()];
                            inStreamDoc.read(tempDoc);
                            inStreamDoc.close();
                            result=result+ new String(tempDoc)+";";
                        }else{
                            result=result+tempresult+";";
                        }
                    }
                }else{
                    Reporter.log("query\nSQL:"+sql+"\nResult:结果集为空",true);
                    res.close();
                    statement.close();
                    statement=null;
                    throw new SQLException("query\nSQL:"+sql+"\nResult:结果集为空");
                }
            }
            res.close();
            statement.close();
            statement=null;
            if (result.endsWith(";")){
                result=result.substring(0, result.length()-1);
            }
            Reporter.log("query\nSQL:"+sql+"\nResult:"+result,true);
            return result;
            }else{
                Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null,"oracle statement create failed");
            }
        } catch (IOException ex) {
            Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null,ex);
        }
        Reporter.log("query\nSQL:"+sql+"\nResult:null",true);
        return null;
      }

     public boolean Update(String sql){
        int i = 0;
        try {
          Statement statement = con.createStatement(1004,1008);
          i = statement.executeUpdate(sql);
          statement.close();
        }
        catch (SQLException e) {
          Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, e);
        }
        boolean result=(i != 0);
        Reporter.log("Update\nSQL:"+sql+"\nResult:"+result,true);
        return result;
     }

      public boolean Insert(String sql)
      {
        int i = 0;
        try {
          Statement statement = con.createStatement(1004,1008);
          i = statement.executeUpdate(sql);
          statement.close();
        }
        catch (SQLException e) {
          Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, e);
        }
        boolean result=(i != 0);
        Reporter.log("Insert\nSQL:"+sql+"\nResult:"+result,true);
        return result;
      }

      public boolean delete(String sql)
      {
        int i = 0;
        try {
          Statement statement = con.createStatement(1004,1008);
          i = statement.executeUpdate(sql);
          statement.close();
        }
        catch (SQLException e) {
          Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, e);
        }
        boolean result=(i != 0);
        Reporter.log("Delete\nSQL:"+sql+"\nResult:"+result,true);
        return result;
      }
      
      public void closeDBcon(){
        try {
            con.close();
        } catch (SQLException ex) {
             Logger.getLogger(Pg.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
}
