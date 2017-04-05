package shelper.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mingxing.sun
 * 加密工具类
 */
public class EncryptUtils {
	public EncryptUtils() {
	}
	/**
	 * 生成3DES密钥.
	 * @param key_byte
	 *            seed key
	 * @return javax.crypto.SecretKey Generated DES key
	 */
	public static SecretKey genDESKey(byte[] key_byte){
		SecretKey k = null;
		k = new SecretKeySpec(key_byte, "DESede");
		return k;
	}

	/**
	 * 3DES 解密(byte[]).
	 * @param key
	 *            SecretKey
	 * @param crypt
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] desDecrypt(SecretKey key, byte[] crypt)
			throws Exception {
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(crypt);
	}

	/**
	 * 3DES 解密(String).
	 *
	 * @param key
	 *            SecretKey
	 * @param crypt
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desDecryptAndBase64Decode(SecretKey key, String crypt,String encode)
			throws Exception {
	    String a = URLDecoder.decode(crypt,encode);
		//byte[] ss = Base64Binrary.decodeBase64Binrary(a);
                byte[] ss = Base64.decodeBase64(a);
		return new String(desDecrypt(key, ss));
	}
	/**
	 * 3DES加密(byte[]).
	 * @param key
	 *            SecretKey
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] desEncrypt(SecretKey key, byte[] src)
			throws Exception {
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(src);
	}

	/**
	 * 3DES加密(String).
	 * @param key
	 *            SecretKey
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desEncrypt(SecretKey key, String src)
			throws Exception {
		return new String(desEncrypt(key, src.getBytes()));
	}
         /*
          * 3DES加密,然后做base64
          * @param orgstring
          * 目标字符串
          * @param key
          * 3DES加密的字符串的key
          * @param encode
          * base64加密使用的编码字符集
          */
        public static String DesEncryptAndBase64Encode(String orgstring,String key,String encode){
		String result = "";
                String orgstr=orgstring;
		if (orgstr!= null && !orgstr.trim().equals("")) {
                    try {
                        byte[] key_byte = key.getBytes(); // 3DES 24
                        SecretKey deskey;
                        deskey = genDESKey(key_byte);
                        byte[] encrypt = desEncrypt(deskey, orgstr.getBytes());
                        result = new String(Base64.encodeBase64(encrypt));
                        result = URLEncoder.encode(result, encode);
                    } catch (Exception ex) {
                        Logger.getLogger(EncryptUtils.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
                return result;
        }

        /*
          * 3DES加密,然后做base64
          * @param orgstring
          * 目标字符串
          * @param key
          * 3DES加密的字符串的key
          */
        public static String DesEncryptAndBase64Encode(String orgstring,String key){
            return DesEncryptAndBase64Encode( orgstring, key, "GB2312");
        }
}
