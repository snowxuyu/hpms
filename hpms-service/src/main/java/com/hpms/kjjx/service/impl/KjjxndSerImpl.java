package com.hpms.kjjx.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hpms.kjjx.entity.HPKJ_KJJXND;
import com.hpms.kjjx.service.KjjxndSer;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.ExcelUtil;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KjjxndSerImpl extends BaseSerImpl<HPKJ_KJJXND> implements
		KjjxndSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.findByPage("kjjxnd.queryAll", page, rows, map);
	}

	@Override
	public List<Object> exportList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		SqlResult sql = SqlUtil.getSql("kjjxnd.exporAll", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPKJ_KJJXND.class);
	}

	@Override
	public HSSFWorkbook export(String title, String cols, List<Object> data)
			throws Exception {
		ExcelUtil<HPKJ_KJJXND> e = new ExcelUtil<HPKJ_KJJXND>();
		HSSFWorkbook wb = e.export(title, cols, data);
		return wb;
	}


}
