package com.hpms.qmys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.qmys.service.hlgzldsSer;
import com.hpms.qmys.vo.HLGZLDS;
import com.hpms.service.impl.BaseSerImpl;

@Service
public class hlgzldsSerImpl extends BaseSerImpl<HLGZLDS> implements hlgzldsSer{

	@Override
	public Map<String, Object> selectAll(Map<String, Object> map, int page,
			int rows) {
		
		return findByPage("hlgzlds.queryAll", page, rows, map);
	}

	
}
