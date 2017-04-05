package com.vipabc.interfacetest.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatUtil {
	
    private static final String RMB_LOWER_PATTERN = "￥###,###.00"; 
    
    public static String toRmbLower(BigDecimal value){
        return NumberFormatUtil.format(value, RMB_LOWER_PATTERN);
    }
    
    public static String format(BigDecimal value, String pattern){
        if(value == null){
            return "";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

	public static BigDecimal formatHalfUpBigDecimal(BigDecimal bigDecimal) {
		return new BigDecimal(bigDecimal.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal halfUp(BigDecimal bigDecimal){
		return halfUp(0,bigDecimal);
	}
	
	public static BigDecimal halfUp(int scale,BigDecimal bigDecimal){
		return null==bigDecimal?bigDecimal:bigDecimal.setScale(scale,BigDecimal.ROUND_HALF_UP);
	}

}
