package com.hpms.entity;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private String id;
	private String text;
	private String pid;
	private List<TreeNode> children= new ArrayList<TreeNode>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}
