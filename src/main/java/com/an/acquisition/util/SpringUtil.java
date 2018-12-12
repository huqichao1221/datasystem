package com.an.acquisition.util;

import org.springframework.context.ApplicationContext;

import com.an.acquisition.util.StringUtil;

public abstract class SpringUtil {

	
private static ApplicationContext applicationContext=null;
	
	public static void setApplicationContext(ApplicationContext ctx){
		applicationContext=ctx;
	}
	public static ApplicationContext getApplicationContext(){
		if(applicationContext!=null){
			return applicationContext;
		}else{
			throw new RuntimeException("applicationContext is null");
		}
	}
	
	
	/**
	 * 
	 * @param beanId
	 * @return 
	 */
	public static Object getBean(String beanId){
		return getBean(beanId,Object.class);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param beanId
	 * @param clazz
	 * @return 
	 */
	public static <T> T getBean(String beanId,Class<T> clazz){
		return getApplicationContext().getBean(beanId,clazz);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(StringUtil.getFirstLow(clazz.getSimpleName()),clazz);
	}
}
