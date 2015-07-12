package com.hpms.yljx.entity;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * 类名：HPCS_PBBDR <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月13日 <br>
 * 描述：护理排班表导入 <br>
 */
public class HPCS_PBBDR {
	
	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String lsh;
	private String ksnm;
	private String ksmc;
	private String ygbh;
	private String ygmc;
	private Integer nd;
	private Integer yd;
	private Integer ts;
	private String bbbm;
	private String bbmc;
	private Double bbcs;
	private String cjr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp cjsj;
	private String xgr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Timestamp xgsj;
	private String zt = "1";
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
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getYgbh() {
		return ygbh;
	}
	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}
	public String getYgmc() {
		return ygmc;
	}
	public void setYgmc(String ygmc) {
		this.ygmc = ygmc;
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
	public Integer getTs() {
		return ts;
	}
	public void setTs(Integer ts) {
		this.ts = ts;
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
	public Double getBbcs() {
		return bbcs;
	}
	public void setBbcs(Double bbcs) {
		this.bbcs = bbcs;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Timestamp getCjsj() {
		return cjsj;
	}
	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}
	public String getXgr() {
		return xgr;
	}
	public void setXgr(String xgr) {
		this.xgr = xgr;
	}
	public Timestamp getXgsj() {
		return xgsj;
	}
	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}

}
