package com.yingshengping.library.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	public static String getNow() {
		String str = "";
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		str = dtf.format(dt);
		return str;
	}
	
}
