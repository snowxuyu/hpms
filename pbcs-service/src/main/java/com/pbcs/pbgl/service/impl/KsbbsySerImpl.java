package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hpms.service.impl.BaseSerImpl;
import com.pbcs.pbgl.entity.PBCS_KSBBSY;
import com.pbcs.pbgl.service.KsbbsySer;
import com.rongda.common.jdbc.helper.DBHelper;
import com.rongda.common.util.SpringContextUtil;

@Service
public class KsbbsySerImpl extends BaseSerImpl<PBCS_KSBBSY> implements
		KsbbsySer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("PBCS_KSBBSY.query", page, rows, map);
	}

	@Transactional
	@Override
	public void deleteByIds(String ids) {
		DBHelper dbHelper = new DBHelper(
				(JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate"));
		dbHelper.getJdbcTemplate().execute(
				"delete from PBCS_KSSYMX where 1=1 and sylsh in(" + ids + ")");
		dbHelper.getJdbcTemplate().execute(
				"delete from PBCS_KSBBMX where 1=1 and sylsh in (" + ids + ")");
		String sql = "delete from PBCS_KSBBSY where sylsh in (" + ids + ")";
		dbHelper.getJdbcTemplate().execute(sql);

	}

	@Transactional
	@Override
	public void saveOrUpdate(String saveJson, String updateJson) {
		Gson gson = new Gson();
		if (!"".equals(saveJson) && null != saveJson) {
			List<PBCS_KSBBSY> list = gson.fromJson(saveJson,
					new TypeToken<List<PBCS_KSBBSY>>() {
					}.getType());

			super.addBatch(list);
		}
		if (!"".equals(updateJson) && null != updateJson) {
			List<PBCS_KSBBSY> list = gson.fromJson(updateJson,
					new TypeToken<List<PBCS_KSBBSY>>() {
					}.getType());

			super.updateBatch(list);
		}

	}

}
