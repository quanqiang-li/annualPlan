package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static void main(String[] args) {
		String mmdd = getMMDD(Calendar.getInstance());
		System.out.println(mmdd);
	}
	
	public static String getMMDD(Calendar calendar){
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("MMdd");
		return df.format(date);
	}
}
