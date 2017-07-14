package java8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by Oliver on 14/07/2017.
 */
public class BASE64 {
    public static void main(String[] args) {
        try {
            /**使用基本编码
             * 基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
             */
            String base64encodedString = Base64.getEncoder().encodeToString("oracle?java8".getBytes("UTF-8"));
            System.out.println("Base64编码字符串（基本）: " + base64encodedString);

            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("Base64解码: " + new String(base64decodedBytes, "UTF-8"));

            /**使用URL编码
             * URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
             */
            base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("UTF-8"));
            System.out.println("Base64编码字符串（url）: " + base64encodedString);


            StringBuilder stringBuilder = new StringBuilder();
            String uuid = UUID.randomUUID().toString();
            System.out.println("Random uuid: " + uuid);
            stringBuilder.append(uuid);

            /**使用MIME编码
             * MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
             */
            byte[] mimeBytes = stringBuilder.toString().getBytes("UTF-8");
            base64encodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64编码字符串（MIME）: " + base64encodedString);
        } catch (UnsupportedEncodingException u) {
            u.printStackTrace();
        }
    }
}
