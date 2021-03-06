package com.hpms.kjjx.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.hpms.util.CustomDateSerializer;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

public class HPKJ_KJXMMX {

	  @Id
	  //@AutoGenerate(type=AutoGenerateType.ID)
	  private String xmid;                
	  private String kjbm;                
	  private Integer rhnd;  
	  @DateTimeFormat(pattern="yyyy-MM")
	  @JsonSerialize(using = CustomDateSerializer.class)
	  private Date rhsj;                
	  private String dysm;               
	  private String dysmmc;             
	  private String desm;                 
	  private String desmmc;               
	  private String dssm;                
	  private String  dssmmc;             
	  private String  dsism;              
	  private String  dsismmc;              
	  private String dwsm;                 
	  private String dwsmmc;            
	  private String  kz1;                 
	  private String  kz2;                 
	  private String  kz3;                 
	  private String  remark;               
	  private String  creator;              
	  private Date createat;            
	  private String  modifor;             
	  private Date modifyat;             
	  private String status;
	  
	  @Transient
	  private String xmmc;
	  @Transient
	  private String lbmc;
	  @Transient
	  private String jbmc;
	  
	  @Transient
	  private String xmbm;
	  @Transient
	  private String lbbm;
	  @Transient
	  private String jbbm;
	  
	public String getXmbm() {
		return xmbm;
	}
	public void setXmbm(String xmbm) {
		this.xmbm = xmbm;
	}
	public String getLbbm() {
		return lbbm;
	}
	public void setLbbm(String lbbm) {
		this.lbbm = lbbm;
	}
	public String getJbbm() {
		return jbbm;
	}
	public void setJbbm(String jbbm) {
		this.jbbm = jbbm;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getLbmc() {
		return lbmc;
	}
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	public String getJbmc() {
		return jbmc;
	}
	public void setJbmc(String jbmc) {
		this.jbmc = jbmc;
	}
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String getKjbm() {
		return kjbm;
	}
	public void setKjbm(String kjbm) {
		this.kjbm = kjbm;
	}
	public Integer getRhnd() {
		return rhnd;
	}
	public void setRhnd(Integer rhnd) {
		this.rhnd = rhnd;
	}
	public Date getRhsj() {
		return rhsj;
	}
	public void setRhsj(Date rhsj) {
		this.rhsj = rhsj;
	}
	public String getDysm() {
		return dysm;
	}
	public void setDysm(String dysm) {
		this.dysm = dysm;
	}
	public String getDysmmc() {
		return dysmmc;
	}
	public void setDysmmc(String dysmmc) {
		this.dysmmc = dysmmc;
	}
	public String getDesm() {
		return desm;
	}
	public void setDesm(String desm) {
		this.desm = desm;
	}
	public String getDesmmc() {
		return desmmc;
	}
	public void setDesmmc(String desmmc) {
		this.desmmc = desmmc;
	}
	public String getDssm() {
		return dssm;
	}
	public void setDssm(String dssm) {
		this.dssm = dssm;
	}
	public String getDssmmc() {
		return dssmmc;
	}
	public void setDssmmc(String dssmmc) {
		this.dssmmc = dssmmc;
	}
	public String getDsism() {
		return dsism;
	}
	public void setDsism(String dsism) {
		this.dsism = dsism;
	}
	public String getDsismmc() {
		return dsismmc;
	}
	public void setDsismmc(String dsismmc) {
		this.dsismmc = dsismmc;
	}
	public String getDwsm() {
		return dwsm;
	}
	public void setDwsm(String dwsm) {
		this.dwsm = dwsm;
	}
	public String getDwsmmc() {
		return dwsmmc;
	}
	public void setDwsmmc(String dwsmmc) {
		this.dwsmmc = dwsmmc;
	}
	public String getKz1() {
		return kz1;
	}
	public void setKz1(String kz1) {
		this.kz1 = kz1;
	}
	public String getKz2() {
		return kz2;
	}
	public void setKz2(String kz2) {
		this.kz2 = kz2;
	}
	public String getKz3() {
		return kz3;
	}
	public void setKz3(String kz3) {
		this.kz3 = kz3;
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
	public Date getCreateat() {
		return createat;
	}
	public void setCreateat(Date createat) {
		this.createat = createat;
	}
	public String getModifor() {
		return modifor;
	}
	public void setModifor(String modifor) {
		this.modifor = modifor;
	}
	public Date getModifyat() {
		return modifyat;
	}
	public void setModifyat(Date modifyat) {
		this.modifyat = modifyat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public HPKJ_KJXMMX() {
		super();
	}
	public HPKJ_KJXMMX(String xmid, String kjbm, Integer rhnd, Date rhsj,
			String dysm, String dysmmc, String desm, String desmmc,
			String dssm, String dssmmc, String dsism, String dsismmc,
			String dwsm, String dwsmmc, String kz1, String kz2, String kz3,
			String remark, String creator, Date createat, String modifor,
			Date modifyat, String status, String xmmc, String lbmc, String jbmc) {
		super();
		this.xmid = xmid;
		this.kjbm = kjbm;
		this.rhnd = rhnd;
		this.rhsj = rhsj;
		this.dysm = dysm;
		this.dysmmc = dysmmc;
		this.desm = desm;
		this.desmmc = desmmc;
		this.dssm = dssm;
		this.dssmmc = dssmmc;
		this.dsism = dsism;
		this.dsismmc = dsismmc;
		this.dwsm = dwsm;
		this.dwsmmc = dwsmmc;
		this.kz1 = kz1;
		this.kz2 = kz2;
		this.kz3 = kz3;
		this.remark = remark;
		this.creator = creator;
		this.createat = createat;
		this.modifor = modifor;
		this.modifyat = modifyat;
		this.status = status;
		this.xmmc = xmmc;
		this.lbmc = lbmc;
		this.jbmc = jbmc;
	}
	
}
