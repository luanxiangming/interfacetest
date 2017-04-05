package com.vipabc.interfacetest.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 单个工作表解析
 */
public class SheetData {
	private Sheet sheet;
	private List<String[]> sheetDataList = new ArrayList<String[]>();

	public SheetData(Sheet sheet) {
		this.sheet = sheet;
		loadSheetData();
	}

	public static String parseDateNumericTypeCell(Cell cell) {
		short format = cell.getCellStyle().getDataFormat();
		SimpleDateFormat sdf = null;
		/**
		 * yyyy-MM-dd   =>14 
		 * yyyy年m月d日    =>31 
		 * yyyy年m月            =>57 
		 * m月d日                    =>58 
		 * HH:mm        =>20 
		 * h时mm分                  =>32
		 */
		if (format == 14 || format == 31 || format == 57 || format == 58) {
			// 日期
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else if (format == 20 || format == 32) {
			// 时间
			sdf = new SimpleDateFormat("HH:mm");
		}
		double dateValue = cell.getNumericCellValue();
		Date date = DateUtil.getJavaDate(dateValue);
		return sdf.format(date);
	}

	public static String parseNonDateNumericTypeCell(Cell cell) {
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String value = cell.getStringCellValue();
		if (value != null && !value.equals("")) {
			if (value.indexOf(".") != -1) { // 含小数
				value = String.valueOf(new Double(value));
			} 
//			else { // 不含小数
//				value = value;
//			}
		}
		return value;
	}

	/**
	 * @param row
	 * @param columm
	 * @return row,column索引起始值为0
	 */
	protected String getCellText(Row row, int columm) {
		String value = "";
		Cell cell = row.getCell(columm, Row.CREATE_NULL_AS_BLANK);
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: // 字符串类型处理
			value = cell.getStringCellValue().trim();
			break;
		case Cell.CELL_TYPE_NUMERIC: // 数字类型处理
			if (DateUtil.isCellDateFormatted(cell)) { // 日期类型处理
				value = parseDateNumericTypeCell(cell).trim();
			} else { // 非日期类型
				value = parseNonDateNumericTypeCell(cell).trim();
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = (cell.getBooleanCellValue() == true ? "Y" : "N");
			break;
		case Cell.CELL_TYPE_FORMULA:
			// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			} else {
				value = cell.getNumericCellValue() + "";
			}
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		case Cell.CELL_TYPE_ERROR:
			value = "";
			break;
		default:
			value = "";
		}
		return value;
	}

	private void loadSheetData() {
		int columnNum = 0;
		if (sheet.getRow(0) != null) { // 标题列
			columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
		}
		if (columnNum > 0) {
			for (Row row : sheet) {
				String[] rowData = new String[columnNum];
				for (int i = 0; i < columnNum; i++) {
					rowData[i] = getCellText(row, i);
				}
				if ("".equals(rowData[0])) {
					continue;
				}
				sheetDataList.add(rowData);
			}
		}
	}

	public List<String[]> getSheetData() {
		return sheetDataList;
	}

	public int getRowCount() {
		return sheet.getLastRowNum();
	}

	public int getColumnCount() {
		Row row = sheet.getRow(0);
		if (row != null && row.getLastCellNum() > 0) {
			return row.getLastCellNum();
		}
		return 0;
	}

	/**
	 * @param rowIndex
	 *            起始值为0
	 * @return
	 */
	public String[] getRowData(int rowIndex) {
		String[] dataArray = null;
		if (rowIndex > getRowCount()) {
			return dataArray;
		} else {
			dataArray = new String[getColumnCount()];
			return sheetDataList.get(rowIndex);
		}
	}	
	
	public Map<String,String> getRowDataMap(int rowIndex){
		Map<String,String> map=new HashMap<String,String>();
		String[] values=getRowData(rowIndex);
		String[] keys=getRowData(0);
		for(int i=0;i<keys.length;i++){
			map.put(keys[i], values[i]);
		}
		return map;
	}
	

	/**
	 * @param columnIndex
	 *            起始值为0
	 * @return
	 */
	public String[] getColumnData(int columnIndex) {
		String[] dataArray = null;
		if (columnIndex > getColumnCount()) {
			return dataArray;
		} else {
			if (sheetDataList != null && sheetDataList.size() > 0) {
				dataArray = new String[getRowCount()];
				int index = 0;
				for (String[] rowData : sheetDataList) {
					if (rowData != null) {
						// 标题列去除
						if (index == 0) {
							index++;
							continue;
						} else {
							dataArray[index - 1] = rowData[columnIndex];
							index++;
						}
					}
				}
			}
		}
		return dataArray;
	}
	
	/**
	 * @param row
	 *            起始值为0
	 * @param column
	 *            起始值为0
	 * @return
	 */
	public String getCellData(int row, int column) {
		return getRowData(row)[column];
	}
}
