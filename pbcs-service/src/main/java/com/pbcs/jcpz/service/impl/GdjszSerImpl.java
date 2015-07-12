package com.pbcs.jcpz.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.pbcs.jcpz.entity.PBCS_GDJQSZ;
import com.pbcs.jcpz.service.GdjszSer;

@Service("GdjszSer")
public class GdjszSerImpl extends BaseSerImpl<PBCS_GDJQSZ> implements GdjszSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("PBCS_GDJQSZ.queryAll", page, rows, map);
	}

}
