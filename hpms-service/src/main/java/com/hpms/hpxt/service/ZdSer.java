package com.hpms.hpxt.service;

import java.util.List;
import java.util.Map;

import com.hpms.hpxt.entity.HPXT_ZDDMZC;
import com.hpms.service.BaseSer;
import com.hpms.yljx.vo.Combobox;

public interface ZdSer extends BaseSer<HPXT_ZDDMZC> {
	public List<Combobox> getList(Map<String, Object> map);
}
