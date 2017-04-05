package shelper.db;

import java.sql.*;

public class FirstTest {
    private  Connection con;
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        try{   
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.6.138)(PORT=1530)))(CONNECT_DATA=(SID=billdb)))", "seashell", "seashell_qa0701");
        //Connection con = DriverManager.getConnection("jdbc:oracle:oci8:@192.168.6.138", "seashell", "seashell_qa0701");
        Statement stmt=con.createStatement();
        ResultSet rst=stmt.executeQuery("Select * From paymentorder Where sequenceid='15431817'");
        while(rst.next())
            {
             System.out.println(rst.getString("sequenceid"));
            }
            rst.close();
            stmt.close();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
