package com.hpms.qmys.service;

import java.util.Map;

public interface JjSer {

	public Map<String, Object> findByGr(int page, int rows, Map<String, Object> map);
	public Map<String, Object> findByGr2(int page, int rows, Map<String, Object> map);
}
