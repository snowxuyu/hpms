package com.hpms.yljx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_HLDEPZ;
import com.hpms.yljx.service.HldepzSer;
@Service("hldepzser")
public class HldepzSerImpl extends BaseSerImpl<HPCS_HLDEPZ> implements HldepzSer {
	@Override
	public Map<String, Object> findByPage(int page, int rows, String ksnm) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ksnm", ksnm);
		return findByPage("hpcs_hldepz.query_hldepz", page, rows, map);
	}
}
