package com.hpms.qmys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.qmys.service.HlcxSer;
import com.hpms.qmys.vo.HLCX;
import com.hpms.service.impl.BaseSerImpl;

@Service
public class HlcxSerImpl extends BaseSerImpl<HLCX> implements HlcxSer {

	@Override
	public Map<String, Object> selectHl(Map<String, Object> map, int page,
			int rows) {
		return findByPage("xddsb.queryHl", page, rows, map);
	}

}
