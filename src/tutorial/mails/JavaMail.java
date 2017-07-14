package mails;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Properties;

/**
 * Created by Oliver on 03/07/2017.
 */
public class JavaMail {
    private String to = "";
    private String from = "";
    private String password = "";
    private String host = "";
    private Properties properties = System.getProperties();
    private boolean debug = false;

    public JavaMail(boolean debug) {
        InputStream in = JavaMail.class.getResourceAsStream("smtp.properties");
        try {
            properties.load(in);
            to = properties.getProperty("mail.sender.send.to");
            from = properties.getProperty("mail.sender.username");
            password = properties.getProperty("mail.sender.password");
            host = properties.getProperty("mail.smtp.host");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.debug = debug;
    }


    public void init() {
        //设置SSL连接、邮件环境
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        properties.setProperty("mail.smtp.host", host);  // 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
    }

    public void plain_mail(String subject, String text) {
        init();
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(debug);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));

            mimeMessage.setSubject(subject);
            mimeMessage.setText(text);  // 设置消息体
            Transport.send(mimeMessage);
            System.out.println("Sent plain message successfully....");
        } catch (AddressException a) {
            a.printStackTrace();
        } catch (MessagingException m) {
            m.printStackTrace();
        }
    }

    public void html_mail(String subject, String content) {
        init();
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(debug);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(content, "text/html");  // 发送 HTML 消息, 可以插入html标签
            Transport.send(mimeMessage);
            System.out.println("Sent html message successfully....");
        } catch (AddressException a) {
            a.printStackTrace();
        } catch (MessagingException m) {
            m.printStackTrace();
        }
    }

    public void attachment_mail(String subject, String text, String filePath) {
        init();
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(debug);

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);

            // 创建消息部分
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(text);

            // 设置文本消息部分
            FileDataSource fileDataSource = new FileDataSource(filePath);
            bodyPart.setDataHandler(new DataHandler(fileDataSource));
            bodyPart.setFileName(filePath);

            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            // 发送完整消息
            mimeMessage.setContent(multipart);

            // 发送消息
            Transport.send(mimeMessage);
            System.out.println("Sent attachment message successfully....");

        } catch (MessagingException m) {
            m.printStackTrace();
        }
    }

}
