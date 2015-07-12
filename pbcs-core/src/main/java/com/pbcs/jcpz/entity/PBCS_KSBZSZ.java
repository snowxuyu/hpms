package com.pbcs.jcpz.entity;

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
 * 创建时间：2015年6月5日-上午10:14:59
 *
 * 类名： 科室编制设置
 *
 * 描述:
 *
 */
public class PBCS_KSBZSZ {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private String ksnm;
	private Integer nd;
	private Integer yd;
	private Integer bzs;
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
	private String rq;
	
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
	public Integer getBzs() {
		return bzs;
	}
	public void setBzs(Integer bzs) {
		this.bzs = bzs;
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
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getRq() {
		String _yd = (String.valueOf(this.yd)).length()<=1?"0"+String.valueOf(this.yd):String.valueOf(this.yd);
		return String.valueOf(this.nd)+"-"+_yd;
	}
	public void setRq(String rq) {
		this.rq = String.valueOf(this.nd)+"-"+String.valueOf(this.yd);
	}
	@Override
	public String toString() {
		return "PBCS_KSBZSZ [lsh=" + lsh + ", ksnm=" + ksnm + ", nd=" + nd
				+ ", yd=" + yd + ", bzs=" + bzs + ", cjr=" + cjr + ", cjsj="
				+ cjsj + ", xgr=" + xgr + ", xgsj=" + xgsj + ", zt=" + zt
				+ ", ksmc=" + ksmc + ", rq=" + rq + "]";
	}
}
