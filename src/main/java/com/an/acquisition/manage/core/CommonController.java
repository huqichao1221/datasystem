package com.an.acquisition.manage.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


public class CommonController {

	 protected static final Logger logger = LogManager.getLogger();

		@InitBinder
		public void initBinder(ServletRequestDataBinder bin){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			CustomDateEditor cust=new CustomDateEditor(sdf, true);
			bin.registerCustomEditor(Date.class, cust);
		}
		
		@ExceptionHandler
		@ResponseBody
	    public String exp(HttpServletRequest request, Exception ex) {
			ex.printStackTrace();
			JSONObject json = new JSONObject();
			String msg=ex.getMessage();
			json.put("Error", msg);
			System.out.println("@ExceptionHandler:"+json.toJSONString());
	        return json.toJSONString();
	    }


	  



	  
	  

	   
	    

	
}
