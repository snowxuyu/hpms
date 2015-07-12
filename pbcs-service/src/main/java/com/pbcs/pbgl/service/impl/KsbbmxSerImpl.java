package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hpms.service.impl.BaseSerImpl;
import com.pbcs.pbgl.entity.PBCS_KSBBMX;
import com.pbcs.pbgl.service.KsbbmxSer;
import com.rongda.common.jdbc.helper.DBHelper;
import com.rongda.common.util.SpringContextUtil;

@Service
public class KsbbmxSerImpl extends BaseSerImpl<PBCS_KSBBMX> implements
		KsbbmxSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("PBCS_KSBBMX.query", page, rows, map);
	}

	@Override
	public void deleteByIds(String ids) {
		DBHelper dbHelper = new DBHelper(
				(JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate"));
		dbHelper.getJdbcTemplate().execute(
				"delete from PBCS_KSBBMX where lsh in (" + ids + ")");
	}

	@Override
	public void saveOrUpdate(String saveJson, String updateJson) {
		Gson gson = new Gson();
		if (!"".equals(saveJson) && null != saveJson) {
			List<PBCS_KSBBMX> list = gson.fromJson(saveJson,
					new TypeToken<List<PBCS_KSBBMX>>() {
					}.getType());

			super.addBatch(list);
		}
		if (!"".equals(updateJson) && null != updateJson) {
			List<PBCS_KSBBMX> list = gson.fromJson(updateJson,
					new TypeToken<List<PBCS_KSBBMX>>() {
					}.getType());

			super.updateBatch(list);
		}
	}

}
