package com.hpms.hpxt.entity;

public class HPXT_ZDDMZC {
	private String zdnm;
	private String zdbm;
	private String zdmc;
	private String flbm;
	private Integer px;

	public String getZdnm() {
		return zdnm;
	}

	public void setZdnm(String zdnm) {
		this.zdnm = zdnm;
	}

	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}

	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

	public String getFlbm() {
		return flbm;
	}

	public void setFlbm(String flbm) {
		this.flbm = flbm;
	}

	public Integer getPx() {
		return px;
	}

	public void setPx(Integer px) {
		this.px = px;
	}

	public HPXT_ZDDMZC() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HPXT_ZDDMZC(String zdnm, String zdbm, String zdmc, String flbm,
			Integer px) {
		super();
		this.zdnm = zdnm;
		this.zdbm = zdbm;
		this.zdmc = zdmc;
		this.flbm = flbm;
		this.px = px;
	}

}
