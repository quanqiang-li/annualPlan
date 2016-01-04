package com.plan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class MakePlan {

	public static void main(String[] args) throws Exception {
		int yearNum = 2016;// 年度
		Calendar instance = Calendar.getInstance();
		instance.set(yearNum, 0, 1);// 月份是从0开始计数
		InputStream inp = new FileInputStream("年度计划表模版.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		HSSFSheet sheet = wb.getSheetAt(0);
		int year = yearNum;
		while (year == yearNum) {
			int month = instance.get(Calendar.MONTH) + 1;// 因为计算机的月份是从0开始计数的,+1就符合了人类的习惯
			int date = instance.get(Calendar.DATE);
			System.out.println(month  + "" + date);
			// 经过上面的处理,月份和日期都是从1开始计数的,而Excel的行列都是从0开始计数的
			// 因为模版把第0行和第0列给占用了,所以结果就是:行坐标对应日期,列坐标对应月份
			HSSFRow row = sheet.getRow(date);
			HSSFCell cell = row.createCell(month);
			//HSSFCell cell = sheet.createRow(date).createCell(month);//这种方式下一个月份的行会把整个行覆盖掉
			cell.setCellValue(month+"-"+date);

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
	 * 提取Excel文本内容
	 * 
	 * @throws Exception
	 * @author carl918@163.com
	 */
	public static void textExtraction() throws Exception {
		InputStream inp = new FileInputStream("年度计划表.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		ExcelExtractor extractor = new ExcelExtractor(wb);

		extractor.setFormulasNotResults(true);
		extractor.setIncludeSheetNames(false);
		String text = extractor.getText();
		System.out.println(text);
		extractor.close();
	}
}
