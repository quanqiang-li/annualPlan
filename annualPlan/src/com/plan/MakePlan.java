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
	
	public static String[] weeks = {"��", "һ", "��", "��", "��", "��", "��" };

	public static void main(String[] args) throws Exception {
		int yearNum = 2016;// ����������
		Calendar instance = Calendar.getInstance();
		instance.set(yearNum, 0, 1);// �·��Ǵ�0��ʼ����
		InputStream inp = new FileInputStream("��ȼƻ���ģ��.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		HSSFSheet sheet = wb.getSheetAt(0);
		
		int year = yearNum;
		while (year == yearNum) {
			int month = instance.get(Calendar.MONTH) + 1;// ��Ϊ��������·��Ǵ�0��ʼ������,+1�ͷ����������ϰ��
			int date = instance.get(Calendar.DATE);
			// ��������Ĵ���,�·ݺ����ڶ��Ǵ�1��ʼ������,��Excel�����ж��Ǵ�0��ʼ������
			// ��Ϊģ��ѵ�0�к͵�0�и�ռ����,���Խ������:�������Ӧ����,�������Ӧ�·�
			HSSFRow row = sheet.getRow(date);
			HSSFCell cell = row.createCell(month);
			//HSSFCell cell = sheet.createRow(date).createCell(month);//���ַ�ʽ��һ���·ݵ��л�������и��ǵ�
			sheet.setColumnWidth(month, 2560);//256��ʾһ���ֽڵĳ���
			cell.setCellValue(getCellValue(instance));

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
	 */
	private static String getCellValue(Calendar instance){
		int week = instance.get(Calendar.DAY_OF_WEEK);//����Ϊ1,����Ϊ7
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
