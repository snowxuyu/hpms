package com.hpms.qmys.service;

import java.util.Map;

import com.hpms.qmys.vo.QYSFJJ;
import com.hpms.service.BaseSer;

public interface qysfjjSer extends BaseSer<QYSFJJ> {

	public Map<String,Object> selectAll(Map<String,Object> map,int page,int rows);
}
