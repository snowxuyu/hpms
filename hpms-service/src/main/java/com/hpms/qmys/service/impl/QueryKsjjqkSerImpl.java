package com.hpms.qmys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.qmys.entity.HPXC_XCKSJS;
import com.hpms.qmys.service.QueryKsjjqkSer;
import com.hpms.qmys.vo.TempVO;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;
@Service("queryksjjqkSer")
public class QueryKsjjqkSerImpl extends BaseSerImpl<HPXC_XCKSJS> implements
		QueryKsjjqkSer {


	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("hpxc_xcksjs.find", page, rows, map);
	}
	
	@Override
	public Map<String, Object> findAll(int page, int rows, Map<String, Object> map) {
		return this.findByPage("hpxc_xcksjs.findAll", page, rows, map);
	}
	
	@Override
	public TempVO findAll(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("hpxc_xcksjs.findAll_count", map);
		List<TempVO> list = DBUtil.find(sql.getSql(), sql.getParam(), TempVO.class);
		if (list == null) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<HPXC_XCKSJS> findCharts(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("hpxc_xcksjs.charts",map);
		List<HPXC_XCKSJS> list = DBUtil.find(sql.getSql(),sql.getParam(),HPXC_XCKSJS.class);
		return list;
	}

}
