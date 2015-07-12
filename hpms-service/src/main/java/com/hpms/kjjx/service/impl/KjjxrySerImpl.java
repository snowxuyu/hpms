package com.hpms.kjjx.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hpms.kjjx.entity.HPKJ_KJJXRY;
import com.hpms.kjjx.service.KjjxrySer;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.ExcelUtil;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KjjxrySerImpl extends BaseSerImpl<HPKJ_KJJXRY> implements
		KjjxrySer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("kjjxry.queryAll", page, rows, map);
	}

	@Override
	public HSSFWorkbook export(String title,String cols,List<Object> data) throws Exception {
		// TODO Auto-generated method stub
		ExcelUtil<HPKJ_KJJXRY> e = new ExcelUtil<HPKJ_KJJXRY>();
		HSSFWorkbook wb = e.export(title, cols, data);
		return wb;
	}

	@Override
	public List<Object> exportList(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("kjjxry.exporAll", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPKJ_KJJXRY.class);
	}

}
