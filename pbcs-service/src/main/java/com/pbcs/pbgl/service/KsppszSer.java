package com.pbcs.pbgl.service;

import java.util.List;
import java.util.Map;

import com.hpms.qmys.vo.TempVO;
import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.pbcs.pbgl.vo.PBCS_KSPBSZVO;

public interface KsppszSer extends BaseSer<PBCS_KSPBSZVO> {
	public List<TempVO> getTitle(Map<String,Object> map);
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public List<HPCS_BBFLK> queryBB(Map<String,Object> map);
}
