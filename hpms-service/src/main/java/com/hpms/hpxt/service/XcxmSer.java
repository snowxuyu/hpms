package com.hpms.hpxt.service;

import java.util.Map;

import com.hpms.hpxt.entity.HPCS_SFYSCC;
import com.hpms.service.BaseSer;

public interface XcxmSer extends BaseSer<HPCS_SFYSCC> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
}
