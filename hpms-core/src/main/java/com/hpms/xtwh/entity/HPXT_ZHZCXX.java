package com.hpms.xtwh.entity;

import java.util.Date;

import com.rongda.common.constant.AutoGenerateType;
import com.rongda.common.jdbc.annotation.AutoGenerate;
import com.rongda.common.jdbc.annotation.Id;

/**
 * 
 * 类名：BPXT_ZDFLZC <br>
 * 作者：吴陶君 <br>
 * 日期：2015年2月12日 <br>
 * 描述：账户注册信息 <br>
 */
public class HPXT_ZHZCXX {

	@Id
	private String zhnm;
	private String ygbh;
	private String zhmm;
	private Date zjdrsj;
	private String zjdlip;
	private Integer dlcs;
	private String zhzt;
	private String cjr;
	@AutoGenerate(type=AutoGenerateType.CREATE_DATE)
	private Date cjsj;
	private String xgr;
	@AutoGenerate(type=AutoGenerateType.MODIFY_DATE)
	private Date xgsj;
	private String zt;
	private String zhfz = "A01";
	
	public String getZhnm() {
		return zhnm;
	}
	public void setZhnm(String zhnm) {
		this.zhnm = zhnm;
	}
	public String getYgbh() {
		return ygbh;
	}
	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}
	public String getZhmm() {
		return zhmm;
	}
	public void setZhmm(String zhmm) {
		this.zhmm = zhmm;
	}
	public Date getZjdrsj() {
		return zjdrsj;
	}
	public void setZjdrsj(Date zjdrsj) {
		this.zjdrsj = zjdrsj;
	}
	public String getZjdlip() {
		return zjdlip;
	}
	public void setZjdlip(String zjdlip) {
		this.zjdlip = zjdlip;
	}
	public Integer getDlcs() {
		return dlcs;
	}
	public void setDlcs(Integer dlcs) {
		this.dlcs = dlcs;
	}
	public String getZhzt() {
		return zhzt;
	}
	public void setZhzt(String zhzt) {
		this.zhzt = zhzt;
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
	public String getZhfz() {
		return zhfz;
	}
	public void setZhfz(String zhfz) {
		this.zhfz = zhfz;
	}

}
