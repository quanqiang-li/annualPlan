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
		int yearNum = 2016;// 设置年度年度
		Calendar instance = Calendar.getInstance();
		instance.clear();//必须清空,不然会受到时分秒带来的影响
		instance.set(yearNum, 0, 1);// 月份是从0开始计数
		InputStream inp = new FileInputStream("年度计划表模版.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);//红色背景
		cellStyle.setFillBackgroundColor(HSSFColor.RED.index);//红色背景
		int year = yearNum;
		while (year == yearNum) {
			int month = instance.get(Calendar.MONTH) + 1;// 因为计算机的月份是从0开始计数的,+1就符合了人类的习惯
			int date = instance.get(Calendar.DATE);
			// 经过上面的处理,月份和日期都是从1开始计数的,而Excel的行列都是从0开始计数的
			// 因为模版把第0行和第0列给占用了,所以结果就是:行坐标对应日期,列坐标对应月份
			HSSFRow row = sheet.getRow(date);
			HSSFCell cell = row.createCell(month);
			//HSSFCell cell = sheet.createRow(date).createCell(month);//这种方式下一个月份的行会把整个行覆盖掉
			sheet.setColumnWidth(month, 4096);//256表示一个字节的长度,一个汉字两个字节，此处为16个字节
			cell.setCellValue(getCellValue(instance));
			//周末背景标红
			String week = CalendarUtil.getWeek(instance);
			if(week.equals("周六") || week.equals("周日")){
				cell.setCellStyle(cellStyle);
			}
			instance.add(Calendar.DATE, 1);
			year = instance.get(Calendar.YEAR);
		}
		File file = new File(yearNum + "年度计划表.xls");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		wb.write(out);
		out.close();
		wb.close();
	}


	/**
	 * 根据日历获取需要写入模版的单元格内容
	 * @param instance 日历实例
	 * @return
	 * @throws ParseException 
	 */
	private static String getCellValue(Calendar instance) throws ParseException{
		/*农历+阳历节日+农历节日*/
		String lunarCalendar = CalendarUtil.getLunarCalendar(instance);//农历
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String solar = propertyUtil.properties.getProperty(CalendarUtil.getStyleDate(instance, "MMdd"),"");//阳历节日
		String lunar = propertyUtil.properties.getProperty(lunarCalendar,"");//农历节日
		String dayGanzhi = CalendarUtil.getDayGanzhi(instance);
		String luckyTimeOfDay = CalendarUtil.getLuckyTimeOfDay(instance);
		//显示的农历只要后两位即可,如果是初一则显示农历月份
		lunarCalendar = lunarCalendar.substring(2).equals("初一") ? lunarCalendar.substring(0,2) : lunarCalendar.substring(2);
		String value = lunarCalendar + solar + lunar + dayGanzhi + luckyTimeOfDay;
		System.out.println(value);
		return value;
	}
}
