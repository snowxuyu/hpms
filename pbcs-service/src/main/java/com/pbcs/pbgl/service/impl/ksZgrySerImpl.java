package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.ExcelUtil;
import com.pbcs.pbgl.service.KsZgrySer;
import com.pbcs.pbgl.vo.PBCS_ZGRYTJVO;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class ksZgrySerImpl extends BaseSerImpl<PBCS_ZGRYTJVO> implements
		KsZgrySer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("PBCS_ZGRYTJVO.query", page, rows, map);
	}

	@Override
	public List<PBCS_ZGRYTJVO> find(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("PBCS_ZGRYTJVO.sumQuery", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), PBCS_ZGRYTJVO.class);
	}

	@Override
	public HSSFWorkbook export(String title, String cols, List<Object> data) {
		ExcelUtil<PBCS_ZGRYTJVO> excleUtil = new ExcelUtil<PBCS_ZGRYTJVO>();
		HSSFWorkbook wb = null;
		try {
			wb = excleUtil.export(title, cols, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wb;
	}

	@Override
	public List<Object> exportList(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("PBCS_ZGRYTJVO.query", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), PBCS_ZGRYTJVO.class);
	}

}
