package com.hpms.util;

import java.util.ArrayList;
import java.util.List;

import com.hpms.entity.TreeNode;

public class TreeUtil {

	public static List<TreeNode> buildTree(List<TreeNode> nodes,String id){
		List<TreeNode> treeNodes=new ArrayList<TreeNode>();
		for (TreeNode treeNode : nodes) {
			TreeNode node=new TreeNode();
			node.setId(treeNode.getId());
			node.setText(treeNode.getText());
			node.setPid(treeNode.getPid());
			if(id.equals(treeNode.getPid())){
				node.setChildren(buildTree(nodes, node.getId()));
				treeNodes.add(node);
			}
			
		}
		return treeNodes;
	}
	
}
