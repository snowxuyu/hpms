package com.hpms.yljx.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_GWXSDZ;

public interface GwxspzSer extends BaseSer<HPCS_GWXSDZ> {

	public Map<String, Object> findByPage(int page, int rows, String ksid);

	
}
