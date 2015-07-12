package com.hpms.jjfp.service;

import java.util.Map;

import com.hpms.qmys.vo.GRJJVO;
import com.hpms.service.BaseSer;

public interface JjfpSer extends BaseSer<GRJJVO> {

	public Map<String, Object> findByGr(int page, int rows, Map<String, Object> map);
}
