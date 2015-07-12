package com.hpms.qmys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.qmys.service.xddssbSer;
import com.hpms.qmys.vo.XDDSB;
import com.hpms.service.impl.BaseSerImpl;

@Service
public class xddsbSerImpl extends BaseSerImpl<XDDSB> implements xddssbSer {

	@Override
	public Map<String, Object> selectAll(Map<String, Object> map, int page,
			int rows) {
		
		return findByPage("xddsb.queryAll", page, rows, map);
	}

}
