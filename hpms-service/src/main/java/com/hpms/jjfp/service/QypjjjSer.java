package com.hpms.jjfp.service;

import java.util.Map;

import com.hpms.jjfp.entity.HPCS_QYPJJJ;
import com.hpms.service.BaseSer;

public interface QypjjjSer extends BaseSer<HPCS_QYPJJJ> {
	public Map<String, Object> findByPage(int page, int rows, Map<String, Object> map);
	
}
