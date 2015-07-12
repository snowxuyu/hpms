package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.ExcelUtil;
import com.pbcs.pbgl.service.KsqqSer;
import com.pbcs.pbgl.vo.PBCS_KSQQTJVO;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KsqqSerImpl extends BaseSerImpl<PBCS_KSQQTJVO> implements KsqqSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("PBCS_KSQQTJVO.query", page, rows, map);
	}

	@Override
	public HSSFWorkbook export(String title, String cols, List<Object> data) {
		ExcelUtil<PBCS_KSQQTJVO> excleUtil = new ExcelUtil<PBCS_KSQQTJVO>();
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
		SqlResult sql = SqlUtil.getSql("PBCS_KSQQTJVO.query", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), PBCS_KSQQTJVO.class);
	}

}
