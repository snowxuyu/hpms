package com.hpms.yljx.service;

import java.util.List;
import java.util.Map;

import com.hpms.entity.TreeNode;
import com.hpms.service.BaseSer;
import com.hpms.yljx.entity.HPXT_KSZCXX;
import com.hpms.yljx.vo.Combobox;

public interface KszcxxSer extends BaseSer<HPXT_KSZCXX> {

	public List<Combobox> findKS();
	public List<Combobox> findKS2();
	public Map<String, Object> findJb2Ks(int page, int rows, Map<String, Object> map);
	public List<TreeNode> getTreeList(Map param);
}
