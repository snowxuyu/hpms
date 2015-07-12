package com.hpms.hpxt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.hpxt.entity.HPXT_GZLMX;
import com.hpms.hpxt.service.GzkmxSer;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("gzkmxSer")
public class GzkmxSerImpl extends BaseSerImpl<HPXT_GZLMX> implements GzkmxSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("HPXT_GZLMX.query", page, rows, map);
	}

	@Override
	public List<HPXT_GZLMX> selectSum(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("HPXT_GZLMX.querySum",map);
		List<HPXT_GZLMX> list = DBUtil.find(sql.getSql(),sql.getParam(),HPXT_GZLMX.class);
		return list;
	}

}
