package com.hpms.qmys.vo;

import java.util.HashMap;
import java.util.Map;

import com.hpms.util.UtilStr;

public class BhgzlCondition {
	
	private String nd;
	private String yd;
	private String ksnm;
	private String fpyz;
	private String xmlbdm;
	private String ygbh;

	private Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> getMap() {
		if (UtilStr.isNotNull(getNd())) {
			map.put("nd", getNd());
		}
		if (UtilStr.isNotNull(getYd())) {
			map.put("yd", getYd());
		}
		if (UtilStr.isNotNull(getKsnm())) {
			map.put("ksnm", getKsnm());
		}
		if (UtilStr.isNotNull(getFpyz())) {
			map.put("fpyz", getFpyz());
		}
		if (UtilStr.isNotNull(getXmlbdm())) {
			map.put("xmlbdm", getXmlbdm());
		}
		if (UtilStr.isNotNull(getYgbh())) {
			map.put("ygbh", getYgbh());
		}
		return map;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getYd() {
		return yd;
	}

	public void setYd(String yd) {
		this.yd = yd;
	}

	public String getKsnm() {
		return ksnm;
	}

	public void setKsnm(String ksnm) {
		this.ksnm = ksnm;
	}

	public String getFpyz() {
		return fpyz;
	}

	public void setFpyz(String fpyz) {
		this.fpyz = fpyz;
	}

	public String getXmlbdm() {
		return xmlbdm;
	}

	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}

	public String getYgbh() {
		return ygbh;
	}

	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}
}
