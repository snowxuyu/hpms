package com.pbcs.jcpz.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.hpms.util.CustomDateSerializer2;
import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

public class PBCS_GDJQSZ {
	
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private String jqmc;
	private int nd;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonSerialize(using = CustomDateSerializer2.class)
	private Date kssj;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonSerialize(using = CustomDateSerializer2.class)
	private Date jzsj;
	private Double sc;
	private String bz;
	
	@Transient
	private String sjd;
	
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getJqmc() {
		return jqmc;
	}
	public void setJqmc(String jqmc) {
		this.jqmc = jqmc;
	}
	public int getNd() {
		return nd;
	}
	public void setNd(int nd) {
		this.nd = nd;
	}
	public Date getKssj() {
		return kssj;
	}
	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}
	public Date getJzsj() {
		return jzsj;
	}
	public void setJzsj(Date jzsj) {
		this.jzsj = jzsj;
	}
	public Double getSc() {
		return sc;
	}
	public void setSc(Double sc) {
		this.sc = sc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSjd() {
		return sjd;
	}
	public void setSjd(String sjd) {
		this.sjd = sjd;
	}
}
