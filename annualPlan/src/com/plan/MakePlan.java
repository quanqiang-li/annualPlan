package com.plan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.util.CalendarUtil;
import com.util.PropertyUtil;

public class MakePlan {
	

	public static void main(String[] args) throws Exception {
		int yearNum = 2016;// ����������
		Calendar instance = Calendar.getInstance();
		instance.clear();//�������,��Ȼ���ܵ�ʱ���������Ӱ��
		instance.set(yearNum, 0, 1);// �·��Ǵ�0��ʼ����
		InputStream inp = new FileInputStream("��ȼƻ���ģ��.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);//��ɫ����
		cellStyle.setFillBackgroundColor(HSSFColor.RED.index);//��ɫ����
		int year = yearNum;
		while (year == yearNum) {
			int month = instance.get(Calendar.MONTH) + 1;// ��Ϊ��������·��Ǵ�0��ʼ������,+1�ͷ����������ϰ��
			int date = instance.get(Calendar.DATE);
			// ��������Ĵ���,�·ݺ����ڶ��Ǵ�1��ʼ������,��Excel�����ж��Ǵ�0��ʼ������
			// ��Ϊģ��ѵ�0�к͵�0�и�ռ����,���Խ������:�������Ӧ����,�������Ӧ�·�
			HSSFRow row = sheet.getRow(date);
			HSSFCell cell = row.createCell(month);
			//HSSFCell cell = sheet.createRow(date).createCell(month);//���ַ�ʽ��һ���·ݵ��л�������и��ǵ�
			sheet.setColumnWidth(month, 4096);//256��ʾһ���ֽڵĳ���,һ�����������ֽڣ��˴�Ϊ16���ֽ�
			cell.setCellValue(getCellValue(instance));
			//��ĩ�������
			String week = CalendarUtil.getWeek(instance);
			if(week.equals("����") || week.equals("����")){
				cell.setCellStyle(cellStyle);
			}
			instance.add(Calendar.DATE, 1);
			year = instance.get(Calendar.YEAR);
		}
		File file = new File(yearNum + "��ȼƻ���.xls");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		wb.write(out);
		out.close();
		wb.close();
	}


	/**
	 * ����������ȡ��Ҫд��ģ��ĵ�Ԫ������
	 * @param instance ����ʵ��
	 * @return
	 * @throws ParseException 
	 */
	private static String getCellValue(Calendar instance) throws ParseException{
		/*ũ��+��������+ũ������*/
		String lunarCalendar = CalendarUtil.getLunarCalendar(instance);//ũ��
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String solar = propertyUtil.properties.getProperty(CalendarUtil.getStyleDate(instance, "MMdd"),"");//��������
		String lunar = propertyUtil.properties.getProperty(lunarCalendar,"");//ũ������
		String dayGanzhi = CalendarUtil.getDayGanzhi(instance);
		String luckyTimeOfDay = CalendarUtil.getLuckyTimeOfDay(instance);
		//��ʾ��ũ��ֻҪ����λ����,����ǳ�һ����ʾũ���·�
		lunarCalendar = lunarCalendar.substring(2).equals("��һ") ? lunarCalendar.substring(0,2) : lunarCalendar.substring(2);
		String value = lunarCalendar + solar + lunar + dayGanzhi + luckyTimeOfDay;
		System.out.println(value);
		return value;
	}
}
