package com.hpms.jjfp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.jjfp.entity.HPYS_RLCBDY;
import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_RLCBZX;
import com.hpms.jjfp.entity.HPYS_Rlcbyse;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.entity.HPYS_ZXL;
import com.hpms.jjfp.service.HpysRlcbysSer;
import com.hpms.service.BaseSer;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;
import com.rongda.framework.util.StringUtil;

@Service
public class HpysRlcbysSerImpl implements HpysRlcbysSer {

	/**
	 * 
	 * @author 许浩
	 * 类名：人力成本预算设置
	 * 
	 */

	//查询年度
	@Override
	public List<BPE_JX001> selectNd(Map<String,Object> map) {
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.query_bpe_jx001", map);
		return DBUtil.find(sql.getSql(),sql.getParam(), BPE_JX001.class);
	}

	@Override
	public List<HPYS_RLCBDY> selectXm(Map<String,Object> map) {
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.query_hpys_rlcbdy", map);
		List<HPYS_RLCBDY> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_RLCBDY.class);
		list = selectTree(list);
		return list;
	}
	//将取出的list转换成树
	private List<HPYS_RLCBDY> selectTree(List<HPYS_RLCBDY> list){
		List<HPYS_RLCBDY>  tree = new ArrayList<HPYS_RLCBDY> ();
		HashMap<String,HPYS_RLCBDY> map = new HashMap<String,HPYS_RLCBDY>();
		for(int i=0;i<list.size();i++){
			HPYS_RLCBDY rlcbdy = list.get(i);
			if(StringUtils.isEmpty(rlcbdy.getFjbm())){
				tree.add(rlcbdy);
				map.put(rlcbdy.getXmbm(), rlcbdy);
				list.remove(i);
				i--;
			}else{
				HPYS_RLCBDY rlcbdy2 = map.get(rlcbdy.getFjbm());
				if(rlcbdy2 != null){
					rlcbdy2.getChildren().add(rlcbdy);
					list.remove(i);
					i--;
				}
				map.put(rlcbdy.getXmbm(), rlcbdy);
			}
		}

		for(int i=0;i<list.size();i++){
			HPYS_RLCBDY rlcbdy = list.get(i);
			HPYS_RLCBDY rlcbdy2 = map.get(rlcbdy.getFjbm());
			if(rlcbdy2 != null){
				rlcbdy2.getChildren().add(rlcbdy);
				list.remove(i);
				i--;
			}
			map.put(rlcbdy.getXmbm(), rlcbdy);
		}
		return tree;
	}

	@Override
	public String insertRlcbys(List<HPYS_RLCBYS> rlcbysList) {
		DBUtil.createBatch(rlcbysList);
		return "1";
	}

	@Override
	public String deleteRlcbys(List<HPYS_RLCBYS> rlcbysList) {
		for(int i=0;i<rlcbysList.size();i++){
			HPYS_RLCBYS rlcbys = rlcbysList.get(i);
			String ysbm = rlcbys.getYsbm();
			DBUtil.deleteById(HPYS_RLCBYS.class, ysbm);
		}
		return "1";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> List<T> selectRlcbdyRlcbys(String year) {
		year = "2014";
		Map<String, String> param = new HashMap<String, String>();
		param.put("year", year);
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.query_rlcbdy_rlcbys", param);
		List<HPYS_RLCBDY> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_RLCBDY.class);
		
		for(HPYS_RLCBDY hr : list){
			param.put("id", hr.getXmbm());
			sql = SqlUtil.getSql("HPYS_RLCBYS.query_rlcbys_zxl", param);
			List<HPYS_Rlcbyse> mapList = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_Rlcbyse.class);
			hr.setRlcbyses(mapList);
		}
		list = selectTree(list);
		return (List<T>) list;
	}

	@Override
	public Map<String,Object> selectByXmbm(String year,String xmbm) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("xmbm", xmbm);
			SqlResult sql1 = SqlUtil.getSql("HPYS_RLCBYS.queryByXmbm", map);
			List<HPYS_RLCBDY> list1 = DBUtil.find(sql1.getSql(),sql1.getParam(),HPYS_RLCBDY.class);
			HashMap<String,Object> map1 = new HashMap<String,Object>();
			map1.put("xmbm", xmbm);
			map1.put("year", year);
			SqlResult sql2 = SqlUtil.getSql("HPYS_RLCBYS.querySl", map1);
			List<HPYS_YSZX> list2 = DBUtil.find(sql2.getSql(),sql2.getParam(),HPYS_YSZX.class);
			HashMap<String, Object> map2 = new HashMap<String,Object>();
			map2.put("rlcbdy", list1.get(0));
			map2.put("list2", list2);
		return map2;
	}

	@Override
	public String insertRlcbzx(List<HPYS_RLCBZX> rlcbzxList) {
		DBUtil.createBatch(rlcbzxList);
		return "1";
	}

	@Override
	public String updateRlcbzx(List<HPYS_RLCBZX> rlcbzxList) {
		DBUtil.updateBatch(rlcbzxList, true);
		return "1";
	}

	@Override
	public void updateRlcbys(List<HPYS_RLCBYS> rlcbysList) {
		DBUtil.updateBatch(rlcbysList, true);
	}

	@Override
	public HPYS_ZXL selectSumZXL(String year) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("year", year);
		SqlResult sql1 = SqlUtil.getSql("HPYS_RLCBYS.querySum", map);
		List<HPYS_ZXL> list1 = DBUtil.find(sql1.getSql(),sql1.getParam(),HPYS_ZXL.class);
		
		if(list1!=null&&list1.size()>0){
		  return list1.get(0);	
		}
		return null;
	}

	@Override
	public List<HPYS_YSZX> selectNdSum(String nd) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (!StringUtil.isNullAndSpace(nd)){
			map.put("nd", nd);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.queryNdSum", map);
		List<HPYS_YSZX> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_YSZX.class);
		return list;
	}

	@Override
	public List<HPYS_YSZX> selectNdYs(String nd) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (!StringUtil.isNullAndSpace(nd)){
			map.put("nd", nd);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.queryNdYs", map);
		List<HPYS_YSZX> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_YSZX.class);
		return list;
	}

	@Override
	public List<HPYS_RLCBZX> selectByysbm(String ysbm) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (!StringUtil.isNullAndSpace(ysbm)){
			map.put("ysbm", ysbm);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.queryZX", map);
		List<HPYS_RLCBZX> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_RLCBZX.class);
		return list;
	}

	@Override
	public HPYS_RLCBDY findByXMBM(String xmbm) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (!StringUtil.isNullAndSpace(xmbm)){
			map.put("xmbm", xmbm);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.queryByXMBM", map);
		List<HPYS_RLCBDY> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_RLCBDY.class);
		return null == list ? null :list.get(0);
	}

	@Override
	public List<HPYS_RLCBYS> selectByFJbm(String fjbm) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (!StringUtil.isNullAndSpace(fjbm)){
			map.put("fjbm", fjbm);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.queryByFJBM", map);
		List<HPYS_RLCBYS> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_RLCBYS.class);
		return list;
	}

	@Override
	public List<HPYS_RLCBYS> selectNotFJ(String nd1, String nd2) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		if (!StringUtil.isNullAndSpace(nd1)){
			map.put("nd1", nd1);
		}
		if (!StringUtil.isNullAndSpace(nd2)){
			map.put("nd2", nd2);
		}
		SqlResult sql = SqlUtil.getSql("HPYS_RLCBYS.query_not_fj", map);
		List<HPYS_RLCBYS> list = DBUtil.find(sql.getSql(),sql.getParam(),HPYS_RLCBYS.class);
		return list;
	}
	
	
}
