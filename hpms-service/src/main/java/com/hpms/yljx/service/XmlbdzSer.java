package com.hpms.yljx.service;

import java.util.List;
import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPCS_XMLBDZ;
import com.hpms.yljx.vo.Combobox;
import com.hpms.yljx.vo.XmlbdzTreeNode;

public interface XmlbdzSer extends BaseSer<HPCS_XMLBDZ> {

	public List<HPCS_XMLBDZ> findByPID(String pid);
	public List<XmlbdzTreeNode> buildTreeGrid(List<HPCS_XMLBDZ> nodes, String id);
	public void removeByIDWithPID(String xmlbbm);
	public Map<String, Object> findByXMLBMC(int page, int rows, String xmlbmc);
	public List<Combobox> findFJ();
}
