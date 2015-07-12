package com.hpms.jjfp.entity;

import java.util.Date;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

public class HPYS_RLCBZX {

	@Id
	@AutoGenerate(type=AutoGenerateType.ID)
	private String lsh;
	private String ysbm;
	private Double sjz;
	private Double zxl;
	private String sqzt;
	private Date sqsj;
	private String sqr;
	private String shzt;
	private Date shsj;
	private String shr;
	private String cjr;
	@AutoGenerate(type=AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@AutoGenerate(type=AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt;
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getYsbm() {
		return ysbm;
	}
	public void setYsbm(String ysbm) {
		this.ysbm = ysbm;
	}
	public Double getSjz() {
		return sjz;
	}
	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}
	public Double getZxl() {
		return zxl;
	}
	public void setZxl(Double zxl) {
		this.zxl = zxl;
	}
	public String getSqzt() {
		return sqzt;
	}
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	public Date getSqsj() {
		return sqsj;
	}
	public void setSqsj(Date sqsj) {
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
	public Date getShsj() {
		return shsj;
	}
	public void setShsj(Date shsj) {
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
	public HPYS_RLCBZX() {
		super();
	}
	public HPYS_RLCBZX(String lsh, String ysbm, Double sjz, Double zxl,
			String sqzt, Date sqsj, String sqr, String shzt, Date shsj,
			String shr, String cjr, Date cjsj, String xgr, Date xgsj, String zt) {
		super();
		this.lsh = lsh;
		this.ysbm = ysbm;
		this.sjz = sjz;
		this.zxl = zxl;
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
	
	@Transient
	private Double ysz;
	
	public Double getYsz() {
		return ysz;
	}
	public void setYsz(Double ysz) {
		this.ysz = ysz;
	}

}
