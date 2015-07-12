package com.hpms.jjfp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_RLCBZX;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.service.RlcbzxSer;
import com.hpms.jjfp.vo.RlcbzxVO;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.UtilStr;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class RlcbzxSerImpl extends BaseSerImpl<HPYS_RLCBZX> implements RlcbzxSer {

	@Override
	public List<HPYS_YSZX> selectNdYs(String nd) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (UtilStr.isNotNull(nd)){
			map.put("nd", nd);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBZX.queryNdYs", map);
		List<HPYS_YSZX> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_YSZX.class);
		return list;
	}

	@Override
	public List<RlcbzxVO> findByNd(String nd) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBZX.query_rlcbzx_nd", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), RlcbzxVO.class);
	}
	
	@Override
	public HPYS_RLCBZX findOne(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBZX.query_one", map);
		List<Object> list = DBUtil.find(sql.getSql(), sql.getParam(), HPYS_RLCBZX.class);
		if (list == null) {
			return null;
		}
		return (HPYS_RLCBZX) list.get(0);
	}

}
