package com.hpms.yljx.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_BBDZDZ;

public interface BbdzdzSer extends BaseSer<HPCS_BBDZDZ> {
	/**
	 * 分页的查询
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> findByPage(int page, int rows, Map<String, Object> map);
	
}
