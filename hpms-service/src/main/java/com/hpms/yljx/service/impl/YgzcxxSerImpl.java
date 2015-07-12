package com.hpms.yljx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.hpms.yljx.service.YgzcxxSer;
import com.hpms.yljx.vo.PbbYGVO;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class YgzcxxSerImpl extends BaseSerImpl<HPXT_YGZCXX> implements YgzcxxSer {

	@Override
	public List<HPXT_YGZCXX> findByQ(String q) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("q", q);
		SqlResult sql = SqlUtil.getSql("hpxt_ygzcxx.query_q", map);
		return DBUtil.find(sql.getSql(), new Object[]{q}, HPXT_YGZCXX.class);
		
	}

	@Override
	public List<PbbYGVO> findAll() {
		SqlResult sql = SqlUtil.getSql("hpxt_ygzcxx.query_all", null);
		return DBUtil.find(sql.getSql(), PbbYGVO.class);
	}

	@Override
	public Map<String, Object> selectAll(int page, int rows, String ygxm) {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("ygxm", ygxm);
	    return findByPage("kjxmmx.queryYgxx",page,rows,hashMap);
	}

	@Override
	public List<HPXT_YGZCXX> selectByP(String ygxm) {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("ygxm", ygxm);
		SqlResult sql = SqlUtil.getSql("kjxmmx.queryYgxx",hashMap);
		List<HPXT_YGZCXX> list = DBUtil.find(sql.getSql(),sql.getParam(),HPXT_YGZCXX.class);
		return list;
	}

}
