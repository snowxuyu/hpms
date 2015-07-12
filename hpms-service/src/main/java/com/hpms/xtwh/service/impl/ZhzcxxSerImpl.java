package com.hpms.xtwh.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hpms.xtwh.entity.HPXT_ZHZCXX;
import com.hpms.xtwh.service.ZhzcxxSer;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class ZhzcxxSerImpl implements ZhzcxxSer {

	/**
	 * 根据账户和密码查询相关账户信息
	 * @param zh
	 * @param mm
	 * @return
	 */
	@Override
	public List<HPXT_ZHZCXX> findByZH(String zh, String mm) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (zh != null && !"".equals(zh)) {
			map.put("zh", zh);
		}
		if (mm != null && !"".equals(mm)) {
			map.put("mm", mm);
		}
		SqlResult sql = SqlUtil.getSql("hpxt_zhzcxx.query_zh",map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPXT_ZHZCXX.class);
	}

	/**
	 * 根据账户和密码判断该账户是否存在
	 * @param zh
	 * @param mm
	 * @return
	 */
	@Override
	public boolean exist(String zh, String mm) {
		if (findByZH(zh, mm).size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据账号找员工信息
	 */
	@Override
	public HPXT_YGZCXX findByZHNM(String zhnm) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (zhnm != null && !"".equals(zhnm)) {
			map.put("zhnm", zhnm);
		}
		SqlResult sql = SqlUtil.getSql("hpxt_zhzcxx.query_ygxx",map);
		List<HPXT_YGZCXX> list = DBUtil.find(sql.getSql(), sql.getParam(), HPXT_YGZCXX.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
}
