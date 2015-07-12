package com.hpms.kjjx.service;

import java.util.List;
import java.util.Map;

import com.hpms.kjjx.entity.HPKJ_KJXMPF;
import com.hpms.service.BaseSer;

public interface KjpfSer extends BaseSer<HPKJ_KJXMPF> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public  List<HPKJ_KJXMPF> findAll();
}
