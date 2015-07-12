package com.hpms.yljx.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_HXXMPZ;
import com.hpms.yljx.entity.HPCS_HXXMZD;
import com.hpms.yljx.vo.Combobox;

public interface HxxmpzSer extends BaseSer<HPCS_HXXMPZ> {
	/**
	 * 多张表的查询
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	/**
	 * 科室核心项目模糊查询
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> mhQueryHxxm(int page, int rows, Map<String, Object> map);
	
	/**
	 * 级联  查询项目类别
	 * @return
	 */
	public List<Combobox> queryXmlb(String fjbm);
	
	/**
	 * 查询所有项目类别
	 * @return
	 */
	public List<Combobox> queryAllXmlb(); 
	
	/**
	 * 查询项目分类
	 * @return
	 */
	public List<Combobox> queryXmfl();
	/**
	 * 查询核心类别
	 * @return
	 */
	public List<Combobox> queryHxlb();
	
	/**
	 * 科室核心项目导入
	 * @param ksnm
	 * @return
	 */
	public String importHxxm(MultipartFile file, String ksnm);
	
	/**
	 * 查询项目标识
	 * @return
	 */
	public List<Combobox> queryXmbz();
	
	public HSSFWorkbook exportHxxm(String ksnm, String xmlbbm);
	
	public List<HPCS_HXXMPZ> findAll();
	public List<HPCS_HXXMZD> findxmbmByZd();
}
