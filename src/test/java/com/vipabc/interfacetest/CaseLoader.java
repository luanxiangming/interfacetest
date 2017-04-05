//package com.vipabc.interfacetest;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.testng.annotations.Listeners;
//
//import com.vipabc.interfacetest.common.ParseCellContent;
//import com.vipabc.interfacetest.utils.ExcelData;
//import com.vipabc.interfacetest.utils.HttpClientUtil;
//import com.vipabc.interfacetest.utils.SheetData;
//
//
//@Listeners({com.vipabc.interfacetest.utils.AssertionListener.class})
//public class CaseLoader {
//
//	static {
//		loadExcel();
//	}
//
//	private static SheetData sheetData;
//	private static List<String[]> expectedResultList = new ArrayList<String[]>();
//
//	private static void loadExcel() {
//		// String
//		// path=System.getProperty("user.dir")+File.separator+"Http_Request_workbook_Data.xlsx";
//		String path = "d:\\" + "Http_Request_workbook_Data.xlsx";
//		String sheetName = "Input";
//		ExcelData excelReader = null;
//
//		try {
//			InputStream in = new FileInputStream(path);
//			excelReader = new ExcelData(in);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (excelReader != null) {
//			sheetData = excelReader.getSheetDataByName(sheetName);
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//		// for(Map.Entry<String, String> map:maps.entrySet()){
//		// System.out.println(map.getKey()+"->"+map.getValue());
//		// }
//
//		for (int i = 1; i <= sheetData.getRowCount(); i++) {
//			Map<String, String> maps = sheetData.getRowDataMap(i);
//			String url = "";
//			if (maps.get("Host").endsWith("/")) {
//				url = maps.get("Host") + maps.get("RequsetSubpath");
//			} else {
//				url = maps.get("Host") + "/" + maps.get("RequsetSubpath");
//			}
//
//			String reqUrl = url;
//			System.out.println(reqUrl);
//
//			String reqJsonBody = maps.get("RequestParams");
//			System.out.println(reqJsonBody);
//
//			String expectedResult = maps.get("ExpectedResult");
//			System.out.println(expectedResult);	
//			
//			
//			ParseCellContent parseCellContent=new ParseCellContent(expectedResult);			
//			expectedResultList=parseCellContent.getExpectedResultList();
//			System.out.println("+++++");	
//			for (String[] line : expectedResultList) {
//				for (String str : line) {
//					System.out.print(str + "、");
//				}
//				System.out.println();
//			}
//			System.out.println("+++++");
//			System.out.println("AAA "+parseCellContent.getExpectedResultSubContent(0,2));		
//
//			Class<?> clazz = HttpClientUtil.class;
//			Object obj = clazz.newInstance();
//			Method postMethodWithRawBody = clazz.getMethod("postMethodWithRawBody", String.class, String.class);
//			postMethodWithRawBody.invoke(obj, reqUrl, reqJsonBody);
//			System.out.println("================================================================================");
//		}
//	}
//
//}
