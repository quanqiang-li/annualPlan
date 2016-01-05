package com.plan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;

import com.util.CalendarUtil;
import com.util.PropertyUtil;

public class MakePlan {
	
	public static String[] weeks = {"日", "一", "二", "三", "四", "五", "六" };

	public static void main(String[] args) throws Exception {
		int yearNum = 2016;// 设置年度年度
		Calendar instance = Calendar.getInstance();
		instance.set(yearNum, 0, 1);// 月份是从0开始计数
		InputStream inp = new FileInputStream("年度计划表模版.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		HSSFSheet sheet = wb.getSheetAt(0);
		
		int year = yearNum;
		while (year == yearNum) {
			int month = instance.get(Calendar.MONTH) + 1;// 因为计算机的月份是从0开始计数的,+1就符合了人类的习惯
			int date = instance.get(Calendar.DATE);
			// 经过上面的处理,月份和日期都是从1开始计数的,而Excel的行列都是从0开始计数的
			// 因为模版把第0行和第0列给占用了,所以结果就是:行坐标对应日期,列坐标对应月份
			HSSFRow row = sheet.getRow(date);
			HSSFCell cell = row.createCell(month);
			//HSSFCell cell = sheet.createRow(date).createCell(month);//这种方式下一个月份的行会把整个行覆盖掉
			sheet.setColumnWidth(month, 2560);//256表示一个字节的长度
			cell.setCellValue(getCellValue(instance));

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
	 */
	private static String getCellValue(Calendar instance){
		int week = instance.get(Calendar.DAY_OF_WEEK);//周日为1,周六为7
		String weekSty = weeks[week-1];
		String mmdd = CalendarUtil.getMMDD(instance);
		System.out.println(mmdd);
		PropertyUtil propertyUtil = PropertyUtil.getInstance();
		String solar = propertyUtil.properties.getProperty(mmdd,"");
		String value = solar.equals("") ? weekSty : weekSty + "|" + solar;
		System.out.println(value);
		return value;
	}
}
