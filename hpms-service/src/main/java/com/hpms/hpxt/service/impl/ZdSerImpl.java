package com.hpms.hpxt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.hpxt.entity.HPXT_ZDDMZC;
import com.hpms.hpxt.service.ZdSer;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.vo.Combobox;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("zdSer")
public class ZdSerImpl extends BaseSerImpl<HPXT_ZDDMZC> implements ZdSer {

	@Override
	public List<Combobox> getList(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("HPXT_ZDDMZC.query_combobox", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPXT_ZDDMZC.class);
	}

}
