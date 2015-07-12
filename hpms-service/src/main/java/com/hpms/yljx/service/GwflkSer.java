package com.hpms.yljx.service;

import java.util.List;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_GWFLK;
import com.hpms.yljx.vo.Combobox;

public interface GwflkSer extends BaseSer<HPCS_GWFLK> {

	public List<Combobox> findGW();
}
