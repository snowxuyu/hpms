package com.hpms.jjfp.service;

import java.util.List;
import java.util.Map;

import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.vo.RlcbysVO;
import com.hpms.service.BaseSer;

public interface RlcbysSer extends BaseSer<HPYS_RLCBYS> {

	public List<RlcbysVO> findByNd(String nd);
	public List<HPYS_RLCBYS> findByXmbm(String nd, String xmbm);
	public List<HPYS_YSZX> selectNdYs(String nd);
	public HPYS_RLCBYS findOne(Map<String, Object> map);
}
