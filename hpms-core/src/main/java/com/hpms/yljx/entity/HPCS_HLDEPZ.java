package com.hpms.yljx.entity;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;
/**
 * 
 * @author 李凯 
 *
 * 创建时间：2015年1月10日-上午11:04:12
 *
 * 类名： 护理定额配置
 *
 * 描述:
 *
 */
public class HPCS_HLDEPZ {
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh; //流水号 
	private String ksnm;//科室内码
	private Double hlssde;//护理时数定额
	private Double hlxsde; //护理系数定额
	private Double hldsde; //护理点数定额
	private Integer hlhdry; //护理核定人员
	private String cjr; //创建人
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp cjsj; //创建时间
	private String xgr; //修改人
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp xgsj; //修改时间
	private String zt; //状态	
	@Transient
	private String ksmc;
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
	public Double getHlssde() {
		return hlssde;
	}
	public void setHlssde(Double hlssde) {
		this.hlssde = hlssde;
	}
	public Double getHlxsde() {
		return hlxsde;
	}
	public void setHlxsde(Double hlxsde) {
		this.hlxsde = hlxsde;
	}
	public Double getHldsde() {
		return hldsde;
	}
	public void setHldsde(Double hldsde) {
		this.hldsde = hldsde;
	}
	public Integer getHlhdry() {
		return hlhdry;
	}
	public void setHlhdry(Integer hlhdry) {
		this.hlhdry = hlhdry;
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
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	
	
}
