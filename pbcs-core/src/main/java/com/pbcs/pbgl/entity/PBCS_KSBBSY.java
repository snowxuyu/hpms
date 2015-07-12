package com.pbcs.pbgl.entity;

import java.util.Date;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class PBCS_KSBBSY {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String sylsh;

	private String symc;

	private String bz;

	private String cjr;

	private Date cjsj;

	private String xgr;

	private Date xgsj;

	private String zt;

	public String getSylsh() {
		return sylsh;
	}

	public void setSylsh(String sylsh) {
		this.sylsh = sylsh;
	}

	public String getSymc() {
		return symc;
	}

	public void setSymc(String symc) {
		this.symc = symc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}
}