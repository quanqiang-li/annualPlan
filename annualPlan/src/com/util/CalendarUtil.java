package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarUtil {

	public static String[] weeks = {"����", "��һ", "�ܶ�", "����", "����", "����", "����" };
	
	public static void main(String[] args) {
		Calendar instance = Calendar.getInstance();
		instance.set(2015, 11, 12);
		String mmdd = getMMDD(instance);
		System.out.println(mmdd);
		System.out.println(instance.getTime().toLocaleString());
	}
	/**
	 * ����������������
	 * @param calendar
	 * @return �·���1-12 ����201512
	 * @author carl918@163.com
	 */
	public static String getMMDD(Calendar calendar){
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("MMdd");
		return df.format(date);
	}
	
	/**
	 * ����ת����ũ��
	 * @param calendar
	 * @return �·ݴ����µ�����  ����إ�����ʮ
	 * @author carl918@163.com
	 */
	public static String getLunarCalendar(Calendar calendar){
		
		return null;
	}
	/**
	 * ����ת��������
	 * @param calendar
	 * @return ����һ������
	 * @author carl918@163.com
	 */
	public static String getWeek(Calendar calendar){
		int week = calendar.get(Calendar.DAY_OF_WEEK);//����Ϊ1,����Ϊ7
		String weekSty = weeks[week-1];
		return weekSty;
	}
}
