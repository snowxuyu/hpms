package com.hpms.yljx.service;

import java.util.List;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.vo.Combobox;


public interface BbflkSer extends BaseSer<HPCS_BBFLK> {
	/**
	 * 查询所有班别的班别别名称和版本编码
	 * @return
	 */
	public List<Combobox> listAllBb();
	public List<HPCS_BBFLK> findAll();
}
