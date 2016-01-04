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
		int yearNum = 2016;// ���
		Calendar instance = Calendar.getInstance();
		instance.set(yearNum, 0, 1);// �·��Ǵ�0��ʼ����
		InputStream inp = new FileInputStream("��ȼƻ���ģ��.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		HSSFSheet sheet = wb.getSheetAt(0);
		int year = yearNum;
		while (year == yearNum) {
			int month = instance.get(Calendar.MONTH) + 1;// ��Ϊ��������·��Ǵ�0��ʼ������,+1�ͷ����������ϰ��
			int date = instance.get(Calendar.DATE);
			System.out.println(month  + "" + date);
			// ��������Ĵ���,�·ݺ����ڶ��Ǵ�1��ʼ������,��Excel�����ж��Ǵ�0��ʼ������
			// ��Ϊģ��ѵ�0�к͵�0�и�ռ����,���Խ������:�������Ӧ����,�������Ӧ�·�
			HSSFRow row = sheet.getRow(date);
			HSSFCell cell = row.createCell(month);
			//HSSFCell cell = sheet.createRow(date).createCell(month);//���ַ�ʽ��һ���·ݵ��л�������и��ǵ�
			cell.setCellValue(month+"-"+date);

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
	 * ��ȡExcel�ı�����
	 * 
	 * @throws Exception
	 * @author carl918@163.com
	 */
	public static void textExtraction() throws Exception {
		InputStream inp = new FileInputStream("��ȼƻ���.xls");
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
		ExcelExtractor extractor = new ExcelExtractor(wb);

		extractor.setFormulasNotResults(true);
		extractor.setIncludeSheetNames(false);
		String text = extractor.getText();
		System.out.println(text);
		extractor.close();
	}
}
