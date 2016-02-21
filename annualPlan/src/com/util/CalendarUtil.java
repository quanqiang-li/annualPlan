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
	 * 天月德,每个月(二十四节气代表的月)按顺序列出:天德,天德合,月德,月德合，是为吉日
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
	/**
	 * 金符经，即九星，固定月份的日干支,是为吉日 
	 */
	public static String[][][] jinFuJings = {{{"正月四月七月十月","煞贡","丁卯丙子乙酉甲午癸卯壬子辛酉"},
												{"正月四月七月十月","直星","戊辰丁丑丙戍乙未甲辰癸丑壬戍"},
												{"正月四月七月十月","人专","辛未庚辰己丑戊戍丁未丙辰"}},
											{{"二月五月八月冬月","煞贡","丙寅乙亥甲申癸巳壬寅辛亥庚申"},
												{"二月五月八月冬月","直星","丁卯丙子乙酉甲午癸卯壬子辛酉"},
												{"二月五月八月冬月","人专","庚午己卯戊子丁酉丙午乙卯"}},
											{{"三月六月九月腊月","煞贡","己丑甲戍癸未壬辰辛丑庚戍己未"},
												{"三月六月九月腊月","直星","丙寅乙亥甲申癸巳壬寅辛卯庚申"},
												{"三月六月九月腊月","人专","己巳戊寅己亥丙申乙巳甲寅癸亥"}}};
									
	/**
	 * 金神七煞,农历年的干支日，大凶
	 */
	public static String jinShenQiSha[][] = {{"甲","午未"},{"乙","辰巳"},{"丙","子丑寅卯"},{"丁","戌亥"},{"戊","申酉"},
											 {"己","午未"},{"庚","辰巳"},{"辛","子丑寅卯"},{"壬","戌亥"},{"癸","申酉"}};
	/**
	 * 杨公忌，凶日
	 */
	public static String yangGongJi[][] = {{"正月","十三"},{"二月","十一"},{"三月","初九"},
											{"四月","初七"},{"五月","初五"},{"六月","初三"},
											{"七月","初一廿九"},{"八月","廿七"},{"九月","廿五"},
											{"十月","廿三"},{"冬月","廿一"},{"腊月","十九"}};
	/**
	 * 月忌日，每月的这几天都是凶日
	 */
	public static String yueJi[] = {"初五","十四","廿三"};
	/**
	 * 红嘴朱雀日，每月的这几天都是凶日
	 */
	public static String hongZuiZhuQue[] = {"初一","初九","十七","十八","廿五"};
	
	/**
	 * 二十八星宿，按顺序
	 */
	public static String xingXiu[] = {"角木蛟七煞","亢金龙七煞","氐土貉凶星","房日兔吉星","心月狐凶星","尾火虎吉星","箕水豹吉星",
									   "斗木獬吉星","牛金牛七煞","女土蝠凶星","虚日鼠凶星","危月燕凶星","室火猪吉星","壁水貐吉星",
									   "奎木狼七煞","娄金狗七煞","胃土雉吉星","昴日鸡凶星","毕月乌吉星","觜火猴凶星","参水猿吉星",
									   "井木犴吉星","鬼金羊七煞","柳土獐凶星","星日马七煞","张月鹿吉星","翼火蛇凶星","轸水蚓吉星"};
	/**
	 * 星宿上任时间表，以第二个字为准
	 */
	public static String xingXiuShangRen[][] = {{"日","3-5"},{"月","5-7"},{"水","7-9"},
												{"金","9-11"},{"土","11-13"},{"木","13-15"},{"火","15-17"}}; 
	/**
	 * 十二建，划分好了黄黑道
	 */
	public static String shiErJian[] = {"建黑道","除黄道","满黑道","平黑道","定黄道","执黄道","破中黑","危黄道","成中黄","收黑道","开中黄","闭中黑"};
	
	public static void main(String[] args) throws ParseException {

		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 0, 21);
		System.out.println(isJie(instance));
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
	 * 获取指定阳历日的年干支，这里说的年，是农历年，即正月初一开始计数<br>
	 * 配置文件读取每个农历年计数开始的日期，叫做节点年<br>
	 * 找到小于等于且最接近给定日历的节点年，即为指定农历年
	 * 以1984作为原点年,此年正好是"甲子",任意甲子年均可<br>
	 * 用(指定年-原点年)%60的值,作为干支数组的索引即可
	 * @param calendar
	 * @return 年干支,如甲子,乙丑
	 */
	public static String getYearGanzhi(Calendar calendar){
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty("yangLiNian","");
		String values = propertyUtil.properties.getProperty("nongLiNian","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		String date = getStyleDate(calendar, "yyyyMMdd");
		int index = 0;
		//从大的往小的方向找,第一个小于等于给定日的即为需要的
		for(int i = nodes.length-1;i>=0;i--){
			if(nodes[i].compareTo(date) <= 0){
				index = i;//节点日确认
				break;
			}
		}
		String nongLiNian = nodeValues[index];
		int value = (Integer.valueOf(nongLiNian) - 1984)  % 60;
		//处理负数的情况
		if(value < 0){
			value = value + 60;
		}
		return ganzhi[value];
	}
	
	/**
	 * 获取指定阳历日的日干支<br>
	 * 以20151214作为原点日,此日正好是"甲子",任意甲子日均可<br>
	 * 用(指定日历-原点日)%60的值,作为干支数组的索引即可
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
		String jiShi = "吉时未知";
		for(int i = 0;i<luckyTime.length;i++){
			if(diZhi.equals(luckyTime[i][0])){
				jiShi = luckyTime[i][1];
				break;
			}
		}
		return jiShi;
	}
	/**
	 * 获取节气对应的农历月份
	 * 找到给定年份所有节气对应的日历,称为节点日,首尾各补充一个,方便计算<br>
	 * 找到小于等于且最接近给定日的节点日,然后确认该节点日(节气)代表的月份<br>
	 * @param calendar
	 * @return 正月，腊月等
	 * @author carl918@163.com
	 */
	public static String getJieQiMonth(Calendar calendar){
		String month = "";//节气代表的月份
		int year = calendar.get(Calendar.YEAR);
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty(year + "jieQiKey","");
		String values = propertyUtil.properties.getProperty(year + "jieQiValue","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		String date = getStyleDate(calendar, "yyyyMMdd");
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
		return month;
	}
	
	/**
	 * 获取指定日的天月德情况<br>
	 * 首先找到日历对应的节气月份
	 * 然后验证给定日的干支是否包含月份的天月德
	 * @param calendar
	 * @return
	 */
	public static String getTianYueDeofDay(Calendar calendar){
		String month = getJieQiMonth(calendar);//节气代表的月份
		String tyd = "";//节气月份天月德
		//节气月份的天月德，四个汉字分别代表：天德,天德合,月德,月德合
		for(int i = 0;i<tianYueDe.length;i++){
			if(tianYueDe[i][0].equals(month)){
				tyd = tianYueDe[i][1];
				break;
			}
		}
		//给定日的干支
		String dayGanzhi = getDayGanzhi(calendar);
		String tyds = "";//天月德综合情况
		for(int i = 0; i<2; i++){
			String subStr = dayGanzhi.substring(i, i+1);//分别用天干，地支来判断天月德情况
			int fromIndex = 0;
			while(true){
				//所有出现的位置都要考虑
				int indexOf = tyd.indexOf(subStr, fromIndex);
				if(indexOf < 0){
					break;//如果没有，退出while
				}
				switch (indexOf) {
				case 0:
					tyds = tyds + "天德";
					break;
				case 1:
					tyds = tyds + "天德合";
					break;
				case 2:
					tyds = tyds + "月德";
					break;
				case 3:
					tyds = tyds + "月德合";
					break;
				default:
					break;
				}
				fromIndex = indexOf + 1;//从出现的位置向后继续找
			}
		}
		return tyds;
	}
	
	/**
	 * 金神七煞<br>
	 * 由阳历日获取对应的农历年，然后再找农历年中的金神七煞日
	 * @param calendar
	 * @return 若是则返回“金神七煞”，若不是则返回空字符串“”，never null
	 * @author carl918@163.com
	 */
	public static String getJinShenQiSha(Calendar calendar){
		String jsqs = "";
		String dayGanzhi = getDayGanzhi(calendar);
		String dayDiZhi = dayGanzhi.substring(1);//日地支
		String yearGanzhi = getYearGanzhi(calendar);
		String yearTianGan = yearGanzhi.substring(0, 1);//年天干
		//年天干对应的日地支即为金神七煞
		for(int i = 0;i<jinShenQiSha.length;i++){
			if(jinShenQiSha[i][0].equals(yearTianGan)){
				jsqs = jinShenQiSha[i][1].contains(dayDiZhi) ? "金神七煞" :"";
				break;
			}
		}
		return jsqs;
	}
	
	/**
	 * 金符经，即九星，其中煞贡，直星，人专是为吉星<br>
	 * 首先确定阳历日的农历月份，然后再确定阳历日的日干支，最后判断是否代表上述的吉星
	 * @param calendar
	 * @return 若是则返回对应的吉星，若不是则返回空字符串“”，never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getJinFuJing(Calendar calendar) throws ParseException{
		String jfj = "";
		String lunarCalendar = getLunarCalendar(calendar);
		String lunarMonth = lunarCalendar.substring(0, 2);//农历月份
		String dayGanzhi = getDayGanzhi(calendar);
		//i代表三组月份，j代表三个吉星
		for(int i = 0;i<jinFuJings.length;i++){
			if(jinFuJings[i][0][0].contains(lunarMonth)){
				for(int j = 0;j<jinFuJings[i].length;j++){
					if(jinFuJings[i][j][2].contains(dayGanzhi)){
						jfj = jinFuJings[i][j][1];
						break;
					}
				}
				break;
			}
		}
		return jfj;
	}
	/**
	 * 获取杨公忌日
	 * @param calendar
	 * @return 如果是则返回“杨公忌”，否则返回空字符串“”，never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getYangGongJi(Calendar calendar) throws ParseException{
		String ygj = "";
		String lunarCalendar = getLunarCalendar(calendar);
		String lunarMonth = lunarCalendar.substring(0, 2);
		String lunarDay = lunarCalendar.substring(2);
		for(int i = 0;i<yangGongJi.length;i++){
			if(yangGongJi[i][0].equals(lunarMonth)){
				ygj = yangGongJi[i][1].contains(lunarDay) ? "杨公忌" : "";
				break;
			}
		}
		return ygj;
	}
	/**
	 * 
	 * 获取月忌日
	 * @param calendar
	 * @return 如果是则返回“月忌”，否则返回空字符串“”，never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getYueJi(Calendar calendar) throws ParseException{
		String yj = "";
		String lunarCalendar = getLunarCalendar(calendar);
		String lunarDay = lunarCalendar.substring(2);
		for (int i = 0; i < yueJi.length; i++) {
			if (yueJi[i].equals(lunarDay)) {
				yj = "月忌";
				break;
			}
		}
		return yj;
	}
	
	/**
	 * 
	 * 获取红嘴朱雀日，类似每月的忌日
	 * @param calendar
	 * @return 如果是则返回“红嘴朱雀”，否则返回空字符串“”，never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getHongZuiZhuQue(Calendar calendar) throws ParseException{
		String hzzq = "";
		String lunarCalendar = getLunarCalendar(calendar);
		String lunarDay = lunarCalendar.substring(2);
		for (int i = 0; i < hongZuiZhuQue.length; i++) {
			if (hongZuiZhuQue[i].equals(lunarDay)) {
				hzzq = "红嘴朱雀";
				break;
			}
		}
		return hzzq;
	}
	
	/**
	 * 获取指定阳历日的二十八星宿及上任时间<br>
	 * 以20160121作为原点日,此日正好是"角木蛟",任意角木蛟日均可<br>
	 * 用(指定日历-原点日)%28的值,作为星宿数组的索引即可<br>
	 * 再用星宿的第二个字确定上任时间
	 * @param calendar
	 * @return 日干支,如甲子,乙丑
	 */
	public static String getXingXiu(Calendar calendar){
		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 0, 21);//原点日,角木蛟
		//比原点日多几天
		Long num = (calendar.getTimeInMillis()-instance.getTimeInMillis())/(24*60*60*1000);
		int value = num.intValue() % 28;
		//处理负数的情况
		if(value < 0){
			value = value + 28;
		}
		String xx = xingXiu[value];
		String substring = xx.substring(1, 2);
		for (int i = 0; i < xingXiuShangRen.length; i++) {
			if(xingXiuShangRen[i][0].equals(substring)){
				xx = xx + xingXiuShangRen[i][1];
				break;
			}
		}
		return xx;
	}
	/**
	 * 是否为二十四节气中的节
	 * @param calendar
	 * @return 是返回true，否返回false
	 * @author carl918@163.com
	 */
	public static boolean isJie(Calendar calendar){
		//判断日历是否为节，注意不包含气
		boolean jq = false;
		int year = calendar.get(Calendar.YEAR);
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty(year + "jieQiKey","");
		String values = propertyUtil.properties.getProperty(year + "jieQiValue","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		String date = getStyleDate(calendar, "yyyyMMdd");
		int nodeIndex = -1;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].equals(date)) {
				nodeIndex = i;
				break;
			}
		}
		//当天即不是节也不是气
		if (nodeIndex == -1) {
			return jq;
		}
		for (int i = 0; i < jieQi.length; i++) {
			//0是节，1是气
			if(jieQi[i][1].indexOf(nodeValues[nodeIndex]) == 0){
				jq = true;
				break;
			}
		}
		return jq;
	}
	
	
	/**
	 * 获取当前日历的十二建<br>
	 * 首先获取前一天的十二建，如果今天是节，重复前一天的，如果不是节，向后加一个，<br>
	 * 递归求解,标记原点日20160101，代表“破中黑” ,这里只能求比原点日大的情况
	 * @param calendar
	 * @return 
	 * @author carl918@163.com
	 */
	public static String getShiErJian(Calendar calendar){
		//判断日历是否为节，注意不包含气
		boolean jie = isJie(calendar);
		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 0, 1);
		if(calendar.compareTo(instance) < 0){
			return "未知";
		}else if(calendar.compareTo(instance) == 0){
			return "破中黑";
		} else {
			calendar.add(Calendar.DATE, -1);
			getShiErJian(calendar);
		}
		return null;
	}
}
