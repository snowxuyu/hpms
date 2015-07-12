package com.hpms.yljx.service.impl;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPBH_BHGRMX;
import com.hpms.yljx.service.HxxmcfblGrSer;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月23日-下午3:11:43
 *
 * 类名： 个人的核心项目拆分补录 业务实现层
 *
 * 描述:  用来实现个人的核心项目拆分不了的功能
 *
 */
@Service("hxxmcfblGrSer")
public class HxxmcfblGrSerImpl extends BaseSerImpl<HPBH_BHGRMX> implements HxxmcfblGrSer {

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("hpbh_hxxmcfbl.findhxxcfbl", page, rows, map);
	}

}
