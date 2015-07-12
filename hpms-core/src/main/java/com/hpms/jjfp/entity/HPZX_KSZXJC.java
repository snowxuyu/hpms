package com.hpms.jjfp.entity;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月19日-下午1:42:06
 *
 * 类名： 科室专项奖惩实体bean
 *
 * 描述:
 *
 */
public class HPZX_KSZXJC {
	
	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String lsh;
	private String ksnm;
	private Integer nd;
	private Integer yd;
	private String zx1;
	private String zx2;
	private Double zx3;
	private Double zx4;
	private Double zx5;
	private Double zx6;
	private Double zx7;
	private Double zx8;
	private Double zx9;
	private Double zx10;
	private Double zx11;
	private Double zx12;
 	private Double zx13;
 	private Double zx14;
 	private Double zx15;
 	private Double zx16;
 	private Double zx17;
 	private Double zx18;
 	private Double zx19;
 	private Double zx20;
 	private Double zx21;
 	private String cjr;
 	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
 	private Timestamp cjsj;
 	private String xgr;
 	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
 	private Timestamp xgsj;
 	private String zt;
 	
 	@Transient
 	private String ksmc;
 	@Transient
 	private String ksbm;
 	@Transient
 	private String fjmc;
 	@Transient
 	private String kslb;
 	
	public String getKsbm() {
		return ksbm;
	}
	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getKslb() {
		return kslb;
	}
	public void setKslb(String kslb) {
		this.kslb = kslb;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
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
	public String getZx1() {
		return zx1;
	}
	public void setZx1(String zx1) {
		this.zx1 = zx1;
	}
	public String getZx2() {
		return zx2;
	}
	public void setZx2(String zx2) {
		this.zx2 = zx2;
	}
	public Double getZx3() {
		return zx3;
	}
	public void setZx3(Double zx3) {
		this.zx3 = zx3;
	}
	public Double getZx4() {
		return zx4;
	}
	public void setZx4(Double zx4) {
		this.zx4 = zx4;
	}
	public Double getZx5() {
		return zx5;
	}
	public void setZx5(Double zx5) {
		this.zx5 = zx5;
	}
	public Double getZx6() {
		return zx6;
	}
	public void setZx6(Double zx6) {
		this.zx6 = zx6;
	}
	public Double getZx7() {
		return zx7;
	}
	public void setZx7(Double zx7) {
		this.zx7 = zx7;
	}
	public Double getZx8() {
		return zx8;
	}
	public void setZx8(Double zx8) {
		this.zx8 = zx8;
	}
	public Double getZx9() {
		return zx9;
	}
	public void setZx9(Double zx9) {
		this.zx9 = zx9;
	}
	public Double getZx10() {
		return zx10;
	}
	public void setZx10(Double zx10) {
		this.zx10 = zx10;
	}
	public Double getZx11() {
		return zx11;
	}
	public void setZx11(Double zx11) {
		this.zx11 = zx11;
	}
	public Double getZx12() {
		return zx12;
	}
	public void setZx12(Double zx12) {
		this.zx12 = zx12;
	}
	public Double getZx13() {
		return zx13;
	}
	public void setZx13(Double zx13) {
		this.zx13 = zx13;
	}
	public Double getZx14() {
		return zx14;
	}
	public void setZx14(Double zx14) {
		this.zx14 = zx14;
	}
	public Double getZx15() {
		return zx15;
	}
	public void setZx15(Double zx15) {
		this.zx15 = zx15;
	}
	public Double getZx16() {
		return zx16;
	}
	public void setZx16(Double zx16) {
		this.zx16 = zx16;
	}
	public Double getZx17() {
		return zx17;
	}
	public void setZx17(Double zx17) {
		this.zx17 = zx17;
	}
	public Double getZx18() {
		return zx18;
	}
	public void setZx18(Double zx18) {
		this.zx18 = zx18;
	}
	public Double getZx19() {
		return zx19;
	}
	public void setZx19(Double zx19) {
		this.zx19 = zx19;
	}
	public Double getZx20() {
		return zx20;
	}
	public void setZx20(Double zx20) {
		this.zx20 = zx20;
	}
	public Double getZx21() {
		return zx21;
	}
	public void setZx21(Double zx21) {
		this.zx21 = zx21;
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
	@Override
	public String toString() {
		return "HPZX_KSZXJC [lsh=" + lsh + ", ksnm=" + ksnm + ", nd=" + nd
				+ ", yd=" + yd + ", zx1=" + zx1 + ", zx2=" + zx2 + ", zx3="
				+ zx3 + ", zx4=" + zx4 + ", zx5=" + zx5 + ", zx6=" + zx6
				+ ", zx7=" + zx7 + ", zx8=" + zx8 + ", zx9=" + zx9 + ", zx10="
				+ zx10 + ", zx11=" + zx11 + ", zx12=" + zx12 + ", zx13=" + zx13
				+ ", zx14=" + zx14 + ", zx15=" + zx15 + ", zx16=" + zx16
				+ ", zx17=" + zx17 + ", zx18=" + zx18 + ", zx19=" + zx19
				+ ", zx20=" + zx20 + ", zx21=" + zx21 + ", cjr=" + cjr
				+ ", cjsj=" + cjsj + ", xgr=" + xgr + ", xgsj=" + xgsj
				+ ", zt=" + zt + ", ksmc=" + ksmc + ", ksbm=" + ksbm
				+ ", fjmc=" + fjmc + ", kslb=" + kslb + "]";
	}
	
}
