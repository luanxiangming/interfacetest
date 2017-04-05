package com.vipabc.interfacetest.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import com.vipabc.interfacetest.utils.SystemLogger;

public class ParseCellContent {

	private static Logger logger = SystemLogger.getLogger(ParseCellContent.class);

	private static List<String[]> expectedResultList = new ArrayList<String[]>();
	private static List<String[]> tranferDateList = new ArrayList<String[]>();

	private static Map<String, String> header = new TreeMap<String, String>(new Comparator<String>() {
		public int compare(String key1, String key2) {
			return key1.compareTo(key2);
		}
	});
	
	
	private static Map<String, String> pathParam = new TreeMap<String, String>(new Comparator<String>() {
		public int compare(String key1, String key2) {
			return key1.compareTo(key2);
		}
	});

	/**
	 * 对单行如下等字符串进行分隔，以##分隔，再以=分隔 ?name=zhangsan ## password=vipjr
	 * 
	 * @param message
	 * @return
	 */
	public static Map<String, String> parseSingleLine(String singleLineStr) {
		Map<String, String> singleLineMap = new TreeMap<String, String>(new Comparator<String>() {
			public int compare(String key1, String key2) {
				return key1.compareTo(key2);
			}
		});
		if (singleLineStr != null && !singleLineStr.equals("")) {
			String[] pairs = singleLineStr.split("##");
			for (String pair : pairs) {
				String[] str = pair.split("=");
				singleLineMap.put(str[0].trim(), str[1].trim());
			}
		}
		return singleLineMap;
	}

	/**
	 * @param multiLineStr
	 * @return 示例： equal ## $..id ## 101 contain ## $..name ## 数学
	 */
	public static List<String[]> parseMultipleLine(String multiLineStr) {
		List<String[]> multiLineList = new ArrayList<String[]>();
		if (multiLineStr.contains("##")) { // 判断是否有分隔符"##"
			if (multiLineStr.contains("\n")) { // 判断断言是否多行
				String[] lines = multiLineStr.split("\n");
				for (String line : lines) {
					if (!line.equals("") && line != null) {
						String cols[] = line.split("##");
						for (int i = 0; i < cols.length; i++) {
							cols[i] = cols[i].trim();
						}
						multiLineList.add(cols);
					}
				}
			} else {
				// 只有一行情况
				String cols[] = multiLineStr.split("##");
				for (int i = 0; i < cols.length; i++) {
					cols[i] = cols[i].trim();
				}
				multiLineList.add(cols);
			}
		} else {
			multiLineList.add(new String[] { multiLineStr });
		}
		return multiLineList;
	}

	public static Map<String, String> parseHeader(String message) {
		header = parseSingleLine(message);
		return header;
	}

	/**
	 * 解析Get请求？后面的参数字符串
	 * 
	 * @param message
	 * @return
	 */
	public static Map<String, String> parsePathParam(String message) {
		pathParam = parseSingleLine(message);
		return pathParam;
	}

	/**
	 * @param cellContent
	 *            ExpectedResult单元格内容
	 * @return
	 */
	public static List<String[]> parseExpectedResult(String cellContent) {
		expectedResultList = parseMultipleLine(cellContent);
		return expectedResultList;
	}

	/**
	 * @param message
	 * @return 对如下等字符串进行分隔，以换行分隔，再以##分隔 "$.data.token ## tokenId $.data.user_id
	 *         ## userId"
	 */
	public static List<String[]> parseParamTransfer(String cellContent) {
		tranferDateList = parseMultipleLine(cellContent);
		return tranferDateList;
	}

	/**
	 * @return Get请求的路径参数，以Map<String,String>显示
	 */
	public static Map<String, String> getPathParamsMap() {
		return pathParam;
	}

	/**
	 * @return 获取Header，以Map<String,String>显示
	 */
	public static Map<String, String> getHeadrMap() {
		return header;
	}

	/**
	 * @return 预期单元格的内容，以List<String[]>显示
	 */
	public static List<String[]> getExpectedResultList() {
		return expectedResultList;
	}

	/**
	 * @return 参数传递单元格的内容，以List<String[]>显示
	 */
	public static List<String[]> getParamTransferList() {
		return tranferDateList;
	}

	/**
	 * @param rowIndex
	 *            起始值为0
	 * @param columnIndex
	 *            起始值为0
	 * @return
	 */
	public static String getExpectedResultSubContent(int rowIndex, int columnIndex) {
		return expectedResultList.get(rowIndex)[columnIndex];
	}

	/**
	 * @param rowIndex
	 *            起始值为0
	 * @param columnIndex
	 *            起始值为0
	 * @return
	 */
	public static String getParamTransferSubContent(int rowIndex, int columnIndex) {
		return tranferDateList.get(rowIndex)[columnIndex];
	}
}
