package com.hpms.jjfp.service;

import java.util.List;
import java.util.Map;

import com.hpms.jjfp.entity.HPCS_CLJSSZ;
import com.hpms.service.BaseSer;

public interface CljsszSer extends BaseSer<HPCS_CLJSSZ> {
	
	/**
	 * 分页查询, 不带参数
	 */
	public Map<String, Object> findAll(int page, int rows);
	
	/**
	 * 分页查询，带参数
	 */
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	
	/**
	 * 点击新增后的分页查询
	 */
	public Map<String, Object> findByNY(int page, int rows, Map<String, Object> map);
	
	
	/**
	 * 合计查询
	 * @param nd
	 * @param yd
	 * @return
	 */
	public List<HPCS_CLJSSZ> sumKscl(Integer nd, Integer yd);
	
	/**
	 * 查询List
	 * @param nd
	 * @param yd
	 * @return
	 */
	public List<HPCS_CLJSSZ> findListByNy(Integer nd, Integer yd);
	
	/**
	 * 从常量设置表中查询出科室内码
	 * @return
	 */
	public List<HPCS_CLJSSZ> queryKsnm();
}
