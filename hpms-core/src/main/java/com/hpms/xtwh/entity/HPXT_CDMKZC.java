package com.hpms.xtwh.entity;

import java.sql.Timestamp;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * 类名：HPXT_CDMKZC <br>
 * 作者：李凯 <br>
 * 日期：2015年1月18日 <br>
 * 描述：菜单模块注册<br>
 * 修改：吴陶君
 * 		修改原因：fx -> px 属性错误
 */
public class HPXT_CDMKZC {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String cdbh;
	private String cdmc;
	private String gssm;
	private String url;
	private String fjbm;
	private String sfcd;
	private String sfqp;
	private String dkms;
	private Integer px;
	private String jb;
	private String cjr;
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp cjsj;
	private String xgr;
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp xgsj;
	private String zt;
	public String getCdbh() {
		return cdbh;
	}
	public void setCdbh(String cdbh) {
		this.cdbh = cdbh;
	}
	public String getCdmc() {
		return cdmc;
	}
	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}
	public String getGssm() {
		return gssm;
	}
	public void setGssm(String gssm) {
		this.gssm = gssm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFjbm() {
		return fjbm;
	}
	public void setFjbm(String fjbm) {
		this.fjbm = fjbm;
	}
	public String getSfcd() {
		return sfcd;
	}
	public void setSfcd(String sfcd) {
		this.sfcd = sfcd;
	}
	public String getSfqp() {
		return sfqp;
	}
	public void setSfqp(String sfqp) {
		this.sfqp = sfqp;
	}
	public String getDkms() {
		return dkms;
	}
	public void setDkms(String dkms) {
		this.dkms = dkms;
	}
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
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
