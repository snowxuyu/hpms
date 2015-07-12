package com.hpms.zhpj.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.zhpj.entity.BPC_HIS001;
import com.hpms.zhpj.service.His001Ser;

@Service("his001Ser")
public class His001SerImpl extends BaseSerImpl<BPC_HIS001> implements His001Ser{

	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		
		/**
		 * 分页查询
		 * sqlId  : 查询的SQL的ID
		 * pager ： 查询的第几页
		 * rows ： 查询多少条记录
		 * map : 查询参数
		 */
		return this.findByPage("bpc_his001.queryHis", page, rows, map);
	}
	
	
}
