package com.hpms.qmys.service;

import java.util.List;
import java.util.Map;

import com.hpms.qmys.entity.HPXC_XCKSJS;
import com.hpms.qmys.vo.TempVO;
import com.hpms.service.BaseSer;

public interface QueryKsjjqkSer extends BaseSer<HPXC_XCKSJS> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public Map<String, Object> findAll(int page, int rows, Map<String, Object> map);
	public List<HPXC_XCKSJS> findCharts(Map<String, Object> map);
	public TempVO findAll(Map<String, Object> map);
}
