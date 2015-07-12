package com.hpms.zhpj.service;

import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.zhpj.entity.BPC_HIS001;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月12日-下午4:43:26
 *
 * 类名： HIS查询
 *
 * 描述:
 *
 */
public interface His001Ser extends BaseSer<BPC_HIS001> {
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
}

