package com.hpms.jjfp.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.jjfp.service.JjfpSer;
import com.hpms.qmys.vo.GRJJVO;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.PageUtil;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.SqlUtil;

@Service
public class JjfpSerImpl extends BaseSerImpl<GRJJVO> implements JjfpSer {

	@Override
	public Map<String, Object> findByGr(int page, int rows, Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_jj.query_ky",map);
		return PageUtil.getPageDataJson(page, rows, sql, GRJJVO.class);
	}

	
	
}
