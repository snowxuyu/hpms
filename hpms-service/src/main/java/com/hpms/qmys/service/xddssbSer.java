package com.hpms.qmys.service;

import java.util.Map;

import com.hpms.qmys.vo.XDDSB;
import com.hpms.service.BaseSer;

public interface xddssbSer extends BaseSer<XDDSB>{

	public Map<String,Object> selectAll(Map<String,Object> map,int page,int rows); 
	
}
