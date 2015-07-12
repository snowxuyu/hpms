package com.pbcs.pbgl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class PBCS_KSPBMX {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;

	private String ygbh;

	private String pblsh;

	private String bbbm;

	private int bbjs;

	private Short rq;

	private String sjxq;

	private Date sjrq;

	private String fjbb;

	private String fjjs;

	private String cjr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt;

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getYgbh() {
		return ygbh;
	}

	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}

	public String getPblsh() {
		return pblsh;
	}

	public void setPblsh(String pblsh) {
		this.pblsh = pblsh;
	}

	public String getBbbm() {
		return bbbm;
	}

	public void setBbbm(String bbbm) {
		this.bbbm = bbbm;
	}

	public int getBbjs() {
		return bbjs;
	}

	public void setBbjs(int bbjs) {
		this.bbjs = bbjs;
	}

	public Short getRq() {
		return rq;
	}

	public void setRq(Short rq) {
		this.rq = rq;
	}

	public String getSjxq() {
		return sjxq;
	}

	public void setSjxq(String sjxq) {
		this.sjxq = sjxq;
	}

	public Date getSjrq() {
		return sjrq;
	}

	public void setSjrq(Date sjrq) {
		this.sjrq = sjrq;
	}

	public String getFjbb() {
		return fjbb;
	}

	public void setFjbb(String fjbb) {
		this.fjbb = fjbb;
	}

	public String getFjjs() {
		return fjjs;
	}

	public void setFjjs(String fjjs) {
		this.fjjs = fjjs;
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