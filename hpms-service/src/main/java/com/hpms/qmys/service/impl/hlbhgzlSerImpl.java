package com.hpms.qmys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.qmys.service.hlbhgzlSer;
import com.hpms.qmys.vo.HLBHGZL;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class hlbhgzlSerImpl extends BaseSerImpl<HLBHGZL> implements hlbhgzlSer {


	@Override
	public Map<String, Object> selectAll(Map<String, Object> map, int page,
			int rows) {
		
		return findByPage("hlbhgzl.queryAll", page, rows, map);
	}

	@Override
	public List<HLBHGZL> selectSum(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("hlbhgzl.querySum",map);
		List<HLBHGZL> list = DBUtil.find(sql.getSql(),sql.getParam(),HLBHGZL.class);
		return list;
	}

}
