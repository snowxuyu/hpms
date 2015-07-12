package com.hpms.yljx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_GWXSDZ;
import com.hpms.yljx.service.GwxspzSer;

@Service
public class GwxspzSerImpl extends BaseSerImpl<HPCS_GWXSDZ> implements GwxspzSer {

	@Override
	public Map<String, Object> findByPage(int page, int rows, String ksnm) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ksnm", ksnm);
		return findByPage("hpcs_gwxsdz.query_ksnm", page, rows, map);
	}

}
