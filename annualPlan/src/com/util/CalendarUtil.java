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
	public static String[] ganzhi = {"����", "�ҳ�", "����", "��î", "�쳽", "����", "����",	"��δ", "����", "����", 
									 "����", "�Һ�", "����", "����", "����", "��î", "����", "����", "����", "��δ", 
									 "����", "����", "����", "����", "����", "����", "����", "��î", "�ɳ�", "����", 
									 "����", "��δ", "����", "����", "����", "����", "����", "����", "����", "��î",
									 "�׳�", "����", "����", "��δ", "����", "����", "����", "����", "����", "���", 
									 "����", "��î", "����", "����", "����", "��δ", "����", "����", "����", "�ﺥ" };
	private static String[][] luckyTime= {{"��","23-3,5-7,11-13,15-19"},{"��","23-3,5-7,11-13,15-19"},
								   {"��","3-7,9-11,15-17,19-23"},{"δ","3-7,9-11,15-17,19-23"},
								   {"��","23-3,7-11,13-15,19-21"},{"��","23-3,7-11,13-15,19-21"},
								   {"î","23-1,3-7,11-15,17-19"},{"��","23-1,3-7,11-15,17-19"},
								   {"��","3-5,7-11,15-19,21-23"},{"��","3-5,7-11,15-19,21-23"},
								   {"��","1-3,7-9,11-15,19-23"},{"��","1-3,7-9,11-15,19-23"}
									};
	


	public static void main(String[] args) throws ParseException {

		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 1, 18);
		String dayGanzhi = getDayGanzhi(instance);
		String diZhi = dayGanzhi.substring(1);
		for(int i = 0;i<luckyTime.length;i++){
			if(diZhi.equals(luckyTime[i][0])){
				System.out.println(luckyTime[i][1]);
			}
		}
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
	/**
	 * ��ȡָ�������յ��ո�֧<br>
	 * ��20151214��Ϊԭ����,����������"����",��������վ���<br>
	 * ��(ָ������-ԭ����)%60��ֵ,��Ϊ��֧�������������
	 * @param calendar
	 * @return �ո�֧,�����,�ҳ�
	 */
	public static String getDayGanzhi(Calendar calendar){
		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2015, 11, 14);//ԭ����,������
		//��ԭ���ն༸��
		Long num = (calendar.getTimeInMillis()-instance.getTimeInMillis())/(24*60*60*1000);
		int value = num.intValue() % 60;
		//�����������
		if(value < 0){
			value = value + 60;
		}
		return ganzhi[value];
	}
}
