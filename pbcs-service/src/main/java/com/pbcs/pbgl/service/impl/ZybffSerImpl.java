package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.ExcelUtil;
import com.pbcs.pbgl.service.ZybffSer;
import com.pbcs.pbgl.vo.PBCS_ZYBFFVO;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("ZybffSer")
public class ZybffSerImpl extends BaseSerImpl<PBCS_ZYBFFVO> implements ZybffSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("PBCS_ZYBFFVO.query", page, rows, map);
	}

	@Override
	public HSSFWorkbook export(String title, String cols, List<Object> data)
			throws Exception {
		ExcelUtil<PBCS_ZYBFFVO> excleUtil = new ExcelUtil<PBCS_ZYBFFVO>();
		HSSFWorkbook wb = excleUtil.export(title, cols, data);
		return wb;
	}

	@Override
	public List<Object> exportList(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("PBCS_ZYBFFVO.query", map);
		return DBUtil.find(sql.getSql(),sql.getParam(),PBCS_ZYBFFVO.class);
	}

	@Override
	public List<PBCS_ZYBFFVO> selectSum(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("PBCS_ZYBFFVO.querySum", map);
		List<PBCS_ZYBFFVO> list = DBUtil.find(sql.getSql(),sql.getParam(),PBCS_ZYBFFVO.class);
		return list;
	}

}
