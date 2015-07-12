package com.pbcs.pbgl.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.pbcs.pbgl.entity.PBCS_KSPBMX;

public interface KspbmxSer extends BaseSer<PBCS_KSPBMX> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
}
