package com.hpms.qmys.service;

import java.util.Map;

import com.hpms.qmys.vo.HLGZLDS;
import com.hpms.service.BaseSer;

public interface hlgzldsSer extends BaseSer<HLGZLDS> {

	public Map<String,Object> selectAll(Map<String,Object> map,int page,int rows);
}
