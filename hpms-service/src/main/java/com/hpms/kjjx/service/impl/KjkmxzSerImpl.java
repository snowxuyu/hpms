package com.hpms.kjjx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hpms.kjjx.entity.HPKJ_KSMX;
import com.hpms.kjjx.service.KjkmxzSer;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.ExcelUtil;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("KjkmxzSer")
public class KjkmxzSerImpl extends BaseSerImpl<HPKJ_KSMX> implements KjkmxzSer {

	@Override
	public Map<String, Object> selectAll(int page, int rows,
			HashMap<String, Object> map) {
		return findByPage("kjxmmx.queryByks", page, rows, map);
	}

	@Override
	public HSSFWorkbook export(String title, String cols, List<Object> data)
			throws Exception {
		ExcelUtil<HPKJ_KSMX> excleUtil = new ExcelUtil<HPKJ_KSMX>();
		HSSFWorkbook wb = excleUtil.export(title, cols, data);
		return wb;
	}

	@Override
	public List<Object> exportList(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("kjxmmx.queryByks", map);
		return DBUtil.find(sql.getSql(),sql.getParam(),HPKJ_KSMX.class);
	}

}
