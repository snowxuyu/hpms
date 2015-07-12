package com.hpms.yljx.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月23日-下午2:38:36
 *
 * 类名： 标化个人明细
 *
 * 描述: 核心项目拆分补录 -- 标化个人明细
 *
 */
public class HPBH_BHGRMX {
	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String lsh;
	private String ksnm;
	private String ygbh;
	private Date fsrq;
	private Integer nd;
	private Integer yd;
	private String xmbm;
	private String xmmc;
	private String xmbsbm;
	private String xmbsmc;
	private String xmlbbm;
	private String xmlbmc;
	private String hxlbbm;
	private String hxlbmc;
	private Double dsjsa;
	private Double dsdeb;
	private Double sl;
	private Double bhl;
	private String ksmc;
	private String ygmc;
	private String fpcs;
	private String xmly;
	private String xmsx;
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
	private String lx;
	
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
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
	public String getYgbh() {
		return ygbh;
	}
	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}
	public Date getFsrq() {
		return fsrq;
	}
	public void setFsrq(Date fsrq) {
		this.fsrq = fsrq;
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
	public String getXmbm() {
		return xmbm;
	}
	public void setXmbm(String xmbm) {
		this.xmbm = xmbm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getXmbsbm() {
		return xmbsbm;
	}
	public void setXmbsbm(String xmbsbm) {
		this.xmbsbm = xmbsbm;
	}
	public String getXmbsmc() {
		return xmbsmc;
	}
	public void setXmbsmc(String xmbsmc) {
		this.xmbsmc = xmbsmc;
	}
	public String getXmlbbm() {
		return xmlbbm;
	}
	public void setXmlbbm(String xmlbbm) {
		this.xmlbbm = xmlbbm;
	}
	public String getHxlbbm() {
		return hxlbbm;
	}
	public void setHxlbbm(String hxlbbm) {
		this.hxlbbm = hxlbbm;
	}
	public String getHxlbmc() {
		return hxlbmc;
	}
	public void setHxlbmc(String hxlbmc) {
		this.hxlbmc = hxlbmc;
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
	public Double getSl() {
		return sl;
	}
	public void setSl(Double sl) {
		this.sl = sl;
	}
	public Double getBhl() {
		return bhl;
	}
	public void setBhl(Double bhl) {
		this.bhl = bhl;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getYgmc() {
		return ygmc;
	}
	public void setYgmc(String ygmc) {
		this.ygmc = ygmc;
	}
	public String getFpcs() {
		return fpcs;
	}
	public void setFpcs(String fpcs) {
		this.fpcs = fpcs;
	}
	public String getXmly() {
		return xmly;
	}
	public void setXmly(String xmly) {
		this.xmly = xmly;
	}
	public String getXmsx() {
		return xmsx;
	}
	public void setXmsx(String xmsx) {
		this.xmsx = xmsx;
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
	public String getXmlbmc() {
		return xmlbmc;
	}
	public void setXmlbmc(String xmlbmc) {
		this.xmlbmc = xmlbmc;
	}
	
}
