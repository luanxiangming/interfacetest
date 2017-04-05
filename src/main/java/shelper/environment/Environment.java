package shelper.environment;

import com.vipabc.interfacetest.utils.Env;
import shelper.autoit3.Autoit3;
import shelper.webdriver.IeReceiveTimeout;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 环境变量控制 在第一次使用set方法是载入所有环境变量 提供set和get用另外的环境变量的设置和获取时使用
 * 
 * @author Peter
 */
public class Environment {
	private static int tag = 0;
	private static int starttag = 0;

	public static void stopStartTag() {
		starttag = 0;
	}
	String env="null";

	private Environment() {
	}

	/**
	 * 设置环境变量。在接口测试这种不涉及浏览器的case中使用。 默认路径是d:/TA/TA.properties
	 */
	public static void set4If() {
//		changeConfig("./src/test/resources/ta/TA.properties", "Selenium.TestEnv", "dev"+".properties");
		String separator = File.separator;
		if (tag == 0) {
			FileInputStream propFile = null;
			try {
//				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+get("Selenium.TestEnv")+"--------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				if (get("Selenium.TestEnv") == null
						|| get("Selenium.TestEnv").equals("")) {
					propFile = new FileInputStream(Env.qa_test_home + separator + "src"
							+ separator + "test" + separator + "resource"
							+ separator + "ta" + separator + "TA.properties");
					Properties p = new Properties(System.getProperties());
					p.load(propFile);
					System.setProperties(p);
					
				}
				// set("d:/TA/"+get("Selenium.TestEnv"));
				set(Env.qa_test_home + separator + "src" + separator + "test" + separator
						+ "resource" + separator + "ta" + separator
						+ get("Selenium.TestEnv"));
				setProperites(new File(
						System.getProperty("Selenium.EnvironmentRoot")));
				String logcontent = "Set test Environment to "
						+ get("Selenium.TestEnv");
				Logger.getLogger(Environment.class.getName()).log(Level.INFO,
						logcontent);
				tag = 1;
				
//				System.out.println("&2&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+get("Selenium.TestEnv")+"--------------&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			} catch (IOException ex) {
				Logger.getLogger(Environment.class.getName()).log(Level.SEVERE,
						null, ex);
			} finally {
				try {
					if (propFile != null) {
						propFile.close();
					}
				} catch (IOException ex) {
					Logger.getLogger(Environment.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
	}

	/**
	 * 设置环境变量。然后设置浏览器相关的用于web ui自动化的配置 默认路径是d:/TA/TA.properties
	 */
	public static void set() {
		set4If();
		// 设置iereceivetimeout
		IeReceiveTimeout.set(Integer.parseInt(Environment
				.get("Selenium.pltimeout")));
	}

	/**
	 * 设置环境变量。然后设置浏览器相关的用于web ui自动化的配置。专门为fi的那些怪异的功能定制 默认路径是d:/TA/TA.properties
	 */
	public static void spSet() {
		set4If();
		// 设置iereceivetimeout
		IeReceiveTimeout.set(Integer.parseInt(Environment
				.get("Selenium.pltimeout")));
		startTag();
	}

	/**
	 * 设置环境变量。然后设置浏览器相关的用于web ui自动化的配置。专门为fi的那些怪异的功能定制 默认路径是d:/TA/TA.properties
	 */
	public static void startTag(final int time) {
		starttag = 1;
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < time * 100 && starttag == 1; i++) {
						Thread.sleep(10);
					}
					if (starttag == 1) {
						Autoit3.run("closeallwindows.au3");
					}
				} catch (InterruptedException ex) {
					Logger.getLogger(Environment.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		};
		t.start();
	}

	/**
	 * 设置环境变量。然后设置浏览器相关的用于web ui自动化的配置。专门为fi的那些怪异的功能定制 默认路径是d:/TA/TA.properties
	 */
	public static void startTag() {
		startTag(Integer.parseInt(Environment.get("Selenium.sptimeout")));
	}

	/**
	 * 
	 * @param f
	 *            目标目录，将目录下所有的properties文件都载入。
	 */
	public static void setProperites(File f) {
		// System.out.println("ext is :"+f.getName().substring(f.getName().lastIndexOf(
		// ".")+1));
		// System.out.println(System.getProperty("Selenium.ProperitiesExt"));
		// System.out.println(f.isFile() &&
		// f.getName().substring(f.getName().lastIndexOf(
		// ".")+1).equals(System.getProperty("Selenium.ProperitiesExt")));
		if (f.isFile()
				&& f.getName().substring(f.getName().lastIndexOf(".") + 1)
						.equals(System.getProperty("Selenium.ProperitiesExt"))) {
			FileInputStream propFile = null;
			try {
				propFile = new FileInputStream(f);
				Properties p = new Properties(System.getProperties());
				p.load(propFile);
				System.setProperties(p);
				// System.getProperties().list(System.out);
			} catch (IOException ex) {
				Logger.getLogger(Environment.class.getName()).log(Level.SEVERE,
						null, ex);
			} finally {
				try {
					propFile.close();
				} catch (IOException ex) {
					Logger.getLogger(Environment.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		} else if (f.isDirectory()) {
			for (File subf : f.listFiles()) {
				setProperites(subf);
			}
		}
	}

	/**
	 * 设置环境变量。
	 * 
	 * @param filename
	 *            环境变量配置文件的绝对路径
	 */
	public static void set(String filename) {
		FileInputStream propFile = null;
		try {
			propFile = new FileInputStream(filename);
			Properties p = new Properties(System.getProperties());
			p.load(propFile);
			System.setProperties(p);
			// System.getProperties().list(System.out);
		} catch (IOException ex) {
			Logger.getLogger(Environment.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				propFile.close();
			} catch (IOException ex) {
				Logger.getLogger(Environment.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	/**
	 * 获得名字为key的环境变量
	 * 
	 * @param key
	 *            要获得环境变量的名字
	 * @return 获取的名字为key的环境变量的值
	 */
	public static String get(String key) {
		String result = "";
		try {
			String tmp1 = System.getProperty(key);
			if (tmp1 == null || tmp1.equals("")) {
				return null;
			}
			byte[] temp2 = tmp1.getBytes("ISO-8859-1");
			result = new String(temp2, "utf-8");
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(Environment.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return result;
	}

	/**
	 * 设置环境变量
	 * 
	 * @param key
	 *            变量名
	 * @param value
	 *            变量值
	 */
	public static void set(String key, String value) {
		System.setProperty(key, value);
	}
	
	
	public static void changeConfig(String filePath,String key, String value)
	{
	        Properties prop = new Properties();  
	        try {  
	            File file = new File(filePath);  
	            if (!file.exists())  
	                file.createNewFile();  
	            InputStream fis = new FileInputStream(file);  
	            prop.load(fis);  
	            //一定要在修改值之前关闭fis  
	            fis.close();  
	            OutputStream fos = new FileOutputStream(filePath);  
	            prop.setProperty(key, value);  
	            //保存，并加入注释  
	            prop.store(fos, "Update '" + key + "' value");  
	            fos.close();  
	            prop.load(fis);  
				System.setProperties(prop);
	        } catch (IOException e) {  
	            System.err.println("Visit " + filePath + " for updating " + value + " value error");  
	        }  
	}
}
