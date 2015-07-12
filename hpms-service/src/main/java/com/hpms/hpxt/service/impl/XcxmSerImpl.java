package com.hpms.hpxt.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.hpxt.entity.HPCS_SFYSCC;
import com.hpms.hpxt.service.XcxmSer;
import com.hpms.service.impl.BaseSerImpl;

@Service("xcxmSer")
public class XcxmSerImpl extends BaseSerImpl<HPCS_SFYSCC> implements XcxmSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("HPCS_SFYSCC.queryAll", page, rows, map);
	}

}
