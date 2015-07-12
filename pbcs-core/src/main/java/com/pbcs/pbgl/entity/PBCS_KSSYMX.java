package com.pbcs.pbgl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class PBCS_KSSYMX {
	
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String ywxh;

	private String ksnm;

	private String sylsh;

	private String cjr;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;

	private String xgr;

	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date xgsj;

	private String zt;

	public String getYwxh() {
		return ywxh;
	}

	public void setYwxh(String ywxh) {
		this.ywxh = ywxh;
	}

	public String getKsnm() {
		return ksnm;
	}

	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}

	public String getSylsh() {
		return sylsh;
	}

	public void setSylsh(String sylsh) {
		this.sylsh = sylsh;
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