/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shelper.db;

/**
 *
 * @author mingxing.sun
 */
public class test {
    public static void main(String args[]){
//    Environment.set();
//    Logger.getLogger(test.class.getName()).log(Level.WARNING, "asfd", "asdf");
        Pg pg=new Pg("172.16.4.138:5432/vliveshow","vu_backoffice","123456");
        pg.query("select * from \"user\" where nick_name='Test&@!=1'");
    }
}
