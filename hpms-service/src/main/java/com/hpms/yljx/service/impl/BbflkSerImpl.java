package com.hpms.yljx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.service.BbflkSer;
import com.hpms.yljx.vo.Combobox;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("bbflkSer")
public class BbflkSerImpl extends BaseSerImpl<HPCS_BBFLK> implements BbflkSer {

	@Override
	public List<Combobox> listAllBb() {
		SqlResult sql = SqlUtil.getSql("hpcs_bbflk.query_bb", null);
		return DBUtil.find(sql.getSql(), Combobox.class);
	}

	@Override
	public List<HPCS_BBFLK> findAll() {
		SqlResult sql = SqlUtil.getSql("hpcs_bbflk.query_bbflk", null);
		return DBUtil.find(sql.getSql(), HPCS_BBFLK.class);
	}
	

}
