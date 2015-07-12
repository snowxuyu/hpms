package com.hpms.kjjx.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hpms.kjjx.entity.HPKJ_KJJXRY;
import com.hpms.service.BaseSer;

public interface KjjxrySer extends BaseSer<HPKJ_KJJXRY> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public HSSFWorkbook export(String title,String cols,List<Object> data) throws Exception;
	public List<Object> exportList(Map<String, Object> map);
}
