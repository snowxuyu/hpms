package com.hpms.entity;

import java.util.List;

/**
 * 分页对象
 *
 */
public class Page {
	private int total;
	private List<?> list;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}

}
