package com.hpms.xtwh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.xtwh.entity.HPXT_ZDDMZC;
import com.hpms.xtwh.service.ZddmzcSer;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class ZddmzcSerImpl extends BaseSerImpl<HPXT_ZDDMZC> implements ZddmzcSer {

	@Override
	public List<HPXT_ZDDMZC> findByFlbm(String flbm) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("flbm", flbm);
		SqlResult sql = SqlUtil.getSql("hpxt_zddmzc.query_flbm", map);
		return DBUtil.find(sql.getSql(), new Object[]{flbm}, HPXT_ZDDMZC.class);
	}

}
