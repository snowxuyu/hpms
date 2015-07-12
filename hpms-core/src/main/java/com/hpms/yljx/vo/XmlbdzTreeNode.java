package com.hpms.yljx.vo;

import java.util.List;

import com.hpms.yljx.entity.HPCS_XMLBDZ;

public class XmlbdzTreeNode extends HPCS_XMLBDZ {

	private List<XmlbdzTreeNode> children;

	public List<XmlbdzTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<XmlbdzTreeNode> children) {
		this.children = children;
	}
}
