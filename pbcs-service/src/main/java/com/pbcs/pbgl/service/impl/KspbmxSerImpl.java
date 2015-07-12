package com.pbcs.pbgl.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.pbcs.pbgl.entity.PBCS_KSPBMX;
import com.pbcs.pbgl.service.KspbmxSer;

@Service
public class KspbmxSerImpl extends BaseSerImpl<PBCS_KSPBMX> implements KspbmxSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("PBCS_KSPBMX.query", page, rows, map);
	}

}
