package com.hpms.jjfp.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.jjfp.service.NdcsSer;
import com.hpms.service.impl.BaseSerImpl;
@Service("ndcsser")
public class NdcsSerImpl extends BaseSerImpl<BPE_JX001> implements NdcsSer {

	@Override
	public Map<String, Object> findByPage(int page, int rows, String nd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nd",nd);
		return this.findByPage("bpe_ndcs.query_ndcs", page, rows, map);
	}

}
