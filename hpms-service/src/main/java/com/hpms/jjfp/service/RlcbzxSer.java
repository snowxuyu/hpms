package com.hpms.jjfp.service;

import java.util.List;
import java.util.Map;

import com.hpms.jjfp.entity.HPYS_RLCBZX;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.vo.RlcbzxVO;
import com.hpms.service.BaseSer;

public interface RlcbzxSer extends BaseSer<HPYS_RLCBZX> {

	public List<RlcbzxVO> findByNd(String nd);
	public List<HPYS_YSZX> selectNdYs(String nd);
	public HPYS_RLCBZX findOne(Map<String, Object> map);
}
