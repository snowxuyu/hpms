package com.hpms.kjjx.entity;

import java.util.Date;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

public class HPKJ_KJGRMX {

	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private String smbm;
	private String ygbh;
	private String xmid;
	private String ksnm;
	private Double fz;
	private String cjr;
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt;
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getSmbm() {
		return smbm;
	}
	public void setSmbm(String smbm) {
		this.smbm = smbm;
	}
	public String getYgbh() {
		return ygbh;
	}
	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String getKsnm() {
		return ksnm;
	}
	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}
	public Double getFz() {
		return fz;
	}
	public void setFz(Double fz) {
		this.fz = fz;
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
	public HPKJ_KJGRMX(String lsh, String smbm, String ygbh, String xmid,
			String ksnm, Double fz, String cjr, Date cjsj, String xgr,
			Date xgsj, String zt) {
		super();
		this.lsh = lsh;
		this.smbm = smbm;
		this.ygbh = ygbh;
		this.xmid = xmid;
		this.ksnm = ksnm;
		this.fz = fz;
		this.cjr = cjr;
		this.cjsj = cjsj;
		this.xgr = xgr;
		this.xgsj = xgsj;
		this.zt = zt;
	}
	public HPKJ_KJGRMX() {
		super();
	}
}
