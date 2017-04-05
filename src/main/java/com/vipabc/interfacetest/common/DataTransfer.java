package com.vipabc.interfacetest.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.vipabc.interfacetest.utils.ProjectProperties;
import com.vipabc.interfacetest.utils.SystemLogger;

/**
 * @author shilingfeng
 * 用于数据传输
 */
public class DataTransfer {
	public  static Logger logger=SystemLogger.getLogger(DataTransfer.class);
	private static String UTF8 = "utf-8";
	private static String ISO8859 = "ISO-8859-1";	

	/**	
	 * @param key
	 *            Properties的Key
	 * @param value
	 *            Properties的Value
	 *            示例：DataTransfer.setData("ApplyNamePro","ApplyName", "申请单20160505001",
	 *            "张三");
	 */
	public static void setData(String key, String value) {		
		String filePath = ProjectProperties.TRANSFER_PATH;
		Properties pro = new Properties();
		OutputStream out = null;
		try {
			String keyStr = new String(key.getBytes(UTF8), ISO8859);
			String valStr = new String(value.getBytes(UTF8), ISO8859);
			pro.put(keyStr, valStr);
			out = new FileOutputStream(new File(filePath),true);
			pro.store(out, "pass Message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{	
			try {				
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	/**	
	 * @param key
	 *            Properties的Key
	 * @return String 读取到的Properties的值
	 *         示例：DataTransfer.getData("ApplyNamePro", "ApplyName");
	 */
	public static String getData(String key) {
		Properties prop = new Properties();
		String filePath = ProjectProperties.TRANSFER_PATH;
		// 处理中文Key及Value乱码
		String value = "not found";
		InputStream input = null;
		try {
			 input= new FileInputStream(new File(filePath));
			prop.load(input);
			value = prop.getProperty(new String(key.getBytes(UTF8), ISO8859));
			if (!value.equals("") && value != null) {
				value = new String(value.getBytes(ISO8859), UTF8);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("根据Key:\"" + key + "\" 未找到对应值");
			e.printStackTrace();
		}finally{
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("读取到的信息是："+value);
		return value;
	}
}
