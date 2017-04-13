package com.vipabc.interfacetest.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JavaMail {
    private MimeMessage message;
    private Session session;
    private Transport transport;
    private String ch_phoneNum_qa = "";
    private String ch_phoneNum_v = "";
    public String ch_phoneNum_vjr = "";

    private Properties properties = new Properties();
    public Map<String, String> brands = new HashMap<String, String>();
    public InputStream in = null;

    public JavaMail(boolean debug) {
        InputStream in = JavaMail.class.getResourceAsStream("MailServer.properties");
        try {
            properties.load(in);
            this.ch_phoneNum_qa = properties.getProperty("sms.ch.qa.phonenums");
            this.ch_phoneNum_v = properties.getProperty("sms.ch.v.phonenums");
            this.ch_phoneNum_vjr = properties.getProperty("sms.ch.vjr.phonenums");
            brands.put("vipabc", properties.getProperty("vipabc"));
            brands.put("vipabcjr", properties.getProperty("vipabcjr"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        session = Session.getInstance(properties);
        session.setDebug(debug);
        message = new MimeMessage(session);
    }

    public void sendSMS(String message) {
        String code = "86";
        try {
            ActiveXComponent dotnetCom = null;
            if (Env.brand.equals("vipabc")) {
                ch_phoneNum_qa = ch_phoneNum_v;
            }
            if (Env.brand.equals("vipabcjr")) {
                ch_phoneNum_qa = ch_phoneNum_vjr;
            }
            String[] phoneNum = ch_phoneNum_qa.split(";");
            String clientInfo = "";
            for (String num : phoneNum) {
                clientInfo = clientInfo + "<ClientInfo><Phone>" + num + "</Phone></ClientInfo>";
            }
            dotnetCom = new ActiveXComponent("TutorGroup.SmsPlatform.SDK");
            Variant var1 = Dispatch.call(dotnetCom, "SendGlobalBatchMsg", "http://webapi.tutorabc.com/Sms/Api/",
                    "E42748A4-8BB1-4B52-95B3-B62E0DFFE2CA", code, message,
                    "<ClientInfos>" + clientInfo + "</ClientInfos>");
            System.out.println("SMS had Sended!");
            String str = var1.toString(); // return
            System.out.println(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendSMS() {
        String message = "";
//        if (tw_phoneNum_qa != null && tw_phoneNum_qa != "" && message != null) {
//            sendSMS("886", tw_phoneNum_omc, message);
    }
}