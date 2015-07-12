package com.hpms.yljx.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_PBBDR;

public interface PbbdrSer extends BaseSer<HPCS_PBBDR> {

	public Map<String, Object> findByKSMCWithYGMC(int page, int rows, String ksmc, String ygmc);
	public Map<String, Object> findByKSMCWithYGMC2(int page, int rows, String ksmc, String ygmc);
	public void removeByNY(String nd, String yd, String ksnm);
	
}
