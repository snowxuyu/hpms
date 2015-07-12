/**
 * 
 */
package com.hpms.qmys.vo;

/**
 * 类名：BhgzlNYVO <br>
 * 作者：吴陶君 <br>
 * 日期：2015年2月3日 <br>
 * 描述：按年月查询的标化工作量 <br>
 * --修改：加入字段：sfbf（是否病房）、fpjj（分配奖金）
 */
public class BhgzlNYVO {

	private String ksbh;
	private String kslb;
	private String kslbmc;
	private String yjks;
	private String ejks;
	private Double ycfp;
	private Double ecfp;
	private Double hj;
	private String sfbf;
	private String fpjj;
	
	public String getKslb() {
		return kslb;
	}
	public void setKslb(String kslb) {
		this.kslb = kslb;
	}
	public String getYjks() {
		return yjks;
	}
	public void setYjks(String yjks) {
		this.yjks = yjks;
	}
	public String getEjks() {
		return ejks;
	}
	public void setEjks(String ejks) {
		this.ejks = ejks;
	}
	public Double getYcfp() {
		return ycfp;
	}
	public void setYcfp(Double ycfp) {
		this.ycfp = ycfp;
	}
	public Double getEcfp() {
		return ecfp;
	}
	public void setEcfp(Double ecfp) {
		this.ecfp = ecfp;
	}
	public Double getHj() {
		return hj;
	}
	public void setHj(Double hj) {
		this.hj = hj;
	}
	public String getKsbh() {
		return ksbh;
	}
	public void setKsbh(String ksbh) {
		this.ksbh = ksbh;
	}
	public String getSfbf() {
		return sfbf;
	}
	public void setSfbf(String sfbf) {
		this.sfbf = sfbf;
	}
	public String getFpjj() {
		return fpjj;
	}
	public void setFpjj(String fpjj) {
		this.fpjj = fpjj;
	}
	public String getKslbmc() {
		return kslbmc;
	}
	public void setKslbmc(String kslbmc) {
		this.kslbmc = kslbmc;
	}
}
