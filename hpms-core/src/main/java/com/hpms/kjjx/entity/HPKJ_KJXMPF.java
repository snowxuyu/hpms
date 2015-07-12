package com.hpms.kjjx.entity;

import java.sql.Timestamp;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

public class HPKJ_KJXMPF {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String smbm;
	// @AutoGenerate(type = AutoGenerateType.ID)
	private String smbh;

	@Transient
	private String jbbm;
	@Transient
	private String jbmc;
	@Transient
	private String xmbm;
	@Transient
	private String xmmc;
	@Transient
	private String kjbm;
	@Transient
	private String lbbm;
	@Transient
	private String lbmc;

	public String getJbbm() {
		return jbbm;
	}

	public void setJbbm(String jbbm) {
		this.jbbm = jbbm;
	}

	public String getJbmc() {
		return jbmc;
	}

	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
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

	public String getLbbm() {
		return lbbm;
	}

	public void setLbbm(String lbbm) {
		this.lbbm = lbbm;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}

	private String smmc;
	private String xzrs;
	private String fz;
	private String remark;
	private String creator;
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp createat;

	private String modifor;
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Timestamp modifyat;
	private String status;

	public String getSmbm() {
		return smbm;
	}

	public void setSmbm(String smbm) {
		this.smbm = smbm;
	}

	public String getSmbh() {
		return smbh;
	}

	public void setSmbh(String smbh) {
		this.smbh = smbh;
	}

	public String getKjbm() {
		return kjbm;
	}

	public void setKjbm(String kjbm) {
		this.kjbm = kjbm;
	}

	public String getSmmc() {
		return smmc;
	}

	public void setSmmc(String smmc) {
		this.smmc = smmc;
	}

	public String getXzrs() {
		return xzrs;
	}

	public void setXzrs(String xzrs) {
		this.xzrs = xzrs;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifor() {
		return modifor;
	}

	public void setModifor(String modifor) {
		this.modifor = modifor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HPKJ_KJXMPF(String smbm, String smbh, String kjbm, String smmc,
			String xzrs, String fz, String remark, String creator,
			String modifor, String status) {
		super();
		this.smbm = smbm;
		this.smbh = smbh;
		this.kjbm = kjbm;
		this.smmc = smmc;
		this.xzrs = xzrs;
		this.fz = fz;
		this.remark = remark;
		this.creator = creator;
		// this.createat = createat;
		this.modifor = modifor;
		// this.modifyat = modifyat;
		this.status = status;
	}

	public Timestamp getCreateat() {
		return createat;
	}

	public void setCreateat(Timestamp createat) {
		this.createat = createat;
	}

	public Timestamp getModifyat() {
		return modifyat;
	}

	public void setModifyat(Timestamp modifyat) {
		this.modifyat = modifyat;
	}

	public HPKJ_KJXMPF() {
		super();
		// TODO Auto-generated constructor stub
	}

}
