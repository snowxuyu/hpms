package com.pbcs.jcpz.service;

import java.util.List;
import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.vo.Combobox;

public interface PbzdszSer extends BaseSer<HPCS_BBFLK> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	public List<Combobox> getList();
	public boolean existBBBM(String bbbm);
	public HPCS_BBFLK findByBbbm(String bbbm);
}
