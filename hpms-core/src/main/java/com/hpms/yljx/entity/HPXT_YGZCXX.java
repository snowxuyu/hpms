package com.hpms.yljx.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;
import com.rongda.common.jdbc.annotation.Transient;

/**
 * 
 * 类名：HPXT_YGZCXX <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月14日 <br>
 * 描述：员工注册信息 <br>
 */
public class HPXT_YGZCXX {

	@Id
	@AutoGenerate(type = AutoGenerateType.ID)
	private String ygbh;
	private String ksnm;
	private String ygxm;
	private String xb;
	private Integer sjh;
	private Integer sfz;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date csrq;
	private Integer nl;
	private String dz;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date byrzrq;
	private Integer yl;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cjgzrq;
	private Integer gl;
	private String xlfl;
	private String xwfl;
	private String xwzl;
	private String hszyzg;
	private String yszgdj;
	private String yszylb;
	private String yszyfw;
	private String ygxz;
	private String gwlb;
	private String gwdj;
	private String zyjszwlb;
	private String zyjszwzc;
	private String ryldlb;
	private String gzdj;
	private Double ygxs;
	private String ksndgw;
	private String cjr;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@AutoGenerate(type = AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt = "1";

	@Transient
	private String kslb;
	@Transient
	private String zc;

	public String getKslb() {
		return kslb;
	}

	public void setKslb(String kslb) {
		this.kslb = kslb;
	}

	public String getYgbh() {
		return ygbh;
	}

	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}

	public String getKsnm() {
		return ksnm;
	}

	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}

	public String getYgxm() {
		return ygxm;
	}

	public void setYgxm(String ygxm) {
		this.ygxm = ygxm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public Integer getSjh() {
		return sjh;
	}

	public void setSjh(Integer sjh) {
		this.sjh = sjh;
	}

	public Integer getSfz() {
		return sfz;
	}

	public void setSfz(Integer sfz) {
		this.sfz = sfz;
	}

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public Integer getNl() {
		return nl;
	}

	public void setNl(Integer nl) {
		this.nl = nl;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public Date getByrzrq() {
		return byrzrq;
	}

	public void setByrzrq(Date byrzrq) {
		this.byrzrq = byrzrq;
	}

	public Integer getYl() {
		return yl;
	}

	public void setYl(Integer yl) {
		this.yl = yl;
	}

	public Date getCjgzrq() {
		return cjgzrq;
	}

	public void setCjgzrq(Date cjgzrq) {
		this.cjgzrq = cjgzrq;
	}

	public Integer getGl() {
		return gl;
	}

	public void setGl(Integer gl) {
		this.gl = gl;
	}

	public String getXlfl() {
		return xlfl;
	}

	public void setXlfl(String xlfl) {
		this.xlfl = xlfl;
	}

	public String getXwfl() {
		return xwfl;
	}

	public void setXwfl(String xwfl) {
		this.xwfl = xwfl;
	}

	public String getXwzl() {
		return xwzl;
	}

	public void setXwzl(String xwzl) {
		this.xwzl = xwzl;
	}

	public String getHszyzg() {
		return hszyzg;
	}

	public void setHszyzg(String hszyzg) {
		this.hszyzg = hszyzg;
	}

	public String getYszgdj() {
		return yszgdj;
	}

	public void setYszgdj(String yszgdj) {
		this.yszgdj = yszgdj;
	}

	public String getYszylb() {
		return yszylb;
	}

	public void setYszylb(String yszylb) {
		this.yszylb = yszylb;
	}

	public String getYszyfw() {
		return yszyfw;
	}

	public void setYszyfw(String yszyfw) {
		this.yszyfw = yszyfw;
	}

	public String getYgxz() {
		return ygxz;
	}

	public void setYgxz(String ygxz) {
		this.ygxz = ygxz;
	}

	public String getGwlb() {
		return gwlb;
	}

	public void setGwlb(String gwlb) {
		this.gwlb = gwlb;
	}

	public String getGwdj() {
		return gwdj;
	}

	public void setGwdj(String gwdj) {
		this.gwdj = gwdj;
	}

	public String getZyjszwlb() {
		return zyjszwlb;
	}

	public void setZyjszwlb(String zyjszwlb) {
		this.zyjszwlb = zyjszwlb;
	}

	public String getZyjszwzc() {
		return zyjszwzc;
	}

	public void setZyjszwzc(String zyjszwzc) {
		this.zyjszwzc = zyjszwzc;
	}

	public String getRyldlb() {
		return ryldlb;
	}

	public void setRyldlb(String ryldlb) {
		this.ryldlb = ryldlb;
	}

	public String getGzdj() {
		return gzdj;
	}

	public void setGzdj(String gzdj) {
		this.gzdj = gzdj;
	}

	public Double getYgxs() {
		return ygxs;
	}

	public void setYgxs(Double ygxs) {
		this.ygxs = ygxs;
	}

	public String getKsndgw() {
		return ksndgw;
	}

	public void setKsndgw(String ksndgw) {
		this.ksndgw = ksndgw;
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

	@Transient
	private String ksbm;

	public String getKsbm() {
		return ksbm;
	}

	public void setKsbm(String ksbm) {
		this.ksbm = ksbm;
	}

	@Transient
	private String ksmc;

	public String getKsmc() {
		return ksmc;
	}

	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}
	
}
