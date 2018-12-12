package com.an.acquisition.manage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.an.acquisition.manage.core.CommonController;
import com.an.acquisition.manage.core.websocket;
import com.an.acquisition.manage.service.DigitalService;
import com.an.acquisition.model.Digital;
import com.an.acquisition.util.SpringUtil;




@Controller
@RequestMapping("/digital")
public class DigitalController extends CommonController {
	
	
	@RequestMapping(value="/saveData")
	@ResponseBody
	public String saveData(Digital digital) {
		JSONObject json=new JSONObject();
		try {
			SpringUtil.getBean(DigitalService.class).insert(digital);
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("errorMsg", e.getMessage());
		}
		return	json.toJSONString();
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public String list()
	{
		JSONObject json = new JSONObject();
		try {
			json=SpringUtil.getBean(DigitalService.class).selectRecordAllInOne();
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("errorMsg", e.getMessage());
		}
		return json.toJSONString();
	}
	@RequestMapping(value = "/modify")
	@ResponseBody
	public String modify(Digital digital)
	{
		JSONObject json = new JSONObject();
		try {
			System.out.println(digital.getInntakepressure()+digital.getIntaketemperature());
			SpringUtil.getBean(DigitalService.class).update(digital);
			websocket.sendMessage();
		} catch (Exception e) {
			e.printStackTrace();
			json.put("errorMsg", e.getMessage());
		}
		return json.toJSONString();
	}
	   @RequestMapping(value="/delete")
	    @ResponseBody
	    public String delete(long id){
	        JSONObject json=new JSONObject();
	        try{
	            SpringUtil.getBean(DigitalService.class).deleteById(id);
	          
	    		
	        }catch(Exception e){
	            e.printStackTrace();
	            json.put("errorMsg", e.getMessage());
	        }
	        return json.toJSONString();
	    }


}
