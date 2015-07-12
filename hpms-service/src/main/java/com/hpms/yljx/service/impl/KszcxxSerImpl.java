package com.hpms.yljx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;
import org.springframework.stereotype.Service;

import com.hpms.entity.TreeNode;
import com.hpms.kjjx.entity.HPKJ_KJJXND;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPXT_KSZCXX;
import com.hpms.yljx.service.KszcxxSer;
import com.hpms.yljx.vo.Combobox;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class KszcxxSerImpl extends BaseSerImpl<HPXT_KSZCXX> implements
		KszcxxSer {

	@Override
	public List<Combobox> findKS() {
		SqlResult sql = SqlUtil.getSql("hpxt_kszcxx.query_combobox", null);
		return DBUtil.find(sql.getSql(), Combobox.class);
	}

	@Override
	public List<Combobox> findKS2() {
		SqlResult sql = SqlUtil.getSql("hpxt_kszcxx.query_combobox_2", null);
		return DBUtil.find(sql.getSql(), Combobox.class);
	}

	@Override
	public Map<String, Object> findJb2Ks(int page, int rows,
			Map<String, Object> map) {
		return findByPage("hpxt_kszcxx.queryJb2", page, rows, map);
	}

	@Override
	public List<TreeNode> getTreeList(Map param) {
		SqlResult sql = SqlUtil.getSql("hpxt_kszcxx.query", param);
		List<HPXT_KSZCXX> list = DBUtil.find(sql.getSql(), sql.getParam(),
				HPXT_KSZCXX.class);
		return spellTree(list);
	}

	private List<TreeNode> spellTree(List<HPXT_KSZCXX> list) {
		List<TreeNode> tree = new ArrayList<TreeNode>();
		Map<String, TreeNode> map = new HashMap<String, TreeNode>();
		HPXT_KSZCXX root = new HPXT_KSZCXX();
		root.setKsnm("0");
		root.setFjbm("");
		root.setKsmc("科室名称");
		list.add(root);
		for (int i = 0; i < list.size(); i++) {
			HPXT_KSZCXX kszcxx = list.get(i);
			TreeNode t = new TreeNode();
			t.setId(kszcxx.getKsnm());
			t.setPid(kszcxx.getFjbm());
			t.setText(kszcxx.getKsmc());
			if (StringUtils.isEmpty(kszcxx.getFjbm())) {
				//t.setPid("0");
				//root.getChildren().add(t);
				tree.add(t);
				map.put(kszcxx.getKsnm(), t);
				list.remove(i);
				i--;
			} else {
				TreeNode t2 = map.get(kszcxx.getFjbm());
				// HPXT_KSZCXX kszcxx2 = map.get(kszcxx.getFjbm());
				if (t2 != null) {
					t2.getChildren().add(t);
					// kszcxx2.getChildrensList().add(kszcxx);
					list.remove(i);
					i--;
				}
				//t.setPid("0");
				//root.getChildren().add(t);
				map.put(kszcxx.getKsnm(), t2);
				//map.put(kszcxx.getKsnm(), t);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			HPXT_KSZCXX kszcxx = list.get(i);
			TreeNode t1 =new TreeNode();
			t1.setId(kszcxx.getKsnm());
			t1.setPid(kszcxx.getFjbm());
			t1.setText(kszcxx.getKsmc());
			TreeNode t2 =map.get(kszcxx.getFjbm());
			if (t2 != null) {
				t2.getChildren().add(t1);
				list.remove(i);
				i--;
			}
			map.put(t1.getId(), t1);
			//Project project2 = map.get(project.getParentId());
		}
		
		
		return tree;
	}

}
