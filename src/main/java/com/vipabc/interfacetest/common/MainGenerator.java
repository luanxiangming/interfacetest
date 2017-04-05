package com.vipabc.interfacetest.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vipabc.interfacetest.utils.ExcelData;
import com.vipabc.interfacetest.utils.ProjectProperties;
import com.vipabc.interfacetest.utils.SheetData;

public class MainGenerator {
	public static SheetData sheetData;	
	public static List<String[]> expectedResultList = new ArrayList<String[]>();	
	public static String responseStr="";

	public static void loadExcel() {	
		String path=System.getProperty("user.dir")+File.separator+ProjectProperties.WORKBOOK_PATH;	
		String sheetName = ProjectProperties.SHEET_NAME;		
		ExcelData excelReader = null;

		try {
			InputStream in = new FileInputStream(path);
			excelReader = new ExcelData(in);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		if (excelReader != null) {
			sheetData = excelReader.getSheetDataByName(sheetName);
		}
	}

	public static void main(String[] args) throws Exception {
		loadExcel();
		List<String> caseList=new ArrayList<>();
		for (int i = 1; i <= sheetData.getRowCount(); i++) {
			Map<String, String> maps = sheetData.getRowDataMap(i);		
			GenerateCase.generateCase(maps.get("TestCase"),String.valueOf(i));
			caseList.add(maps.get("TestCase"));
		}
		GenerateTestNgXml.generateTestNgXml(caseList);
	}
}
