package com.pbcs.jcpz.service;

import java.util.List;
import java.util.Map;
import com.hpms.service.BaseSer;
import com.pbcs.jcpz.entity.PBCS_KSBZSZ;

public interface RybzszSer extends BaseSer<PBCS_KSBZSZ> {
	/**
	 * 页面显示查询
	 * @param map
	 * @return
	 */
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	
	public List<PBCS_KSBZSZ> listByNy(Map<String, Object> map);
}
