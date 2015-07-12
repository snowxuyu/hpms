package com.hpms.yljx.entity;



import java.sql.Timestamp;





import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月7日-下午12:58:38
 *
 * 类名： 班别点值对照
 *
 * 描述:
 *
 */

public class HPCS_BBDZDZ {
	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String lsh;  //流水号
	private String ksnm; //科室内码
	private String bbbm; //班别编码
	private String bbmc; //班别名称
	private Double bbdz; //班别点值
	private Double gwxs; //岗位系数
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
	public Double getBbdz() {
		return bbdz;
	}
	public void setBbdz(Double bbdz) {
		this.bbdz = bbdz;
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
	public Double getGwxs() {
		return gwxs;
	}
	public void setGwxs(Double gwxs) {
		this.gwxs = gwxs;
	}
}
