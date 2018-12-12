package com.an.acquisition.util;

import java.util.UUID;

public class StringUtil {

	/**
	 * 返回首字母小写
	 * @param str
	 * @return 
	 */
	public static String getFirstLow(String str){
		return str.substring(0, 1).toLowerCase()+str.substring(1);
	}
	/**
	 * ""," ",null  返回true
	 * @param str
	 * @return 
	 */
	public static boolean isEmpty(String str){
		return str==null||str.trim().equals("");
	}
	public static boolean notEmpty(String str){
		return !isEmpty(str);
	}
	
	public static String random(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	

	
	
	
	public static int getCharAscii(char aChar) {
		
		byte[] bytes=null;  
		try {
			bytes = (String.valueOf(aChar)).getBytes("GBK");

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
			return 0;
		}
		
		if (bytes.length == 1) {
			return bytes[0];
		}
		else if (bytes.length == 2) {
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			int ascii = (256 * hightByte + lowByte);
			return ascii;
		}else{
			return 0;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(StringUtil.random());
	}
}
