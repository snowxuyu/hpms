package com.hpms.jjfp.entity;


import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

public class HPCS_CLJSSZ {
	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String lsh;
	private String ksnm;
	private Integer nd;
	private Integer yd;
	private Double kscl;
	private String cjr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp cjsj;
	private String xgr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Timestamp xgsj;
	private String zt;
	@Transient
	private String ksmc;
	@Transient
	private String ksfl;
	@Transient
	private String yjks;
	@Transient
	private String ejks;
	
	public String getKsfl() {
		return ksfl;
	}
	public void setKsfl(String ksfl) {
		this.ksfl = ksfl;
	}
	public String getYjks() {
		return yjks;
	}
	public void setYjks(String yjks) {
		this.yjks = yjks;
	}
	public String getEjks() {
		return ejks;
	}
	public void setEjks(String ejks) {
		this.ejks = ejks;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getKsnm() {
		return ksnm;
	}
	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}
	public Integer getNd() {
		return nd;
	}
	public void setNd(Integer nd) {
		this.nd = nd;
	}
	public Integer getYd() {
		return yd;
	}
	public void setYd(Integer yd) {
		this.yd = yd;
	}
	public Double getKscl() {
		return kscl;
	}
	public void setKscl(Double kscl) {
		this.kscl = kscl;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getXgr() {
		return xgr;
	}
	public void setXgr(String xgr) {
		this.xgr = xgr;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public Timestamp getCjsj() {
		return cjsj;
	}
	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}
	public Timestamp getXgsj() {
		return xgsj;
	}
	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}
	
}
