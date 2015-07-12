package com.pbcs.jcpz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.vo.Combobox;
import com.pbcs.jcpz.service.PbzdszSer;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("PbzdszSer")
public class PbzdszSerImpl extends BaseSerImpl<HPCS_BBFLK> implements PbzdszSer{

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("HPCS_BBFLKVO.queryAll", page, rows, map);
	}

	@Override
	public List<Combobox> getList() {
		SqlResult sql = SqlUtil.getSql("HPCS_BBFLKVO.queryList");
		List<Combobox> list = DBUtil.find(sql.getSql(),Combobox.class);
		return list;
	}

	@Override
	public boolean existBBBM(String bbbm) {
		if(findByBbbm(bbbm) == null){
			return false;
		}
		return true;
	}

	@Override
	public HPCS_BBFLK findByBbbm(String bbbm) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bbbm", bbbm);
		SqlResult sql = SqlUtil.getSql("HPCS_BBFLKVO.queryExist",map);
		List<HPCS_BBFLK> list = DBUtil.find(sql.getSql(),sql.getParam(),HPCS_BBFLK.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

}
