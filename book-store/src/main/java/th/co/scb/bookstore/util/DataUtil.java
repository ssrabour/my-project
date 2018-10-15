package th.co.scb.bookstore.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataUtil {
	
	public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	public static boolean isEmpty(Object[] objs) {
		return objs == null || objs.length == 0;
	}
	
	public static Date toDate(String date, String pattern) {
		try {
			DateFormat df = new SimpleDateFormat(pattern, Locale.US);
			return df.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String toString(Date date, String pattern) {
		try {
			DateFormat df = new SimpleDateFormat(pattern, Locale.US);
			return df.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean isDate(String date, String pattern) {
		return toDate(date, pattern) != null;
	}
}
