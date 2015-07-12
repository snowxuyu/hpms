package com.hpms.hpxt.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class HPCS_SFYSCC {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private String ybm;
	private String ymc;
	private String nd;
	private String yd;
	private Double sjz;
	private String yld;
	private String ydxbm;
	private String ydxmc;
	private String cjr;
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt;

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getYbm() {
		return ybm;
	}

	public void setYbm(String ybm) {
		this.ybm = ybm;
	}

	public String getYmc() {
		return ymc;
	}

	public void setYmc(String ymc) {
		this.ymc = ymc;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getYd() {
		return yd;
	}

	public void setYd(String yd) {
		this.yd = yd;
	}

	public Double getSjz() {
		return sjz;
	}

	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}

	public String getYld() {
		return yld;
	}

	public void setYld(String yld) {
		this.yld = yld;
	}

	public String getYdxbm() {
		return ydxbm;
	}

	public void setYdxbm(String ydxbm) {
		this.ydxbm = ydxbm;
	}

	public String getYdxmc() {
		return ydxmc;
	}

	public void setYdxmc(String ydxmc) {
		this.ydxmc = ydxmc;
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

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public HPCS_SFYSCC() {
		super();
		// TODO Auto-generated constructor stub
	}

}
