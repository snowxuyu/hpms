package com.hpms.yljx.entity;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * 类名：HPCS_XMLBDZ <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月11日 <br>
 * 描述：项目类别对照 <br>
 */
public class HPCS_XMLBDZ {

	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String xmlbbm;
	private String xmlbmc;
	private String fjbm;
	private String bzxx;
	
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
	public String getFjbm() {
		return fjbm;
	}
	public void setFjbm(String fjbm) {
		this.fjbm = fjbm;
	}
	public String getBzxx() {
		return bzxx;
	}
	public void setBzxx(String bzxx) {
		this.bzxx = bzxx;
	}
	
	@Transient
	private String fjmc;
	
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	
}
