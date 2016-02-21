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
	/**
	 * ���µ�,ÿ����(��ʮ�Ľ����������)��˳���г�:���,��º�,�µ�,�µºϣ���Ϊ����
	 */
	public static String[][] tianYueDe = {{"����","���ɱ���"},{"����","���ȼ׼�"},{"����","�ɶ��ɶ�"},
										  {"����","��������"},{"����","��������"},{"����","�׼��׼�"},
											{"����","�����ɶ�"},{"����","��������"},{"����","��������"},
											{"ʮ��","�Ҹ��׼�"},{"����","�����ɶ�"},{"����","���Ҹ���"}};
	/**
	 * ��ʮ�Ľ��������ũ���·�,ÿ����,��˳���г�,�Ƚں���
	 */
	public static String[][] jieQi ={{"����","������ˮ"},{"����","���ݴ���"},{"����","��������"},
		  							{"����","����С��"},{"����","â������"},{"����","С�����"},
		  							{"����","���ﴦ��"},{"����","��¶���"},{"����","��¶˪��"},
		  							{"ʮ��","����Сѩ"},{"����","��ѩ����"},{"����","С����"}};
	/**
	 * ������������ǣ��̶��·ݵ��ո�֧,��Ϊ���� 
	 */
	public static String[][][] jinFuJings = {{{"������������ʮ��","ɷ��","��î�������ϼ����î��������"},
												{"������������ʮ��","ֱ��","�쳽���������δ�׳��������"},
												{"������������ʮ��","��ר","��δ��������������δ����"}},
											{{"�������°��¶���","ɷ��","�����Һ��������������������"},
												{"�������°��¶���","ֱ��","��î�������ϼ����î��������"},
												{"�������°��¶���","��ר","���缺î���Ӷ��ϱ�����î"}},
											{{"�������¾�������","ɷ��","���������δ�ɳ����������δ"},
												{"�������¾�������","ֱ��","�����Һ��������������î����"},
												{"�������¾�������","��ר","�������������������ȼ����ﺥ"}}};
									
	/**
	 * ������ɷ,ũ����ĸ�֧�գ�����
	 */
	public static String jinShenQiSha[][] = {{"��","��δ"},{"��","����"},{"��","�ӳ���î"},{"��","�纥"},{"��","����"},
											 {"��","��δ"},{"��","����"},{"��","�ӳ���î"},{"��","�纥"},{"��","����"}};
	/**
	 * ��ɣ�����
	 */
	public static String yangGongJi[][] = {{"����","ʮ��"},{"����","ʮһ"},{"����","����"},
											{"����","����"},{"����","����"},{"����","����"},
											{"����","��һإ��"},{"����","إ��"},{"����","إ��"},
											{"ʮ��","إ��"},{"����","إһ"},{"����","ʮ��"}};
	/**
	 * �¼��գ�ÿ�µ��⼸�춼������
	 */
	public static String yueJi[] = {"����","ʮ��","إ��"};
	/**
	 * ������ȸ�գ�ÿ�µ��⼸�춼������
	 */
	public static String hongZuiZhuQue[] = {"��һ","����","ʮ��","ʮ��","إ��"};
	
	/**
	 * ��ʮ�����ޣ���˳��
	 */
	public static String xingXiu[] = {"��ľ����ɷ","��������ɷ","ص��������","�����ü���","���º�����","β�𻢼���","��ˮ������",
									   "��ľ⳼���","ţ��ţ��ɷ","Ů��������","����������","Σ��������","�һ�����","��ˮ؅����",
									   "��ľ����ɷ","¦����ɷ","θ��������","���ռ�����","�����ڼ���","���������","��ˮԳ����",
									   "��ľ����","�������ɷ","���������","��������ɷ","����¹����","���������","��ˮ򾼪��"};
	/**
	 * ��������ʱ����Եڶ�����Ϊ׼
	 */
	public static String xingXiuShangRen[][] = {{"��","3-5"},{"��","5-7"},{"ˮ","7-9"},
												{"��","9-11"},{"��","11-13"},{"ľ","13-15"},{"��","15-17"}}; 
	/**
	 * ʮ���������ֺ��˻ƺڵ�
	 */
	public static String shiErJian[] = {"���ڵ�","���Ƶ�","���ڵ�","ƽ�ڵ�","���Ƶ�","ִ�Ƶ�","���к�","Σ�Ƶ�","���л�","�պڵ�","���л�","���к�"};
	
	public static void main(String[] args) throws ParseException {

		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 0, 21);
		System.out.println(isJie(instance));
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
		//�Ӵ����С�ķ�����,��һ��С�ڵ��ڸ����յļ�Ϊ��Ҫ��
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
	 * ��ȡָ�������յ����֧������˵���꣬��ũ���꣬�����³�һ��ʼ����<br>
	 * �����ļ���ȡÿ��ũ���������ʼ�����ڣ������ڵ���<br>
	 * �ҵ�С�ڵ�������ӽ����������Ľڵ��꣬��Ϊָ��ũ����
	 * ��1984��Ϊԭ����,����������"����",������������<br>
	 * ��(ָ����-ԭ����)%60��ֵ,��Ϊ��֧�������������
	 * @param calendar
	 * @return ���֧,�����,�ҳ�
	 */
	public static String getYearGanzhi(Calendar calendar){
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty("yangLiNian","");
		String values = propertyUtil.properties.getProperty("nongLiNian","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		String date = getStyleDate(calendar, "yyyyMMdd");
		int index = 0;
		//�Ӵ����С�ķ�����,��һ��С�ڵ��ڸ����յļ�Ϊ��Ҫ��
		for(int i = nodes.length-1;i>=0;i--){
			if(nodes[i].compareTo(date) <= 0){
				index = i;//�ڵ���ȷ��
				break;
			}
		}
		String nongLiNian = nodeValues[index];
		int value = (Integer.valueOf(nongLiNian) - 1984)  % 60;
		//�����������
		if(value < 0){
			value = value + 60;
		}
		return ganzhi[value];
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
	/**
	 * ��ȡָ���յļ�ʱ<br>
	 * ���Ȼ�ȡָ���յĸ�֧,Ȼ���ٻ�ȡ��֧�еĵ�֧��Ӧ�ļ�ʱ
	 * @param calendar
	 * @return
	 */
	public static String getLuckyTimeOfDay(Calendar calendar){
		String dayGanzhi = getDayGanzhi(calendar);
		String diZhi = dayGanzhi.substring(1);
		String jiShi = "��ʱδ֪";
		for(int i = 0;i<luckyTime.length;i++){
			if(diZhi.equals(luckyTime[i][0])){
				jiShi = luckyTime[i][1];
				break;
			}
		}
		return jiShi;
	}
	/**
	 * ��ȡ������Ӧ��ũ���·�
	 * �ҵ�����������н�����Ӧ������,��Ϊ�ڵ���,��β������һ��,�������<br>
	 * �ҵ�С�ڵ�������ӽ������յĽڵ���,Ȼ��ȷ�ϸýڵ���(����)������·�<br>
	 * @param calendar
	 * @return ���£����µ�
	 * @author carl918@163.com
	 */
	public static String getJieQiMonth(Calendar calendar){
		String month = "";//����������·�
		int year = calendar.get(Calendar.YEAR);
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String keys = propertyUtil.properties.getProperty(year + "jieQiKey","");
		String values = propertyUtil.properties.getProperty(year + "jieQiValue","");
		String[] nodes = keys.split(",");
		String[] nodeValues = values.split(",");
		String date = getStyleDate(calendar, "yyyyMMdd");
		int index = 0;
		//�Ӵ����С�ķ�����,��һ��С�ڵ��ڸ����յļ�Ϊ��Ҫ��
		for(int i = nodes.length-1;i>=0;i--){
			if(nodes[i].compareTo(date) <= 0){
				index = i;//�ڵ���ȷ��
				break;
			}
		}
		//����
		String value = nodeValues[index];
		//����������·�
		for(int i = 0;i<jieQi.length;i++){
			if(jieQi[i][1].contains(value)){
				month = jieQi[i][0];
				break;
			}
		}
		return month;
	}
	
	/**
	 * ��ȡָ���յ����µ����<br>
	 * �����ҵ�������Ӧ�Ľ����·�
	 * Ȼ����֤�����յĸ�֧�Ƿ�����·ݵ����µ�
	 * @param calendar
	 * @return
	 */
	public static String getTianYueDeofDay(Calendar calendar){
		String month = getJieQiMonth(calendar);//����������·�
		String tyd = "";//�����·����µ�
		//�����·ݵ����µ£��ĸ����ֱַ�������,��º�,�µ�,�µº�
		for(int i = 0;i<tianYueDe.length;i++){
			if(tianYueDe[i][0].equals(month)){
				tyd = tianYueDe[i][1];
				break;
			}
		}
		//�����յĸ�֧
		String dayGanzhi = getDayGanzhi(calendar);
		String tyds = "";//���µ��ۺ����
		for(int i = 0; i<2; i++){
			String subStr = dayGanzhi.substring(i, i+1);//�ֱ�����ɣ���֧���ж����µ����
			int fromIndex = 0;
			while(true){
				//���г��ֵ�λ�ö�Ҫ����
				int indexOf = tyd.indexOf(subStr, fromIndex);
				if(indexOf < 0){
					break;//���û�У��˳�while
				}
				switch (indexOf) {
				case 0:
					tyds = tyds + "���";
					break;
				case 1:
					tyds = tyds + "��º�";
					break;
				case 2:
					tyds = tyds + "�µ�";
					break;
				case 3:
					tyds = tyds + "�µº�";
					break;
				default:
					break;
				}
				fromIndex = indexOf + 1;//�ӳ��ֵ�λ����������
			}
		}
		return tyds;
	}
	
	/**
	 * ������ɷ<br>
	 * �������ջ�ȡ��Ӧ��ũ���꣬Ȼ������ũ�����еĽ�����ɷ��
	 * @param calendar
	 * @return �����򷵻ء�������ɷ�����������򷵻ؿ��ַ���������never null
	 * @author carl918@163.com
	 */
	public static String getJinShenQiSha(Calendar calendar){
		String jsqs = "";
		String dayGanzhi = getDayGanzhi(calendar);
		String dayDiZhi = dayGanzhi.substring(1);//�յ�֧
		String yearGanzhi = getYearGanzhi(calendar);
		String yearTianGan = yearGanzhi.substring(0, 1);//�����
		//����ɶ�Ӧ���յ�֧��Ϊ������ɷ
		for(int i = 0;i<jinShenQiSha.length;i++){
			if(jinShenQiSha[i][0].equals(yearTianGan)){
				jsqs = jinShenQiSha[i][1].contains(dayDiZhi) ? "������ɷ" :"";
				break;
			}
		}
		return jsqs;
	}
	
	/**
	 * ������������ǣ�����ɷ����ֱ�ǣ���ר��Ϊ����<br>
	 * ����ȷ�������յ�ũ���·ݣ�Ȼ����ȷ�������յ��ո�֧������ж��Ƿ���������ļ���
	 * @param calendar
	 * @return �����򷵻ض�Ӧ�ļ��ǣ��������򷵻ؿ��ַ���������never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getJinFuJing(Calendar calendar) throws ParseException{
		String jfj = "";
		String lunarCalendar = getLunarCalendar(calendar);
		String lunarMonth = lunarCalendar.substring(0, 2);//ũ���·�
		String dayGanzhi = getDayGanzhi(calendar);
		//i���������·ݣ�j������������
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
	 * ��ȡ�����
	 * @param calendar
	 * @return ������򷵻ء���ɡ������򷵻ؿ��ַ���������never null
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
				ygj = yangGongJi[i][1].contains(lunarDay) ? "���" : "";
				break;
			}
		}
		return ygj;
	}
	/**
	 * 
	 * ��ȡ�¼���
	 * @param calendar
	 * @return ������򷵻ء��¼ɡ������򷵻ؿ��ַ���������never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getYueJi(Calendar calendar) throws ParseException{
		String yj = "";
		String lunarCalendar = getLunarCalendar(calendar);
		String lunarDay = lunarCalendar.substring(2);
		for (int i = 0; i < yueJi.length; i++) {
			if (yueJi[i].equals(lunarDay)) {
				yj = "�¼�";
				break;
			}
		}
		return yj;
	}
	
	/**
	 * 
	 * ��ȡ������ȸ�գ�����ÿ�µļ���
	 * @param calendar
	 * @return ������򷵻ء�������ȸ�������򷵻ؿ��ַ���������never null
	 * @author carl918@163.com
	 * @throws ParseException 
	 */
	public static String getHongZuiZhuQue(Calendar calendar) throws ParseException{
		String hzzq = "";
		String lunarCalendar = getLunarCalendar(calendar);
		String lunarDay = lunarCalendar.substring(2);
		for (int i = 0; i < hongZuiZhuQue.length; i++) {
			if (hongZuiZhuQue[i].equals(lunarDay)) {
				hzzq = "������ȸ";
				break;
			}
		}
		return hzzq;
	}
	
	/**
	 * ��ȡָ�������յĶ�ʮ�����޼�����ʱ��<br>
	 * ��20160121��Ϊԭ����,����������"��ľ��",�����ľ���վ���<br>
	 * ��(ָ������-ԭ����)%28��ֵ,��Ϊ�����������������<br>
	 * �������޵ĵڶ�����ȷ������ʱ��
	 * @param calendar
	 * @return �ո�֧,�����,�ҳ�
	 */
	public static String getXingXiu(Calendar calendar){
		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 0, 21);//ԭ����,��ľ��
		//��ԭ���ն༸��
		Long num = (calendar.getTimeInMillis()-instance.getTimeInMillis())/(24*60*60*1000);
		int value = num.intValue() % 28;
		//�����������
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
	 * �Ƿ�Ϊ��ʮ�Ľ����еĽ�
	 * @param calendar
	 * @return �Ƿ���true���񷵻�false
	 * @author carl918@163.com
	 */
	public static boolean isJie(Calendar calendar){
		//�ж������Ƿ�Ϊ�ڣ�ע�ⲻ������
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
		//���켴���ǽ�Ҳ������
		if (nodeIndex == -1) {
			return jq;
		}
		for (int i = 0; i < jieQi.length; i++) {
			//0�ǽڣ�1����
			if(jieQi[i][1].indexOf(nodeValues[nodeIndex]) == 0){
				jq = true;
				break;
			}
		}
		return jq;
	}
	
	
	/**
	 * ��ȡ��ǰ������ʮ����<br>
	 * ���Ȼ�ȡǰһ���ʮ��������������ǽڣ��ظ�ǰһ��ģ�������ǽڣ�����һ����<br>
	 * �ݹ����,���ԭ����20160101���������кڡ� ,����ֻ�����ԭ���մ�����
	 * @param calendar
	 * @return 
	 * @author carl918@163.com
	 */
	public static String getShiErJian(Calendar calendar){
		//�ж������Ƿ�Ϊ�ڣ�ע�ⲻ������
		boolean jie = isJie(calendar);
		Calendar instance = Calendar.getInstance();
		instance.clear();
		instance.set(2016, 0, 1);
		if(calendar.compareTo(instance) < 0){
			return "δ֪";
		}else if(calendar.compareTo(instance) == 0){
			return "���к�";
		} else {
			calendar.add(Calendar.DATE, -1);
			getShiErJian(calendar);
		}
		return null;
	}
}
