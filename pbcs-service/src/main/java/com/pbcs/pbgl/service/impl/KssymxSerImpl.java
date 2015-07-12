package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hpms.qmys.vo.TempVO;
import com.hpms.service.impl.BaseSerImpl;
import com.pbcs.pbgl.entity.PBCS_KSSYMX;
import com.pbcs.pbgl.service.KssymxSer;
import com.rongda.common.jdbc.helper.DBHelper;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SpringContextUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KssymxSerImpl extends BaseSerImpl<PBCS_KSSYMX> implements
		KssymxSer {

	@Override
	public List<TempVO> getKsnms(Map<String, Object> map) {
		// TODO Auto-generated method stub
		SqlResult sql = SqlUtil.getSql("PBCS_KSSYMX.query_ksnms", map);
		List<TempVO> list = DBUtil.find(sql.getSql(), sql.getParam(),
				TempVO.class);
		// this.add(t);
		return list;
	}

	@Override
	public void deleteByIds(String ids) {
		DBHelper dbHelper = new DBHelper(
				(JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate"));
		dbHelper.getJdbcTemplate().execute(
				"delete from PBCS_KSSYMX where 1=1 and sylsh in(" + ids + ")");
	}

}
