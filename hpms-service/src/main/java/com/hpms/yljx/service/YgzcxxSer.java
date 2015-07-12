package com.hpms.yljx.service;

import java.util.List;
import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.hpms.yljx.vo.PbbYGVO;

public interface YgzcxxSer extends BaseSer<HPXT_YGZCXX> {

	public List<HPXT_YGZCXX> findByQ(String q);
	public List<PbbYGVO> findAll();
	
	public Map<String,Object> selectAll(int page,int rows,String ygxm);
	public List<HPXT_YGZCXX> selectByP(String ygxm);

}
