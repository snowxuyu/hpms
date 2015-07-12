package com.pbcs.pbgl.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.rongda.common.jdbc.annotation.Transient;

public class PBCS_KSBBMX {
	private String lsh;

	private String bbbm;

	private String sylsh;

	private String sjxs;

	private BigDecimal bfje;

	private String cjr;

	private Date cjsj;

	private String xgr;

	private Date xgsj;

	private String zt;

	@Transient
	private String bbmc;
	@Transient
	private String bbjx;

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getBbbm() {
		return bbbm;
	}

	public void setBbbm(String bbbm) {
		this.bbbm = bbbm;
	}

	public String getSylsh() {
		return sylsh;
	}

	public void setSylsh(String sylsh) {
		this.sylsh = sylsh;
	}

	public String getSjxs() {
		return sjxs;
	}

	public void setSjxs(String sjxs) {
		this.sjxs = sjxs;
	}

	public BigDecimal getBfje() {
		return bfje;
	}

	public void setBfje(BigDecimal bfje) {
		this.bfje = bfje;
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

	public String getBbmc() {
		return bbmc;
	}

	public void setBbmc(String bbmc) {
		this.bbmc = bbmc;
	}

	public String getBbjx() {
		return bbjx;
	}

	public void setBbjx(String bbjx) {
		this.bbjx = bbjx;
	}

}