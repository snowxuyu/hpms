package com.hpms.jjfp.service;

import java.util.Map;

import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.service.BaseSer;

public interface NdcsSer extends BaseSer<BPE_JX001> {
	public Map<String, Object> findByPage(int page, int rows, String nd);
}
