package com.an.acquisition.manage.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.an.acquisition.manage.core.CommonDao;
import com.an.acquisition.manage.dao.DigitalDao;
import com.an.acquisition.model.Digital;


@Repository("digitalDao")
@Scope("prototype")
public class DigitalDaoImpl extends CommonDao implements DigitalDao {

	
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public Digital insert(Digital digital) {
		String sql="insert into digital(`inntakepressure`,`intaketemperature`) values (:inntakepressure,:intaketemperature)";
		SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(digital);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.namedParameterJdbcTemplate.update(sql, parameterSource,keyHolder);
		digital.setId(keyHolder.getKey().longValue());
		return digital;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public int update(Digital digital) {

		String sql="update digital set inntakepressure=:inntakepressure,intaketemperature=:intaketemperature where id=:id";
		SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(digital);
		int ret=this.namedParameterJdbcTemplate.update(sql, parameterSource);
		return ret;

	}

	@Override
	public int deleteById(long id) {
		 String sql = "delete from digital where id=?";
	        int ret=this.jdbcTemplate.update(sql, id);

	        logger.info("删除办事处信息:"+id);
	        return ret;
	}

	@Override
	public int selectCountNum() {
		String sql = "select count(id) from digital  where 1=1";
		
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
	}

	@Override
	public List<Map<String, Object>> selectFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectRecordAllInOne() {
		String sql="select * from digital";
		Object[] args = new Object[] {};
		List<Map<String, Object>> list=this.jdbcTemplate.queryForList(sql, args);
		return list;
	}
	
	

	

}
