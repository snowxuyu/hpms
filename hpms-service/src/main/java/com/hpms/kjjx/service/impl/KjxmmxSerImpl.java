package com.hpms.kjjx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.kjjx.entity.HPKJ_ExpKJXMMX;
import com.hpms.kjjx.entity.HPKJ_KJGRMX;
import com.hpms.kjjx.entity.HPKJ_KJXMMX;
import com.hpms.kjjx.entity.HPKJ_KJXMPF;
import com.hpms.kjjx.entity.HPKJ_KJXMZD;
import com.hpms.kjjx.service.KjxmmxSer;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.util.ExcelUtil;
import com.hpms.yljx.vo.Combobox;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;
import com.rongda.framework.util.StringUtil;

@Service("KjxmmxSer")
public class KjxmmxSerImpl extends BaseSerImpl<HPKJ_KJXMMX> implements KjxmmxSer {

	@Override
	public Map<String,Object> selectAll(int page,int rows,HashMap<String,Object> map) {
		return findByPage("kjxmmx.queryAll",page,rows,map);
	}

//	@Override
//	public List<HPKJ_KJXMZD> selectXmzd() {
//		SqlResult sql = SqlUtil.getSql("kjxmmx.queryXMZD");
//		List<HPKJ_KJXMZD> list = DBUtil.find(sql.getSql(),HPKJ_KJXMZD.class);
//		return list;
//	}

	@Override
	public List<BPE_JX001> selectNd() {
		SqlResult sql = SqlUtil.getSql("kjxmmx.queryNd");
		List<BPE_JX001> list = DBUtil.find(sql.getSql(),BPE_JX001.class);
		return list;
	}

	@Override
	public List<HPKJ_KJXMZD> selectzd(String param, Map<String, Object> map) {
		String sqlS = null;
		if(!StringUtil.isNullAndSpace(param) && "xm".equals(param)){
			sqlS = "kjxmmx.query_khbzxm";
		}
		if(!StringUtil.isNullAndSpace(param) && "lb".equals(param)){
			sqlS = "kjxmmx.query_khbzlb";
		}
		if(!StringUtil.isNullAndSpace(param) && "jb".equals(param)){
			sqlS = "kjxmmx.query_khbzjb";
		}
		
		SqlResult sql = SqlUtil.getSql(sqlS, map);
		List<HPKJ_KJXMZD> list = DBUtil.find(sql.getSql(),sql.getParam(), HPKJ_KJXMZD.class);
		
		return list;
	}

	@Override
	public List<HPKJ_KJXMPF> selectSm(String param, Map<String, Object> map) {
		String sqlS = null;
		if(!StringUtil.isNullAndSpace(param) && "sm".equals(param)){
			sqlS = "kjxmmx.query_khbzsm";
		}
		SqlResult sql = SqlUtil.getSql(sqlS, map);
		List<HPKJ_KJXMPF> list = DBUtil.find(sql.getSql(), sql.getParam(),HPKJ_KJXMPF.class);
		return list;
	}

	@Override
	public String delete(String xmid) {
		DBUtil.deleteById(HPKJ_KJXMMX.class, xmid);
		return "1";
	}

	@Override
	public String insert(HPKJ_KJXMMX kjxmmx) {
		DBUtil.create(kjxmmx);
		return "1";
	}

	@Override
	public String updateTable(HPKJ_KJXMMX kjxmmx) {
		DBUtil.update(kjxmmx, true);
		return "1";
	}

	@Override
	public void insertGrmx(List<HPKJ_KJGRMX> grmxList) {
		DBUtil.createBatch(grmxList);
	}

	@Override
	public void deleteGrmx(String xmid) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("xmid", xmid);
		DBUtil.deleteByProperties(HPKJ_KJGRMX.class, map);
	}

	@Override
	public void updateGrmx(List<HPKJ_KJGRMX> grmxList) {
		for(int i=0;i<grmxList.size();i++){
			HPKJ_KJGRMX kjgrmx = grmxList.get(i);
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("xmid", kjgrmx.getXmid());
			DBUtil.updateByProperties(kjgrmx, map);
		}
	}

	@Override
	public HSSFWorkbook export(String title, String cols, List<Object> data)
			throws Exception {
		ExcelUtil<HPKJ_ExpKJXMMX> excleUtil = new ExcelUtil<HPKJ_ExpKJXMMX>();
		HSSFWorkbook wb = excleUtil.export(title, cols, data);
		return wb;
	}

	@Override
	public List<Object> exportList(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("kjxmmx.queryAll",map);
		return DBUtil.find(sql.getSql(),sql.getParam(),HPKJ_ExpKJXMMX.class);
	}

	@Override
	public void deleteBysmbm(String smbm) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("smbm", smbm);
		DBUtil.deleteByProperties(HPKJ_KJGRMX.class, map);
	}

	@Override
	public List<HPKJ_KJXMZD> selectxmmc() {
		SqlResult sql = SqlUtil.getSql("kjxmmx.queryXmmc");
		List<HPKJ_KJXMZD> list = DBUtil.find(sql.getSql(),HPKJ_KJXMZD.class);
		return list;
	}

	@Override
	public List<Combobox> queryExportExcel() {
		SqlResult sql = SqlUtil.getSql("kjxmmx.query_exportExcel");
		return DBUtil.find(sql.getSql(), Combobox.class);
	}
}
