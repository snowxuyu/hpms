package com.hpms.kjjx.service;

import java.util.List;
import java.util.Map;

import com.hpms.kjjx.entity.HPKJ_YGXMMX;
import com.hpms.service.BaseSer;

public interface KjygxmmxSer extends BaseSer<HPKJ_YGXMMX> {
	public List<HPKJ_YGXMMX> findBybh(Map<String, Object> map);
}
