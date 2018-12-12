package com.an.acquisition.manage.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.an.acquisition.model.Digital;

;


public interface DigitalDao {
	
	public Digital insert(Digital digital);
	public int update(Digital area);
	public int deleteById(long id);
	public int selectCountNum();
	public List<Map<String,Object>> selectFooter();
	public List<Map<String,Object>> selectRecordAllInOne();

}
