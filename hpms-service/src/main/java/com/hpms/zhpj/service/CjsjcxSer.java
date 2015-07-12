package com.hpms.zhpj.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.zhpj.entity.BPC_SM005;

public interface CjsjcxSer extends BaseSer<BPC_SM005> {
	public Map<String, Object> findByPage(int page, int rows,  Map<String, Object> map);

}
