package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.pbcs.pbgl.entity.PBCS_KSPBGL;
import com.pbcs.pbgl.service.PbbszSer;
import com.pbcs.pbgl.vo.PbmxVo;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class PbbszSerImpl implements PbbszSer {

	@Override
	public PBCS_KSPBGL findPB(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("pbbsz.query_pb", map);
		List<PBCS_KSPBGL> list = DBUtil.find(sql.getSql(), sql.getParam(), PBCS_KSPBGL.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<HPXT_YGZCXX> findYG(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("pbbsz.query_yg", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPXT_YGZCXX.class);
	}

	@Override
	public List<PbmxVo> findMX(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("pbbsz.query_mx", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), PbmxVo.class);
	}

	@Override
	public List<HPCS_BBFLK> findBbfl(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("pbbsz.query_bbfl", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPCS_BBFLK.class);
	}
	
}
