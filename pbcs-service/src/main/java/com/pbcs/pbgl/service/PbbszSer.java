package com.pbcs.pbgl.service;

import java.util.List;
import java.util.Map;

import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.pbcs.pbgl.entity.PBCS_KSPBGL;
import com.pbcs.pbgl.vo.PbmxVo;

/**
 * @author 吴陶君
 * 排班表设置
 */
public interface PbbszSer {

	/**
	 * 查找科室排班管理
	 * 参数（年度、月度、科室内码）
	 * @param map
	 * @return
	 */
	public PBCS_KSPBGL findPB(Map<String, Object> map);
	/**
	 * 查找员工
	 * 参数（科室内码）
	 * @param map
	 * @return
	 */
	public List<HPXT_YGZCXX> findYG(Map<String, Object> map);
	/**
	 * 查找科室排班管理
	 * 参数（年度、月度、科室内码）
	 * @param map
	 * @return
	 */
	public List<PbmxVo> findMX(Map<String, Object> map);
	/**
	 * 查找班别分了库
	 * 参数（班别分类）
	 * @param map
	 * @return
	 */
	public List<HPCS_BBFLK> findBbfl(Map<String, Object> map);
	
	
}
