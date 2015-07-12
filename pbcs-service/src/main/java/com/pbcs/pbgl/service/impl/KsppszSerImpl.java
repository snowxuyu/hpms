package com.pbcs.pbgl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hpms.qmys.vo.TempVO;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.pbcs.pbgl.service.KsppszSer;
import com.pbcs.pbgl.vo.PBCS_KSPBSZVO;
import com.rongda.common.jdbc.helper.DBHelper;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SpringContextUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KsppszSerImpl extends BaseSerImpl<PBCS_KSPBSZVO> implements
		KsppszSer {

	@Override
	public List<TempVO> getTitle(Map<String, Object> map) {
		DBHelper dbHelper = new DBHelper(
				(JdbcTemplate) SpringContextUtil.getBean("jdbcTemplate"));
		String sql="select '[{title:''日期'',field:''ygxm''},'||wm_concat('{field:''bValue'||to_char(to_date('"+map.get("rq")+"-01','yyyy-mm-dd')+level-1,'yyyy-mm-dd')||''',title:'''||to_char(to_date('"+map.get("rq")+"','yyyy-mm')+level-1,'dd')||'''}')||'],[{title:''星期''},'||wm_concat('{title:'''||substr(to_char(to_date('"+map.get("rq")+"','yyyy-mm')+level-1,'day'),3,4)||'''}')||']' as p1 from dual connect by level<=last_day(to_date('"+map.get("rq")+"','yyyy-mm'))-to_date('"+map.get("rq")+"-01','yyyy-mm-dd')+1";
		List<TempVO> list = dbHelper.find(sql, TempVO.class);
		return list;
	}

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("PBCS_KSPPSZ.queryAll", page, rows, map);
	}

	@Override
	public List<HPCS_BBFLK> queryBB(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("PBCS_KSPPSZ.queryBB",map);
		List<HPCS_BBFLK> list = DBUtil.find(sql.getSql(),sql.getParam(),HPCS_BBFLK.class);
		return list;
	}

}
