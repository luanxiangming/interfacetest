package shelper.iffixture;/*package shelper.iffixture;

import java.net.MalformedURLException;
import java.net.URL;
import shelper.environment.Environment;
*//**
 * @author mingxing.sun
 *//*
public class FirstTest {
    public static void main(String args[]) throws MalformedURLException{
        Environment.set4If();
        String content="<?xml version=\"1.0\" encoding=\"UTF-8\"?><MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\"><version>1.0</version>"
			+ "<TxnMsgContent><txnType>PUR</txnType><interactiveStatus>TR1</interactiveStatus><cardNo>4380880000000007</cardNo>"
			+ "<expiredDate>1111</expiredDate><cvv2>111</cvv2><amount>5</amount><termInMonths></termInMonths>"
			+ "<merchantId>200900000072400</merchantId><terminalId>20090724</terminalId><entryTime>20101110175832</entryTime>"
			+ "<externalRefNumber>20101110175833</externalRefNumber><cardHolderId>152122197310230000</cardHolderId>"
			+ "</TxnMsgContent></MasMessage>";
        
        HttpFixture hf=new HttpFixture("192.168.55.25", 8455 ,"200900000072400","123456",
                                        new URL("file:"+Environment.get("Selenium.resource")+"cpsa\\VPOS_cnp"),"123456",
                                        new URL("file:"+Environment.get("Selenium.resource")+"cpsa\\channel_srv_eval_ks"),"mas123");
        hf.setUrl("https://192.168.55.25:8455/cnp/purchase");
        hf.addRequestBody(content);
        hf.Post();
        
//        String result=HttpFixture4.post("https://192.168.55.25:8455/cnp/purchase",content, "utf-8",
//                new URL("file:"+Environment.get("Selenium.resource")+"cpsa\\VPOS_cnp"),"123456",
//                new URL("file:"+Environment.get("Selenium.resource")+"cpsa\\channel_srv_eval_ks"),"mas123",
//                "192.168.55.25", 8455, "200900000072400", "123456",8455);
//        System.out.println(result);
    }
}
*/