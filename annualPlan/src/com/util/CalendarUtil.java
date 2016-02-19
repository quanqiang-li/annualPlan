package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

	public static String[] weeks = {"周日", "周一", "周二", "周三", "周四", "周五", "周六" };
	public static String[] days = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十",
								   "十一","十二","十三","十四","十五","十六","十七","十八","十九","二十",
								   "廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","三十"};
	public static String[] ganzhi = {"甲子", "乙丑", "丙寅", "丁卯", "戊辰", "己巳", "庚午",	"辛未", "壬申", "癸酉", 
									 "甲戌", "乙亥", "丙子", "丁丑", "戊寅", "己卯", "庚辰", "辛巳", "壬午", "癸未", 
									 "甲申", "乙酉", "丙戌", "丁亥", "戊子", "己丑", "庚寅", "辛卯", "壬辰", "癸巳", 
									 "甲午", "乙未", "丙申", "丁酉", "戊戌", "己亥", "庚子", "辛丑", "壬寅", "癸卯",
									 "甲辰", "乙巳", "丙午", "丁未", "戊申", "己酉", "庚戌", "辛亥", "壬子", "癸丑", 
									 "甲寅", "乙卯", "丙辰", "丁巳", "戊午", "己未", "庚申", "辛酉", "壬戌", "癸亥" };
	private static String[][] luckyTime= {{"子","23-3,5-7,11-13,15-19"},{"午","23-3,5-7,11-13,15-19"},
								   {"丑","3-7,9-11,15-17,19-23"},{"未","3-7,9-11,15-17,19-23"},
								   {"寅","23-3,7-11,13-15,19-21"},{"申","23-3,7-11,13-15,19-21"},
								   {"卯","23-1,3-7,11-15,17-19"},{"酉","23-1,3-7,11-15,17-19"},
								   {"辰","3-5,7-11,15-19,21-23"},{"戌","3-5,7-11,15-19,21-23"},
								   {"巳","1-3,7-9,11-15,19-23"},{"亥","1-3,7-9,11-15,19-23"}
									};
	/**
	 * 天月德,每个月(二十四节气代表的月)按顺序列出:天德,天德合,月德,月德合
	 */
	public static String[][] tianYueDe = {{"正月","丁壬丙辛"},{"二月","申巳甲己"},{"三月","壬丁壬丁"},
										  {"四月","辛丙庚乙"},{"五月","亥寅丙辛"},{"六月","甲己甲己"},
											{"七月","癸戊壬丁"},{"八月","寅亥庚乙"},{"九月","丙辛丙辛"},
											{"十月","乙庚甲己"},{"冬月","巳申壬丁"},{"腊月","庚乙庚乙"}};
	/**
	 * 二十四节气代表的农历月份,每个月,按顺序列出,先节后气
	 */
	public static String[][] jieQi ={{"正月","立春雨水"},{"二月","惊蛰春分"},{"三月","清明谷雨"},
		  							{"四月","立夏小满"},{"五月","芒种夏至"},{"六月","小暑大暑"},
		  							{"七月","立秋处暑"},{"八月","白露秋分"},{"九月","寒露霜降"},
		  							{"十月","立冬小雪"},{"冬月","大雪冬至"},{"腊月","小寒大寒"}};
	
	public static void main(String[] args) throws ParseException {

		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 1, 18);
		System.out.println("bba".indexOf("b", 1));
	}
	/**
	 * 根据指定样式把日历转换成字符串
	 * @param calendar 日历实例
	 * @param style 年用YYYY或者yyyy,月用MM,日用DD或dd,<br>
	 * 小时HH 24制,hh 12制,分钟mm,秒ss
	 * @return 
	 */
	public static String getStyleDate(Calendar calendar,String style){
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat(style);
		return df.format(date);
	}
	/**
	 * 根据指定样式把字符串转换成日期
	 * @param date
	 * @param style 年用YYYY或者yyyy,月用MM,日用DD或dd,<br>
	 * 小时HH 24制,hh 12制,分钟mm,秒ss
	 * @return
	 * @throws ParseException
	 */
	public static Date getStyleDate(String date,String style) throws ParseException{
		DateFormat df = new SimpleDateFormat(style);
		return df.parse(date);
	}
	
	/**
	 * 阳历转换成农历<br>
	 * 给定阳历年份去获取配置文件中录入的所有该年的农历月份初一对应的阳历日,称作节点日<br>
	 * 找到一个小于等于且最接近给定阳历日的节点日,获取该节点日对应位置的农历月份<br>
	 * 用给定日-节点日+1得到农历日期
	 * @param calendar 日历实例
	 * @return 月份从正月到腊月  天数廿代表二十,如果没有记录,则返回"农历暂缺";占四个汉字位置,never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getLunarCalendar(Calendar calendar) throws ParseException{
		String lunar = "农历暂缺";
		String date = getStyleDate(calendar, "yyyyMMdd");
		int year = calendar.get(Calendar.YEAR);
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty(year + "key","");
		String values = propertyUtil.properties.getProperty(year + "value","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		int index = 0;
		//从大的往小的方向找,第一个小于等于给定日的即为需要的
		for(int i = nodes.length-1;i>=0;i--){
			if(nodes[i].compareTo(date) <= 0){
				index = i;//月份确认
				break;
			}
		}
		//获取的long不可强制转int,会得到预料之外的结果,使用intValue
		Long day = (calendar.getTimeInMillis() - getStyleDate(nodes[index], "yyyyMMdd").getTime())/(24*60*60*1000) + 1;
		//月份+日子
		lunar = nodeValues[index] + days[day.intValue()-1];
		return lunar;
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
	/**
	 * 获取指定阳历日的日干支<br>
	 * 以20151214作为原点日,此日正好是"甲子",任意甲子日均可<br>
	 * 用(指定日历-原地日)%60的值,作为干支数组的索引即可
	 * @param calendar
	 * @return 日干支,如甲子,乙丑
	 */
	public static String getDayGanzhi(Calendar calendar){
		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2015, 11, 14);//原点日,甲子日
		//比原点日多几天
		Long num = (calendar.getTimeInMillis()-instance.getTimeInMillis())/(24*60*60*1000);
		int value = num.intValue() % 60;
		//处理负数的情况
		if(value < 0){
			value = value + 60;
		}
		return ganzhi[value];
	}
	/**
	 * 获取指定日的吉时<br>
	 * 首先获取指定日的干支,然后再获取干支中的地支对应的吉时
	 * @param calendar
	 * @return
	 */
	public static String getLuckyTimeOfDay(Calendar calendar){
		String dayGanzhi = getDayGanzhi(calendar);
		String diZhi = dayGanzhi.substring(1);
		String jiShi = "未知";
		for(int i = 0;i<luckyTime.length;i++){
			if(diZhi.equals(luckyTime[i][0])){
				jiShi = luckyTime[i][1];
				break;
			}
		}
		return jiShi;
	}
	/**
	 * 获取指定日的天月德情况<br>
	 * 找到给定年份所有节气对应的日历,称为节点日,首尾各补充一个,方便计算<br>
	 * 找到小于等于且最接近给定日的节点日,然后确认该节点日(节气)代表的月份<br>
	 * 最后验证给定日的干支是否包含月份的天月德
	 * @param calendar
	 * @return
	 */
	public static String getTianYueDeofDay(Calendar calendar){
		String month = "";//节气代表的月份
		String tyd = "";//节气月份天月德
		String date = getStyleDate(calendar, "yyyyMMdd");
		int year = calendar.get(Calendar.YEAR);
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty(year + "jieQiKey","");
		String values = propertyUtil.properties.getProperty(year + "jieQiValue","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		int index = 0;
		//从大的往小的方向找,第一个小于等于给定日的即为需要的
		for(int i = nodes.length-1;i>=0;i--){
			if(nodes[i].compareTo(date) <= 0){
				index = i;//节点日确认
				break;
			}
		}
		//节气
		String value = nodeValues[index];
		//节气代表的月份
		for(int i = 0;i<jieQi.length;i++){
			if(jieQi[i][1].contains(value)){
				month = jieQi[i][0];
				break;
			}
		}
		//节气月份的天月德
		for(int i = 0;i<tianYueDe.length;i++){
			if(tianYueDe[i][0].equals(value)){
				tyd = tianYueDe[i][1];
				break;
			}
		}
		//给定日的干支
		String dayGanzhi = getDayGanzhi(calendar);
		String tiangan  = dayGanzhi.substring(0, 1);
		String dizhi  = dayGanzhi.substring(1, 2);
		int fromIndex = 0;
		while(true){
			int indexOf = tyd.indexOf(tiangan, fromIndex);
			if(indexOf < 0){
				break;
			}
			switch (indexOf) {
			case 0:
				dizhi = "天德";
				break;
			case 1:
				dizhi = "天德合";
				break;
			case 2:
				dizhi = "月德";
				break;
			case 3:
				dizhi = "月德合";
				break;
			default:
				break;
			}
			fromIndex = indexOf + 1;
		}
		
		return null;
	}
}
