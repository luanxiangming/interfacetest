package shelper.db;
import shelper.environment.Environment;

/*
 * @author mingxing.sun
 */
public class OracleTest {
    public static void main(String args[]){
        Environment.set();
        Oracle o1=new Oracle(Environment.get("db.seashell.connect"), Environment.get("db.seashell.username"), Environment.get("db.seashell.password"));
        System.out.println(o1.query("Select sequenceid From paymentorder Where sequenceid='15431817'"));
        o1.closeDBcon();
    }
}
