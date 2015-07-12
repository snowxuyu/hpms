package com.hpms.qmys.service;

import java.util.List;
import java.util.Map;

import com.hpms.qmys.vo.HLBHGZL;
import com.hpms.service.BaseSer;

public interface hlbhgzlSer extends BaseSer<HLBHGZL> {

		public Map<String,Object> selectAll(Map<String,Object> map,int page,int rows);
		public List<HLBHGZL> selectSum(Map<String,Object> map);
}
