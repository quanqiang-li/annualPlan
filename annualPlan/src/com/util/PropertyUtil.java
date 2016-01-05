package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * ��Ϊռ����Դ����,����������ö���ʽ����ģʽ
 * �ص�:��ǰʵ������û�ж��߳����⣬�����������ǲ��ǵ���getInstance()�������һ��ʵ�����ڴ���
 * @author Administrator
 *
 */
public class PropertyUtil {

	/**
	 * Ԥ��������������ļ�
	 */
	public Properties properties;
	
	private static PropertyUtil propertyUtil = new PropertyUtil();
	
	private PropertyUtil(){
		properties = new Properties();
		try {
			properties.load(new FileInputStream("src/predefinedDate.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PropertyUtil getInstance(){
		return propertyUtil;
	}
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/predefinedDate.properties"));
			properties.list(System.out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
