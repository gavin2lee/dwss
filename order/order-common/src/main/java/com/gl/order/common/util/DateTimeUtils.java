package com.gl.order.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateTimeUtils {
	public static final String SIMPLE_DATE_FMT = "YYYYMMDD";
	public static final String SIMPLE_DATE_TIME_FMT = "YYYYMMDDHHmmssSSS";

	public static String date2string(Date date) {
		if (date == null) {
			return null;
		}

		DateFormat df = new SimpleDateFormat(SIMPLE_DATE_FMT);
		return df.format(date);
	}

	public static String date2string() {
		Date sysdate = new Date();
		return date2string(sysdate);
	}

	public static String datetime2string(Date datetime) {
		if (datetime == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(SIMPLE_DATE_TIME_FMT);
		return df.format(datetime);
	}

	public static String datetime2string() {
		Date sysdate = new Date();
		return datetime2string(sysdate);
	}
}
