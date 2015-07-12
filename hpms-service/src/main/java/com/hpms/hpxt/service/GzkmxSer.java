package com.hpms.hpxt.service;

import java.util.List;
import java.util.Map;

import com.hpms.hpxt.entity.HPXT_GZLMX;
import com.hpms.service.BaseSer;

public interface GzkmxSer extends BaseSer<HPXT_GZLMX> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public List<HPXT_GZLMX> selectSum(Map<String, Object> map);
}
