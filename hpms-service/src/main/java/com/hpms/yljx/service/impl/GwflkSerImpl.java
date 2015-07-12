package com.hpms.yljx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_GWFLK;
import com.hpms.yljx.service.GwflkSer;
import com.hpms.yljx.vo.Combobox;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class GwflkSerImpl extends BaseSerImpl<HPCS_GWFLK> implements
		GwflkSer {

	@Override
	public List<Combobox> findGW() {
		SqlResult sql = SqlUtil.getSql("hpcs_gwflk.query_combobox", null);
		return DBUtil.find(sql.getSql(), Combobox.class);
		
	}

}
