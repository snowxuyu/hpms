package com.hpms.jjfp.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.jjfp.entity.HPCS_QYPJJJ;
import com.hpms.jjfp.service.QypjjjSer;
import com.hpms.service.impl.BaseSerImpl;
@Service("qypjjjSer")
public class QypjjjSerImpl extends BaseSerImpl<HPCS_QYPJJJ> implements QypjjjSer {

	@Override
	public Map<String, Object> findByPage(int page, int rows, Map<String, Object> map) {
		return findByPage("hpcs_qypjjj.query_qypjjj", page, rows, map);
	}
}
