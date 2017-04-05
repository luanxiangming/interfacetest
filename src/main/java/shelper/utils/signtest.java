package shelper.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * @author mingxing.sun
 */
public class signtest {
    public static KeyStore test1() throws Exception{
        InputStream is = new FileInputStream(new File("D:\\mywork\\ta\\svcpki\\PKI\\fo-api-pki-client\\WEB-INF\\classes\\rsatest.pfx"));
        if (is == null) {
          return null;
        }
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(is, StringUtils.defaultString("asd123").toCharArray());
        is.close();
        ks.getKey("tester", StringUtils.defaultString("asd123").toCharArray());
        return ks;
    }
    public static void main(String args[]){
        

    }
}
