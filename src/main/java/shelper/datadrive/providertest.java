package shelper.datadrive;

import shelper.environment.Environment;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Peter Sun
 */
public class providertest {
    public void method1(){
    }
    public static void main(String args[]){
        providertest p=new providertest();
        Iterator i=new ExcelProvider(p,"method1");
        while(i.hasNext()){
            Object o = i.next();
            Object[] o1=(Object[])o;
            Map<String,String> m=(Map)o1[0];
            String name=m.get("name");
            String age=m.get("age");
            System.out.println(name);
            System.out.println(age);
            System.out.println(m.get("peter"));
            System.out.println(Environment.get("oneenv"));
            System.out.println(Environment.get("twoenv"));
        }
    }
}
