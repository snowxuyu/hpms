package com.hpms.yljx.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_HLDEPZ;

public interface HldepzSer extends BaseSer<HPCS_HLDEPZ> {
	public Map<String, Object> findByPage(int page, int rows, String ksnm);
	
}
