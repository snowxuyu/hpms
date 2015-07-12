package com.hpms.yljx.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * 类名：BPXT_KSZCXX <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月10日 <br>
 * 描述：科室注册信息 <br>
 */
public class HPXT_KSZCXX {

	@Id
	private String ksnm;
	private String yybm;
	private String ksmc;
	private String ksjc;
	private String fjbm;
	private String jb;
	private Integer px;
	private String kslb;
	private String mzlb;
	private String sfbf;
	private String sfss;
	private String sfhl;
	private String hsbz;
	private String ksppbm;
	private String ksppmc;
	private String bqppbm;
	private String bqppmc;
	private String kszt;
	private String cjr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt;
	public String getKsnm() {
		return ksnm;
	}
	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}
	public String getYybm() {
		return yybm;
	}
	public void setYybm(String yybm) {
		this.yybm = yybm;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getKsjc() {
		return ksjc;
	}
	public void setKsjc(String ksjc) {
		this.ksjc = ksjc;
	}
	public String getFjbm() {
		return fjbm;
	}
	public void setFjbm(String fjbm) {
		this.fjbm = fjbm;
	}
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
	}
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	public String getKslb() {
		return kslb;
	}
	public void setKslb(String kslb) {
		this.kslb = kslb;
	}
	public String getMzlb() {
		return mzlb;
	}
	public void setMzlb(String mzlb) {
		this.mzlb = mzlb;
	}
	public String getSfbf() {
		return sfbf;
	}
	public void setSfbf(String sfbf) {
		this.sfbf = sfbf;
	}
	public String getSfss() {
		return sfss;
	}
	public void setSfss(String sfss) {
		this.sfss = sfss;
	}
	public String getSfhl() {
		return sfhl;
	}
	public void setSfhl(String sfhl) {
		this.sfhl = sfhl;
	}
	public String getHsbz() {
		return hsbz;
	}
	public void setHsbz(String hsbz) {
		this.hsbz = hsbz;
	}
	public String getKsppbm() {
		return ksppbm;
	}
	public void setKsppbm(String ksppbm) {
		this.ksppbm = ksppbm;
	}
	public String getKsppmc() {
		return ksppmc;
	}
	public void setKsppmc(String ksppmc) {
		this.ksppmc = ksppmc;
	}
	public String getBqppbm() {
		return bqppbm;
	}
	public void setBqppbm(String bqppbm) {
		this.bqppbm = bqppbm;
	}
	public String getBqppmc() {
		return bqppmc;
	}
	public void setBqppmc(String bqppmc) {
		this.bqppmc = bqppmc;
	}
	public String getKszt() {
		return kszt;
	}
	public void setKszt(String kszt) {
		this.kszt = kszt;
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
