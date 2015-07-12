package com.pbcs.jcpz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.pbcs.jcpz.entity.PBCS_KSBZSZ;
import com.pbcs.jcpz.service.RybzszSer;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("rybzszSer")
public class RybzszSerImpl extends BaseSerImpl<PBCS_KSBZSZ> implements
		RybzszSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("pbcs_ksbzsz.find", page, rows, map);
	}

	@Override
	public List<PBCS_KSBZSZ> listByNy(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("pbcs_ksbzsz.listByNy", map);
		return DBUtil.find(sql.getSql(),sql.getParam(),PBCS_KSBZSZ.class);
	}
	
}
