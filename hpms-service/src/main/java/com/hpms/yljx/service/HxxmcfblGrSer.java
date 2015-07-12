package com.hpms.yljx.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPBH_BHGRMX;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月23日-下午3:02:41
 *
 * 类名： 核心项目拆分补录业务层接口
 *
 * 描述: 标化个人明细 -- 核心项目拆分补录 
 *
 */
public interface HxxmcfblGrSer extends BaseSer<HPBH_BHGRMX> {
	
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
}
