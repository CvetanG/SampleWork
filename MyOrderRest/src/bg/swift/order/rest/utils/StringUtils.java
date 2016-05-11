package bg.swift.order.rest.utils;

public class StringUtils {
	
	protected StringUtils() {}
	
	public static String setNotNull(String t) {
		if (t != null) {
			return t;
		}
		return "";
	}
	
	public static boolean isNotNullOrEmpty(String t) {
		String notNull = setNotNull(t);
		return !"".equals(notNull);
	}
	
	public static boolean isNullOrEmpty(String t) {
		String notNull = setNotNull(t);
		return "".equals(notNull);
	}
}
