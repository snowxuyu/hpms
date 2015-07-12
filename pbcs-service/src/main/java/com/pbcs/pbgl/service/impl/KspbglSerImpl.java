package com.pbcs.pbgl.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.pbcs.pbgl.entity.PBCS_KSPBGL;
import com.pbcs.pbgl.service.KspbglSer;

@Service
public class KspbglSerImpl extends BaseSerImpl<PBCS_KSPBGL> implements
		KspbglSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("PBCS_KSPBGL.query", page, rows, map);
	}

}
