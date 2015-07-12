package com.hpms.kjjx.entity;

import java.util.ArrayList;
import java.util.List;

public class HPKJ_PFMID {

	private String code;

	private String name;

	private String fz;

	private List<HPKJ_PFMID> list = new ArrayList<>();

	private int count = 0;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HPKJ_PFMID> getList() {
		return list;
	}

	public void setList(List<HPKJ_PFMID> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFz() {
		return fz;
	}

	public void setFz(String fz) {
		this.fz = fz;
	}

}
