package com.hpms.xtwh.service;

import java.util.List;

import com.hpms.service.BaseSer;
import com.hpms.xtwh.entity.HPXT_ZDDMZC;

public interface ZddmzcSer extends BaseSer<HPXT_ZDDMZC> {

	public List<HPXT_ZDDMZC> findByFlbm(String flbm);
	
}
