package com.hpms.jjfp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.jjfp.entity.HPCS_CLJSSZ;
import com.hpms.jjfp.service.CljsszSer;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("cljsszSer")
public class CljsszSerImpl extends BaseSerImpl<HPCS_CLJSSZ> implements CljsszSer {
	
	@Override
	public Map<String, Object> find(int page, int rows ,Map<String, Object> map){
		return this.findByPage("hpcs_cljssz.findByPage", page, rows, map);
	}

	@Override
	public Map<String, Object> findByNY(int page, int rows,
			Map<String, Object> map) {
		return this.findByPage("hpcs_cljssz.findByNY", page, rows, map);
	}

	@Override
	public Map<String, Object> findAll(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		return this.findByPage("hpcs_cljssz.findAll", page, rows, map);
	}

	@Override
	public List<HPCS_CLJSSZ> sumKscl(Integer nd, Integer yd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("yd", yd);
		SqlResult sql = SqlUtil.getSql("hpcs_cljssz.sum_kscl", map);
		return DBUtil.find(sql.getSql(),sql.getParam(),HPCS_CLJSSZ.class);
	}

	@Override
	public List<HPCS_CLJSSZ> findListByNy(Integer nd, Integer yd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("yd", yd);
		SqlResult sql = SqlUtil.getSql("hpcs_cljssz.findByNY", map);
		return DBUtil.find(sql.getSql(),sql.getParam(),HPCS_CLJSSZ.class);
	}

	@Override
	public List<HPCS_CLJSSZ> queryKsnm() {
		SqlResult sql = SqlUtil.getSql("hpcs_cljssz.queryksnm", null);
		return DBUtil.find(sql.getSql(),sql.getParam(),HPCS_CLJSSZ.class);
	}

	
}
