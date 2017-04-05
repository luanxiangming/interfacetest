package com.vipabc.interfacetest.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * @author shilingfeng
 * 读取配置文件automation.properties
 */

public final class ProjectProperties {	
	
	private static Properties prop = readProjectProperties();

	//Excel的存放路径
	public static final String WORKBOOK_PATH = getWorkbookPath();
	//Excel的当前读取的SheetName
	public static final String SHEET_NAME = getSheetName();
	
	public static final String TRANSFER_PATH=getTransferPath();
	
	public static final String TIMEOUT=getTimeout();
	
	public static Properties readProjectProperties() {
		Properties ps = new Properties();		
		String fileName = System.getProperty("user.dir")+File.separator+"config"+File.separator+"automation.properties";
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(fileName));
			ps.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ps;
	}		
	
	public static final String getTimeout(){
		return prop.getProperty("TIMEOUT");
	}
	
	private static String getWorkbookPath() {
		return prop.getProperty("WORKBOOK_PATH");
	}	
	
	private static String getSheetName() {
		return prop.getProperty("SHEET_NAME");
	}	
	
	private static String getTransferPath() {
		return prop.getProperty("TRANSFER_PATH");
	}	
	
}
