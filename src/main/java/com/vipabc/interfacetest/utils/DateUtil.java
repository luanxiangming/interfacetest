package com.vipabc.interfacetest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间相关功能
 */
public class DateUtil {

	public static String patternIntervalDefault = "yyyy/MM/dd HH:mm:ss";

	
	/**
	 * 获取当前时间
	 */
	public static String getCurrentTime(String pattern) {
		return new SimpleDateFormat(patternIntervalDefault).format(new Date());
	}

	/**
	 * 获取当前日
	 */
	public static String getCurrentDay(String pattern) {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	/**
	 * 获取当前月	
	 */
	public static String getCurrentMonth(String pattern) {
		return new SimpleDateFormat("yyyy/MM").format(new Date());
	}


	/*	
	 * 获取当前时间之前或之后几天	 
	 */
	public static String getTimeIntervalByDay(int day,String pattern) {		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}	
	
	/*	
	 * 获取当前时间之前或之后几天	  默认yyyy/MM/dd HH:mm:ss 
	 */
	public static String getTimeIntervalByDay(int day) {
		return getTimeIntervalByDay(day,patternIntervalDefault);
	}
	

	/*	
	 * 获取当前时间之前或之后几小时 hour	 
	 */
	public static String getTimeIntervalByHour(int hour,String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
		
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 00);
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}
	
	/*	
	 * 获取当前时间之前或之后几小时 hour	 默认yyyy/MM/dd HH:mm:ss 
	 */
	public static String getTimeIntervalByHour(int hour) {
		return getTimeIntervalByHour(hour,patternIntervalDefault);
	}
	
	

	/*	
	 * 获取当前时间之前或之后几分钟 minute	
	 */
	public static String getTimeIntervalByMinute(int minute,String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}
	
	/*	
	 * 获取当前时间之前或之后几分钟 minute	  默认yyyy/MM/dd HH:mm:ss
	 */
	public static String getTimeIntervalByMinute(int minute) {
		return getTimeIntervalByMinute(minute,patternIntervalDefault);
	}
	
	
	/**
	 * 预定在30分钟档的课程时间
	 */
	public static String getReservationTimeIn30Min(int intervalHours){		
		Calendar calendar = Calendar.getInstance();			
		int currentMin=Integer.valueOf( new SimpleDateFormat("mm").format(new Date()));	
		
		if(intervalHours<24){
			intervalHours=24; // 预约需一天后，不满一天，按一天处理		
			if(currentMin>=30){ //超过30分，小时需加1	
				intervalHours ++;			
			}	
		}else if(currentMin>=30)	{
			intervalHours ++;	 //超过30分，小时需加1		 
		}
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + intervalHours);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 00);
		Date date=calendar.getTime();
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
	}
	
	public static void main(String[] args) {
		
		System.out.println(getReservationTimeIn30Min(24));
	}
}
