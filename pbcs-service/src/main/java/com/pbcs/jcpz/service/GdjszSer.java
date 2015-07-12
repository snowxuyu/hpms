package com.pbcs.jcpz.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.pbcs.jcpz.entity.PBCS_GDJQSZ;

public interface GdjszSer extends BaseSer<PBCS_GDJQSZ> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
}
