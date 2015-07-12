package com.hpms.qmys.entity;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/*
 * 修改：吴陶君
 * fljj -> fpjj
 */
public class HPXC_XCKSJS {
	
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private String ksnm;
	private Integer nd;
	private Integer yd;
	private Double dj; //单价
	private Double cl; //常量
	private Double pjxs; //评价系数
	private Double myd; //满意度
	private Double zxjj; //专项奖金
	private Double qypjjj; //全院平均奖金
	private Double ycgzl; //一次分配奖金
	private Double ecgzl; //二次分配奖金
	private Double fpjj; //可分配奖金
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Timestamp cjsj; //创建时间
	@Transient
	private String ksmc;
	@Transient
	private Double bhgzl;
	@Transient
	private Double kszxjj; //科室专项奖金
	@Transient
	private Double grzxjj; //个人专项奖金
	
	
	public Double getKszxjj() {
		return kszxjj;
	}
	public void setKszxjj(Double kszxjj) {
		this.kszxjj = kszxjj;
	}
	public Double getGrzxjj() {
		return grzxjj;
	}
	public void setGrzxjj(Double grzxjj) {
		this.grzxjj = grzxjj;
	}
	public Double getBhgzl() {
		return bhgzl;
	}
	public void setBhgzl(Double bhgzl) {
		this.bhgzl = bhgzl;
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
	public Double getDj() {
		return dj;
	}
	public void setDj(Double dj) {
		this.dj = dj;
	}
	public Double getCl() {
		return cl;
	}
	public void setCl(Double cl) {
		this.cl = cl;
	}
	public Double getPjxs() {
		return pjxs;
	}
	public void setPjxs(Double pjxs) {
		this.pjxs = pjxs;
	}
	public Double getMyd() {
		return myd;
	}
	public void setMyd(Double myd) {
		this.myd = myd;
	}
	public Double getZxjj() {
		return zxjj;
	}
	public void setZxjj(Double zxjj) {
		this.zxjj = zxjj;
	}
	public Double getQypjjj() {
		return qypjjj;
	}
	public void setQypjjj(Double qypjjj) {
		this.qypjjj = qypjjj;
	}
	public Double getYcgzl() {
		return ycgzl;
	}
	public void setYcgzl(Double ycgzl) {
		this.ycgzl = ycgzl;
	}
	public Double getEcgzl() {
		return ecgzl;
	}
	public void setEcgzl(Double ecgzl) {
		this.ecgzl = ecgzl;
	}
	
	public Timestamp getCjsj() {
		return cjsj;
	}
	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public Double getFpjj() {
		return fpjj;
	}
	public void setFpjj(Double fpjj) {
		this.fpjj = fpjj;
	}

}
