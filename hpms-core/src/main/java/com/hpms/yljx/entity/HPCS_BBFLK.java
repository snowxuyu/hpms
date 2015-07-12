package com.hpms.yljx.entity;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月7日-下午12:58:38
 *
 * 类名： 班别分类库
 *
 * 描述:
 * 吴陶君：新增bbcs、bbfl、bz 
 * 
 */
public class HPCS_BBFLK {
	
	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String bbbm; //班别编码
	private String bbmc; //班别名称
	private String bbjx; //班别简写
	private String bbcs;
	private String bbfl;
	private String bz;
	
	public String getBbjx() {
		return bbjx;
	}
	public void setBbjx(String bbjx) {
		this.bbjx = bbjx;
	}
	public String getBbbm() {
		return bbbm;
	}
	public void setBbbm(String bbbm) {
		this.bbbm = bbbm;
	}
	public String getBbmc() {
		return bbmc;
	}
	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}
	public String getBbcs() {
		return bbcs;
	}
	public void setBbcs(String bbcs) {
		this.bbcs = bbcs;
	}
	public String getBbfl() {
		return bbfl;
	}
	public void setBbfl(String bbfl) {
		this.bbfl = bbfl;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public HPCS_BBFLK(String bbbm, String bbmc) {
		super();
		this.bbbm = bbbm;
		this.bbmc = bbmc;
	}
	public HPCS_BBFLK() {
		super();
	}
	
}
