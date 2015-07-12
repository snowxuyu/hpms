package com.hpms.jjfp.entity;

import java.sql.Timestamp;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class HPYS_RLCBYS {

	/**
	 * 
	 * @author 许浩
	 * 类名：人力成本预算
	 * 
	 */
	@Id
	private String ysbm;
	private String yybm;
	private String xmbm;
	private String xmmc;
	private String fjbm;
	private String fjmc;
	private String jb;
	private Integer px;
	private Integer nd;
	private Integer yd;
	private Double ysz;
	private String sqzt;
	private Timestamp sqsj;
	private String sqr;
	private String shzt;
	private Timestamp shsj;
	private String shr;
	private String cjr;
	@AutoGenerate(type=AutoGenerateType.CREATE_DATE)
	private Timestamp cjsj;
	private String xgr;
	@AutoGenerate(type=AutoGenerateType.MODIFY_DATE)
	private Timestamp xgsj;
	private String zt;
	public String getYsbm() {
		return ysbm;
	}
	public void setYsbm(String ysbm) {
		this.ysbm = ysbm;
	}
	public String getYybm() {
		return yybm;
	}
	public void setYybm(String yybm) {
		this.yybm = yybm;
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
	public String getFjbm() {
		return fjbm;
	}
	public void setFjbm(String fjbm) {
		this.fjbm = fjbm;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
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
	public Double getYsz() {
		return ysz;
	}
	public void setYsz(Double ysz) {
		this.ysz = ysz;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public Timestamp getSqsj() {
		return sqsj;
	}
	public void setSqsj(Timestamp sqsj) {
		this.sqsj = sqsj;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public Timestamp getShsj() {
		return shsj;
	}
	public void setShsj(Timestamp shsj) {
		this.shsj = shsj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
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
	public HPYS_RLCBYS(String ysbm, String yybm, String xmbm, String xmmc,
			String fjbm, String fjmc, String jb, Integer px, Integer nd, Integer yd,
			Double ysz, String sqzt, Timestamp sqsj, String sqr, String shzt,
			Timestamp shsj, String shr, String cjr, Timestamp cjsj, String xgr,
			Timestamp xgsj, String zt) {
		super();
		this.ysbm = ysbm;
		this.yybm = yybm;
		this.xmbm = xmbm;
		this.xmmc = xmmc;
		this.fjbm = fjbm;
		this.fjmc = fjmc;
		this.jb = jb;
		this.px = px;
		this.nd = nd;
		this.yd = yd;
		this.ysz = ysz;
		this.sqzt = sqzt;
		this.sqsj = sqsj;
		this.sqr = sqr;
		this.shzt = shzt;
		this.shsj = shsj;
		this.shr = shr;
		this.cjr = cjr;
		this.cjsj = cjsj;
		this.xgr = xgr;
		this.xgsj = xgsj;
		this.zt = zt;
	}
	public HPYS_RLCBYS() {
		super();
	}
	
}
