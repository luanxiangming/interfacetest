package shelper.utils;

import org.apache.commons.codec.binary.Base64;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.bouncycastle.openssl.PEMReader;

/**
 *签名工具类
 * @author mingxing.sun
 */
public class SignUtils {
/*
 * 获得目标字符串的md5散列。默认密码utf-8
 */
    public static String getMd5(String orstring){
        return getMd5(orstring,"utf-8");
    }
/*
 * 获得目标字符串的md5散列。默认密码utf-8
 */
    public static String getMd5(String orstring,String encode){
        try{
//        String[] temp=orstring.split("&");
//        String or="";
//        for (String s1:temp){
//            String[] array1=s1.split("=");
//            if(array1.length==1){
//                or=or+array1[0]+"="+"&";
//            }else{
//                or=or+array1[0]+"="+URLEncoder.encode(array1[1], encode);
//                for(int m=2;m<array1.length;m++){
//                        or=or+URLEncoder.encode("="+array1[m], encode);
//                }
//                  or=or+"&";
//            }
//        }
//        orstring=or.substring(0, or.length()-1);
        String md5str=toHexString(getDigest().digest(orstring.getBytes(encode))).toUpperCase();
        return md5str;
        }catch(UnsupportedEncodingException ex){
            Logger.getLogger(SignUtils.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

   private static MessageDigest  getDigest() {
    try {
      return MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  private static String toHexString(byte[] b) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < b.length; ++i) {
      sb.append("0123456789abcdef".charAt(b[i] >>> 4 & 0xF));
      sb.append("0123456789abcdef".charAt(b[i] & 0xF));
    }
    return sb.toString();
  }
  /*
   * SHA1WithRSA证书签名。data源串，certFile pem秘钥文件
   */
//  public static String SHA1WithRSASign(byte[] data,String certFile) throws RuntimeException{
//      FileReader read=null;
//      PemReader reader=null;
//    try{
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//        read = new FileReader(certFile);
//        reader = new PemReader(read);
//        KeyPair obj = (KeyPair)reader.readObject();
//    //    PublicKey publicKey = obj.getPublic();
//        PrivateKey privateKey = obj.getPrivate();
//        java.security.Signature signatureChecker = java.security.Signature
//          .getInstance("SHA1WithRSA");
//        signatureChecker.initSign(privateKey);
//        signatureChecker.update(data);
//        byte[] s1 = signatureChecker.sign();
//        //return Base64.encodeBase64String(s1);
//        return new String(Base64.encodeBase64(s1),"utf-8");
//        }catch(Exception e){
//           Logger.getLogger(SignUtils.class.getName()).log(Level.SEVERE, null, e);
//           throw new RuntimeException(e.getMessage(), e);
//        }finally {
//                    if (reader != null) {
//                            try{
//                                    reader.close();
//                            }catch(Exception e){
//                                    throw new RuntimeException(e.getMessage(), e);
//                            }
//                    }
//                    if (read != null) {
//                            try{
//                                    read.close();
//                            }catch(Exception e){
//                                    throw new RuntimeException(e.getMessage(), e);
//                            }
//                    }
//            }
//  }
  /*
   * SHA1WithRSA证书验签名。data源串，signData目标签名，certFile公钥
   * @param data
   * @param signData
   * @param certFile
   */
    public static boolean SHA1WithRSAverifySign(byte[] data, byte[] signData, String certFile)
                                                        throws RuntimeException{
            InputStream is = null;
            try {
                    //加载公钥
                    is = new FileInputStream(certFile);
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    Certificate cert = cf.generateCertificate(is);
                    PublicKey publicKey = cert.getPublicKey();
                    Signature sig = Signature.getInstance("SHA1WithRSA");
                    String signStr = new String(signData);
                    byte[] signed = Base64.decodeBase64(signStr);
                    sig.initVerify(publicKey);
                    sig.update(data);
                    boolean result=sig.verify(signed);
                    Reporter.log("检查：SHA1WithRSAverifySign\n 源串："+new String(data)+"\n签名："+signStr+"\n结果："+result);
                    return result;
            } catch (Exception ex) {
                    Logger.getLogger(SignUtils.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex.getMessage(), ex);
            } finally {
                    if (is != null) {
                            try{
                                    is.close();
                            }catch(Exception e){
                                    throw new RuntimeException(e.getMessage(), e);
                            }
                    }
            }
    }

  public static void main(String args[]){
    System.out.println(getMd5("inputCharset=1&version=v1.0&signType=1&merchantId=&frozenMerchantId=&frozenIdContent=&bizCode=01&requestId=&orderId=&dealId=&payAmount=&bgUrl=&key=号","utf-8"));
//    StringUtils.
  }
}
