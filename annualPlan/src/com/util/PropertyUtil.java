package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 因为占用资源较少,所以这里采用饿汉式单例模式
 * 特点:提前实例化，没有多线程问题，但不管我们是不是调用getInstance()都会存在一个实例在内存中
 * @author Administrator
 *
 */
public class PropertyUtil {

	/**
	 * 预定义的日期配置文件
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
