package com.hpms.kjjx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.kjjx.entity.HPKJ_KJXMPF;
import com.hpms.kjjx.service.KjpfSer;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KjpfSerImpl extends BaseSerImpl<HPKJ_KJXMPF> implements KjpfSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("kjpf.queryAll", page, rows, map);
	}

	@Override
	public List<HPKJ_KJXMPF> findAll() {
		SqlResult sql = SqlUtil.getSql("kjpf.queryAll");
		return DBUtil.find(sql.getSql(), HPKJ_KJXMPF.class);
	}

}
