package shelper.ifmock;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author mingxing.sun
 */
public class TestNano extends HttpMock{
	public TestNano() throws IOException
	{
		super(8081);
	}
     @Override
	public Response serve( String uri, String method, Properties header, Properties parms,String rowbodystr, Properties files )
	{
		System.out.println( method + " '" + uri + "' " );
                System.out.println( "row body is :" +rowbodystr);
                for(Object key:parms.keySet()){
                    System.out.println((String)key+":"+parms.getProperty((String)key));
                }
		return new HttpMock.Response( HTTP_OK, MIME_HTML, "how old are you孙明星" );
	}

	public static void main( String[] args )throws IOException{
//			TestNano t=new TestNano();
//                        t.setEncode("gbk");
//                        t.setWaitTime(100);
//                        t.waitUntilMockFinish();
            System.out.println(StringUtils.substringsBetween("asd123123123123ddd", "asd", "ddd"));
  
	}
}
