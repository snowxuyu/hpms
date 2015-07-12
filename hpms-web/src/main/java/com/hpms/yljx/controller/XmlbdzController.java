package com.hpms.yljx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.entity.TreeNode;
import com.hpms.util.TreeUtil;
import com.hpms.yljx.entity.HPCS_XMLBDZ;
import com.hpms.yljx.service.XmlbdzSer;
import com.hpms.yljx.vo.XmlbdzTreeNode;

/**
 * 
 * 类名：XmlbdzController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月13日 <br>
 * 描述：项目类别对照控制器 <br>
 */
@Controller
@RequestMapping("/yljx/xmlbdz")
public class XmlbdzController {

	@Autowired
	private XmlbdzSer ser;
	
	@RequestMapping
	public String show() {
		return "/yljx/xmlbdz";
	}
	
	/**
	 * 根据父级ID查询项目类别信息（树结构）
	 * @param pid
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<TreeNode> getTree(@RequestParam(required=false) String pid) {
		if (pid == null || "".equals(pid)) {
			pid = "0";
		}
		List<TreeNode> nodes=new ArrayList<TreeNode>();
		List<HPCS_XMLBDZ> list = ser.findByPID(pid);
		for (HPCS_XMLBDZ bean : list) {
			TreeNode treeNode=new TreeNode();
			treeNode.setId(bean.getXmlbbm());
			treeNode.setPid(bean.getFjbm());
			treeNode.setText(bean.getXmlbmc());
			nodes.add(treeNode);
		}
		List<TreeNode> treeNodes=TreeUtil.buildTree(nodes,pid);
		TreeNode t = new TreeNode();
		t.setId("");
		t.setPid("");
		t.setText("");
		treeNodes.add(0, t);
		return treeNodes;
	}
	
	/**
	 * 根据父级ID查询项目类别信息（树网格结构）
	 * @param pid
	 * @return
	 */
	@RequestMapping("/treegrid")
	@ResponseBody
	public List<XmlbdzTreeNode> getTreeGrid(@RequestParam(required=false) String pid) {
		if (pid == null || "".equals(pid)) {
			pid = "0";
		}
		List<HPCS_XMLBDZ> list = ser.findByPID(pid);
		return ser.buildTreeGrid(list, pid);
	}
	
	/**
	 * 更新项目类别信息
	 * @param xmlbbm
	 * @param xmlbmc
	 * @param bzxx
	 * @return
	 */
	@RequestMapping("/treegrid/update")
	@ResponseBody
	public String updateTreeGrid(String xmlbbm, String xmlbmc, String bzxx) {
		try {
			HPCS_XMLBDZ bean = ser.findById(xmlbbm);
			bean.setXmlbmc(xmlbmc);
			bean.setBzxx(bzxx);
			ser.update(bean);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 删除项目类别
	 * @param xmlbbm
	 * @return
	 */
	@RequestMapping("/treegrid/remove")
	@ResponseBody
	public String removeTreeGrid(String xmlbbm) {
		try {
			ser.removeByIDWithPID(xmlbbm);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 新增项目类别
	 * @param xmlbbm
	 * @return
	 */
	@RequestMapping("/treegrid/add")
	@ResponseBody
	public String addTreeGrid(String xmlbbm) {
		try {
			HPCS_XMLBDZ bean = ser.findById(xmlbbm);
			//TODO
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
