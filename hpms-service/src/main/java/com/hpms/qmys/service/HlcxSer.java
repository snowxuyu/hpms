package com.hpms.qmys.service;

import java.util.Map;

import com.hpms.qmys.vo.HLCX;
import com.hpms.service.BaseSer;

public interface HlcxSer extends BaseSer<HLCX>{

	public Map<String,Object> selectHl(Map<String,Object> map,int page,int rows);
}
