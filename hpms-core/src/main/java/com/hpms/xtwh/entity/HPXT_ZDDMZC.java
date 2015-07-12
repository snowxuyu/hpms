package com.hpms.xtwh.entity;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * 类名：HPXT_ZDDMZC <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月15日 <br>
 * 描述：字典代码注册 <br>
 */
public class HPXT_ZDDMZC {

	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String zdnm;
	private String zdbm;
	private String zdmc;
	private String flbm;
	
	public String getZdnm() {
		return zdnm;
	}
	public void setZdnm(String zdnm) {
		this.zdnm = zdnm;
	}
	public String getZdbm() {
		return zdbm;
	}
	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getFlbm() {
		return flbm;
	}
	public void setFlbm(String flbm) {
		this.flbm = flbm;
	}
}
