package com.hpms.xtwh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.xtwh.entity.HPXT_CDMKZC;
import com.hpms.xtwh.service.CdmkzcSer;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("cdmkzcser")
public class CdmkzcSerImpl extends BaseSerImpl<HPXT_CDMKZC> implements CdmkzcSer {
	@Override
	public Map<String, Object> findByPage(int page, int rows, String ks) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ks", ks);
		return findByPage("hpxt_cdmkzc.query_cdmkzc", page, rows, map);
	}

	@Override
	public List<HPXT_CDMKZC> findAll() {
		return find("hpxt_cdmkzc.query_cdmkzc");
	}

	@Override
	public Map<String, List<HPXT_CDMKZC>> findToMap() {
		Map<String, List<HPXT_CDMKZC>> map = new HashMap<String, List<HPXT_CDMKZC>>();
		List<HPXT_CDMKZC> list1 = new ArrayList<HPXT_CDMKZC>();
		List<HPXT_CDMKZC> list2 = new ArrayList<HPXT_CDMKZC>();
		List<HPXT_CDMKZC> list3 = new ArrayList<HPXT_CDMKZC>();
		List<HPXT_CDMKZC> list = findAll();
		for (HPXT_CDMKZC bean : list) {
			if ("1".equals(bean.getJb())) {
				list1.add(bean);
			}
			if ("2".equals(bean.getJb())) {
				list2.add(bean);
			}
			if ("3".equals(bean.getJb())) {
				list3.add(bean);
			}
		}
		map.put("1", list1);
		map.put("2", list2);
		map.put("3", list3);
		return map;
	}
}

