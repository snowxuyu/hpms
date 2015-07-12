package com.hpms.yljx.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_BBDZDZ;
import com.hpms.yljx.service.BbdzdzSer;

@Service("bbdzdzSer")
public class BbdzdzSerImpl extends BaseSerImpl<HPCS_BBDZDZ> implements BbdzdzSer {
	
	@Override
	public Map<String, Object> findByPage(int page, int rows, Map<String, Object> map) {
		
		return this.findByPage("hpcs_bbdzdz.query_bbdzdz_ByKsnm", page, rows, map);
	}
	
}
