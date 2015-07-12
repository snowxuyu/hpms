package com.hpms.qmys.service;

import java.util.List;
import java.util.Map;

import com.hpms.qmys.vo.BhgzlFPVO;
import com.hpms.qmys.vo.BhgzlLBVO;
import com.hpms.qmys.vo.BhgzlNYVO;
import com.hpms.qmys.vo.BhgzlTJVO;
import com.hpms.qmys.vo.GRJJVO;
import com.hpms.qmys.vo.TempVO;

public interface BhgzlSer {

	public Map<String, Object> findByNY(int page, int rows, Map<String, Object> map);
	public BhgzlTJVO findByNY(Map<String, Object> map);
	public Map<String, Object> findByFP(int page, int rows, Map<String, Object> map);
	public BhgzlTJVO findByFP(Map<String, Object> map);
	public Map<String, Object> findByLB(int page, int rows, Map<String, Object> map);
	public BhgzlTJVO findByLB(Map<String, Object> map);
	public Map<String, Object> findByLBGr(int page, int rows, Map<String, Object> map);
	public BhgzlTJVO findByLBGR(Map<String, Object> map);
	public Map<String, Object> findByLBKsGr(int page, int rows, Map<String, Object> map);
	public BhgzlTJVO findByLBKsGr(Map<String, Object> map);
	
	public List<BhgzlNYVO> findAllByNY(Map<String, Object> map);
	public List<BhgzlFPVO> findAllByFP(Map<String, Object> map);
	public List<BhgzlLBVO> findAllByLB(Map<String, Object> map);
	public List<GRJJVO> findAllByLBGr(Map<String, Object> map);
	
	public Map<String, Object> findSR(int page, int rows, Map<String, Object> _map);
	public List<TempVO> findSR(Map<String, Object> _map);
	public Map<String, Object> findByLBXM(int page, int rows,Map<String, Object> map);
	public BhgzlTJVO findByLBXM(Map<String, Object> map);
	
}
