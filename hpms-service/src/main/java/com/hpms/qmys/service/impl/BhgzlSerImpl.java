package com.hpms.qmys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.qmys.service.BhgzlSer;
import com.hpms.qmys.vo.BhgzlFPVO;
import com.hpms.qmys.vo.BhgzlLBVO;
import com.hpms.qmys.vo.BhgzlNYVO;
import com.hpms.qmys.vo.BhgzlTJVO;
import com.hpms.qmys.vo.GRJJVO;
import com.hpms.qmys.vo.TempVO;
import com.hpms.util.PageUtil;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class BhgzlSerImpl implements BhgzlSer {

	@Override
	public Map<String, Object> findByNY(int page, int rows, Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_nd",map);
		return PageUtil.getPageDataJson(page, rows, sql, BhgzlNYVO.class);
	}
	
	@Override
	public Map<String, Object> findByFP(int page, int rows, Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_fp",map);
		return PageUtil.getPageDataJson(page, rows, sql, BhgzlFPVO.class);
	}
	
	@Override
	public Map<String, Object> findByLB(int page, int rows, Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb",map);
		return PageUtil.getPageDataJson(page, rows, sql, BhgzlLBVO.class);
	}
	
	@Override
	public BhgzlTJVO findByNY(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_nd_count", map);
		List<BhgzlTJVO> list = DBUtil.find(sql.getSql(), sql.getParam(), BhgzlTJVO.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public BhgzlTJVO findByFP(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_fp_count", map);
		List<BhgzlTJVO> list = DBUtil.find(sql.getSql(), sql.getParam(), BhgzlTJVO.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public BhgzlTJVO findByLB(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb_count", map);
		List<BhgzlTJVO> list = DBUtil.find(sql.getSql(), sql.getParam(), BhgzlTJVO.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Map<String, Object> findByLBGr(int page, int rows,
			Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb_gr",map);
		return PageUtil.getPageDataJson(page, rows, sql, GRJJVO.class);
	}
	
	@Override
	public Map<String, Object> findByLBKsGr(int page, int rows,
			Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb_ksgr",map);
		return PageUtil.getPageDataJson(page, rows, sql, BhgzlFPVO.class);
	}
	
	@Override
	public BhgzlTJVO findByLBKsGr(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb_ksgr_count", map);
		List<BhgzlTJVO> list = DBUtil.find(sql.getSql(), sql.getParam(), BhgzlTJVO.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<BhgzlNYVO> findAllByNY(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_nd", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), BhgzlNYVO.class);
	}

	@Override
	public List<BhgzlFPVO> findAllByFP(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_fp", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), BhgzlFPVO.class);
	}

	@Override
	public List<BhgzlLBVO> findAllByLB(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), BhgzlLBVO.class);
	}

	@Override
	public List<GRJJVO> findAllByLBGr(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb_gr", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), GRJJVO.class);
	}

	@Override
	public Map<String, Object> findSR(int page, int rows, Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_sr",map);
		return PageUtil.getPageDataJson(page, rows, sql, TempVO.class);
	}
	
	@Override
	public List<TempVO> findSR(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_sr", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), TempVO.class);
	}

	@Override
	public Map<String, Object> findByLBXM(int page, int rows, Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lbxm",map);
		return PageUtil.getPageDataJson(page, rows, sql, BhgzlLBVO.class);
	}

	@Override
	public BhgzlTJVO findByLBXM(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lbxm_count", map);
		List<BhgzlTJVO> list = DBUtil.find(sql.getSql(), sql.getParam(), BhgzlTJVO.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public BhgzlTJVO findByLBGR(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("qmys_query.query_lb_gr_count", map);
		List<BhgzlTJVO> list = DBUtil.find(sql.getSql(), sql.getParam(), BhgzlTJVO.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

}
