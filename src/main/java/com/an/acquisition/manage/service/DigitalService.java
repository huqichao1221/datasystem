package com.an.acquisition.manage.service;

import com.alibaba.fastjson.JSONObject;
import com.an.acquisition.model.Digital;

public interface DigitalService {
	public Digital insert(Digital digital);
	public int update(Digital digital);
	public int deleteById(long id);
	public JSONObject selectRecordAllInOne();

}
