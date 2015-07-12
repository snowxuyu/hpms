package com.hpms.yljx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.entity.HPCS_BBDZDZ;
import com.hpms.yljx.service.BbdzdzSer;
import com.hpms.yljx.service.BbflkSer;

/**
 * @author 高国祥 
 *
 * 创建时间：2015年1月10日-下午2:21:04
 *
 * 类名： 班别点值设置控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/yljx/bbdzdz")
public class BbdzdzController {
	
	@Resource
	private BbdzdzSer bbdzdzSer;
	
	@Resource
	private BbflkSer bbflkSer;
	
	
	@RequestMapping
	public String showIndex(){
		return "yljx/bbdspz";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HPCS_BBDZDZ item){
		try {
//System.out.println(item);
			item.setLsh(null);
			item.setCjr("superadmin");
			item.setZt("1");
			bbdzdzSer.add(item);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/removeById", method=RequestMethod.POST)
	@ResponseBody
	public String removeById(String lsh){
		try {
			bbdzdzSer.removeById(lsh);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	

	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HPCS_BBDZDZ item){
		try {
			HPCS_BBDZDZ bbdzdz = bbdzdzSer.findById(item.getLsh());
			bbdzdz.setBbbm(item.getBbbm());
			bbdzdz.setBbmc(item.getBbmc());
			bbdzdz.setBbdz(item.getBbdz());
			bbdzdz.setGwxs(item.getGwxs());
			bbdzdz.setKsnm(item.getKsnm());
			bbdzdzSer.update(bbdzdz);
		} catch (Exception e) {
			return "error";
		}
		return "success";

	}
	
	@RequestMapping(value="/findBypage")
	@ResponseBody
	public Map<String,Object> findByPage(@RequestParam int page, @RequestParam int rows, 
			@RequestParam(required=false) String ksnm){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ksnm", ksnm);
		return bbdzdzSer.findByPage(page, rows, map);
	}
}
