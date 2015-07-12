package com.hpms.kjjx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.kjjx.entity.HPKJ_YGXMMX;
import com.hpms.kjjx.service.KjygxmmxSer;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KjygxmmxSerImpl extends BaseSerImpl<HPKJ_YGXMMX> implements
		KjygxmmxSer {

	@Override
	public List<HPKJ_YGXMMX> findBybh(Map<String, Object> map) {
		// TODO Auto-generated method stub
		SqlResult sql = SqlUtil.getSql("ryxmmx.queryAll", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPKJ_YGXMMX.class);
	}
}
