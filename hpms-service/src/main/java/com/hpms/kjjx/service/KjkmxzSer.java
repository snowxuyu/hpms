package com.hpms.kjjx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hpms.kjjx.entity.HPKJ_KSMX;
import com.hpms.service.BaseSer;

public interface KjkmxzSer extends BaseSer<HPKJ_KSMX> {

	public Map<String,Object> selectAll(int page, int rows,HashMap<String,Object> map);
	
	public HSSFWorkbook export(String title,String cols,List<Object> data) throws Exception;
	public List<Object> exportList(Map<String, Object> map);
}
