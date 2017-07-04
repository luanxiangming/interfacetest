package mails;

import java.io.File;

/**
 * Created by Oliver on 04/07/2017.
 */
public class TestMail {
    public static void main(String[] args) {
        JavaMail javaMail = new JavaMail(true);
        javaMail.plain_mail("Java smtp testing with plain content", "Plain Message using JavaMail");
        javaMail.html_mail("Java smtp testing with html content", "<h1>HTML Message using JavaMail</h1>");

        String filePath = System.getProperty("user.dir") + File.separator + "pom.xml";
        javaMail.attachment_mail("Java smtp testing with attachment content", "Plain text with attachment", filePath);
    }
}
