package com.an.acquisition.manage.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.an.acquisition.manage.dao.DigitalDao;
import com.an.acquisition.manage.service.DigitalService;
import com.an.acquisition.model.Digital;
import com.an.acquisition.util.SpringUtil;


@Service("digitalService")
public class DigitalServiceImpl implements DigitalService {

	@Override
	@Transactional
	public Digital insert(Digital digital) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(DigitalDao.class).insert(digital);
	}

	@Override
	public int update(Digital digital) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(DigitalDao.class).update(digital);
	}

	@Override
	public int deleteById(long id) {
		// TODO Auto-generated method stub
		return SpringUtil.getBean(DigitalDao.class).deleteById(id);
	}

	@Override
	public JSONObject selectRecordAllInOne() {
		DigitalDao dao=SpringUtil.getBean(DigitalDao.class);
		List<Map<String,Object>> list=dao.selectRecordAllInOne();
		int total=dao.selectCountNum();
		List<Map<String, Object>> footer=dao.selectFooter();
		JSONObject json=new JSONObject();
		json.put("total", total);
		json.put("rows",list);
		json.put("footer", footer);
		return json;
	}

	
	

}
