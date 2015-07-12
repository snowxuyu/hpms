package com.pbcs.pbgl.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.pbcs.pbgl.entity.PBCS_KSBBMX;

public interface KsbbmxSer extends BaseSer<PBCS_KSBBMX> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public void deleteByIds(String ids);
	public void saveOrUpdate(String saveJson, String updateJson);
}
