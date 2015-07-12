package com.hpms.yljx.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * 类名：HPCS_HXXMZD <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月11日 <br>
 * 描述：核心项目字典 <br>
 */
public class HPCS_HXXMZD {

	@Id
	//@AutoGenerate(type=AutoGenerateType.ID)
	private String xmbm;
	private String xmlbbm;
	private String xmmc;
	private String hxlbbm;
	private String xmlx;
	private Double dsjsa;
	private Double dsdeb;
	private String cjr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt = "1";
	
	public String getXmbm() {
		return xmbm;
	}
	public void setXmbm(String xmbm) {
		this.xmbm = xmbm;
	}
	public String getXmlbbm() {
		return xmlbbm;
	}
	public void setXmlbbm(String xmlbbm) {
		this.xmlbbm = xmlbbm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
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
	private String hxlbmc;

	public String getHxlbmc() {
		return hxlbmc;
	}
	public void setHxlbmc(String hxlbmc) {
		this.hxlbmc = hxlbmc;
	}
	@Transient
	private String xmlbmc;
	
	public String getXmlbmc() {
		return xmlbmc;
	}
	public void setXmlbmc(String xmlbmc) {
		this.xmlbmc = xmlbmc;
	}

}
