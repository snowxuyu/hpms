package com.hpms.xtwh.service;

import java.util.List;
import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.xtwh.entity.HPXT_CDMKZC;

public interface CdmkzcSer extends BaseSer<HPXT_CDMKZC> {
	public Map<String, Object> findByPage(int page, int rows, String ksnm);
	public List<HPXT_CDMKZC> findAll();
	public Map<String,List<HPXT_CDMKZC>> findToMap();
}
