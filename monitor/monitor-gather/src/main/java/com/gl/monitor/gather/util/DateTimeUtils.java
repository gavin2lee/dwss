package com.gl.monitor.gather.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtils {
	public static final String DATE_FORMAT = "yyyyMMdd";
	public static final String TIME_FORMAT = "HHmmssSSS";
	public static final String DATE_TIME_FORMAT = "YYYYmmddHHmmss";
	
	public static String date2string(Date date){
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		return df.format(date);
	}
	
	public static String time2string(Date date){
		DateFormat df = new SimpleDateFormat(TIME_FORMAT);
		return df.format(date);
	}
	
	public static String datetime2string(Date date){
		DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
		return df.format(date);
	}
}
