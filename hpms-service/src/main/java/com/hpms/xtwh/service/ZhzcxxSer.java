package com.hpms.xtwh.service;

import java.util.List;

import com.hpms.xtwh.entity.HPXT_ZHZCXX;
import com.hpms.yljx.entity.HPXT_YGZCXX;

public interface ZhzcxxSer {

	public List<HPXT_ZHZCXX> findByZH(String zh, String mm);
	public boolean exist(String zh, String mm);
	public HPXT_YGZCXX findByZHNM(String zhnm);
}
