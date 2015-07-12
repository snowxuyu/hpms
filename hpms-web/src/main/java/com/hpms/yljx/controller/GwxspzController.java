package com.hpms.yljx.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.entity.HPCS_GWXSDZ;
import com.hpms.yljx.service.GwxspzSer;

/**
 * 
 * 类名：GwxsdzController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月8日 <br>
 * 描述：岗位系数配置控制器 <br>
 */

@Controller
@RequestMapping("/yljx/gwxspz")
public class GwxspzController {

	@Autowired
	private GwxspzSer ser;
	
	@RequestMapping
	public String show() {
		return "/yljx/gwxspz";
	}
	/**
	 * 根据查询科室内码分页查询岗位系数
	 * @param page
	 * @param rows
	 * @param ksnm
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(int page, int rows, @RequestParam(required=false) String ksnm) {
		return ser.findByPage(page, rows, ksnm);
	}
	
	/**
	 * 新增岗位系数
	 * @param item
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(HPCS_GWXSDZ item) {
		try {
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			ser.add(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 更新岗位系数
	 * @param item
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String update(HPCS_GWXSDZ item) {
		HPCS_GWXSDZ gwxsdz = ser.findById(item.getLsh());
		gwxsdz.setKsnm(item.getKsnm());
		gwxsdz.setGwbm(item.getGwbm());
		gwxsdz.setGwmc(item.getGwmc());
		gwxsdz.setGwxs(item.getGwxs());
		try {
			ser.update(gwxsdz);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 根据流水号删除岗位系数
	 * @param lsh
	 * @return
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(String lsh) {
		try {
			ser.removeById(lsh);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
}
