package com.hpms.yljx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_XMLBDZ;
import com.hpms.yljx.service.XmlbdzSer;
import com.hpms.yljx.vo.Combobox;
import com.hpms.yljx.vo.XmlbdzTreeNode;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class XmlbdzSerImpl extends BaseSerImpl<HPCS_XMLBDZ> implements XmlbdzSer {

	@Override
	public List<HPCS_XMLBDZ> findByPID(String pid) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("fjbm", pid);
		SqlResult sql = SqlUtil.getSql("hpcs_xmlbdz.query_fjbm", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPCS_XMLBDZ.class);
	}
	
	@Override
	public List<XmlbdzTreeNode> buildTreeGrid(List<HPCS_XMLBDZ> nodes,String id){
		List<XmlbdzTreeNode> treeGridNodes=new ArrayList<XmlbdzTreeNode>();
		for (HPCS_XMLBDZ bean : nodes) {
			XmlbdzTreeNode node=new XmlbdzTreeNode();
			node.setXmlbbm(bean.getXmlbbm());
			node.setXmlbmc(bean.getXmlbmc());
			node.setFjbm(bean.getFjbm());
			node.setBzxx(bean.getBzxx());
			if(id.equals(bean.getFjbm())){
				node.setChildren(buildTreeGrid(nodes, node.getXmlbbm()));
				treeGridNodes.add(node);
			}
			
		}
		return treeGridNodes;
	}

	@Override
	public void removeByIDWithPID(String xmlbbm) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xmlbbm1", xmlbbm);
		map.put("xmlbbm2", xmlbbm);
		SqlResult sql = SqlUtil.getSql("hpcs_xmlbdz.query_id_pid", map);
		List<HPCS_XMLBDZ> list = DBUtil.find(sql.getSql(), new Object[]{xmlbbm,xmlbbm}, HPCS_XMLBDZ.class);
		removeBatch(list);
		
	}

	@Override
	public Map<String, Object> findByXMLBMC(int page, int rows, String xmlbmc) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xmlbmc", xmlbmc);
		return findByPage("hpcs_xmlbdz.query_xmlbmc", page, rows, map);
	}

	@Override
	public List<Combobox> findFJ() {
		SqlResult sql = SqlUtil.getSql("hpcs_xmlbdz.query_fj");
		return DBUtil.find(sql.getSql(), Combobox.class);
	}

}
