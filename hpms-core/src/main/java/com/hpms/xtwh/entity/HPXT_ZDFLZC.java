package com.hpms.xtwh.entity;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * 类名：BPXT_ZDFLZC <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月15日 <br>
 * 描述：字典分类注册 <br>
 */
public class HPXT_ZDFLZC {

	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String flbm;
	private String flmc;
	public String getFlbm() {
		return flbm;
	}
	public void setFlbm(String flbm) {
		this.flbm = flbm;
	}
	public String getFlmc() {
		return flmc;
	}
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	
}
