package com.hpms.jjfp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rongda.common.jdbc.annotation.Transient;

public class HPYS_RLCBDY {
	/**
	 * 
	 * @author 许浩
	 * 类名：人力成本定义
	 * 
	 */
	private List<HPYS_RLCBDY> children = new ArrayList<HPYS_RLCBDY>();
	
	private List<HPYS_Rlcbyse> rlcbyses = new ArrayList<HPYS_Rlcbyse>();
	
	public List<HPYS_Rlcbyse> getRlcbyses() {
		return rlcbyses;
	}
	public void setRlcbyses(List<HPYS_Rlcbyse> rlcbyses) {
		this.rlcbyses = rlcbyses;
	}
	public List<HPYS_RLCBDY> getChildren() {
		return children;
	}
	public void setChildren(List<HPYS_RLCBDY> children) {
		this.children = children;
	}
	
	private HPYS_RLCBYS hpys_RLCBYS = new HPYS_RLCBYS();
	
	public HPYS_RLCBYS getHpys_RLCBYS() {
		return hpys_RLCBYS;
	}
	public void setHpys_RLCBYS(HPYS_RLCBYS hpys_RLCBYS) {
		this.hpys_RLCBYS = hpys_RLCBYS;
	}
	@Transient
	private String checked;
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	@Transient
	private String fjmc;
	
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	private String xmbm;
	private String xmmc;
	private String fjbm;
	private String jb;
	private Integer px;
	private String sdzt;
	private String sjzt;
	private String cjr;
	private Date cjsj;
	private String xgr;
	private Date xgsj;
	private String zt;
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
	public String getFjbm() {
		return fjbm;
	}
	public void setFjbm(String fjbm) {
		this.fjbm = fjbm;
	}
	public String getJb() {
		return jb;
	}
	public void setJb(String jb) {
		this.jb = jb;
	}
	public Integer getPx() {
		return px;
	}
	public void setPx(Integer px) {
		this.px = px;
	}
	public String getSdzt() {
		return sdzt;
	}
	public void setSdzt(String sdzt) {
		this.sdzt = sdzt;
	}
	public String getSjzt() {
		return sjzt;
	}
	public void setSjzt(String sjzt) {
		this.sjzt = sjzt;
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
	public HPYS_RLCBDY() {
		super();
	}
	public HPYS_RLCBDY(String xmbm, String xmmc, String fjbm, String jb,
			Integer px, String sdzt, String sjzt, String cjr, Date cjsj,
			String xgr, Date xgsj, String zt) {
		super();
		this.xmbm = xmbm;
		this.xmmc = xmmc;
		this.fjbm = fjbm;
		this.jb = jb;
		this.px = px;
		this.sdzt = sdzt;
		this.sjzt = sjzt;
		this.cjr = cjr;
		this.cjsj = cjsj;
		this.xgr = xgr;
		this.xgsj = xgsj;
		this.zt = zt;
	}
	
}
