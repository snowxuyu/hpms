package com.hpms.yljx.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_HXXMZD;

public interface HxxmzdSer extends BaseSer<HPCS_HXXMZD> {
	
	public HPCS_HXXMZD findByXMBM(String xmbm);
	public HPCS_HXXMZD findByXMMC(String xmmc);
	public List<HPCS_HXXMZD> findByHXLBBM(String hxlbbm);
	public List<HPCS_HXXMZD> findByXMLBBM(String xmlbbm);
	
	public boolean existXMBM(String xmbm);
	public boolean existXMMC(String xmmc);
	public boolean existHXLBBM(String hxlbbm);
	public boolean existXMLBBM(String xmlbbm);

	public Map<String, Object> findByPage(int page, int rows, String xmlbbm);
	
	/**
	 * 查询核心项目字典表， 根据项目名称进行模糊查询
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> findHxxmzd(int page, int rows, Map<String, Object> map);

	public Map<String, Object> findXm(int page, int rows, Map<String, Object> map);
	public List<HPCS_HXXMZD> findXm(Map<String, Object> map);
	
	public HSSFWorkbook getBook(List<HPCS_HXXMZD> list, String name);
	
	public List<HPCS_HXXMZD> selectXmmc();
}
