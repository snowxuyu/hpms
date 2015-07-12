package com.hpms.kjjx.entity;

import java.util.Date;

import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

public class HPKJ_KJXMZD {

	   @Id
	   private String KJBM;                
	   private String XMBM;                 
	   private String XMMC;           
	   private String LBBM;                
	   private String LBMC;               
	   private String JBBM;               
	   private String JBMC;               
	   private String REMARK;              
	   private String CREATOR;              
	   private Date CREATEAT;             
	   private String MODIFOR;             
	   private Date MODIFYAT;            
	   private String STATUS;
	  
	   @Transient
	   private String id;
	   @Transient
	   private String name;
	   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKJBM() {
		return KJBM;
	}
	public void setKJBM(String kJBM) {
		KJBM = kJBM;
	}
	public String getXMBM() {
		return XMBM;
	}
	public void setXMBM(String xMBM) {
		XMBM = xMBM;
	}
	public String getXMMC() {
		return XMMC;
	}
	public void setXMMC(String xMMC) {
		XMMC = xMMC;
	}
	public String getLBBM() {
		return LBBM;
	}
	public void setLBBM(String lBBM) {
		LBBM = lBBM;
	}
	public String getLBMC() {
		return LBMC;
	}
	public void setLBMC(String lBMC) {
		LBMC = lBMC;
	}
	public String getJBBM() {
		return JBBM;
	}
	public void setJBBM(String jBBM) {
		JBBM = jBBM;
	}
	public String getJBMC() {
		return JBMC;
	}
	public void setJBMC(String jBMC) {
		JBMC = jBMC;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public Date getCREATEAT() {
		return CREATEAT;
	}
	public void setCREATEAT(Date cREATEAT) {
		CREATEAT = cREATEAT;
	}
	public String getMODIFOR() {
		return MODIFOR;
	}
	public void setMODIFOR(String mODIFOR) {
		MODIFOR = mODIFOR;
	}
	public Date getMODIFYAT() {
		return MODIFYAT;
	}
	public void setMODIFYAT(Date mODIFYAT) {
		MODIFYAT = mODIFYAT;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public HPKJ_KJXMZD(String kJBM, String xMBM, String xMMC, String lBBM,
			String lBMC, String jBBM, String jBMC, String rEMARK,
			String cREATOR, Date cREATEAT, String mODIFOR, Date mODIFYAT,
			String sTATUS) {
		super();
		KJBM = kJBM;
		XMBM = xMBM;
		XMMC = xMMC;
		LBBM = lBBM;
		LBMC = lBMC;
		JBBM = jBBM;
		JBMC = jBMC;
		REMARK = rEMARK;
		CREATOR = cREATOR;
		CREATEAT = cREATEAT;
		MODIFOR = mODIFOR;
		MODIFYAT = mODIFYAT;
		STATUS = sTATUS;
	}
	public HPKJ_KJXMZD() {
		super();
	}              
	
}
