package com.vipabc.interfacetest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author echoshi
 * 读取日志配置文件:log4j.properties
 */
public class SystemLogger {
	public static Logger logger;
	public static String log4jFilePath=System.getProperty("user.dir")+File.separator+"config"+File.separator+"log4j.properties";
	
	static {
		InputStream input;
		try {
			input = new FileInputStream(log4jFilePath);
			Properties props=new Properties();		
			props.load(input);
			PropertyConfigurator.configure(props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static Logger getLogger(Class<?> clazz){
		logger=Logger.getLogger(clazz);
		return logger;
	}		
}
