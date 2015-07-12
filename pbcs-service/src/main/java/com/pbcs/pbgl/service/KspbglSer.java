package com.pbcs.pbgl.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.pbcs.pbgl.entity.PBCS_KSPBGL;

public interface KspbglSer extends BaseSer<PBCS_KSPBGL> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
}
