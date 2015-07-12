package com.hpms.jjfp.entity;


public class HPYS_YSZX {

	private String ysbm;
	private Integer nd;
	private Double ysz;
	private Double sjz;
	private Double zxl;
	private String lsh;
	private Double yszje;
	private Double sjzje;
	private Double zzxl;
	private String ztName;
	
	public Double getZzxl() {
		return zzxl;
	}
	public void setZzxl(Double zzxl) {
		this.zzxl = zzxl;
	}
	public Double getYszje() {
		return yszje;
	}
	public void setYszje(Double yszje) {
		this.yszje = yszje;
	}
	public Double getSjzje() {
		return sjzje;
	}
	public void setSjzje(Double sjzje) {
		this.sjzje = sjzje;
	}
	public String getZtName() {
		return ztName;
	}
	public void setZtName(String ztName) {
		this.ztName = ztName;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getYsbm() {
		return ysbm;
	}
	public void setYsbm(String ysbm) {
		this.ysbm = ysbm;
	}
	public Integer getNd() {
		return nd;
	}
	public void setNd(Integer nd) {
		this.nd = nd;
	}
	public Double getYsz() {
		return ysz;
	}
	public void setYsz(Double ysz) {
		this.ysz = ysz;
	}
	public Double getSjz() {
		return sjz;
	}
	public void setSjz(Double sjz) {
		this.sjz = sjz;
	}
	public Double getZxl() {
		return zxl;
	}
	public void setZxl(Double zxl) {
		this.zxl = zxl;
	}
	public HPYS_YSZX(String ysbm, Integer nd, Double ysz, Double sjz, Double zxl,String lsh,Double yszje,Double sjzje,Double zzxl) {
		super();
		this.lsh = lsh;
		this.ysbm = ysbm;
		this.nd = nd;
		this.ysz = ysz;
		this.sjz = sjz;
		this.zxl = zxl;
		this.yszje = yszje;
		this.sjzje = sjzje;
		this.zzxl = zzxl;
	}
	public HPYS_YSZX() {
		super();
	}
	
}
