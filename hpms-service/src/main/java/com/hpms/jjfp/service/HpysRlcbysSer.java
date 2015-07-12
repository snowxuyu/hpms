package com.hpms.jjfp.service;

import java.util.List;
import java.util.Map;

import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.jjfp.entity.HPYS_RLCBDY;
import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_RLCBZX;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.entity.HPYS_ZXL;

public interface HpysRlcbysSer {
	/**
	 * 
	 * @author 许浩
	 * 类名：人力成本预算设置
	 * 
	 */
	
	//查询年度
	public List<BPE_JX001> selectNd(Map<String,Object> map);
	
	//查询项目
	public List<HPYS_RLCBDY> selectXm(Map<String,Object> map);
	
	public HPYS_RLCBDY findByXMBM(String xmbm);
	
	//新增项目
	public String insertRlcbys(List<HPYS_RLCBYS> rlcbysList);
	//删除项目
	public String deleteRlcbys(List<HPYS_RLCBYS> rlcbysList);
	
	/**
	 * 
	 * @param year 年度
	 * @return
	 */
	<T>List<T> selectRlcbdyRlcbys(String year);
	
	public Map<String,Object> selectByXmbm(String year,String xmbm);
	
	public String insertRlcbzx(List<HPYS_RLCBZX> rlcbzxList);
	
	public String updateRlcbzx(List<HPYS_RLCBZX> rlcbzxList);
	
	public void updateRlcbys(List<HPYS_RLCBYS> rlcbysList);
	
	
	public HPYS_ZXL selectSumZXL(String year);
	 
	public List<HPYS_YSZX> selectNdSum(String nd);
	
	public List<HPYS_YSZX> selectNdYs(String nd);
	
	public List<HPYS_RLCBZX> selectByysbm(String ysbm);

	public List<HPYS_RLCBYS> selectByFJbm(String fjbm); 
	
	public List<HPYS_RLCBYS> selectNotFJ(String nd1, String nd2);
}
