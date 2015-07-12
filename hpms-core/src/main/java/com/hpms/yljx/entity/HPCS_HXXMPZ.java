package com.hpms.yljx.entity;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * 类名：HPCS_HXXMPZ <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月11日 <br>
 * 描述：核心项目配置 <br>
 */
public class HPCS_HXXMPZ {

	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private String ksnm;
	private String xmbm;
	private Double dsjsa;
	private Double dsdeb;
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
	//@Transient
	private String hxlbbm;
	@Transient
	private String hxlbmc;
	@Transient
	private String xmlbbm;
	@Transient
	private String xmlbmc;
	//@Transient
	private String xmlx;
	@Transient
	private String xmmc;
	@Transient
	private String xmbs;
	@Transient
	private String xmbsmc;
	
	public String getXmbs() {
		return xmbs;
	}
	public void setXmbs(String xmbs) {
		this.xmbs = xmbs;
	}
	public String getXmbsmc() {
		return xmbsmc;
	}
	public void setXmbsmc(String xmbsmc) {
		this.xmbsmc = xmbsmc;
	}
	public String getHxlbmc() {
		return hxlbmc;
	}
	public void setHxlbmc(String hxlbmc) {
		this.hxlbmc = hxlbmc;
	}
	public String getXmlbbm() {
		return xmlbbm;
	}
	public void setXmlbbm(String xmlbbm) {
		this.xmlbbm = xmlbbm;
	}
	public String getXmlbmc() {
		return xmlbmc;
	}
	public void setXmlbmc(String xmlbmc) {
		this.xmlbmc = xmlbmc;
	}
	public String getHxlbbm() {
		return hxlbbm;
	}
	public void setHxlbbm(String hxlbbm) {
		this.hxlbbm = hxlbbm;
	}
	public String getXmlx() {
		return xmlx;
	}
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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
	public String getXmbm() {
		return xmbm;
	}
	public void setXmbm(String xmbm) {
		this.xmbm = xmbm;
	}
	public Double getDsjsa() {
		return dsjsa;
	}
	public void setDsjsa(Double dsjsa) {
		this.dsjsa = dsjsa;
	}
	public Double getDsdeb() {
		return dsdeb;
	}
	public void setDsdeb(Double dsdeb) {
		this.dsdeb = dsdeb;
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
