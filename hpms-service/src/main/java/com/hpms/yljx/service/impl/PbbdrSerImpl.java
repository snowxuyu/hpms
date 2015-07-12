package com.hpms.yljx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.PageUtil;
import com.hpms.yljx.entity.HPCS_PBBDR;
import com.hpms.yljx.service.PbbdrSer;
import com.hpms.yljx.vo.PBBVO;
import com.rongda.common.jdbc.helper.DBHelper;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.SpringContextUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class PbbdrSerImpl extends BaseSerImpl<HPCS_PBBDR> implements PbbdrSer {

	@Override
	public Map<String, Object> findByKSMCWithYGMC(int page, int rows,
			String ksmc, String ygmc) {
		Map<String,Object> map = new HashMap<String, Object>();
		if (ksmc != null && !"".equals(ksmc)) {
			map.put("ksmc", ksmc);
		}
		if (ygmc != null && !"".equals(ygmc)) {
			map.put("ygmc", ygmc);
		}
		SqlResult sql = SqlUtil.getSql("hpcs_pbbdr.query_ksmc_ygmc", map);
		return PageUtil.getPageDataJson(page, rows, sql, PBBVO.class);
	}
	
	@Override
	public Map<String, Object> findByKSMCWithYGMC2(int page, int rows,
			String ksmc, String ygmc) {
		Map<String,Object> map = new HashMap<String, Object>();
		if (ksmc != null && !"".equals(ksmc)) {
			map.put("ksmc", ksmc);
		}
		if (ygmc != null && !"".equals(ygmc)) {
			map.put("ygmc", ygmc);
		}
		SqlResult sql = SqlUtil.getSql("hpcs_pbbdr.query_ksmc_ygmc_jjb", map);
		return PageUtil.getPageDataJson(page, rows, sql, PBBVO.class);
	}

	@Override
	public void removeByNY(String nd, String yd, String ksnm) {
		DBHelper dbHelper = new DBHelper((JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate"));
		String delSql = "delete from HPCS_PBBDR where nd=" + nd + " and yd=" + yd + " and ksnm=" + ksnm;
		dbHelper.getJdbcTemplate().execute(delSql);
		
	}

}
