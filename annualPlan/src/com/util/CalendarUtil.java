package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static String[] weeks = {"����", "��һ", "�ܶ�", "����", "����", "����", "����" };
	public static String[] days = {"��һ","����","����","����","����","����","����","����","����","��ʮ",
								   "ʮһ","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","ʮ��","��ʮ",
								   "إһ","إ��","إ��","إ��","إ��","إ��","إ��","إ��","إ��","��ʮ"};
	
	public static void main(String[] args) throws ParseException {
		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 0, 5);
		String a = "���³���";
		System.out.println(a.substring(2).equals("��һ") ? a.substring(0,2) : a.substring(2) );
		
	}
	/**
	 * ����ָ����ʽ������ת�����ַ���
	 * @param calendar ����ʵ��
	 * @param style ����YYYY����yyyy,����MM,����DD��dd,<br>
	 * СʱHH 24��,hh 12��,����mm,��ss
	 * @return 
	 */
	public static String getStyleDate(Calendar calendar,String style){
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(style);
		return df.format(date);
	}
	/**
	 * ����ָ����ʽ���ַ���ת��������
	 * @param date
	 * @param style ����YYYY����yyyy,����MM,����DD��dd,<br>
	 * СʱHH 24��,hh 12��,����mm,��ss
	 * @return
	 * @throws ParseException
	 */
	public static Date getStyleDate(String date,String style) throws ParseException{
		DateFormat df = new SimpleDateFormat(style);
		return df.parse(date);
	}
	
	/**
	 * ����ת����ũ��<br>
	 * �����������ȥ��ȡ�����ļ���¼������и����ũ���·ݳ�һ��Ӧ��������,�����ڵ���<br>
	 * �ҵ�һ��С�ڵ�������ӽ����������յĽڵ���,��ȡ�ýڵ��ն�Ӧλ�õ�ũ���·�<br>
	 * �ø�����-�ڵ���+1�õ�ũ������
	 * @param calendar ����ʵ��
	 * @return �·ݴ����µ�����  ����إ�����ʮ,���û�м�¼,�򷵻�"ũ����ȱ";ռ�ĸ�����λ��,never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getLunarCalendar(Calendar calendar) throws ParseException{
		String lunar = "ũ����ȱ";
		String date = getStyleDate(calendar, "yyyyMMdd");
		int year = calendar.get(Calendar.YEAR);
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty(year + "key","");
		String values = propertyUtil.properties.getProperty(year + "value","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		int index = 0;
		for(int i = nodes.length-1;i>=0;i--){
			if(nodes[i].compareTo(date) <= 0){
				index = i;//�·�ȷ��
				break;
			}
		}
		//��ȡ��long����ǿ��תint,��õ�Ԥ��֮��Ľ��,ʹ��intValue
		Long day = (calendar.getTimeInMillis() - getStyleDate(nodes[index], "yyyyMMdd").getTime())/(24*60*60*1000) + 1;
		//�·�+����
		lunar = nodeValues[index] + days[day.intValue()-1];
		return lunar;
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
