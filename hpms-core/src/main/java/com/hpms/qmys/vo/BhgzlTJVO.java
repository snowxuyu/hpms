package com.hpms.qmys.vo;

import java.util.HashMap;
import java.util.Map;

import com.hpms.util.UtilStr;

public class BhgzlTJVO {

	private String name = "合计";
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private String p5;
	private String p6;
	private String p7;
	
	private Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> getMap() {
		if (UtilStr.isNotNull(getName())) {
			map.put("name", getName());
		}
		if (UtilStr.isNotNull(getP1())) {
			map.put("p1", getP1());
		}
		if (UtilStr.isNotNull(getP2())) {
			map.put("p2", getP2());
		}
		if (UtilStr.isNotNull(getP2())) {
			map.put("p3", getP3());
		}
		if (UtilStr.isNotNull(getP2())) {
			map.put("p4", getP4());
		}
		if (UtilStr.isNotNull(getP2())) {
			map.put("p5", getP5());
		}
		if (UtilStr.isNotNull(getP2())) {
			map.put("p6", getP6());
		}
		if (UtilStr.isNotNull(getP2())) {
			map.put("p7", getP7());
		}
		return map;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public String getP7() {
		return p7;
	}

	public void setP7(String p7) {
		this.p7 = p7;
	}
}
