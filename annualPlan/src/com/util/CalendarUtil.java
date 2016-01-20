package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarUtil {

	public static String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六" };
	
	public static void main(String[] args) {
		Calendar instance = Calendar.getInstance();
		instance.set(2015, 11, 12);
		String mmdd = getMMDD(instance);
		System.out.println(mmdd);
		System.out.println(instance.getTime().toLocaleString());
	}
	/**
	 * 根据日历返回年月
	 * @param calendar
	 * @return 月份是1-12 ，如201512
	 * @author carl918@163.com
	 */
	public static String getMMDD(Calendar calendar){
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("MMdd");
		return df.format(date);
	}
	
	/**
	 * 阳历转换成农历
	 * @param calendar
	 * @return 月份从正月到腊月  天数廿代表二十
	 * @author carl918@163.com
	 */
	public static String getLunarCalendar(Calendar calendar){
		
		return null;
	}
	/**
	 * 阳历转换成星期
	 * @param calendar
	 * @return 从周一到周日
	 * @author carl918@163.com
	 */
	public static String getWeek(Calendar calendar){
		int week = calendar.get(Calendar.DAY_OF_WEEK);//周日为1,周六为7
		String weekSty = weeks[week-1];
		return weekSty;
	}
}
