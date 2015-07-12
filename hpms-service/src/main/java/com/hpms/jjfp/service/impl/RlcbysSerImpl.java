package com.hpms.jjfp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.service.RlcbysSer;
import com.hpms.jjfp.vo.RlcbysVO;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.UtilStr;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class RlcbysSerImpl extends BaseSerImpl<HPYS_RLCBYS> implements RlcbysSer {

	@Override
	public List<RlcbysVO> findByNd(String nd) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.query_rlcbys_nd", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), RlcbysVO.class);
	}

	@Override
	public List<HPYS_RLCBYS> findByXmbm(String nd, String xmbm) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("xmbm", xmbm);
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.query_nd_xmbm", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPYS_RLCBYS.class);
	}

	@Override
	public List<HPYS_YSZX> selectNdYs(String nd) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (UtilStr.isNotNull(nd)){
			map.put("nd", nd);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.queryNdYs", map);
		List<HPYS_YSZX> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_YSZX.class);
		return list;
	}

	@Override
	public HPYS_RLCBYS findOne(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.query_one", map);
		List<Object> list = DBUtil.find(sql.getSql(), sql.getParam(), HPYS_RLCBYS.class);
		if (list == null) {
			return null;
		}
		return (HPYS_RLCBYS) list.get(0);
	}
	
}
