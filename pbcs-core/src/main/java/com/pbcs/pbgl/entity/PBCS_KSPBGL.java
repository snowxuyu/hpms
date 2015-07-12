package com.pbcs.pbgl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class PBCS_KSPBGL {
	@Id
	private String pblsh;

	private String ksnm;

	private Short nd;

	private Short yd;

	private String pbr;

	private String pbzt;

	private String cjr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt;

	public String getPblsh() {
		return pblsh;
	}

	public void setPblsh(String pblsh) {
		this.pblsh = pblsh;
	}

	public String getKsnm() {
		return ksnm;
	}

	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}

	public Short getNd() {
		return nd;
	}

	public void setNd(Short nd) {
		this.nd = nd;
	}

	public Short getYd() {
		return yd;
	}

	public void setYd(Short yd) {
		this.yd = yd;
	}

	public String getPbr() {
		return pbr;
	}

	public void setPbr(String pbr) {
		this.pbr = pbr;
	}

	public String getPbzt() {
		return pbzt;
	}

	public void setPbzt(String pbzt) {
		this.pbzt = pbzt;
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