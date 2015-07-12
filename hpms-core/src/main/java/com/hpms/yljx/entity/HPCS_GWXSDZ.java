package com.hpms.yljx.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * 类名：HPCS_GWXSDZ <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月7日 <br>
 * 描述：岗位系数对照 <br>
 */
public class HPCS_GWXSDZ {

	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;		//流水号
	private String ksnm;	//科室内码
	private String gwbm;	//岗位编码
	private String gwmc;	//岗位名称
	private String gwxs;	//岗位系数
	private String cjr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
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
	public String getGwbm() {
		return gwbm;
	}
	public void setGwbm(String gwbm) {
		this.gwbm = gwbm;
	}
	public String getGwmc() {
		return gwmc;
	}
	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}
	public String getGwxs() {
		return gwxs;
	}
	public void setGwxs(String gwxs) {
		this.gwxs = gwxs;
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
	@Transient
	private String ksmc;
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	
}
