package com.hpms.zhpj.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.zhpj.entity.BPC_SM005;
import com.hpms.zhpj.service.CjsjcxSer;
@Service("cjsjcxser")
public class CjsjcxSerImpl extends BaseSerImpl<BPC_SM005> implements CjsjcxSer {
	@Override
	public Map<String, Object> findByPage(int page, int rows,Map<String, Object> map) {
		return this.findByPage("bpc_cjsjcx.query_cjsjcx", page, rows, map);
	}

}
