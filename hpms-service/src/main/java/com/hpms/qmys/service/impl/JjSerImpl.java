package com.hpms.qmys.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.qmys.service.JjSer;
import com.hpms.qmys.vo.GRJJVO;
import com.hpms.util.PageUtil;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.SqlUtil;

@Service
public class JjSerImpl implements JjSer {

	@Override
	public Map<String, Object> findByGr(int page, int rows,
			Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_jj.query_gr",map);
		return PageUtil.getPageDataJson(page, rows, sql, GRJJVO.class);
	}
	
	@Override
	public Map<String, Object> findByGr2(int page, int rows,
			Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_jj.query_gr2",map);
		return PageUtil.getPageDataJson(page, rows, sql, GRJJVO.class);
	}

}
