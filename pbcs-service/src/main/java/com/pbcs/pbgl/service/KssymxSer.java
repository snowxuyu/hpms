package com.pbcs.pbgl.service;

import java.util.List;
import java.util.Map;

import com.hpms.qmys.vo.TempVO;
import com.hpms.service.BaseSer;
import com.pbcs.pbgl.entity.PBCS_KSSYMX;


public interface KssymxSer extends BaseSer<PBCS_KSSYMX> {
	public List<TempVO> getKsnms(Map<String, Object> map);
	public void deleteByIds(String ids);
}
