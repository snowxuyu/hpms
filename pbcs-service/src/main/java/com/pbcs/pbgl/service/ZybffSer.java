package com.pbcs.pbgl.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hpms.service.BaseSer;
import com.pbcs.pbgl.vo.PBCS_ZYBFFVO;

public interface ZybffSer extends BaseSer<PBCS_ZYBFFVO> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public List<PBCS_ZYBFFVO> selectSum(Map<String, Object> map);
	public HSSFWorkbook export(String title,String cols,List<Object> data) throws Exception;
	public List<Object> exportList(Map<String, Object> map);
}
